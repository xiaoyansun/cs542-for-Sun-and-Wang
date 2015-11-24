package cs542project4;

/* the purpose of this class is to test the relations are identical.
 * that is, for example, city.csv and city 2.csv have the same value of poplulation 
 * for each city
 */


public class testRelations {
	private relation a= null;
	private relation b=null;
	int attr;
		
	public testRelations(relation a, relation b, int c){
		this.a=a;
		this.b=b;
		this.attr=c;
	}
	
	/*param: two relations to be compared and the index of attribute to compare on
	 * 
	 */
	public void testPopulation(){
		a.Open();
		b.Open();
		tuple t=a.GetNext();
		tuple w=b.GetNext();
		do{
			if(!t.getValues()[attr].equals(t.getValues()[attr])){
				System.out.println("Mismatch! Relation "+a.getName()+" Relation "+b.getName()+
						" are not the same on attribute "+ a.getAttribute().getValues()[attr]);
				break;
			}
			t=a.GetNext();
			w=b.GetNext();
		}while(t!=null&&w!=null);
		if(t==null||w==null)
			System.out.println(a.getName()+" and "+b.getName()+" are same on attribute population..");
		a.Close();
		b.Close();
	}
}
