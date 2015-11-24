package cs542project4;

import java.io.IOException;

public class updatetest {

	public static void main(String[] args) throws IOException {
		
		relation city = new relation("city.csv");
		relation country = new relation("country.csv");
		
		cityupdate ucity = new cityupdate(city);
		countryupdate ucountry = new countryupdate(country);
		
		ucity.Open();
		ucountry.Open();
		
		ucity.update();
		ucountry.update();
		
		//ucity.updateprint();
		//ucountry.updateprint();
		
		ucity.logprint();
		ucountry.logprint();
		
		ucity.close();
		ucountry.close();
	}
}
