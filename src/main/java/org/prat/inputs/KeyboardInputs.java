package org.prat.inputs;

import org.prat.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.prat.utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {

    GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println(e.getKeyChar()+" Key is pressed");
        switch (e.getKeyCode()){
            case KeyEvent.VK_W -> gamePanel.setDirection(UP);
            case KeyEvent.VK_A -> gamePanel.setDirection(LEFT);
            case KeyEvent.VK_S -> gamePanel.setDirection(DOWN);
            case KeyEvent.VK_D -> gamePanel.setDirection(RIGHT);
            default -> System.out.println("Some other Key is pressed!");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W, KeyEvent.VK_A,
                    KeyEvent.VK_S, KeyEvent.VK_D -> gamePanel.setMoving(false);
            default -> System.out.println("Some other Key is pressed!");
        }
    }
}
