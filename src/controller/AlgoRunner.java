package controller;

import java.io.File;
import java.util.List;
import java.util.Observer;

import model.Graph;
import model.InstanceList;
import model.universals.IOCsv;

public class AlgoRunner {
	private String srcPath;
	private String destFileName;
	private int classCol;
	private String separator;
	private Graph graph = new Graph();
	
	/**
	 * Method getSrcPath
	 * @return the source path
	 */
	public String getSrcPath() {
		return srcPath;
	}

	/**
	 * method setSrcPath : set the source path
	 * @param srcPath
	 */
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}

	/**
	 * Method getDestFileName
	 * @return the destination file name
	 */
	public String getDestFileName() {
		return destFileName;
	}

	/**
	 * Method setDestFileName : set the destination file name
	 * @param fileName
	 */
	public void setDestFileName(String fileName) {
		this.destFileName = fileName;
	}

	/**
	 * Method getClassCol
	 * @return the column of the class name of instances
	 */
	public int getClassCol() {
		return classCol;
	}

	/**
	 * Method set ClassCol : set the column of the class name of instances
	 * @param classCol
	 */
	public void setClassCol(int classCol) {
		this.classCol = classCol;
	}
	
	/**
	 * Method getSeparator
	 * @return the separator of the attributs in the file
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 * Method set Separator : set separator of the attributs in the file
	 * @param separator
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	/**
	 * Method addObserver : add an observer on the graph construction to see if it works
	 * @param obs
	 */
	public void addObserver(Observer obs) {
		graph.addObserver(obs);
	}

	/**
	 * Method runAlgo : run the algorithm
	 */
	public void runAlgo() {
		File srcFile = new File(srcPath);
		String destPath = srcFile.getParent() + "/";
		String arcsListPath = destPath + destFileName + "/arcsList.csv";
		String nodesListPath = destPath + destFileName + "/nodesList.csv";
		String accessLvlListPath = destPath + destFileName + "/accessLvlList.csv";
		String distanceMatrixPath = destPath + destFileName + "/distancesMatrix.csv";
		
		(new File(destPath + destFileName)).mkdirs();
		new File(arcsListPath);
		new File(nodesListPath);
		new File(accessLvlListPath);
		new File(distanceMatrixPath);
		
		InstanceList i2= new InstanceList (IOCsv.importCsv(srcFile, separator), classCol-1); //interface pour afficher le graphe , temps de progression, temps de calcul
		graph.createGraphMonocore(i2);
		
		List<Integer> simNodes = graph.getSimilarNodes();
		System.out.println(simNodes.toString());
		
		graph.exportToCsv(arcsListPath, nodesListPath, distanceMatrixPath);
		try {
			graph.calculateAccessLevel(accessLvlListPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
