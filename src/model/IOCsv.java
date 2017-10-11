package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IOCsv {
	
	
	public static ArrayList<List<String>> importCsv(File file) throws IOException
	{
		int i,j;
		
		ArrayList<List<String>> list=new ArrayList<List<String>>();
	    Scanner scanner = new Scanner(file);
	    
	    
	    while(scanner.hasNext()){
	    	List <String> attributs;
	        //String[] tokens = scanner.nextLine().split(",");
	    	attributs = Arrays.asList(scanner.nextLine().split(","));
	        /*for(String value : scanner.nextLine().split(","))
	        {
	        	attributs.add(value);
	        }*/
	    	list.add(attributs);
	    }

    	//System.out.println(list);
	    return list;
	}
	
	public static void exportCsv(String fileName,ArrayList<List<String>> list ) throws IOException
	{
		PrintWriter outFile = new PrintWriter(fileName);
		for(List<String> attributsList : list) {
			String line = "";
			for(String attribut : attributsList) {
				line += attribut + ";";
			}
			System.out.println(line);
			outFile.println(line);
		}
		outFile.close();
	}
	

}
