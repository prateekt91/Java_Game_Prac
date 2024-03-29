package org.prat;

import org.prat.inputs.KeyboardInputs;
import org.prat.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100;
    private float xDir = 1f;
    private float yDelta = 100;
    private float yDir = 1f;
    private int frames = 0;
    private long lastCheck = 0;

    private Color color  = new Color(150,20,90);
    private Random random;
    public GamePanel() {

        random = new Random();
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
        updateRectangle();
        g.setColor(color);
        g.fillRect((int) xDelta, (int) yDelta,200,50);

    }

    private void updateRectangle() {
        xDelta += xDir;
        if(xDelta > 400 || xDelta <0){
            xDir *= -1;
            color = getRandomColor();
        }
        yDelta += yDir;
        if (yDelta > 400 || yDelta <0){
            yDir *= -1;
            color = getRandomColor();
        }
    }

    private Color getRandomColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        return new Color(r,g,b);
    }
}
