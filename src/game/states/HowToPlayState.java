package game.states;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import game.Handler;
import game.ui.UIManager;

public class HowToPlayState extends JFrame{
    
    //private UIManager uiManager;
    private JButton b1; 
    //private MenuState m;
    private Handler handler;
    
    public HowToPlayState(Handler handler) {
    	this.handler=handler;
    	//uiManager = new UIManager(handler);
    	
        //handler.getMouseManager().setUiManager(uiManager);
        final JLabel tf =new JLabel(new ImageIcon("res/textures/tutorial.png"));
 
    	b1 = new JButton("Exit");    
        b1.setBounds(210,330,80,30);    
        b1.addActionListener((ActionEvent e) -> {
            //handler.getMouseManager().setUiManager(uiManager);
            //handler.getGame().menuState.setActive(true);
        	State.setState(handler.getGame().menuState);
        	handler.getGame().menuState.setActive(true);
            dispose();  
        });
           
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(b1);
        getContentPane().add(new JScrollPane(tf));
        setSize(500,400);
        setLocationRelativeTo(null);;
        setVisible(true);
        setResizable(false);
    }
}
