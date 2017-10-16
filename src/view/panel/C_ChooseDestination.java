package view.panel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;


@SuppressWarnings("serial")
public class C_ChooseDestination extends StepsPanelBuilder {
	public JTextField filePathTextField;
	public JTextField fileNameTextField;

	public C_ChooseDestination(NavigationBar navBar, Component parent) {
		super(navBar, parent);
		title.setText("Choix du fichier de destination");
		
		JLabel lblRepertoireDeDestination = new JLabel("Repertoire de destination :");
		lblRepertoireDeDestination.setBounds(10, 52, 174, 14);
		add(lblRepertoireDeDestination);
		
		JLabel lblNomDuDossier = new JLabel("Nom du dossier :");
		lblNomDuDossier.setBounds(10, 120, 119, 14);
		add(lblNomDuDossier);
		
		filePathTextField = new JTextField();
		filePathTextField.setBounds(10, 77, 210, 20);
		add(filePathTextField);
		filePathTextField.setColumns(10);
		
		fileNameTextField = new JTextField();
		fileNameTextField.setBounds(10, 143, 210, 20);
		add(fileNameTextField);
		fileNameTextField.setColumns(10);
		
		JButton searchFile = new JButton("...");
		searchFile.setBounds(230, 76, 45, 23);
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
		
		this.setVisible(false);
	}

	@Override
	public void setPanel() {
		super.setPanel();
		navBar.setBarView(true, true);
	}
}
