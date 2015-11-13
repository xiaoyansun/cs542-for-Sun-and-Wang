package cs542project3;

import java.util.Observable;

public class join extends Observable{
	
	relation A= null;
	relation B=null;
	tuple r=null;
	tuple l=null;
	private tuple toSelect=null;
	
	public join(relation a, relation b){
		this.A=a;
		this.B=b;
	}
	
	public void Open(){
		A.Open();
		B.Open();
		r=A.GetNext();
	}
	public tuple GetNext(){
		//r.print();
		do{
			l=B.GetNext();
			//System.out.println(r.getOthers()[2]);
			//System.out.println(l.getOthers()[0]);
			if(l==null){
				//B is exhausted for the current r
				B.Close();
				r=A.GetNext();
				if(r==null) return null;
				B.Open();
				l=B.GetNext();
			}
		}while(!r.getOthers()[2].equals(l.getOthers()[0]));
		String[] s=new String[r.getOthers().length+l.getOthers().length-1];
		//System.out.println(s.length);
		for(int i=0;i<r.getOthers().length;i++)
			s[i]=r.getOthers()[i];
		for(int i=r.getOthers().length;i<s.length;i++){
			s[i]=l.getOthers()[i-r.getOthers().length+1];
		}
		tuple newTuple= new tuple(s);
		this.toSelect=newTuple;
		return newTuple;
	}
	
	public void GotoSelect(){
        this.setChanged();  
        this.notifyObservers(this.toSelect);
	}
	
	public void Close(){
		A.Close();
		B.Close();
	}
}
