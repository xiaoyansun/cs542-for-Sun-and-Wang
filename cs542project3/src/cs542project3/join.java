package cs542project3;

public class join {
	
	relation A= null;
	relation B=null;
	tuple r=null;
	tuple l=null;
	
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
		r.print();
		do{
			l=B.GetNext();
			l.print();
			if(l==null){
				//B is exhausted for the current r
				B.Close();
				r=A.GetNext();
				if(r==null) return null;
				B.Open();
				l=B.GetNext();
			}
		}while(r!=null && !r.getOthers()[3].equals(l.getOthers()[0]));
		String[] s=new String[r.getOthers().length+l.getOthers().length-1];
		System.out.println(s.length);
		for(int i=0;i<r.getOthers().length;i++)
			s[i]=r.getOthers()[i];
		for(int i=r.getOthers().length;i<s.length;i++){
			s[i]=l.getOthers()[i-r.getOthers().length+1];
		}
		tuple newTuple= new tuple(s);
		return newTuple;
	}
	
	public void Close(){
		A.Close();
		B.Close();
	}
}
