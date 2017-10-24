package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class InstanceList {
	private Instance [] iList;
	private int attributCount;
	private int size = 0;
	private ArrayList<String> classesList;
	/*public InstanceList(ArrayList<List<String>> instances)
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
	}*/
	
	public InstanceList(ArrayList<List<String>> instances)
	{
		int i;
		attributCount = instances.get(0).size();
		ArrayList<Instance> iArrayList = new ArrayList<Instance>();
		classesList = new ArrayList<String>();
		for(List<String> inst : instances)
		{
			ArrayList<Double> values = new ArrayList<Double>();
			classesList.add(inst.get(0));
			for (i = 1; i < inst.size(); i++) {
				values.add(Double.parseDouble(inst.get(i)));
			}
			Instance instance = new Instance(values);
			iArrayList.add(instance);
		}
		size = iArrayList.size();
		iList = iArrayList.toArray(new Instance[iArrayList.size()]);
	}
	
	public InstanceList(int attributCount) {
		this.attributCount = attributCount;
	}
	
	public void addInstance(Instance instance) {
		if(instance.getAttributCount() != attributCount)
			throw new NullPointerException();
		//iList.add(instance);
		size ++;
		java.util.Arrays.copyOf(iList, size);
		iList[size-1] = instance;
	}
	
	public void removeInstanceOn(int index) {
		//iList.remove(index);
		size --;
		java.util.Arrays.copyOf(iList, size);
	}
	
	public double euclideanDistance(int indexInstance1, int indexInstance2)
	{
		double result = 0;
		int i;
		Instance instance1 = iList[indexInstance1];
		Instance instance2 = iList[indexInstance2];
		for(i=0; i<instance1.getAttributCount(); i++)
		{
			result+=Math.pow(instance1.getValueAt(i)-instance2.getValueAt(i),2);
		}
		double sqrtResult = Math.sqrt(result);
		
		return sqrtResult;
	}
	
	public Matrix calculateAllEuclideanDistancesUnicore() {
		int i, j, n = size;
		Matrix matrix = new Matrix(n);
		for(i=0;i<n;i++)
		{
			for(j=i+1;j<n;j++)
			{
				Double r = euclideanDistance(i,j);
				matrix.set(i, j, r);
				matrix.set(j, i, r);
			}
		}
		return matrix;
	}
	
	public Matrix calculateAllEuclideanDistances() {
		int n = size;
		Matrix matrix = new Matrix(n);
		IntStream.range(0, n).parallel().forEach(i->{
			int j;
			
			for(j=i+1;j<n;j++)
			{
				Double r = euclideanDistance(i,j);
				matrix.set(i, j, r);
				matrix.set(j, i, r);
			}
		});
		return matrix;
	}
	
	public ArrayList<List<Double>> calculateArcs() {
		Chrono ch = new Chrono();
		ch.start();
		Matrix matrix = calculateAllEuclideanDistancesUnicore();
		ch.stop();
		System.out.println("Durée calcul distances : " + ch.getTime());
		return calculateArcsUnicore(matrix);
	}
	
	public ArrayList<List<Double>> calculateArcsUnicore(Matrix matrix)
	{
		ArrayList<List<Double>> table = new ArrayList<List<Double>>();
		int i,j,k;
		int n=size;
		boolean canConnect;
		double r;
		
		for(i=0;i<n;i++)
		{
			for(j=i+1;j<n;j++)
			{
				//System.out.println("i = " + i + "\nj = " + j);
				canConnect = true;
				r=(double) matrix.get(i, j);
				for(k=0;k<n;k++)
				{
					if(k!=i && k!=j)
					{
						if((double) matrix.get(i, k)<=r && (double)matrix.get(j, k)<=r )
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
	
	private <T> List<T> twoDArrayToList(T[][] twoDArray) {
	    List<T> list = new ArrayList<T>();
	    for (T[] array : twoDArray) {
	        list.addAll(Arrays.asList(array));
	    }
	    return list;
	}
	
	public ArrayList<List<Double>> calculateArcs(Matrix matrix)
	{
		ArrayList<List<Double>> table = new ArrayList<List<Double>>();
		int n=size;
		IntStream.range(0, n).parallel().forEach(i->{
			ArrayList<List<Double>> localTable = new ArrayList<List<Double>>();
			int j,k;
			for(j=i+1;j<n;j++)
			{
				boolean canConnect;
				double r;
				//System.out.println("i = " + i + "\nj = " + j);
				canConnect = true;
				r=(double) matrix.get(i, j);
				for(k=0;k<n;k++)
				{
					if(k!=i && k!=j)
					{
						if((double)matrix.get(i, k)<=r && (double)matrix.get(j, k)<=r )
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
			table.addAll(localTable);
		});
		return table;
	}
	
	public ArrayList<String> getClasses() {
		return classesList;
	}
	
	public String toString()
	{
		String str = "";
		for(Instance inst : iList) {
			str += inst + "\n";
		}
		return str;
	}
}
