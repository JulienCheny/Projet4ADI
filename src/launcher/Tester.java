package launcher;

import java.io.File;

import java.io.IOException;

import model.Instance;
import model.InstanceList;
import model.universals.IOCsv;
import model.Chrono;
import model.Graph;

public class Tester {
	public static void main(String[] args) {
		
		Chrono ch = new Chrono();
		
		Double[] array = {1.1, 2.2 , 3.3};
		Double[] array2 = {4.7, 8.3 , 10.1};
		Double[] array3 = {6.0, 2.8 , 9.7};
		
		Instance i1 = new Instance(array);
		//InstanceList iList= new InstanceList(3);
		
		//iList.addInstance(i1);
		//iList.addInstance(new Instance(array2));
		//iList.addInstance(new Instance(array3));
		
		InstanceList i2 = null;
		try 
		{
			i2= new InstanceList (IOCsv.importCsv(new File("src/iris.data.txt"))); //interface pour afficher le graphe , temps de progression, temps de calcul
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		ch.start();
		Graph graph = new Graph();
		graph.createGraphMonocore(i2);
		ch.stop();
		System.out.println("Durée total calcul distance et arcs : " + ch.getTime());
		try {
			graph.exportToCsv("src/arcsList.csv", "src/nodesList.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//new MainWindow();
		
		//output csv (un avec classe + noeud l'autre cf IOGraph)
		//paralleliser
		//Mini-rapport avec diagramme de classe
		
	}
}
