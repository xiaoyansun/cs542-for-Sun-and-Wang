package cs542project3;

import java.util.Observable;

/*
 * Join different relations by the same attributes.
 * Open() method
 * GetNext() method
 * GoToSelect() method
 * Close() method 
 */
public class join extends Observable{
	
	relation A= null;
	relation B=null;
	tuple r=null;
	tuple l=null;
	private tuple toSelect=null;
	
	/*
	 * Join relation a and b
	 * @param a
	 * @param b
	 */
	public join(relation a, relation b){
		this.A=a;
		this.B=b;
	}
	
	/*
	 * Open relations
	 */
	public void Open(){
		A.Open();
		B.Open();
		r=A.GetNext();
	}
	
	/*
	 * Set new tuple by join
	 * @return
	 */
	public tuple GetNext(){
		//When the same attributes equal, join the other attributes in two relations  
		do{
			l=B.GetNext();
			if(l==null){
				//B is exhausted for the current r
				B.Close();
				r=A.GetNext();
				if(r==null) return null;
				B.Open();
				l=B.GetNext();
			}
		}while(!r.getOthers()[2].equals(l.getOthers()[0]));
		//Set new join tuples
		String[] s=new String[r.getOthers().length+l.getOthers().length-1];
		//Set the first relation's new join tuples
		for(int i=0;i<r.getOthers().length;i++){
			s[i]=r.getOthers()[i];
		}
		//Set the Second relation's new join tuples
		for(int i=r.getOthers().length;i<s.length;i++){
			s[i]=l.getOthers()[i-r.getOthers().length+1];
		}
		//Create a new tuple after join
		tuple newTuple= new tuple(s);
		this.toSelect=newTuple;
		return newTuple;
	}
	
	/*
	 * Go to Class select with observer(dealing with a pipeline way)
	 * Set the new joined tuple one by one to select the required tuples
	 */
	public void GotoSelect(){
        this.setChanged();  
        this.notifyObservers(this.toSelect);
	}
	
	/*
	 * Close two relations
	 */
	public void Close(){
		A.Close();
		B.Close();
	}
}
