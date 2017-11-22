package model.universals;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IOCsv {
	
	
	/**
	 * Method importCsv : 
	 * @param file
	 * @return the 2D list imported from a Csv File
	 * @throws IOException
	 */
	public static ArrayList<List<String>> importCsv(File file) throws IOException
	{
		
		ArrayList<List<String>> list=new ArrayList<List<String>>();
	    Scanner scanner = new Scanner(file);
	    
	    
	    while(scanner.hasNext()){
	    	List <String> attributs;
	    	attributs = Arrays.asList(scanner.nextLine().split(","));
	    	list.add(attributs);
	    }
	    scanner.close();
    	//System.out.println(list);
	    return list;
	}
	
	/**
	 * Method exportCsv : Export a 2D List into a Csv file
	 * @param fileName
	 * @param list
	 * @throws IOException
	 */
	public static void exportCsv(String fileName,ArrayList<List<String>> list ) throws IOException
	{
		int i;
		PrintWriter outFile = new PrintWriter(fileName);
		for(List<String> attributsList : list) {
			String line = "";
			for(String attribut : attributsList) {
				line += attribut;
				if(attributsList.get(attributsList.size()-1) != attribut)
					line +=";";
			}
			//System.out.println(line);
			outFile.println(line);
		}
		outFile.close();
	}
	

}
