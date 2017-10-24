package model;


public class Matrix {
	
	//ArrayList<ArrayList<Double>> matrix;
	Object [] [] matrix;
	int size =  0;
	
	public Matrix(int m,int n)
	{
		size = m;
		matrix = new Object[m][n];
		
	}
	
	public Matrix(int n)
	{
		size = n;
		matrix = new Object[n][n];
		/*matrix= new ArrayList<ArrayList<Double>>();
		for(i=0;i<nbNodes;i++)
		{
			ArrayList<Double> row= new ArrayList<Double>(Collections.nCopies(nbNodes, 0d));
			matrix.add(row);
		}*/
		
	}
	
	
	
	public Object get(int row, int col) 
	{
		return matrix[row][col];
		//return matrix.get(row).get(col);
	}



	public void set(int row, int col, Object obj) 
	{
		matrix[row][col] = obj;
		//matrix.get(row).set(col, value);
	}

	public Object[][] getAll() 
	{
		return matrix;
		//return matrix.get(row).get(col);
	}



	public void setAll(Object [][]matrix) 
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
