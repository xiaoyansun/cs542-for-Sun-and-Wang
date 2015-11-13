package cs542project3;

public class test_1 {
	public static void main(String[] args) {
		relation city= new relation("city.csv");
		relation country =new relation("country.csv");
		
		join newjoin= new join(city, country);
		newjoin.Open();
		
		// result= new relation("result.csv");
		 while (true) {
			 tuple record = newjoin.GetNext();
			 if (record==null) {
			 break;
			 }
			 
			 if(Integer.parseInt(record.getOthers()[4])>=Integer.parseInt(record.getOthers()[10])*0.4)
				 record.print();
		}
		newjoin.Close();
	}
}


