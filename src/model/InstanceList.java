package model;

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
	private ArrayList<String> classesList;
	private int indexInstance1;
	private int indexInstance2;
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
	/*public InstanceList(InstanceList i1, int indexInst1, int indexInst2)
	{
		iList=i1.getiList();
		attributCount=i1.getAttributCount();
		size=i1.getSize();
		classesList=i1.getClassesList();
		indexInstance1=indexInst1;
		indexInstance1=indexInst2;
		
	}*/
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
	
	/**
	 * Constructor with 1 argument : Initialize InstanceList with a empty list
	 * @param attributCount
	 */
	public InstanceList(int attributCount) {
		this.attributCount = attributCount;
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
	public ArrayList<String> getClasses() {
		return classesList;
	}
	
	/**
	 * Method setClassesList : set instances' classes
	 * @param classesList
	 */
	public void setClassesList(ArrayList<String> classesList) {
		this.classesList = classesList;
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
