package view.panel;

import java.awt.Component;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.AlgoRunner;

import javax.swing.JFormattedTextField;

@SuppressWarnings("serial")
public class B_Settings extends StepsPanelBuilder implements StepsPanelInterface {

	public JFormattedTextField indexColumnFormattedTextField;
	
	public B_Settings(NavigationBar navBar, Component parent, AlgoRunner algoRunner) {
		super(navBar, parent, algoRunner);
		title.setText("Options du constructeur");
		
		JLabel lblIndexColumn = new JLabel("Index de la colonne de la classe :");
		lblIndexColumn.setBounds(10, 88, 193, 14);
		add(lblIndexColumn);
		
		indexColumnFormattedTextField = new JFormattedTextField( NumberFormat.getNumberInstance());
		indexColumnFormattedTextField.setBounds(210, 85, 34, 20);
		indexColumnFormattedTextField.setText("0");
		add(indexColumnFormattedTextField);
		this.setVisible(false);
	}

	@Override
	public void setPanel() {
		super.setPanel();
		navBar.setBarView(true, true);
	}
	
	@Override
	public void forward() {
		algoRunner.setClassCol(Integer.parseInt(indexColumnFormattedTextField.getText()));
	}
}
