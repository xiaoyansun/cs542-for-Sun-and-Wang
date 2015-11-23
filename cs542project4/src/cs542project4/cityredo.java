package cs542project4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class cityredo {

	relation C = null;
	relation logCity = null;
	tuple cityredo = null;
	tuple citylog = null;
	File city2;
	FileWriter cityredowriter;
	private String[] news;
	private List<tuple> utuples = new ArrayList<tuple>();
	private BufferedReader pcity2;
	
	public cityredo(relation c, relation logcity){
		this.C = c;
		this.logCity = logcity;
	}
	
	public void Open() {
		C.Open();
		logCity.Open();
		cityredo = C.GetNext();
		citylog = logCity.GetNext();
	}

	public void redo() throws IOException {
		cityredowriter = new FileWriter("city 2.csv");
		while(cityredo.getValues()[0].equals(citylog.getValues()[0]) == true && cityredo.getValues()[4] != null ){
			cityredo.getValues()[4] = citylog.getValues()[3];
			
			news = new String[cityredo.getValues().length];
	        for(int i=0; i<cityredo.getValues().length; i++){
	        	news[i] = cityredo.getValues()[i];
	        }
	        tuple utuple = new tuple(news);
	        utuples.add(utuple);
	        
	        cityredo = C.GetNext();
	        if(cityredo == null){
	        	break;
	        }
	        citylog = logCity.GetNext();
	        if(citylog == null){
	        	break;
	        }
		}
		
		cityredowriter.write("ID,Name,CountryCode,District,Population");
		cityredowriter.write("\n");
		for(int i = 0; i < utuples.size(); i++){
			for(int j = 0; j < 5; j++){
				if(j == 4){
					cityredowriter.write(utuples.get(i).getValues()[j]);
				}
				else{
					cityredowriter.write(utuples.get(i).getValues()[j]+",");
				}
			}
			cityredowriter.write("\n");
		}
		cityredowriter.close();
	}
	
	public void redoprint() throws IOException{
		pcity2 = new BufferedReader(new FileReader("city 2.csv"));
		while(pcity2.readLine() != null){
			System.out.println(pcity2.readLine());
		}
	}
	
	public void close() {
		C.Close();
		logCity.Close();
	}
}
