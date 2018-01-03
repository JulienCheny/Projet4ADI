package view.panel;

import java.awt.Component;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JProgressBar;

import controller.AlgoRunner;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class D_Progression extends StepsPanelBuilder implements StepsPanelInterface, Observer {

	private JProgressBar progressBar;
	private JLabel lblStat = new JLabel("");
	
	/**
	 * Constructor D_Progression : set the window of progression
	 * @param navBar
	 * @param parent
	 * @param algoRunner
	 */
	public D_Progression(NavigationBar navBar, Component parent, AlgoRunner algoRunner) {
		super(navBar, parent, algoRunner);
		title.setText("Construction du graphe");
		
		progressBar = new JProgressBar();
		progressBar.setBounds(10, 131, 280, 38);
		add(progressBar);
		
		lblStat.setBounds(10, 112, 280, 14);
		add(lblStat);
	}

	@Override
	public void setPanel() {
		super.setPanel();
		navBar.setBarView(false, false);
	}
	
	@Override
	public void forward() {
		
	}
	
	public void setProgressBar(int percent) {
		progressBar.setValue(percent);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		int stat = (int) arg1;
		progressBar.setValue(stat*10);
		switch(stat) {
		case 0:lblStat.setText("Initialisation") ;break;
		case 1:lblStat.setText("Calcul de la matrice de distances") ;break;
		case 2:lblStat.setText("Calcul des arcs du graphe 1/8") ;break;
		case 3:lblStat.setText("Calcul des arcs du graphe 2/8") ;break;
		case 4:lblStat.setText("Calcul des arcs du graphe 3/8") ;break;
		case 5:lblStat.setText("Calcul des arcs du graphe 4/8") ;break;
		case 6:lblStat.setText("Calcul des arcs du graphe 5/8") ;break;
		case 7:lblStat.setText("Calcul des arcs du graphe 6/8") ;break;
		case 8:lblStat.setText("Calcul des arcs du graphe 7/8") ;break;
		case 9:lblStat.setText("Calcul des arcs du graphe 8/8") ;break;
		case 10:lblStat.setText("Terminé !"); navBar.setBarView(true, true) ;break;
		}
	}
}
