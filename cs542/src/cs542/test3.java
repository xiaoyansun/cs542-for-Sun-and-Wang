package cs542;

/**
 * The Fragmentation Test
 * 
 * 
 */


public class test3 {
	public static void m() {
		
	byte[] A = new byte[1024*1024];
	for(int i=0;i<1024*1024;i++){
		A[i]=1;
	}
	byte[]  B = new byte[1024*1024];
	for(int i=0;i<1024*1024;i++){
		B[i]=2;
	}
	byte[]  C = new byte[1024*1024];
	for(int i=0;i<1024*1024;i++){
		C[i]=3;
	}
	byte[]  D = new byte[1024*1024];
	for(int i=0;i<1024*1024;i++){
		D[i]=4;
	}
	byte[]  E = new byte[1024*512];
	for(int i=0;i<1024*512;i++){
		E[i]=5;
	}
	byte[]  F = new byte[1024*1024];
	for(int i=0;i<1024*1024;i++){
		F[i]=6;
	}
	byte[]  G = new byte[1024*1024];
	for(int i=0;i<1024*1024;i++){
		G[i]=7;
	}
	byte[]  H = new byte[1024*1024];
	for(int i=0;i<1024*1024;i++){
		H[i]=8;
	}
	
	KeyValueStore.Clear();
	KeyValueStore.WriteMeta();
	
	System.out.println("---Put data 1 (1M) into DB:");
	KeyValueStore.Put(1,A);
	System.out.println("---Put data 2 (1M) into DB:");
	KeyValueStore.Put(2,B);
	System.out.println("---Put data 3 (1M) into DB:");
	KeyValueStore.Put(3,C);
	System.out.println("---Put data 4 (1M) into DB:");
	KeyValueStore.Put(4, D);
	
	System.out.println();
	System.out.println("---Remove data 2 (1M) from DB:");
	KeyValueStore.Remove(2);
	System.out.println("---Put data 5 (0.5M) into DB:");
	KeyValueStore.Put(5, E);
	
	System.out.println();
	System.out.println("---Display the layout in DB. *: has data; -: no data");
	System.out.println("---Data exists at 1M to 3.5M and 4M to 5M");
	KeyValueStore.printDB();
	
	System.out.println("---Put data 6 (1M) into DB: (fail expected)");
	KeyValueStore.Put(6, F); 
	
	System.out.println("---Remove data 3 (1M) from DB:");
	KeyValueStore.Remove(3);
	System.out.println("---Put data 7 (1M) into DB: (success expected)");
	KeyValueStore.Put(7, G); 
	
	System.out.println("---Remove data 5 (0.5M) from DB:");
	KeyValueStore.Remove(5);
	System.out.println("---Put data 8 (1M) into DB:");
	KeyValueStore.Put(8, H);
	
	System.out.println();
	System.out.println("---Display the layout in DB. *: has data; -: no data");
	System.out.println("---Data exists at 1M to 2M , 2.5M-3.5M, and 4M to 5M");
	KeyValueStore.printDB();

	} 

}
