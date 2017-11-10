package model;

import java.util.Arrays;
import java.util.List;

/**
 * @author François and Julien
 *
 *
 */
public class Instance {
	private List<Double> values;
	
	
	/**
	 * Constructor with one argument : initialize Instance
	 * @param values : List<Double>
	 */
	public Instance (List<Double> values)
	{
		
		super();
		this.values = values;
	}
	
	/**
	 * Constructor with one argument : initialize Instance 
	 * @param values : Double []
	 */
	public Instance(Double [] values) {
		super();
		this.values = Arrays.asList(values);
	}
	
	/**
	 * Method getValueAt
	 * @param index
	 * @return the value at the index
	 */
	public Double getValueAt(int index) {
		return values.get(index);
	}
	/**
	 * 
	 * Method : getAttributCount
	 * @return the number of attribut
	 */
	public int getAttributCount() {
		return values.size();
	}

	public String toString() {
		String str = "";
		for(Double value : values)
			str += value + ", ";
		return str + "\n";
	}
}
