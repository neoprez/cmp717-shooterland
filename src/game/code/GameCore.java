package game.code;

import javax.swing.*;
import java.awt.*;

/**
 * Simple abstract class used for testing. Subclasses should
 * implement the draw() method.
 */
public abstract class GameCore
{
    protected static final int FONT_SIZE = 24;

    private static final DisplayMode POSSIBLE_MODES[] = {
            new DisplayMode(2880, 1800, 32, 0),
            new DisplayMode(2880, 1800, 24, 0),
            new DisplayMode(2880, 1800, 16, 0),
            new DisplayMode(1440, 900, 32, 0),
            new DisplayMode(1440, 900, 24, 0),
            new DisplayMode(1440, 900, 16, 0),
            new DisplayMode(1024, 768, 32, 0),
            new DisplayMode(1024, 768, 24, 0),
            new DisplayMode(1024, 768, 16, 0),
            new DisplayMode(800, 600, 32, 0),
            new DisplayMode(800, 600, 24, 0),
            new DisplayMode(800, 600, 16, 0),
            new DisplayMode(640, 480, 32, 0),
            new DisplayMode(640, 480, 24, 0),
            new DisplayMode(640, 480, 16, 0),
    };

    private boolean isRunning;
    protected ScreenManager screen;

    /**
     * Signals the game loop that it's time to quit
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * Calls init() and gameLoop()
     */
    public void run() {
        try {
            init();
            gameLoop();
        } finally {
            screen.restoreScreen();
        }
    }

    /**
     * Sets full screen mode and initiates and objects.
     */
    public void init() {
        screen = new ScreenManager();
        isRunning = true;
    }

    public void setFullScreen() {
        DisplayMode displayMode = screen.findFirstCompatibleMode(POSSIBLE_MODES);
        screen.setFullScreen(displayMode);
    }

    public void setWindowMode(int width, int height) {
        screen.setWindowMode(width, height);
    }

    public Image loadImage(String fileName) {
        return new ImageIcon(fileName).getImage();
    }

    /**
     * Runs through the game loop until stop() is called.
     */
    public void gameLoop() {
        long startTime = System.currentTimeMillis();
        long currTime = startTime;

        while(isRunning) {
            long elapsedTime = System.currentTimeMillis() - currTime;
            currTime += elapsedTime;

            // update the sprites
            update(elapsedTime);

            // draw and update screen
            Graphics2D g = screen.getGraphics();
            if(g!=null)
                g.setBackground(Color.black);
            draw(g);
            if(g != null)
                g.dispose();
            screen.update();

            // take a nap

            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {}
        }
    }

    /**
     * Updates the state of the game/animation based on the
     * amount of elapsed time that has passed.
     */
    public void update(long elapsedTime) {
       // do nothing
    }


    /**
     * Draws to the screen. Subclasses must override this
     * method.
     */
    public abstract void draw(Graphics2D g);
}
