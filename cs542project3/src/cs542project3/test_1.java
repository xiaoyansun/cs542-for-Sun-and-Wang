package cs542project3;

public class test_1 {
	public static void main(String[] args) {
		relation city= new relation("city.csv");
		relation country =new relation("country.csv");
		
		join newjoin= new join(city, country);
		newjoin.addObserver(new select()); 
		newjoin.Open();
		
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


