package view.panel;

import java.awt.Component;

import controller.AlgoRunner;


@SuppressWarnings("serial")
public class E_End extends StepsPanelBuilder implements StepsPanelInterface {

	/**
	 * Constructor E_End : set the window of ending
	 * @param navBar
	 * @param parent
	 * @param algoRunner
	 */
	public E_End(NavigationBar navBar, Component parent, AlgoRunner algoRunner) {
		super(navBar, parent, algoRunner);
		title.setText("Construction terminé");
	}

	@Override
	public void setPanel() {
		super.setPanel();
		navBar.setBarView(false, false);
		navBar.close.setText("Terminer");
	}

	@Override
	public void forward() {
		
	}
}
