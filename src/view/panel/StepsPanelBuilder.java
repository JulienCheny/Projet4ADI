package view.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;

public class StepsPanelBuilder extends JPanel implements StepsPanelInterface {
	protected Component parent;
	protected NavigationBar navBar;
	protected JLabel title;
	public StepsPanelBuilder(NavigationBar navBar, Component parent) {
		this.parent = parent;
		this.navBar = navBar;
		this.setSize(300, 180);
		this.setLayout(null);
		
		title = new JLabel("");
		title.setFont(new Font("Tahoma", Font.PLAIN, 15));
		title.setBounds(10, 11, 280, 14);
		this.add(title);
	}

	@Override
	public void setPanel() {
		this.setVisible(true);
	}

	@Override
	public boolean isValidated() {
		return false;
	}
}
