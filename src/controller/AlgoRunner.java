package controller;

import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.xml.bind.ParseConversionEvent;

import model.Graph;
import model.InstanceList;
import model.universals.IOCsv;
import view.MainWindow;
import view.panel.D_Progression;

public class AlgoRunner {
	private String srcPath;
	private String destFileName;
	private int classCol;
	private Graph graph = new Graph();
	
	public String getSrcPath() {
		return srcPath;
	}

	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}

	public String getDestFileName() {
		return destFileName;
	}

	public void setDestFileName(String fileName) {
		this.destFileName = fileName;
	}

	public int getClassCol() {
		return classCol;
	}

	public void setClassCol(int classCol) {
		this.classCol = classCol;
	}
	
	public void addObserver(Observer obs) {
		graph.addObserver(obs);
	}

	public void runAlgo() {
		File srcFile = new File(srcPath);
		String destPath = srcFile.getParent() + "/";
		String arcsListPath = destPath + destFileName + "/arcsList.csv";
		String nodesListPath = destPath + destFileName + "/nodesList.csv";
		
		(new File(destPath + destFileName)).mkdirs();
		new File(arcsListPath);
		new File(nodesListPath);
		
		InstanceList i2= new InstanceList (IOCsv.importCsv(srcFile), classCol); //interface pour afficher le graphe , temps de progression, temps de calcul
		graph.createGraphMonocore(i2);
		
		List<Integer> simNodes = graph.getSimilarNodes();
		System.out.println(simNodes.toString());
		graph.exportToCsv(arcsListPath, nodesListPath);
	}
}
