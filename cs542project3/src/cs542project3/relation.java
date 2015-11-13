package cs542project3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class relation {
	private String name;
	private String path;
	//private String[] attributes;
	private List<tuple> tuples= new ArrayList<tuple>();
	private BufferedReader fileReader=null;
	private String primaryKey="";
	
	
	public relation(String name){
		this.name=name;
		//this.path=path;
		//this.primaryKey=primary;
	}
	
	public void Open(){

		final String DELIMITER = ",";
		try{
			this.fileReader = new BufferedReader(new FileReader(this.name));
			String line = "";
			//read in attributes
			fileReader.readLine();
			
			while ((line = fileReader.readLine()) != null)
            {
                String[] tokens = line.split(DELIMITER);
//              for(String token : tokens)
//              {	
//              	System.out.println(token);
//              }
                tuple tuple=new tuple (tokens);
                //tuple.print();
                tuples.add(tuple);
            }
			//tuples.get(50).print();
		}
        catch (Exception e) {
            e.printStackTrace();
        } 
	}
	
	public tuple GetNext(){
		if(!tuples.isEmpty()){
			tuple t= tuples.get(0);
			tuples.remove(0);
			//t.print();
			return t;
		}
		else{
//			 Open();
//			 if (tuples.isEmpty()) {
//				 return null;
//			 }
//			 tuple t = tuples.get(0);
//			 tuples.remove(0);
//			 return t;
			return null;
		}
	}
	
	public void Close(){
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
