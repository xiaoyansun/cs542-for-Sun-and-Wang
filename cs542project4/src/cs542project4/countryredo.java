package cs542project4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class countryredo {

	relation D = null;
	relation logCountry = null;
	tuple countryredo = null;
	tuple countrylog = null;
	File country2;
	FileWriter countryredowriter;
	private List<tuple> utuples = new ArrayList<tuple>();
	private BufferedReader pcountry2;
	
	public countryredo(relation d, relation logcountry){
		this.D = d;
		this.logCountry = logcountry;
	}
	
	public void Open() {
		D.Open();
		logCountry.Open();
		countryredo = D.GetNext();
		countrylog = logCountry.GetNext();
	}

	public void redo() throws IOException {
		countryredowriter = new FileWriter("country 2.csv");
		while(countryredo.getValues()[0].equals(countrylog.getValues()[1]) == true && countryredo.getValues()[6] != null ){
			String[] a = countryredo.getValues();
			a[6] = countrylog.getValues()[3].substring(0, countrylog.getValues()[3].length()-1);
			countryredo.setValues(a);
	        utuples.add(countryredo);
	        
	        countryredo = D.GetNext();
	        if(countryredo == null){
	        	break;
	        }
	        countrylog = logCountry.GetNext();
	        if(countrylog == null){
	        	break;
	        }
		}
		
		countryredowriter.write("Code,Name,Continent,Region,SurfaceArea,IndepYear,Population,LifeExpectancy,GNP,GNPOld,LocalName,GovernmentForm,HeadOfState,Capital,Code2");
		countryredowriter.write("\n");
		for(int i = 0; i < utuples.size(); i++){
			for(int j = 0; j < 15; j++){
				if(j == 14){
					countryredowriter.write(utuples.get(i).getValues()[j]);
				}
				else{
					countryredowriter.write(utuples.get(i).getValues()[j]+",");
				}
			}
			countryredowriter.write("\n");
		}
		countryredowriter.close();
	}
	
	public void redoprint() throws IOException{
		pcountry2 = new BufferedReader(new FileReader("country 2.csv"));
		String line=pcountry2.readLine();
		while(line != null){
			System.out.println(line);
			line=pcountry2.readLine();
		}
	}
	
	public void close() {
		D.Close();
		logCountry.Close();
	}
}
