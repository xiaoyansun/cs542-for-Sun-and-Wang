package cs542project3;

/*
 * getOthers() method
 * setOthers() method
 * print() method
 * ProjectPrint() method
 */
public class tuple {

	private String[] others;
	
	/*
	 * Separate a tuple into different attributes
	 */
	public tuple(String[] other){
		this.others=new String[other.length];
		for(int i=0;i<other.length;i++){	
			this.others[i]=other[i];
		}
	}	
	
	/*
	 * Get attributes
	 */
	public String[] getOthers() {
		//Get attributes of different tuples
		return others;
	}
	
	/*
	 * 
	 * Set attributes
	 */
	public void setOthers(String[] others) {
		for(int i=0;i<others.length;i++)
			//Set attributes of different tuples
			this.others[i]=others[i];
	}

	/*
	 * Print attributes
	 */
	public void print(){
		//Print attributes of different tuples
		for (int i=0;i<others.length;i++){
			System.out.print(others[i]+"\t");
		}
		System.out.println();
	}
	
	/*
	 * Print Project Result
	 */
	public void ProjectPrint(){
		System.out.print(others[1]);
		System.out.println();
	}

}
