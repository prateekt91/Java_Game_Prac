package org.prat;

import javax.swing.*;

public class GameWindow {

    private JFrame jFrame;
    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame();
        jFrame.setSize(400,400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setVisible(true);
    }
}
