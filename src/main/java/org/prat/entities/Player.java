package org.prat.entities;

import org.prat.Game;
import org.prat.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.prat.utils.Constants.PlayerConstants.*;
import static org.prat.utils.HelpMethods.CanMoveHere;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = IDLE;
    private boolean moving = false;
    private boolean attacking = false;

    private boolean left, right, up, down, jump;
    private float playerSpeed = 2.0f;
    private int [][] lvlData;
    private float xDrawOffSet = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

//    private float airSpeed = 0f;
//    private float gravity = 0.04f * Game.SCALE;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitBox(x, y, 20 * Game.SCALE, 28 * Game.SCALE);
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) (hitBox.x - xDrawOffSet), (int) (hitBox.y - yDrawOffset), width, height, null);
        drawHitBox(g);
    }

    private void setAnimation() {
        int startAni = playerAction;
        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if(attacking)
            playerAction = ATTACK_1;

        if(startAni != playerAction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {

        moving  = false;
        if (!left && !right && !up && !down)
            return;

        float xSpeed = 0;
        float ySpeed = 0;

        if(left && !right)
            xSpeed = -playerSpeed;
        else if (right && !left)
            xSpeed = playerSpeed;


        if(up && !down)
            ySpeed = -playerSpeed;
        else if (down && !up)
            ySpeed = playerSpeed;

//        if(CanMoveHere(x + xSpeed, y + ySpeed, width, height, lvlData)) {
//            this.x += xSpeed;
//            this.y += ySpeed;
//            moving = true;
//        }

        if(CanMoveHere(hitBox.x + xSpeed, hitBox.y + ySpeed, hitBox.width, hitBox.height, lvlData)) {
            hitBox.x += xSpeed;
            hitBox.y += ySpeed;
            moving = true;
        }
    }

    private void loadAnimations() {

            BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

            animations = new BufferedImage[9][6];
            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);


    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    private void updateAnimationTick() {

        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }

        }
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
