package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IOGraph {
	
	public static void exportGraphCsv(String ArcsFileName, String NodeFileName, ArrayList<List<Double>> ArcsList) throws IOException
	{
		String[] nameArray = {"RNGSource", "RNGTarget", "RNGDistance"};
		ArrayList<String> nameList = new ArrayList<String>(Arrays.asList(nameArray));
		ArrayList<List<String>> list = new ArrayList<List<String>>();
		
		list.add(nameList);
		for(List <Double> arc : ArcsList) {
			ArrayList<String> arcStrList = new ArrayList<>();
			arcStrList.add(Integer.toString(arc.get(0).intValue()));
			arcStrList.add(Integer.toString(arc.get(1).intValue()));
			arcStrList.add(String.format("%.8g",arc.get(2)));
			list.add(arcStrList);
		}
		
		IOCsv.exportCsv(ArcsFileName, list);
	}
}
