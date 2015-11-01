package cs542project2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class test2 extends btree<Integer, String> {
	static String[] title;
	static Integer[] year;
	static String[] format;
	private static BufferedReader br;
	static String s;
	static String[] str;
	
	public static void main(String[] args) {
		// test for the btree on assignment4
		StringBuffer sb = new StringBuffer();
		try {
		br = new BufferedReader(new FileReader("testTxtFile//movies.txt"));
		try {
			s = br.readLine();
			while(s != null){
	            sb.append(s);
	            s = br.readLine();
	        }
	        s = sb.toString();
	        str = s.split(",");
	        title = new String[50];
	        year = new Integer[50];
	        format = new String[50];
	        btree<Integer, String> tree = new btree<>();
	        for(int i=0,j=0;i<450;){
	        	title[j] = str[i];
	        	year[j] = Integer.parseInt(str[i+1]);
	        	format[j] = str[i+2];
	        	i=i+10;	
	        	tree.Put(year[j],title[j]);
	        	j=j+1;
	        }
	        //tree.printTree();
	        System.out.println("************************************");
	        System.out.println("2000");
	        tree.Get(2000);
	        System.out.println("************************************");
	        System.out.println("2005");
	        tree.Get(2005);
	        System.out.println("************************************");
	        System.out.println("2010");
	        tree.Get(2010);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}