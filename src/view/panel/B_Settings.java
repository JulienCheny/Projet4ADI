package view.panel;

import java.awt.Component;
import java.text.NumberFormat;

import javax.swing.JLabel;

import controller.AlgoRunner;

import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class B_Settings extends StepsPanelBuilder implements StepsPanelInterface {

	public JFormattedTextField indexColumnFormattedTextField;
	private JTextField separatorTextField;
	

	/**
	 * Constructor B_Settings : set the window for settings
	 * @param navBar
	 * @param parent
	 * @param algoRunner
	 */
	public B_Settings(NavigationBar navBar, Component parent, AlgoRunner algoRunner) {
		super(navBar, parent, algoRunner);
		title.setText("Options du constructeur");
		
		JLabel lblIndexColumn = new JLabel("Index de la colonne de la classe :");
		lblIndexColumn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIndexColumn.setBounds(10, 88, 193, 14);
		add(lblIndexColumn);
		
		indexColumnFormattedTextField = new JFormattedTextField( NumberFormat.getNumberInstance());
		indexColumnFormattedTextField.setBounds(210, 85, 34, 20);
		indexColumnFormattedTextField.setText("1");
		add(indexColumnFormattedTextField);
		
		JLabel lblAttributsSeparator = new JLabel("S\u00E9parateur des attributs :");
		lblAttributsSeparator.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAttributsSeparator.setBounds(10, 113, 193, 14);
		add(lblAttributsSeparator);
		
		separatorTextField = new JTextField();
		separatorTextField.setText(",");
		separatorTextField.setBounds(210, 110, 34, 20);
		add(separatorTextField);
		separatorTextField.setColumns(10);
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
		algoRunner.setSeparator(separatorTextField.getText());
	}
}
