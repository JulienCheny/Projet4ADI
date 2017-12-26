package view.panel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.AlgoRunner;

import javax.swing.JButton;
import javax.swing.JFileChooser;


@SuppressWarnings("serial")
public class C_ChooseDestination extends StepsPanelBuilder implements StepsPanelInterface {
	public JTextField fileNameTextField;

	public C_ChooseDestination(NavigationBar navBar, Component parent, AlgoRunner algoRunner) {
		super(navBar, parent, algoRunner);
		title.setText("Choix du fichier de destination");
		
		JLabel lblFolderName = new JLabel("Nom du repertoire de destination :");
		lblFolderName.setBounds(10, 58, 210, 14);
		add(lblFolderName);
		
		fileNameTextField = new JTextField();
		fileNameTextField.setBounds(10, 81, 210, 20);
		add(fileNameTextField);
		fileNameTextField.setColumns(10);
		
		this.setVisible(false);
	}

	@Override
	public void setPanel() {
		super.setPanel();
		navBar.setBarView(true, true);
	}
	
	@Override
	public void forward() {
		algoRunner.setDestFileName(fileNameTextField.getText());
		algoRunner.runAlgo();
	}
}
