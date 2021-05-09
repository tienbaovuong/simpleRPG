package game.states;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

import game.Game;
import game.Handler;
import game.Launcher;
import game.gfx.Assets;
import game.ui.UIImageButton;
import game.ui.UIManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gameover {
    private UIManager uiManager;
    private Handler handler;
    private JFrame f;

    public Gameover(Handler handler) throws IOException{
        uiManager = new UIManager(handler);
        f = new JFrame();
        handler.getMouseManager().setUiManager(uiManager);
        final JLabel label2=new JLabel("<html>YOU DIE<br/> Score: "
                + handler.getGame().getPlayer().getScore()+"<htmt/",JLabel.CENTER);
        label2.setFont(new Font("Courier", Font.BOLD,40));
        //label2.setPreferredSize(new Dimension(850, 600));
        JPanel panel = new JPanel();
        f.getContentPane().add(label2);
        f.setSize(500,400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension size = label2.getPreferredSize();
        label2.setBounds(150, 100, size.width, size.height);
        panel.setLayout(null);
        panel.add(label2);
        JButton okButton = new JButton("Return to main menu");
        panel.add(okButton);
        f.getContentPane().add(panel);
        Dimension size1 = okButton.getPreferredSize();
        okButton.setBounds(150, 300, size1.width, size1.height);
        panel.setLayout(null);
        f.add(panel);
        f.setVisible(true);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game game = new Game("Title Game!", 800, 608);
                game.start();
                f.dispose();
            }
        });
    }

    public void close() {
        f.dispose();
    }
}
