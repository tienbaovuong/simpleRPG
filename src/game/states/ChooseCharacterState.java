package game.states;

import game.Handler;
import game.entities.Entity;
import game.entities.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;

public class ChooseCharacterState extends JFrame {
    private Handler handler;

    private JButton rb1,rb2;
    public ChooseCharacterState(Handler handler) {
        this.handler = handler;

        final JLabel tf = new JLabel("Choose your character");
        tf.setBounds(75, 50, 150, 20);
        rb1 = new JButton("Male");
        rb1.setBounds(100,100,100,30);
        rb1.addActionListener((ActionEvent e) -> {
        	
            handler.getGame().menuState.setActive(false);
            State.setState(handler.getGame().gameState);
            dispose();
        });


        rb2 = new JButton("Female");
        rb2.setBounds(100,150,100,30);
        rb2.addActionListener((ActionEvent e) -> {

                    
             Player.characterNumber=1;
             handler.getGame().getPlayer().setAnimation();
             handler.getGame().menuState.setActive(false);
             State.setState(handler.getGame().gameState);
             dispose();
        }
        );



        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);bg.add(rb2);

        add(rb1);
        add(rb2);
        add(tf);
        setUndecorated(true);
        setSize(300,300);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

}
