package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import model.universals.IOCsv;

public class Graph {
	private ArrayList<List<Double>> arcsList;
	private ArrayList<String> classesList;
	
	public Graph() {
		arcsList = new ArrayList<List<Double>>();
	}
	
	public void createGraphMulticore(InstanceList instanceList) {
		arcsList.clear();
		classesList = instanceList.getClasses();
		int n = instanceList.size();
		
		DistanceMatrix distances = new DistanceMatrix();
		distances.createDistanceMatrixMulticore(instanceList);
		
		IntStream.range(0, n).parallel().forEach(i->{
			ArrayList<List<Double>> localTable = new ArrayList<List<Double>>();
			int j,k;
			for(j=i+1;j<n;j++)
			{
				boolean canConnect;
				double r;
				//System.out.println("i = " + i + "\nj = " + j);
				canConnect = true;
				r= distances.get(i, j);
				for(k=0;k<n;k++)
				{
					if(k!=i && k!=j)
					{
						if(distances.get(i, k)<=r && distances.get(j, k)<=r )
						{
							canConnect = false;
							break;
						}
					}
				}
				if(canConnect) {
					ArrayList<Double> connection = new ArrayList<Double>();
					connection.add((double) i);
					connection.add((double) j);
					connection.add(r);
					localTable.add(connection);
				}
			}
			arcsList.addAll(localTable);
		});
	}
	
	public void createGraphMonocore(InstanceList instanceList) {
		arcsList.clear();
		classesList = instanceList.getClasses();
		int i,j,k,n = instanceList.size();
		
		DistanceMatrix distances = new DistanceMatrix();
		distances.createDistanceMatrixMonocore(instanceList);
		boolean canConnect;
		double r;
		
		
		for(i=0;i<n;i++)
		{
			for(j=i+1;j<n;j++)
			{
				//System.out.println("i = " + i + "\nj = " + j);
				canConnect = true;
				r = distances.get(i, j);
				for(k=0;k<n;k++)
				{
					if(k!=i && k!=j)
					{
						if(distances.get(i, k)<=r && distances.get(j, k)<=r )
						{
							canConnect = false;
							break;
						}
					}	 
				}
				if(canConnect) {
					ArrayList<Double> connection = new ArrayList<Double>();
					connection.add((double) i);
					connection.add((double) j);
					connection.add(r);
					arcsList.add(connection);
				}
			}
		}
	}
	
	public void exportToCsv(String arcsFileName, String nodesFileName) throws IOException
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