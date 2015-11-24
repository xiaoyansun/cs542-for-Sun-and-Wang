package cs542project4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class countryupdate {

	relation B = null;
	tuple country = null;
	private String[] logs = null;
	private List<tuple> ltuples = new ArrayList<tuple>(); 
	private List<tuple> utuples = new ArrayList<tuple>();
	private File countrylog; 
	private FileWriter countrylogwriter, countryupdatewriter;
	private BufferedReader pcountry;
	private BufferedReader pcountrylog;
	
	public countryupdate(relation b) {
		this.B = b;
	}
	
	public void Open() {
		B.Open();
		country = B.GetNext();
	}
	
	public void update() throws IOException {
		countrylog = new File("country_log.csv");
		countrylogwriter = new FileWriter(countrylog);
		countryupdatewriter = new FileWriter("country.csv");
		
		while(country.getValues()[6] !=null){
			int origin = Integer.parseInt(country.getValues()[6]);
	        int current = (int)(origin*(1+2/100.0));
	        String[] a = country.getValues();
	        a[6] = String.valueOf(current);
	        country.setValues(a);
	        B.setAttribute(country);
	        utuples.add(country);
	        
	        logs = new String[country.getValues().length];
	        logs[1] = country.getValues()[0];
	        logs[0] = country.getValues()[1]+"_Population";
	        logs[2] = String.valueOf(origin);
		    logs[3] = country.getValues()[6];
			tuple ltuple = new tuple(logs);
			ltuples.add(ltuple);
			
	        country = B.GetNext();
	        if(country == null){
	        	break;
	        }
		}
		
		countryupdatewriter.write("Code,Name,Continent,Region,SurfaceArea,IndepYear,Population,LifeExpectancy,GNP,GNPOld,LocalName,GovernmentForm,HeadOfState,Capital,Code2");
		countryupdatewriter.write("\n");
		for(int i = 0; i < utuples.size(); i++){
			for(int j = 0; j < 15; j++){
				if(j == 14){
					countryupdatewriter.write(utuples.get(i).getValues()[j]);
				}
				else{
					countryupdatewriter.write(utuples.get(i).getValues()[j]+",");
				}
			}
			countryupdatewriter.write("\n");
		}
		countryupdatewriter.close();
		
		countrylogwriter.write("<START T>\n");
		//countrylogwriter.write("T,X,O,N\n");
		for(int i = 0; i < ltuples.size(); i++){
			for(int j = 0; j < 4; j++){
				if(j == 0){
					countrylogwriter.write("<T,");
				}
				else if(j == 3){
					countrylogwriter.write(ltuples.get(i).getValues()[j]+">");
				}
				else if(j > 0){
					countrylogwriter.write(ltuples.get(i).getValues()[j]+",");
				}
			}
			countrylogwriter.write("\n");
		}
		countrylogwriter.write("<COMMIT T>\n");
		countrylogwriter.close();
	}
	
	public void updateprint() throws IOException{
		pcountry = new BufferedReader(new FileReader("country.csv"));
		while(pcountry.readLine() != null){
			System.out.println(pcountry.readLine());
		}
	}
	
	public void logprint() throws IOException{
		pcountrylog = new BufferedReader(new FileReader(countrylog));
		String line=pcountrylog.readLine();
		while(line!=null){
			System.out.println(line);
			line=pcountrylog.readLine();
		}
	}
	
	public void close() {
		B.Close();
	}
}
