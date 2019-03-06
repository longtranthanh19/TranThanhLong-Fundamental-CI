package game;

import game.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by huynq on 7/4/17.
 */
public class Program {
    public static void main(String[] args) {
        GameWindow window = new GameWindow();
//        window.setSize(800, 600);
        window.setTitle("Game Toubou");
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();
        panel.setPreferredSize(new Dimension(800, 600));
        panel.setBackground(Color.CYAN);


        window.add(panel);
        window.pack();

        window.setVisible(true);

        panel.gameLoop();
    }
}
