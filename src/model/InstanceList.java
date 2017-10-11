package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InstanceList {
	private ArrayList<Instance> iList = new ArrayList<Instance>();
	private int attributCount;
	
	public InstanceList(ArrayList<List<String>> instances)
	{
		attributCount = instances.get(0).size();
		for(List<String> inst : instances)
		{
			ArrayList<Double> values = new ArrayList<Double>();
			for (String str : inst) {
				values.add(Double.parseDouble(str));
			}
			Instance instance = new Instance(values);
			iList.add(instance);
		}
	}
	public InstanceList(int attributCount) {
		this.attributCount = attributCount;
	}
	
	public void addInstance(Instance instance) {
		if(instance.getAttributCount() != attributCount)
			throw new NullPointerException();
		iList.add(instance);
	}
	public void removeInstanceOn(int index) {
		iList.remove(index);
	}
	
	public Matrix calculateAllEuclideanDistances() {
		int i, j, n = iList.size();
		Matrix matrix = new Matrix(n);
		for(i=0;i<n;i++)
		{
			for(j=i+1;j<n;j++)
			{
				Double r = InstanceOperations.euclideanDistance(iList.get(i), iList.get(j));
				matrix.set(i, j, r);
				matrix.set(j, i, r);
			}
		}
		return matrix;
	}
	
	public ArrayList<List<Double>> calculateArcs() {
		Matrix matrix = calculateAllEuclideanDistances();
		return calculateArcs(matrix);
	}
	
	public ArrayList<List<Double>> calculateArcs(Matrix matrix)
	{
		ArrayList<List<Double>> table = new ArrayList<List<Double>>();
		int i,j,k;
		int n=iList.size();
		boolean canConnect;
		double r;
		
		for(i=0;i<n;i++)
		{
			for(j=i+1;j<n;j++)
			{
				//System.out.println("i = " + i + "\nj = " + j);
				canConnect = true;
				r=matrix.get(i, j);
				for(k=0;k<n;k++)
				{
					if(k!=i && k!=j)
					{
						if(matrix.get(i, k)<=r && matrix.get(j, k)<=r )
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
					table.add(connection);
				}
			}
		}
		return table;
	}
	
	
	
	public String toString()
	{
		String str = "";
		int i;
		for(Instance inst : iList) {
			str += inst + "\n";
		}
		return str;
	}
}
