package game.states;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import game.Handler;
import game.entities.Entity;

public class ChooseState extends JFrame{
    private Handler handler;
    
    private JButton rb1,rb2,rb3;      
    public ChooseState(Handler handler) {
        this.handler = handler;
        
        final JLabel tf = new JLabel("Choose your level:");  
        tf.setBounds(75, 50, 150, 20); 
        rb1 = new JButton("Easy");    
        rb1.setBounds(100,100,100,30); 
        rb1.addActionListener((ActionEvent e) -> {
           
                this.handler.getGame().setLevel(0);
                Iterator<Entity> it = this.handler.getWorld().getEntityManager().getEntities().iterator();
                while(it.hasNext()){
                    Entity entity = it.next();
                    entity.setLevel(0);              
                }
                ChooseCharacterState a = new ChooseCharacterState(handler);
                dispose();
        });
    
        
        rb2 = new JButton("Hard");    
        rb2.setBounds(100,150,100,30);
        rb2.addActionListener((ActionEvent e) -> {   
        	
                this.handler.getGame().setLevel(1);
                Iterator<Entity> it = this.handler.getWorld().getEntityManager().getEntities().iterator();
                while(it.hasNext()){
                    Entity entity = it.next();
                    entity.setLevel(1);
                }
                ChooseCharacterState a = new ChooseCharacterState(handler);
                dispose();
            }
        );
        rb3 = new JButton("Extreme");    
        rb3.setBounds(100,200,100,30); 
        rb3.addActionListener((ActionEvent e) -> {
           
                this.handler.getGame().setLevel(2);
                Iterator<Entity> it = this.handler.getWorld().getEntityManager().getEntities().iterator();
                while(it.hasNext()){
                    Entity entity = it.next();
                    entity.setLevel(2);              
                }
                ChooseCharacterState a = new ChooseCharacterState(handler);
                dispose();
        });
        ButtonGroup bg = new ButtonGroup();    
        bg.add(rb1);bg.add(rb2); bg.add(rb3);
        
        add(rb1);
        add(rb2); 
        add(rb3);
        add(tf);
        setSize(300,300);    
        setLayout(null); 
        setLocationRelativeTo(null);
        setVisible(true);    
    }    


}
