package game.states;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import game.Handler;

public class HowToPlayState extends JFrame{

    private JButton b1;
    private Handler handler;
    
    public HowToPlayState(Handler handler) {
    	this.handler=handler;

        final JLabel tf =new JLabel(new ImageIcon("res/textures/tutorial.png"));
 
    	b1 = new JButton("Exit");    
        b1.setBounds(210,330,80,30);    
        b1.addActionListener((ActionEvent e) -> {
        	State.setState(handler.getGame().menuState);
        	handler.getGame().menuState.setActive(true);
            dispose();  
        });
        add(b1);
        getContentPane().add(new JScrollPane(tf));
        setUndecorated(true);
        setSize(500,400);
        setLocationRelativeTo(null);;
        setVisible(true);
        setResizable(false);
    }
}
