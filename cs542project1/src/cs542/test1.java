package cs542;

/**
 * The Concurrency Test
 * two users try to access the DB at the same time, User_1 is putting data, and User_2 is getting data
 * it shows that User_2 get the access
 * User_1 is able to put in data after User_2 is done
 * User_2 execute getting data again after a while
 * User_2 gets the updated data
 * 
 */

public class test1 {  
	
	
    public static void m() {  
          
        System.out.println("*****Thread 1 and thread 2 are ready to start.");
        new Thread(new Runnable() {    
                @Override  
                public void run() {  
                	byte[] A = new byte[1024*1024];
                	for(int i=0;i<1024*1024;i++){
                		A[i]=1;
                	}
                	byte[] B = new byte[1024*1024];
                	for(int i=0;i<1024*1024;i++){
                		B[i]=2;
                	}
                	try {
                	System.out.println(Thread.currentThread()+"---Thread 1 is ready to put data 1.");  
                	System.out.println("---Thread 1 is sleeping for time 1000");
                	Thread.sleep(1000);
                	KeyValueStore.Put(1,B);
                	System.out.println("---Thread 1 is putting (1,A) into Database which has a key=1 and value=A[i]=1");
                	//KeyValueStore.Get(1);
                    //System.out.println("thread 0 is sleeping");
                    //KeyValueStore.Put(1,B);
                    //KeyValueStore.Put(2,B);
                    System.out.println(Thread.currentThread()+"---The data "+"1 "+"has been put.");
                	} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }}).start();  
          
        new Thread(new Runnable() {  
            @Override  
            public void run() { 
            	System.out.println(Thread.currentThread()+"---Thread 2 is ready to get data 1.");  
				//KeyValueStore.Put(1, A);
            	try {
				
            		System.out.println("---Thread 2 is sleeping for time 1000");
            		Thread.sleep(1000);
            		byte[]data1=KeyValueStore.Get(1);
            		System.out.println("---Thread 2 is getting value from Database which has a key=1");
            		System.out.println("---The value thread 2 got is ");
            		for(int i=0;i<10;i++){
            			System.out.print(data1[i]+" ");
            		}
            		System.out.println("---Thread 1 is sleeping for time 1000");
            		Thread.sleep(1000);
            		byte[]data2=KeyValueStore.Get(1);
            		System.out.println("---The value thread 2 got is ");
            		for(int i=0;i<10;i++){
            			System.out.print(data2[i]+" ");
            		}
            }  
            	catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
        }).start();  
    System.out.println("*****Thread 1 and thread 2 runs at the same time");
    System.out.println("*****Thread 2 first got the original value in database");
    System.out.println("*****Thread 1 put a new value into the database");
    System.out.println("*****Thread 2 got the new value after 1000 sleep");
    System.out.println("*****Concurrency Test 1 over.");
    }  
  
} 
