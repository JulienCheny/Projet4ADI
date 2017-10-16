package view.panel;

import java.awt.Component;


@SuppressWarnings("serial")
public class E_End extends StepsPanelBuilder {

	public E_End(NavigationBar navBar, Component parent) {
		super(navBar, parent);
		title.setText("Construction terminé");
	}

	@Override
	public void setPanel() {
		super.setPanel();
		navBar.setBarView(false, false);
		navBar.close.setText("Terminer");
	}

}
