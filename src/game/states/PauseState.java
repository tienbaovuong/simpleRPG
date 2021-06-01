package game.states;


import game.Handler;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class PauseState extends JFrame{
    private Handler handler;
    private boolean isActive;
    private JButton rb1,rb2;
    public PauseState(Handler handler) {
        this.handler = handler;

        rb1 = new JButton("Continue");
        rb1.setBounds(100,100,125,30);
        rb1.addActionListener((ActionEvent e) -> {
            dispose();
        });

        rb2 = new JButton("Back to menu");
        rb2.setBounds(100,150,125,30);
        rb2.addActionListener((ActionEvent e) -> {
                    dispose();
                    State.setState(handler.getGame().menuState);
                    handler.getGame().menuState.setActive(true);

                }
        );

        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);bg.add(rb2);
        add(rb1);
        add(rb2);
        setUndecorated(true);
        setSize(300,300);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }


}