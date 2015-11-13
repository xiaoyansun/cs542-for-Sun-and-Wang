package cs542project3;
import java.util.Observable;  
import java.util.Observer; 


public class select implements Observer{
	private tuple t;
	
	@Override
	public void update(Observable o, Object arg) {
		t = (tuple) arg;  
		//t.print();
		if(t!=null){
			if(Integer.parseInt(t.getOthers()[4])>=Integer.parseInt(t.getOthers()[10])*0.4)
				t.ProjectPrint();
		}
	}	
}
