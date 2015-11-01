package cs542;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Arrays;
import java.util.HashMap;


public class KeyValueStore{
	
    /**
     * Define constants
     * MetaSize: Space reserved for Meta data
     * MaxDataEnd: Maximum space for all data 
     */
	
	private static final int M=1024*1024;
	private static final int MetaSize=M;
	private final static int MaxDataEnd=5*M;
	
	
	public static void Put(int key, byte[] value){
		put(key, value);
	}
	
	public static byte[] Get(int key){
		return get(key);
	}
	
	public static void Remove(int key){
		remove(key);
	}
	
    /**
     * index: 
     * key: key of the data
     * value: first element: length of data; second element: address of the data in DB
     */
	
	private static HashMap<Integer, int[]> index = new HashMap<Integer,int[]>();
	
    /**
     * Read out meta data into object index
     * 
     */
	
	@SuppressWarnings("unchecked")
	public static void ReadMeta(){
    	try
        {
           FileInputStream fis = new FileInputStream("CS542.meta");
           ObjectInputStream ois = new ObjectInputStream(fis);
           index = (HashMap<Integer, int[]>) ois.readObject();
           ois.close();
           fis.close();
           
        }catch(IOException ioe)
        {
           ioe.printStackTrace();
           return;
        }catch(ClassNotFoundException c)
        {
           System.out.println("Class not found");
           c.printStackTrace();
           return;
        }
		
	}
	
    /**
     * Write meta data back to file 
     * Lock applied
     * Channel will be locked while one user write data into file 
     */
	

	public static void WriteMeta(){
   	 	try
   	 	{
            FileOutputStream fos = new FileOutputStream("CS542.meta");
            FileChannel channel = fos.getChannel();
            FileLock lock = channel.lock();
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(index);
            lock.release();
            oos.close();
            fos.close();
            channel.close();
            //System.out.printf("Serialized HashMap data is saved in db\n");
   	 	}catch(IOException ioe)
	 		{
	 			ioe.printStackTrace();
	 		}
   	 	//catch(IOException ioe)
   	 		//{
   	 			//ioe.printStackTrace();
   	 		//}
	}
	
    /**
     * Read out data from DB
     * @param addr: The address in DB to read from
     * @param length: The length of data to read 
     * @return Data read out from DB 
     */
	
	private static byte[] ReadDB(int addr, int length){
		RandomAccessFile out1;
		byte[] value= new byte[length];
		try {
			out1 = new RandomAccessFile("CS542.db","rw");
			out1.seek(addr+1);
			
			out1.read(value,0, length);
			out1.close();
			//return value;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;

	}
	
    /**
     * Write data to DB
     * @param value: The data to write
     * @param addr: The address in DB where to start writing
     * Lock applied
     * Channel will be locked while one user write data into file 
     */
		
	private static void WriteDB(byte[] value, int addr){
		byte[] value1= new byte[1024*1024*4+1];
		value1=ReadDB(1048577, 4194304+1);
		try {
			for(int i=addr-1048576;i<addr-1048576+value.length;i++){
				value1[i]=value[i-addr+1048576];
			}
			RandomAccessFile out1=new RandomAccessFile("CS542.db","rw");
			FileChannel channel = out1.getChannel();
			FileLock lock = channel.lock();
			out1.seek(1048577);
			out1.write(value1);
			lock.release();
			channel.close();
			out1.close();	
		} catch (IOException e) {
			System.out.println("I/O Error in Writing DB" + e.getMessage());
		}
	}
	
    /**
     * Put data into DB
     * @param key: The key of data
     * @param value: the value of data
     */
	
	private static void put(int key, byte[] value){
		
	    /**
	     * Put Data Sequentially in DB  
	     * When there is not enough space at the end, calculate available space in the 1M-4M memory space
	     * If the available space is large enough to hold the input data, put it at the first available one found
	     * If there is no available space that is large enough to hold the data, reject the data 
	     * 
	     * Since information of data is store in meta data in the format of key, length, address
	     * With only one length and address 
	     * It is not possible to divide data into pieces and store in the DB
	     * Thus in test 3, last value H was rejected 
	     * Because there were only two pieces of 0.5M left in DB, and value H is of the size 1M
	     * 
	     * Possible adjustment can be made to hold H
	     * For example add more length and address information in meta data
	     * 
	     */
		

	    /**
	     * Read meta data into index
	     * If index is empty, that is DB is empty, add the value at address 1M (given by MetaSize) 
	     * Add value information into index, and write value into DB
	     * If key already exists in index, remove that key in index to replace, then read in meta data again
	     * 
	     */
		
		ReadMeta();
		
		while(!index.isEmpty()){
			
			if(index.containsKey(key)){
				
				System.out.println("Key "+key+" existed! Now replacing it with new value.");
				remove(key);
				ReadMeta();
				continue;

			}
			
		    /**
		     * startpos contains a list of addresses that are the starting point of a value stored in DB
		     * endpos is a list of addresses that are the end point of a value in DB
		     * get starpos from index, and calculate endpos by adding length to startpos
		     * sort these two lists and subtract endpos from startpos to get available space length and beginning address
		     * store available space information in map len 
		     * len: key: the first vacant address; value: the length of space 
		     * 
		     */
			
			int[] startpos= new int[index.size()];
			int[] endpos=new int[index.size()];
			int i=0;

			for(int k: index.keySet()){
				startpos[i]=index.get(k)[1];
				endpos[i]=index.get(k)[0]+index.get(k)[1]-1;
				i++;
			}
			Arrays.sort(startpos);
			Arrays.sort(endpos);


		    /**
		     * Calculate space between the end address of the last value and 5M
		     * Put value there first when there is enough space
		     */
			
			if(endpos[endpos.length-1]<MaxDataEnd && (MaxDataEnd-endpos[endpos.length-1])>=value.length){
				int[] addr = new int[2];
				addr[0]=value.length;
				addr[1]=(endpos[endpos.length-1]+1);
				
				index.put(key,addr);
				WriteMeta();
				WriteDB(value, addr[1]);
				System.out.println("Successfully put " +key+" into db..");
				break;
			}
			
		    /**
		     * len: key: the first vacant address; value: the length of space 
		     * 
		     * Add space from 1M to first data and last data to 5M to len,  length could be zero in these two cases
		     * Add other blank space address and length into len
		     * Compare length in len to length of the input value
		     * Put the value in DB if large enough available space found 
		     * 
		     * Reject the value if no space found
		     */
			
			else {

				HashMap<Integer, Integer> len = new HashMap<Integer,Integer>();
				len.put(endpos[endpos.length-1]+1, MaxDataEnd-endpos[endpos.length-1]);
				len.put(MetaSize, startpos[0]-MetaSize); 
	
				for(int j=0;j<startpos.length-1;j++){
					int leng=startpos[j+1]-endpos[j]-1;
					if(leng!=0)
						len.put(endpos[j]+1, leng);
					}
				boolean flag=true;
				for(int k: len.keySet()){
						
					if(len.get(k)>=value.length){
						flag=false;
				// addr contains address and value length regarding to a certain key
						int[] addr = new int[2];
						addr[0]=value.length;
						addr[1]=(k);
						index.put(key,addr);
						WriteMeta();
						WriteDB(value, k);
						System.out.println("Successfully put " +key+" into db...");
						break;  //break out of for loop
					}					 
				}
				if(flag)
					System.out.println("Put value "+ key+" to database fail: not enough space.");
				break;	
			}
		}
		if(index.isEmpty()){
			
			int[] addr = new int[2];
			addr[0]=value.length;
			addr[1]=(MetaSize);
			index.put(key,addr);
			WriteMeta();
			WriteDB(value, addr[1]);
			System.out.println("Successfully put " +key+" into db..");
			
			}
	}
	
    /**
     * Obtain data from the DB
     * @param key for the search
     * @return value by the key key
     * 
     * Mesg key not existed in case of key not found
     */
	
	private static byte[] get(int key){

		ReadMeta();
		
		if(index.containsKey(key)){
			int addr=index.get(key)[1];
			return ReadDB(addr, index.get(key)[0]);
		}
		else
			System.out.println("Cannot get data: key "+key+" not existed.");
			return null;
	}
	
    /**
     * Delete data from DB
     * @param key the key to be deleted
     * 
     * Data will not be actually deleted from DB, but all information of the data in meta data will be deleted
     * So value cannot be search by this key, and the space is available for writing other data in
     * 
     */
	
	private static void remove(int key){
		
		ReadMeta();
		if(index.containsKey(key)){
		// delete information in meta data
			index.remove(key);
			WriteMeta();
		//delete value in DB
			System.out.println("Deleted "+key +" in DB..");
		}
		else
			System.out.println("Key "+ key+" not found.");
	}
	
	
    /************************************************************************************
     * The following methods are for the purpose of testing
     * 
     ************************************************************************************/
	
    /**
     * display the 40 bytes around address @param Mega M
     * mega cannot be zero
     */
	
	public static void layout(int mega){
		byte[] value= new byte[1024*1024*4+40];
		value=ReadDB(0, MaxDataEnd+1);
		for(int i=0+mega*M-10;i<30+mega*M;i++){
			System.out.print(value[i]+" ");
		}
		System.out.println();
	}
	
    /**
     * check the status of index
     * 1st key
     * 2nd length of value
     * 3rd address in DB
     * 
     */
	
	public static void keyStatus(){
		for(int k: index.keySet()){
			System.out.print(k);
			System.out.print("\t");
			System.out.print(index.get(k)[0]/1024);
			System.out.print("KB");
			System.out.print("\t");
			System.out.print((index.get(k)[1]));
			//System.out.print("KB");
			System.out.println();
		}
	}
	
	public static void printDB(){
		System.out.println("-----------------------0M");
		boolean flag=false;

		ReadMeta();
		for(int i=1;i<40;i++){
			for(int k: index.keySet()){
				if(index.get(k)[1]<=i*M/8 && i*M/8<index.get(k)[1]+index.get(k)[0]){
					flag=true;
					break;
				}
				else
					flag=false;

			}
			if(flag){
				System.out.println("*************************");
			}
			else{
				System.out.println("-------------------------");
			}
		}
	}
	
	public static void Clear(){
		index.clear();
	}
}

