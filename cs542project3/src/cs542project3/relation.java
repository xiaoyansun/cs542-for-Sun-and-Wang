package cs542project3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Open() method
 * GetNext() method
 * Close() method
 */
public class relation {
	
	private String name;
	private List<tuple> tuples= new ArrayList<tuple>();
	private BufferedReader fileReader=null;
	
	public relation(String name){
		this.name=name;
		}
	
	/*
	 * Read the data by line and put the data into different tuples.
	 */
	public void Open(){
		//Separate the date by ","
		final String DELIMITER = ",";
		//Read attributes in line by line
		try{
			this.fileReader = new BufferedReader(new FileReader(this.name));
			String line = "";
			fileReader.readLine();
			//Set attributes to new tuple
			while ((line = fileReader.readLine()) != null){
                String[] tokens = line.split(DELIMITER);
                tuple tuple=new tuple (tokens);
                tuples.add(tuple);
                }
			}catch (Exception e) {
			e.printStackTrace();
			}
		}
	
	/*
	 * Get the next tuple in the relation.
	 * @return 
	 */
	public tuple GetNext(){
		//Get the next tuple in the relation
		if(!tuples.isEmpty()){
			tuple t= tuples.get(0);
			tuples.remove(0);
			return t;
			}
		else{
			return null;
			}
		}
	
	/*
	 * Close the relation.
	 */
	public void Close(){
        //Close the file reader
		try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            }
        }
	}
