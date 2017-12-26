package launcher;

import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import controller.AlgoRunner;
import model.Instance;
import model.InstanceList;
import model.universals.IOCsv;
import view.MainWindow;
import model.Chrono;
import model.Graph;

public class Tester {
	public static void main(String[] args) {
		
		new MainWindow();
		
		
		
		/*
		Double[] array = {1.1, 2.2 , 3.3};
		Double[] array2 = {4.7, 8.3 , 10.1};
		Double[] array3 = {6.0, 2.8 , 9.7};
		
		Instance i1 = new Instance(array);
		InstanceList iList= new InstanceList(3);
		
		iList.addInstance(i1);
		iList.addInstance(new Instance(array2));
		iList.addInstance(new Instance(array3))*/
		
		/*try {
			graph.createGraphMocore(i2);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		/*try {
			graph.calculateAccessLevel();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//output csv (un avec classe + noeud l'autre cf IOGraph)
				//paralleliser
				//Mini-rapport avec diagramme de classe
		/*
		Chrono ch = new Chrono();
		InstanceList i2= new InstanceList (IOCsv.importCsv(new File("src/iris.data.txt")), 0); //interface pour afficher le graphe , temps de progression, temps de calcul
		ch.start();
		Graph graph = new Graph();
		graph.createGraphMonocore(i2);
		
		ch.stop();
		System.out.println("Durée total calcul distance et arcs : " + ch.getTime());
		
		List<Integer> simNodes = graph.getSimilarNodes();
		System.out.println(simNodes.toString());
		
		
		graph.exportToCsv("src/arcsList.csv", "src/nodesList.csv");*/
		
		
		
		
		
	}
}
