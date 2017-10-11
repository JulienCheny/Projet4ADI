package model;

public class InstanceOperations {

	public static double euclideanDistance(Instance instance1, Instance instance2)
	{
		int i;
		double result = 0;
		for(i=0; i<instance1.getAttributCount(); i++)
		{
			result+=Math.pow(instance1.getValueAt(i)-instance2.getValueAt(i),2);
		}
		double sqrtResult = Math.sqrt(result);
		
		return sqrtResult;
	}
}
