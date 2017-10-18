package model;

import java.util.ArrayList;
import java.util.Collections;

public class Matrix {
	
	//ArrayList<ArrayList<Double>> matrix;
	Double [] [] matrix;
	int size =  0;
	
	public Matrix(int nbNodes)
	{
		size = nbNodes;
		int i;
		matrix = new Double[nbNodes][nbNodes];
		/*matrix= new ArrayList<ArrayList<Double>>();
		for(i=0;i<nbNodes;i++)
		{
			ArrayList<Double> row= new ArrayList<Double>(Collections.nCopies(nbNodes, 0d));
			matrix.add(row);
		}*/
		
	}
	
	
	
	public Double get(int row, int col) 
	{
		return matrix[row][col];
		//return matrix.get(row).get(col);
	}



	public void set(int row, int col, Double value) 
	{
		matrix[row][col] = value;
		//matrix.get(row).set(col, value);
	}

	public Double[][] getAll() 
	{
		return matrix;
		//return matrix.get(row).get(col);
	}



	public void setAll(Double [][]matrix) 
	{
		this.matrix = matrix;
		//matrix.get(row).set(col, value);
	}

	/*public String toString()
	{
		String str = "";
		int i;
		for(i=0;i<size;i++)
		{
			str += matrix.get(i) + "\n";
		}
		return str;
	}*/
}
