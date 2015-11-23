package cs542project4;

/*
 * getOthers() method
 * setOthers() method
 * print() method
 * ProjectPrint() method
 */
public class tuple {

	private String[] values;
	
	/*
	 * Separate a tuple into different attributes
	 */
	public tuple(String[] other){
		this.values=new String[other.length];
		for(int i=0;i<other.length;i++){	
			this.values[i]=other[i];
		}
	}	
	
	/*
	 * Get values
	 */
	public String[] getValues() {
		//Get attributes of different tuples
		return values;
	}
	
	/*
	 * 
	 * Set values
	 */
	public void setValues(String[] others) {
		for(int i=0;i<others.length;i++)
			//Set attributes of different tuples
			this.values[i]=others[i];
	}

	/*
	 * Print the tuple
	 */
	public void print(){
		for (int i=0;i<values.length;i++){
			System.out.print(values[i]+"\t");
		}
		System.out.println();
	}
	
	/*
	 * Print Project Result
	 */
	public void ProjectPrint(){
		System.out.print(values[1]);
		System.out.println();
	}

}
