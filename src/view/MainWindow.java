package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.panel.A_ChooseSource;
import view.panel.B_Settings;
import view.panel.C_ChooseDestination;
import view.panel.D_Progression;
import view.panel.E_End;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	public Component parent = this;
	public String filePath = null;
	public ListIterator<JPanel> steps;
	
	public MainWindow() {
		this.setTitle("Generateur de graphes");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 200);
		
		
		this.setVisible(true);
	}
	
	private void init_Display() {
		steps.add(new A_ChooseSource());
		steps.add(new B_Settings());
		steps.add(new C_ChooseDestination());
		steps.add(new D_Progression());
		steps.add(new E_End());
	}
}
