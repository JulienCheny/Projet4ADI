package view;

import java.awt.BorderLayout;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.AlgoRunner;
import view.panel.A_ChooseSource;
import view.panel.B_Settings;
import view.panel.C_ChooseDestination;
import view.panel.D_Progression;
import view.panel.E_End;
import view.panel.NavigationBar;
import view.panel.StepsPanelBuilder;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	public Component parent = this;
	public String filePath = null;
	public List<StepsPanelBuilder> list =  new ArrayList<StepsPanelBuilder>() ;
	public ListIterator<StepsPanelBuilder> steps;
	public NavigationBar navBar;
	public StepsPanelBuilder currentPanel;
	
	public A_ChooseSource chooseSource;
	public B_Settings settings;
	public C_ChooseDestination chooseDestination;
	public D_Progression progression;
	public E_End end;
	
	private AlgoRunner algoRunner;
	
	/**
	 * Constructor MainWindow : set the main window of the application
	 * 
	 */
	public MainWindow() {
		this.setTitle("Generateur de graphes");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 280);
		this.setResizable(false);
		initDisplay();
		
		this.setVisible(true);
	}
	
	/**
	 * Method initDisplay : initialize the display
	 * 
	 */
	private void initDisplay() {
		navBar = new NavigationBar(parent);
		algoRunner = new AlgoRunner();
		chooseSource = new A_ChooseSource(navBar, this, algoRunner);
		settings = new B_Settings(navBar, this, algoRunner);
		chooseDestination = new C_ChooseDestination(navBar, this, algoRunner);
		progression = new D_Progression(navBar, this, algoRunner);
		algoRunner.addObserver(progression);
		end = new E_End(navBar, this, algoRunner);
		
		list.add(chooseSource);
		list.add(settings);
		list.add(chooseDestination);
		list.add(progression);
		list.add(end);
		
		steps = list.listIterator();
		currentPanel = steps.next();
		for (JPanel panel : list) {
			getContentPane().add(panel, BorderLayout.CENTER);
		}
		getContentPane().add(navBar,BorderLayout.SOUTH);
		currentPanel.setPanel();
		
		progression.setProgressBar(32);
	}
	
	/**
	 * Method forwardPanel : initialize the forward Panel
	 * 
	 */
	public void forwardPanel() {
		StepsPanelBuilder futurPanel = steps.next();
		currentPanel.setVisible(false);
		if(futurPanel == currentPanel)
			futurPanel = steps.next();
		futurPanel.setPanel();
		currentPanel.forward();
		currentPanel = futurPanel;
		System.out.println(currentPanel.getClass().getName());
	}
	
	/**
	 * Method backPanel : initialize the back panel
	 * 
	 */
	public void backPanel() {
		StepsPanelBuilder futurPanel = steps.previous();
		currentPanel.setVisible(false);
		if(futurPanel == currentPanel)
			futurPanel = steps.previous();
		currentPanel = futurPanel;
		currentPanel.setPanel();
		System.out.println(currentPanel.getClass().getName());
	}
	
	/**
	 * Method getSourceFilePath
	 * @return the source file path selected on the "A_ChooseSource" window
	 */
	public String getSourceFilePath() {
		return chooseSource.filePathTextField.getText();
	}
	
	/**
	 * Method getColumnNumber
	 * @return the value of the index column on the "B_Settings" window
	 */
	public int getColumnNumber() {
		return (int) settings.indexColumnFormattedTextField.getValue();
	}
	
	/**
	 * Method close : close the main window
	 */
	public void close() {
		this.dispose();
	}
}
