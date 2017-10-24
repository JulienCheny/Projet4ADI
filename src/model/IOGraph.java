package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IOGraph {
	
	public static void exportGraphCsv(String arcsFileName, String nodesFileName, ArrayList<List<Double>> arcsList, ArrayList<String> classesList) throws IOException
	{
		String[] arcsNameArray = {"RNGSource", "RNGTarget", "RNGDistance"};
		ArrayList<String> nameList = new ArrayList<String>(Arrays.asList(arcsNameArray));
		ArrayList<List<String>> list = new ArrayList<List<String>>();
		int i, size = arcsList.size();
		list.add(nameList);
		for(i = 0; i < size; i++) {
			List <Double> arc = arcsList.get(i);
			ArrayList<String> arcStrList = new ArrayList<>();
			arcStrList.add(Integer.toString(arc.get(0).intValue()));
			arcStrList.add(Integer.toString(arc.get(1).intValue()));
			arcStrList.add(String.format("%.8g",arc.get(2)));
			list.add(arcStrList);
		}
		
		IOCsv.exportCsv(arcsFileName, list);
		
		String[] nodesNameArray = {"IdNode", "Class"};
		nameList = new ArrayList<String>(Arrays.asList(nodesNameArray));
		list.clear();
		size = classesList.size();
		list.add(nameList);
		for(i = 0; i < size; i++) {
			ArrayList<String> nodesStrList = new ArrayList<>();
			nodesStrList.add(Integer.toString(i));
			nodesStrList.add(classesList.get(i));
			list.add(nodesStrList);
		}
		
		IOCsv.exportCsv(nodesFileName, list);
	}
}
