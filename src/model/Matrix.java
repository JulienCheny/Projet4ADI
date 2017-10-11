package model;

import java.util.ArrayList;
import java.util.Collections;

public class Matrix {
	
	ArrayList<ArrayList<Double>> matrix;
	
	public Matrix(int nbNodes)
	{
		int i;
		matrix= new ArrayList<ArrayList<Double>>();
		for(i=0;i<nbNodes;i++)
		{
			ArrayList<Double> row= new ArrayList<Double>(Collections.nCopies(nbNodes, 0d));
			matrix.add(row);
		}
		
	}
	
	
	
	public Double get(int row, int col) 
	{
		return matrix.get(row).get(col);
	}



	public void set(int row, int col, Double value) 
	{
		matrix.get(row).set(col, value);
	}



	public String toString()
	{
		String str = "";
		int i;
		for(i=0;i<matrix.size();i++)
		{
			str += matrix.get(i) + "\n";
		}
		return str;
	}
}
