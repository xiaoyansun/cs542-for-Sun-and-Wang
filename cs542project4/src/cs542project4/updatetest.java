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
		ucity.updateprint();
		ucity.logprint();
		
		ucountry.update();
		ucountry.updateprint();
		ucountry.logprint();
		
		ucity.close();
		ucountry.close();
	}
}
