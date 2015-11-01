package cs542project2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class test1 extends btree<String, String> {
	static String[] title;
	static String[] year;
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
	        year = new String[50];
	        format = new String[50];
	        btree<String, String> tree = new btree<>();
	        for(int i=0,j=0;i<450;){
	        	title[j] = str[i];
	        	year[j] = str[i+1];
	        	format[j] = str[i+2];
	        	i=i+10;	
	        	tree.Put(year[j]+"|"+format[j],title[j]);
	        	j=j+1;
	        }
	        //tree.printTree();
	        System.out.println("************************************");
	        System.out.println("1997|DVD");
	        tree.Get("1997|DVD");
	        System.out.println("************************************");
	        System.out.println("1990|VHS");
	        tree.Get("1990|VHS");
	        System.out.println("************************************");
	        System.out.println("2001|DVD");
	        tree.Get("2001|DVD");
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