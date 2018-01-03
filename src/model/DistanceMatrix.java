package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import model.universals.IOCsv;

/**
 * @author Francois and Julien
 *
 */

public class DistanceMatrix {
	private int size = 0;

	private double [] [] matrix;
	
	/**
	 * Method createDistanceMatrixMonocore : create the matrix of Distance without parralelism
	 * @param instanceList
	 */
	public void createDistanceMatrixMonocore(InstanceList instanceList) {
		int i, j, n = instanceList.size();
		size = n;
		matrix = new double[n][n];
		for(i=0;i<n;i++)
		{
			for(j=i+1;j<n;j++)
			{
				double r = instanceList.euclideanDistance(i,j);
				set(i, j, r);
				set(j, i, r);
			}
		}
	}
	
	

	/**
	 * Method createDistanceMatrixMonocore : create the matrix of Distance with parralelism
	 * @param instanceList
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public void createDistanceMatrixMulticore(InstanceList instanceList) throws InterruptedException, ExecutionException {
		int n = instanceList.size();
		size = n;
		matrix = new double[n][n];
		
		/*List<Callable<Double>> tasks=new ArrayList<>();
		List<Future<Double>>results=new ArrayList<>();
		
		ExecutorService executor;
		int nbProcs = Runtime.getRuntime().availableProcessors();
	    executor = Executors.newFixedThreadPool(nbProcs);
	    for(i=0;i<n;i++)
	    {
	    	for(j=0;j<n;j++)
		    {
		    	for(int t=0;t<nbProcs;t++)
				{
					tasks.add(instanceList);
				}
		    	results =executor.invokeAll(tasks);
		    	for(Future<Double> result: results)
				{
		    		set(i, j, result.get());
					set(j, i, result.get());
				}
		    	results.clear();
				tasks.clear();
				
		    }
	    }
		executor.shutdown();*/
		IntStream.range(0, n).parallel().forEach(i->{
			int j;
			for(j=i+1;j<n;j++)
			{
				double r = instanceList.euclideanDistance(i,j);
				set(i, j, r);
				set(j, i, r);
			}
		});
	}
	
	/**Method : set the value in Matrix's case
	 * @param row
	 * @param col
	 * @param value
	 */
	public void set(int row, int col, Double value) {
		matrix[row][col] = value;
	}
	
	/**
	 * Method get
	 * @param row
	 * @param col
	 * @return the value on Matrix's case
	 */
	public double get(int row, int col) {
		return matrix[row] [col];
	}
	
	/**
	 * Method getSize
	 * @return Size of DistanceMatrix
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Method exportToCsv : export DistanceMatrix in Csv file
	 * @param fileName
	 * 
	 */
	public void exportToCsv(String fileName) {
		if(size == 0) 
			return;
		List<List<String>> list = new ArrayList<List<String>>();
		for (double[] array : matrix) {
			List<String> row = new ArrayList<>();
	        for(double value : array) {
	        	row.add(Double.toString(value));
	        }
	        list.add(row);
	    }
		IOCsv.exportCsv(fileName, list);
	}
	
}
