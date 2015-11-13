package cs542project3;

public class tuple {
	//private String PrimaryKey;
	private String[] others;
	
//	public tuple (String PrimaryKey, String[] others){
//		this.PrimaryKey=PrimaryKey;
//		this.others=others;
//	}
	public tuple(String[] other){
		//this.PrimaryKey=tuple.getPrimaryKey();
		this.others=new String[other.length];
		for(int i=0;i<other.length;i++)
		{	
			this.others[i]=other[i];
			//System.out.println(this.others[3]);
		}
	}	
	
	/*
		getters and setters 
	*/
//	public String getPrimaryKey() {
//		return PrimaryKey;
//	}
//	public void setPrimaryKey(String primaryKey) {
//		PrimaryKey = primaryKey;
//	}
	public String[] getOthers() {
		return others;
	}
	public void setOthers(String[] others) {
		for(int i=0;i<others.length;i++)
			this.others[i]=others[i];
	}
	/*
		methods	
	*/
	public void print(){
		for (int i=0;i<others.length;i++){
			System.out.print(others[i]+"\t");
		}
		System.out.println();
	}

}
