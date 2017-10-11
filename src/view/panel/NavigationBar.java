package view.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class NavigationBar extends JPanel {
	public JButton forward;
	public JButton back;
	public JButton close;
	
	public NavigationBar(){
        ButtonPressHandler handler = new ButtonPressHandler();

        nav_bar_panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        back = new JButton("Precedent");
        back.addActionListener(handler);
        close = new JButton("Annuler");
        close.addActionListener(handler);
        forward = new JButton("Suivant");
        forward.addActionListener(handler);

        nav_bar_panel.add(back);
        nav_bar_panel.add(close);
        nav_bar_panel.add(forward);

        return nav_bar_panel;
    }
	
	private class ButtonPressHandler implements ActionListener {
       public void actionPerformed( ActionEvent event )
       {
           if(event.getSource() == back){
               previous_display();
               System.out.print("You selected back");
           } else if(event.getSource() == forward){
               forward_display();
               System.out.print("You selected forward");
           }
       } // end method actionPerformed
    } // end private inner class TextFieldHandler
}
