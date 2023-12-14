package org.prat;

import javax.swing.*;

public class GameWindow {

    private JFrame jFrame;
    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setLocationRelativeTo(null); // To make the window come in centre of the screen (passing null)
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
