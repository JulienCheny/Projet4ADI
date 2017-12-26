package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import model.universals.IOCsv;

/**
 * @author Francois and Julien
 *
 */
public class Graph extends Observable {
	private ArrayList<List<Double>> arcsList;
	private boolean [][] arcsMatrix;
	private DistanceMatrix distances;
	private ArrayList<String> classesList;
	private InstanceList instanceList;
	
	/**
	 * Default 's Constructor : initialize Graph with nothing
	 */
	public Graph() {
		arcsList = new ArrayList<List<Double>>();
	}
	
	/**
	 * Method createGraphMulticore : Create the graph with parallelism
	 * @param instanceList
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public void createGraphMulticore(InstanceList instanceList) throws InterruptedException, ExecutionException {
		arcsList.clear();
		this.instanceList = instanceList;
		classesList = instanceList.getClasses();
		int n = instanceList.size();
		
		distances = new DistanceMatrix();
		distances.createDistanceMatrixMulticore(instanceList);
		
		arcsMatrix = new boolean[n][n];
		
		IntStream.range(0, n).parallel().forEach(i->{
			ArrayList<List<Double>> localTable = new ArrayList<List<Double>>();
			int j,k;
			for(j=i+1;j<n;j++)
			{
				boolean canConnect;
				double r;
				canConnect = true;
				r= distances.get(i, j);
				for(k=0;k<n;k++)
				{
					if(k!=i && k!=j)
					{
						if(distances.get(i, k)<r && distances.get(j, k)<r )
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
					arcsMatrix[i][j] = true;
					arcsMatrix[j][i] = true;
				}
			}
			arcsList.addAll(localTable);
		});
	}
	
	/**
	 * Method createGraphMonocore : Create the graph without parallelism
	 * @param instanceList
	 */
	public void createGraphMonocore(InstanceList instanceList) {
		arcsList.clear();
		this.instanceList = instanceList;
		classesList = instanceList.getClasses();
		int i,j,k,n = instanceList.size();
		
		setChanged();
		notifyObservers(1);
		
		distances = new DistanceMatrix();
		distances.createDistanceMatrixMonocore(instanceList);
		arcsMatrix = new boolean[n][n];
		
		setChanged();
		notifyObservers(2);
		
		boolean canConnect;
		Double r;
		
		for(i=0;i<n;i++)
		{
			if(i%(n/8) == 0) {
				int regularUpdate = i/(n/8);
				setChanged();
				notifyObservers(regularUpdate+2);
			}
			for(j=i+1;j<n;j++)
			{
				canConnect = true;
				r = distances.get(i, j);
				for(k=0;k<n;k++)
				{
					if(k!=i && k!=j)
					{
						if(distances.get(i, k)<r && distances.get(j, k)<r )
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
					arcsMatrix[i][j] = true;
					arcsMatrix[j][i] = true;
				}
			}
		}
		setChanged();
		notifyObservers(10);
	}
	
	public boolean areSimilarNodes() {
		int n = instanceList.size();
		for(int row = 0; row < n; row++)
			for(int col = 0; col < n; col++)
				if(row != col && (distances.get(row, col) == 0))
					return true;
					
		return false;
	}
	
	public List<Integer> getSimilarNodes() {
		int n = instanceList.size();
		List<Integer> idNodeList = new ArrayList<Integer>();
		for(int row = 0; row < n; row++)
			for(int col = 0; col < n; col++)
				if(row != col && (distances.get(row, col) == 0))
					if(!idNodeList.contains(row))
						idNodeList.add(row);
		return idNodeList;
	}
	
	/**
	 * Method exportToCsv : Export the Grpah into Csv file
	 * @param arcsFileName
	 * @param nodesFileName
	 * @throws IOException
	 */
	public void exportToCsv(String arcsFileName, String nodesFileName)
	{
		String[] arcsNameArray = {"RNGSource", "RNGTarget", "RNGDistance"};
		ArrayList<String> nameList = new ArrayList<String>(Arrays.asList(arcsNameArray));
		ArrayList<List<String>> list = new ArrayList<List<String>>();
		int i, size = arcsList.size();
		list.add(nameList);
		for(i = 0; i < size; i++) {
			List <Double> arc = arcsList.get(i);
			ArrayList<String> arcStrList = new ArrayList<>();
			try{
				arcStrList.add(Integer.toString(arc.get(0).intValue()));
			}
			catch (Exception e) {
				System.out.println("Error on iteration " + i);
			}
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

	/**
	 * Method calculateAccessLevel
	 * @return a list of integer which specifies the difficulty to accessing nodes
	 * @throws Exception 
	 */
	public int[] calculateAccessLevel() throws Exception {
		if(areSimilarNodes())
			throw new Exception("Similar Nodes in list");
		int n = instanceList.size();
		int access[] = new int[n];
		int di,dj;
		for(di = 0;di < n; di++) {
			for(dj = 0;dj < n; dj++) {
				int dk = dj;
				boolean continu = true;
				double minDistance = -1;
				while(continu) {
					int currentBest = -1;
					for(int i = 0; i < n; i++) {
						if(arcsMatrix[i][dk]) {
							if(minDistance == -1 || minDistance > distances.get(di, i)) {
								minDistance = distances.get(di, i);
								currentBest = i;
							}
						}
					}
					if(currentBest != di)
						if(currentBest == -1)
							continu = false;
						else
							dk = currentBest;
					else {
						continu = false;
						access[di]++;
					}
				}
			}
		}
		
		return access;
	}
}
