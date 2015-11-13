package cs542project3;

public class test_1 {
	public static void main(String[] args) {
		relation city= new relation("city.csv");
		relation country =new relation("country.csv");
		
		join newjoin= new join(city, country);
		newjoin.Open();
		
		// result= new relation("result.csv");
		newjoin.GetNext().print();
		newjoin.Close();
	}
}
