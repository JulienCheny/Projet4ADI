package view.panel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.AlgoRunner;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Font;


@SuppressWarnings("serial")
public class A_ChooseSource extends StepsPanelBuilder implements StepsPanelInterface {
	public JTextField filePathTextField;

	public A_ChooseSource(NavigationBar navBar, Component parent, AlgoRunner algoRunner) {
		super(navBar, parent, algoRunner);
		title.setText("Choix du fichier source");
		
		JLabel lblFichierSource = new JLabel("Fichier source :");
		lblFichierSource.setBounds(20, 58, 105, 14);
		add(lblFichierSource);
		
		JLabel lblValidTestMessage = new JLabel("\u25B2 Chemin de fichier invalide");
		lblValidTestMessage.setForeground(Color.RED);
		lblValidTestMessage.setBounds(20, 104, 188, 14);
		add(lblValidTestMessage);
		
		filePathTextField = new JTextField();
		filePathTextField.setBounds(20, 83, 211, 20);
		add(filePathTextField);
		filePathTextField.setColumns(10);
		filePathTextField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				warn();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				warn();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				warn();
				
			}

			private void warn() {
				String filePath = filePathTextField.getText();
				if(filePath != "") {
					try {
					    BufferedReader buffread = new BufferedReader (new FileReader(filePath));
					    lblValidTestMessage.setForeground(Color.GREEN);
					    lblValidTestMessage.setText("\u25B2 Chemin de fichier valide");
					    A_ChooseSource.this.navBar.setBarView(false, true);
					} catch (IOException ex) {
						lblValidTestMessage.setForeground(Color.RED);
					    lblValidTestMessage.setText("\u25B2 Chemin de fichier invalide");
						A_ChooseSource.this.navBar.setBarView(false, false);
					}
						
				}
				 
				
			}
			
		});
		
		JButton searchFile = new JButton("...");
		searchFile.setBounds(241, 82, 32, 23);
		searchFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "csv", "txt");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(parent);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	filePathTextField.setText(chooser.getSelectedFile().getAbsolutePath());
			       System.out.println("You chose to open this file: " + filePathTextField.getText());
			    }
				
			}
		});
		add(searchFile);
		
		JLabel lblInfos = new JLabel("Entrez un fichier texte. Le s\u00E9parateur est la virgule");
		lblInfos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblInfos.setBounds(20, 129, 270, 14);
		add(lblInfos);
	}

	@Override
	public void setPanel() {
		super.setPanel();
		navBar.setBarView(false, false);
	}
	
	@Override
	public boolean isValidated() {
		return filePathTextField.getText() != "";
	}
	
	@Override
	public void forward() {
		algoRunner.setSrcPath(filePathTextField.getText());
	}
}
