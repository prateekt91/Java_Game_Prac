package org.prat;

import org.prat.entities.Player;
import org.prat.levels.LevelManager;

import java.awt.*;

public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Player player;
    private LevelManager levelManager;
    public static final int TILES_DEFAULT_SIZE = 32;
    public static final float SCALE = 1.5f;
    public static final int TILES_IN_WIDTH = 26;
    public static final int TILES_IN_HEIGHT = 14;
    public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    public Game() {

        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocusInWindow();
        startGameLoop();
    }

    private void initClasses() {
        player = new Player(200,200, (int)(64 * SCALE), (int)(40 * SCALE));
        levelManager = new LevelManager(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
       player.update();
       levelManager.update();
    }

    public void render(Graphics g) {
        levelManager.draw(g);
        player.render(g);
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;       //In nanoseconds
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        while (gameThread != null) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime- previousTime) / timePerUpdate;
            deltaF += (currentTime- previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >=1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >=1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if(System.currentTimeMillis() - lastCheck >=1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }
}
