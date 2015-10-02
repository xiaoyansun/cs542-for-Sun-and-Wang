package cs542;

/**
 * The Durability Test
 * 
 * Put data into DB, then clear out objects (simulate reboot of machine)
 * Then get value
 * 
 */

public class test2 {
	public static void m() {
		
		byte[] A = new byte[30];
		for(int i=0;i<30;i++){
			A[i]=1;
		}
		
		System.out.println("---Put data 111 into DB...");
		KeyValueStore.Put(111, A);
		
		System.out.println("---Machine restarted...");
		KeyValueStore.Clear();
		
		System.out.println("---Get 111 from DB...and display the data..");
		byte[] C= KeyValueStore.Get(111);
		for(int i=0;i<C.length;i++){
			System.out.print(C[i]+" ");
		}
		System.out.println();
	}
}
