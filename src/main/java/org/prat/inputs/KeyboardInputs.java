package org.prat.inputs;

import org.prat.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
            case KeyEvent.VK_W -> gamePanel.changeYDelta(-5);
            case KeyEvent.VK_A -> gamePanel.changeXDelta(-5);
            case KeyEvent.VK_S -> gamePanel.changeYDelta(5);
            case KeyEvent.VK_D -> gamePanel.changeXDelta(5);
            default -> System.out.println("Some other Key is pressed!");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
