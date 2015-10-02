package cs542;

/**
 * The Concurrency Test 2
 * 
 * 
 */

public class test11 {  
	
    public static void m() {   
        System.out.println("*****Thread 1 and thread 2 are ready to start.");
        new Thread(new Runnable() {    
                @Override  
                public void run() { 
                	byte[] A = new byte[1024*1024];
                	for(int i=0;i<1024*1024;i++){
                		A[i]=1;
                	}
                	System.out.println(Thread.currentThread()+"---Thread 1 is ready to remove data 1.");  
                	KeyValueStore.Put(1, A);
                	KeyValueStore.Remove(1);
                	System.out.println("---Thread 1 is removing Data which has a key=1");
                    System.out.println(Thread.currentThread()+"---The data "+"1 "+"has been removed.");
                }}).start();  
          
        new Thread(new Runnable() {  
            @Override  
            public void run() { 
            	System.out.println(Thread.currentThread()+"---Thread 2 is ready to get data 1.");  
				//KeyValueStore.Put(1, A);
            	try {
                	byte[] B = new byte[1024*1024];
                	for(int i=0;i<1024*1024;i++){
                		B[i]=1;
                	}
            		//KeyValueStore.Put(1, B);
            		System.out.println("---Thread 2 is sleeping for time 1000");
            		Thread.sleep(100);
            		byte[]data1=KeyValueStore.Get(1);
            		System.out.println("---Thread 2 is getting value from Database which has a key=1");
            		if(data1==null)
            			System.out.println("---The data not exists");
            		else
            			{System.out.println("---The value thread 2 got is ");
            		for(int i=0;i<10;i++){
            			System.out.print(data1[i]+" ");
            		}
            }  }
            	catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
        }).start();  
    System.out.println("*****Thread 1 and thread 2 runs at the same time");
    System.out.println("*****Thread 1 first remove the value with a key=1");
    System.out.println("*****Thread 2 gets null after 1000 sleep");
    }  
  
} 
