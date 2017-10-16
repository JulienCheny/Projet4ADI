package view.panel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		navBar.setBarView(false, true);
	}
}
