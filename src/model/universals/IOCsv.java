package model.universals;

import java.io.File;
import java.io.FileNotFoundException;
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
	public static List<List<String>> importCsv(File file, String separator)
	{
		
		List<List<String>> list=new ArrayList<List<String>>();
	    Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	    
	    while(scanner.hasNext()){
	    	List <String> attributs;
	    	attributs = Arrays.asList(scanner.nextLine().split(separator));
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
	public static void exportCsv(String fileName,List<List<String>> list )
	{
		PrintWriter outFile = null;
		try {
			outFile = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
