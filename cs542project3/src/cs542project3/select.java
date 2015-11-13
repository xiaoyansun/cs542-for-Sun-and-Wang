package cs542project3;
import java.util.Observable;  
import java.util.Observer; 
/*
 * Select the required tuples with an observer
 */
public class select implements Observer{
	
	private tuple t;
	
	/*
	 * Select required tuples
	 */
	@Override
	public void update(Observable o, Object arg) {
		t = (tuple) arg;  
		if(t!=null){
			//Choose the required tuples by the condition
			if(Integer.parseInt(t.getOthers()[4]) > Integer.parseInt(t.getOthers()[10])*0.4)
				t.ProjectPrint();
		}
	}	
}
