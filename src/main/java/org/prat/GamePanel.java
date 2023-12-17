package org.prat;

import org.prat.inputs.KeyboardInputs;
import org.prat.inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static org.prat.utils.Constants.Directions.*;
import static org.prat.utils.Constants.PlayerConstants.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100;
    private float yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;

    public GamePanel() {

        mouseInputs = new MouseInputs(this);
        importImg();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {

        animations = new BufferedImage[9][6];

        for (int j=0;j<animations.length;j++)
            for (int i=0; i< animations[j].length; i++){
                animations[j][i] = img.getSubimage(i*64,j*40,64,40);
        }
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
      //  setMinimumSize(size);
        setPreferredSize(size);
       // setMaximumSize(size);
    }

    public void setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void setAnimation() {
        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }

    private void updatePos() {
        if (moving) {
            switch (playerDir) {
                case LEFT -> xDelta -= 5;
                case UP ->  yDelta -= 5;
                case RIGHT ->   xDelta += 5;
                case DOWN ->    yDelta += 5;
                default -> System.out.println("Wrong Input!");
            }
        }
    }

    public void updateGame() {
        updateAnimationTick();
        setAnimation();
        updatePos();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta,256,160, null);
    }


    private void updateAnimationTick() {

        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(playerAction))
                aniIndex = 0;
        }
    }
}
