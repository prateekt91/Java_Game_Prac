package org.prat;

import org.prat.inputs.KeyboardInputs;
import org.prat.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private int xDelta = 100;
    private int yDelta = 100;
    private int frames = 0;
    private long lastCheck = 0;
    public GamePanel() {

        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value) {
        xDelta += value;
        //repaint();
    }

    public void changeYDelta(int value) {
        yDelta += value;
        //repaint();
    }

    public void setRectPos(int x, int y) {

        this.xDelta = x;
        this.yDelta = y;
        //repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(xDelta, yDelta,200,50);
        frames ++;
        if(System.currentTimeMillis() - lastCheck >=1000) {
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frames);
            frames = 0;
        }
        repaint();

    }
}
