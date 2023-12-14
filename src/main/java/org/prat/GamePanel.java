package org.prat;

import org.prat.inputs.KeyboardInputs;
import org.prat.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100;
    private float xDir = 1f;
    private float yDelta = 100;
    private float yDir = 1f;
    private int frames = 0;
    private long lastCheck = 0;

    public GamePanel() {

        mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void changeXDelta(int value) {
        xDelta += value;
        //repaint();
    }

    public void changeYDelta(int value) {
        yDelta += value;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

}
