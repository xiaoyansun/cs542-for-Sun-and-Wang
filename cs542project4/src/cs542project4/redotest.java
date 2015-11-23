package cs542project4;

import java.io.IOException;

public class redotest {

	public static void main(String[] args) throws IOException {
		
		relation city2 = new relation("city 2.csv");
		relation citylog = new relation("city_log.csv");
		relation country2 = new relation("country 2.csv");
		relation countrylog = new relation("country_log.csv");
		
		cityredo ciredo = new cityredo(city2, citylog);
		countryredo coredo = new countryredo(country2, countrylog);
		
		ciredo.Open();
		coredo.Open();
		
		ciredo.redo();
		ciredo.redoprint();
		
		coredo.redo();
		coredo.redoprint();
		
		ciredo.close();
		coredo.close();
		
		relation city1= new relation ("city.csv");
		testRelations t= new testRelations(city1, city2, 4);
		t.testPopulation();
		relation country1= new relation ("country.csv");
		testRelations s= new testRelations(country1, country2, 6);
		s.testPopulation();
	}
}
