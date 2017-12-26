package view.panel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.MainWindow;

public class NavigationBar extends JPanel {
	public JButton forward;
	public JButton back;
	public JButton close;
	private MainWindow parent;
	
	public NavigationBar(Component parent){
        ButtonPressHandler handler = new ButtonPressHandler();
        this.parent = (MainWindow) parent;
        
        //this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        back = new JButton("Precedent");
        back.addActionListener(handler);
        close = new JButton("Annuler");
        close.addActionListener(handler);
        forward = new JButton("Suivant");
        forward.addActionListener(handler);

        this.add(back);
        this.add(close);
        this.add(forward);
        this.setVisible(true);
    }
	
	public void setBarView(boolean backEnable, boolean forwardEnable) {
		forward.setEnabled(forwardEnable);
		back.setEnabled(backEnable);
	}
	
	private class ButtonPressHandler implements ActionListener {
       public void actionPerformed( ActionEvent event )
       {
           if(event.getSource() == back){
               parent.backPanel();
               System.out.print("You selected back");
           } else if(event.getSource() == forward){
               parent.forwardPanel();
               System.out.print("You selected forward");
           } else if(event.getSource() == close) 
        	   parent.close();
       } // end method actionPerformed
    } // end private inner class TextFieldHandler
}
