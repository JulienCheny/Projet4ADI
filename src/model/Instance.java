package model;

import java.util.Arrays;
import java.util.List;

public class Instance {
	private List<Double> values;
	
	public Instance (List<Double> values)
	{
		super();
		this.values = values;
	}
	
	public Instance(Double [] values) {
		super();
		this.values = Arrays.asList(values);
	}
	
	public Double getValueAt(int index) {
		return values.get(index);
	}
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
