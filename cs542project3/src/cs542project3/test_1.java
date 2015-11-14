package cs542project3;

/*
 * Test
 * Find all cities whose population is more than 40% of the population of their entire country.
 */
public class test_1 {
	
	public static void main(String[] args) {
		//Initialize two new relations: city and country
		relation city= new relation("city.db");
		relation country =new relation("country.db");
		//Join city and country
		join newjoin= new join(city, country);
		newjoin.addObserver(new select()); 
		newjoin.Open();
		System.out.print("Cities:"+"\n");
		//Select the required tuples
		 while (true) {
			 tuple record = newjoin.GetNext();  
			 if (record==null) {
			 break;
			 }
			 newjoin.GotoSelect();
		}
		newjoin.Close();
	}
}


