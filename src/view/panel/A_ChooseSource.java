package view.panel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;


@SuppressWarnings("serial")
public class A_ChooseSource extends StepsPanelBuilder {
	public JTextField filePathTextField;

	public A_ChooseSource(NavigationBar navBar, Component parent) {
		super(navBar, parent);
		title.setText("Choix du fichier source");
		
		JLabel lblFichierSource = new JLabel("Fichier source :");
		lblFichierSource.setBounds(20, 58, 105, 14);
		add(lblFichierSource);
		
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
				if(filePathTextField.getText() != "") {
					Path path = Paths.get(filePathTextField.getText());
					if (Files.exists(path))
						A_ChooseSource.this.navBar.setBarView(false, true);
					else
						A_ChooseSource.this.navBar.setBarView(false, false);
						
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
	}

	@Override
	public void setPanel() {
		super.setPanel();
		navBar.setBarView(false, false);
	}
	
	@Override
	public boolean isValidated() {
		return filePathTextField.getText() == "";
	}
}
