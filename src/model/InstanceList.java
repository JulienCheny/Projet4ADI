package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Francois and Julien
 *
 */

public class InstanceList implements Callable<Double> {
	private Instance [] iList;
	private int attributCount;
	private int size = 0;
	private List<String> classesList;
	
	/**
	 * Method getiList
	 * @return the list of instances
	 */
	public Instance[] getiList() {
		return iList;
	}
	/**
	 * Method setiList : set the list of instances
	 * @param iList
	 */
	public void setiList(Instance[] iList) {
		this.iList = iList;
	}
	/**
	 * Method getAttributCount
	 * @return the number of attributs in the instance
	 */
	public int getAttributCount() {
		return attributCount;
	}
	/**
	 * Method setAttributCount : set the number of attributs in the instance
	 * @param attributCount
	 */
	public void setAttributCount(int attributCount) {
		this.attributCount = attributCount;
	}
	/**
	 * Constructor with 1 argument : Initialize InstanceList with a list of instances
	 * @param instances
	 */
	public InstanceList(List<List<String>> instances, int classIndex)
	{
		int i;
		attributCount = instances.get(0).size();
		List<Instance> iArrayList = new ArrayList<Instance>();
		classesList = new ArrayList<String>();
		for(List<String> inst : instances)
		{
			List<Double> values = new ArrayList<Double>();
			for (i = 0; i < inst.size(); i++) {
				if(i == classIndex)
					classesList.add(inst.get(i));
				else
					values.add(Double.parseDouble(inst.get(i)));
			}
			Instance instance = new Instance(values);
			iArrayList.add(instance);
		}
		size = iArrayList.size();
		iList = iArrayList.toArray(new Instance[iArrayList.size()]);
	}
	
	
	/**
	 * Method addInstance  : add an instance on the list 
	 * @param instance
	 */
	public void addInstance(Instance instance) {
		if(instance.getAttributCount() != attributCount)
			throw new NullPointerException();
		//iList.add(instance);
		size ++;
		java.util.Arrays.copyOf(iList, size);
		iList[size-1] = instance;
	}
	
	/**
	 * Method removeInstanceOn : Remove an instance on the specific index
	 * @param index
	 */
	public void removeInstanceOn(int index) {
		//iList.remove(index);
		size --;
		java.util.Arrays.copyOf(iList, size);
	}
	
	/**
	 * Method euclideanDistance
	 * @param indexInstance1
	 * @param indexInstance2
	 * @return the euclidean distance of 2 instances
	 */
	public double euclideanDistance(int indexInstance1, int indexInstance2)
	{
		double result = 0d;
		int i;
		Instance instance1 = iList[indexInstance1];
		Instance instance2 = iList[indexInstance2];
		for(i=0; i<instance1.getAttributCount(); i++)
		{
			result+=Math.pow(instance1.getValueAt(i) - instance2.getValueAt(i),2);
		}
		//double sqrtResult = round(Math.sqrt(result),16);
		double sqrtResult = Math.sqrt(result);
		return sqrtResult;
	}
	
	/**
	 * Method size
	 * @return the number of instances
	 */
	public int size() {
		return size;
	}
	
	
	/**
	 * Method getClasses
	 * @return the name of classes
	 */
	public List<String> getClasses() {
		return classesList;
	}
	
	/**
	 * Method setClassesList : set instances' classes
	 * @param classesList
	 */
	public void setClassesList(List<String> classesList) {
		this.classesList = classesList;
	}
	
	/**
	 * Method round
	 * @param value
	 * @param places
	 * @return the double value rounded
	 */
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


	@Override
	public Double call() throws Exception {
		// TODO Auto-generated method stub
		return null;
		//return this.euclideanDistance(DistanceMatrix.getI(),DistanceMatrix.getJ());
	}
}
