package model;

import java.util.stream.IntStream;

public class DistanceMatrix {
	private int size = 0;
	private Double [] [] matrix;
	
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
	
	public void createDistanceMatrixMulticore(InstanceList instanceList) {
		int n = instanceList.size();
		size = n;
		matrix = new Double[n][n];
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
	
	public void set(int row, int col, Double value) {
		matrix[row][col] = value;
	}
	
	public Double get(int row, int col) {
		return matrix[row] [col];
	}
}
