package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
		double result = 0d;
		int i;
		Instance instance1 = iList[indexInstance1];
		Instance instance2 = iList[indexInstance2];
		if(indexInstance1 == 2 && indexInstance2 ==12)
			System.out.println("break");
		for(i=0; i<instance1.getAttributCount(); i++)
		{
			result+=Math.pow(instance1.getValueAt(i) - instance2.getValueAt(i),2);
		}
		double sqrtResult = round(Math.sqrt(result),16);
		return sqrtResult;
	}
	
	public int size() {
		return size;
	}
	
	public ArrayList<String> getClasses() {
		return classesList;
	}
	
	public double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
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
