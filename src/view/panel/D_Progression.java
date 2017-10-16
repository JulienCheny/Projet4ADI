package view.panel;

import java.awt.Component;
import javax.swing.JProgressBar;


@SuppressWarnings("serial")
public class D_Progression extends StepsPanelBuilder {

	private JProgressBar progressBar;
	
	public D_Progression(NavigationBar navBar, Component parent) {
		super(navBar, parent);
		title.setText("Construction du graphe");
		
		progressBar = new JProgressBar();
		progressBar.setBounds(10, 131, 280, 38);
		add(progressBar);
	}

	@Override
	public void setPanel() {
		super.setPanel();
		navBar.setBarView(false, false);
	}
	
	public void setProgressBar(int percent) {
		progressBar.setValue(percent);
	}
}
