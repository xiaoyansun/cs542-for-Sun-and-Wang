package cs542project4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class cityupdate {

	relation A = null;
	tuple city = null;
	private String[] logs = null;
	private String[] news = null;
	private List<tuple> ltuples = new ArrayList<tuple>(); 
	private List<tuple> utuples = new ArrayList<tuple>();
	private File citylog; 
	private FileWriter citylogwriter, cityupdatewriter;
	private BufferedReader pcitylog;
	private BufferedReader pcity;
	
	public cityupdate(relation a) {
		this.A = a;
	}
	
	public void Open() {
		A.Open();
		city = A.GetNext();
	}
	
	public void update() throws IOException {
		citylog = new File("city_log.csv");
		citylogwriter = new FileWriter(citylog);
		cityupdatewriter = new FileWriter("city.csv");
		
		while(city.getValues()[4] !=null){
			int origin = Integer.parseInt(city.getValues()[4]);
	        int current = (int)(origin*(1+2/100.0));
	        city.getValues()[4] = String.valueOf(current);
	        
	        news = new String[city.getValues().length];
	        for(int i=0; i<city.getValues().length; i++){
	        	news[i] = city.getValues()[i];
	        }
	        tuple utuple = new tuple(news);
	        utuples.add(utuple);
	        
	        logs = new String[city.getValues().length];
	        logs[0] = city.getValues()[0];
	        logs[1] = "Population";
	        logs[2] = String.valueOf(origin);
		    logs[3] = city.getValues()[4];
			tuple ltuple = new tuple(logs);
			ltuples.add(ltuple);
			
	        city = A.GetNext();
	        if(city == null){
	        	break;
	        }
		}
		
		cityupdatewriter.write("ID,Name,CountryCode,District,Population");
		cityupdatewriter.write("\n");
		for(int i = 0; i < utuples.size(); i++){
			for(int j = 0; j < 5; j++){
				if(j == 4){
					cityupdatewriter.write(utuples.get(i).getValues()[j]);
				}
				else{
					cityupdatewriter.write(utuples.get(i).getValues()[j]+",");
				}
			}
			cityupdatewriter.write("\n");
		}
		cityupdatewriter.close();
		
		citylogwriter.write("T,X,O,N");
		citylogwriter.write("\n");
		for(int i = 0; i < ltuples.size(); i++){
			for(int j = 0; j < 4; j++){
				if(j == 3){
					citylogwriter.write(ltuples.get(i).getValues()[j]);
				}
//				if(j == 0){
//					citylogwriter.write("<"+ltuples.get(i).getValues()[j]+",");
//				}
				else{
					citylogwriter.write(ltuples.get(i).getValues()[j]+",");
				}
			}
			citylogwriter.write("\n");
		}
		citylogwriter.close();
	}
	
	public void updateprint() throws IOException{
		pcity = new BufferedReader(new FileReader("city.csv"));
		while(pcity.readLine() != null){
			System.out.println(pcity.readLine());
		}
	}
	
	public void logprint() throws IOException{
		pcitylog = new BufferedReader(new FileReader(citylog));
		while(pcitylog.readLine() != null){
			System.out.println(pcitylog.readLine());
		}
	}
	
	public void close() {
		A.Close();
	}
}
