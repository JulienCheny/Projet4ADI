package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
	
	public double euclideanDistance(int indexInstance1, int indexInstance2)
	{
		double result = 0;
		int i;
		Instance instance1 = iList.get(indexInstance1);
		Instance instance2 = iList.get(indexInstance2);
		for(i=0; i<instance1.getAttributCount(); i++)
		{
			result+=Math.pow(instance1.getValueAt(i)-instance2.getValueAt(i),2);
		}
		double sqrtResult = Math.sqrt(result);
		
		return sqrtResult;
	}
	
	public Matrix calculateAllEuclideanDistancesUniCore() {
		int i, j, n = iList.size();
		Matrix matrix = new Matrix(n);
		Double[][] mList = matrix.getAll();
		for(i=0;i<n;i++)
		{
			for(j=i+1;j<n;j++)
			{
				Double r = euclideanDistance(i,j);
				/*matrix.set(i, j, r);
				matrix.set(j, i, r);*/
				mList[i][j] = r;
				mList[j][i] = r;
			}
		}
		matrix.setAll(mList);
		return matrix;
	}
	
	public Matrix calculateAllEuclideanDistances() {
		int n = iList.size();
		Matrix matrix = new Matrix(n);
		Double[][] mList = matrix.getAll();
		IntStream.range(0, n).parallel().forEach(i->{
			int j;
			for(j=i+1;j<n;j++)
			{
				Double r = euclideanDistance(i,j);
				/*matrix.set(i, j, r);
				matrix.set(j, i, r);*/
				mList[i][j] = r;
				mList[j][i] = r;
			}
		});
		matrix.setAll(mList);
		return matrix;
	}
	
	public ArrayList<List<Double>> calculateArcs() {
		Chrono ch = new Chrono();
		ch.start();
		Matrix matrix = calculateAllEuclideanDistances();
		ch.stop();
		System.out.println(ch.getTime());
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
		for(Instance inst : iList) {
			str += inst + "\n";
		}
		return str;
	}
}
