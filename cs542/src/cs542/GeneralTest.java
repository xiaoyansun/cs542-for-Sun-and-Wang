package cs542;

/**
 * The General test
 * 
 * 
 * 
 */

public class GeneralTest {
	public static void m() {

	byte[] A = new byte[30];
	for(int i=0;i<30;i++){
		A[i]=1;
	}
	byte[]  B = new byte[15];
	for(int i=0;i<15;i++){
		B[i]=2;
	}

	KeyValueStore.WriteMeta();
	System.out.println("---Put data 111 into DB...");
	KeyValueStore.Put(111,A);
	System.out.println("---Put data 222 into DB...");
	KeyValueStore.Put(222,B);
	
	System.out.println("---Get data 111 from DB...");
	byte[] C=KeyValueStore.Get(111);
	System.out.println("---Print 111 value...");
	
	for(int i=0;i<C.length;i++){
		System.out.print(C[i]+" ");
	}
	System.out.println();
	
	System.out.println("---Remove data 222 from DB...");
	KeyValueStore.Remove(222);
	
	System.out.println("---Put data 222 back to DB...");
	KeyValueStore.Put(222,B);
    byte[] E=KeyValueStore.Get(222);
	System.out.println("---Print 222 value...");
	
	for(int i=0;i<E.length;i++){
		System.out.print(E[i]+" ");
	}
	System.out.println();
	
	System.out.println("---Replace 222 with A...");
	KeyValueStore.Put(222,A);
	
	System.out.println("---Get data 222 from DB...");
	byte[] D=KeyValueStore.Get(222);
	System.out.println("---Print 222 value...");
	
	for(int i=0;i<C.length;i++){
		System.out.print(D[i]+" ");
	}
	System.out.println();
	}
}
