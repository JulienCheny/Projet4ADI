package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * @author Francois and Julien
 *
 */

public class DistanceMatrix {
	private int size = 0;
	private Double [] [] matrix;
	/*private static int i;
	private static int j ;*/
	
	/**
	 * Method createDistanceMatrixMonocore : create the matrix of Distance without parralelism
	 * @param instanceList
	 */
	public void createDistanceMatrixMonocore(InstanceList instanceList) {
		int i, j, n = instanceList.size();
		size = n;
		matrix = new Double[n][n];
		for(i=0;i<n;i++)
		{
			for(j=i+1;j<n;j++)
			{
				Double r = instanceList.euclideanDistance(i,j);
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
		matrix = new Double[n][n];
		
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
				Double r = instanceList.euclideanDistance(i,j);
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
	public Double get(int row, int col) {
		return matrix[row] [col];
	}
	
	
	/**
	 * Method getI
	 * @return i 
	 */
	/*public static int getI() {
		return i;
	}*/

	/**
	 * 
	 * Method setI : set i value
	 * @param i
	 */
	/*public static void setI(int i) {
		DistanceMatrix.i = i;
	}*/

	/**
	 * Method getJ
	 * @return j 
	 */
	/*public static int getJ() {
		return j;
	}*/

	/**
	 * Method setJ : set j value
	 * @param j
	 */
	/*public static void setJ(int j) {
		DistanceMatrix.j = j;
	}*/
}
