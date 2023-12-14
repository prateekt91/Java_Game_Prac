package org.prat;

import org.prat.inputs.KeyboardInputs;
import org.prat.inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100;
    private float xDir = 1f;
    private float yDelta = 100;
    private float yDir = 1f;
    private int frames = 0;
    private long lastCheck = 0;

    private BufferedImage img, subImg;

    public GamePanel() {

        mouseInputs = new MouseInputs(this);
        importImg();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImg() {
        try (InputStream is = getClass().getResourceAsStream("/player_sprites.png")){
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public void setRectPos(int x, int y) {

        this.xDelta = x;
        this.yDelta = y;
        //repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        subImg = img.getSubimage(1*64,8*40,64,40);
        g.drawImage(subImg, (int) xDelta, (int) yDelta,128,80, null);
    }

}
