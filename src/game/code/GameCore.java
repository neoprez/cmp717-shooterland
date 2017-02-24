package game.code;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public abstract class GameCore extends Applet implements KeyListener, Runnable {
    private boolean leftPressed, rightPressed, upPressed, downPressed;
    private Thread gameThread;
    private boolean isGameRunning;
    private BufferStrategy bufferStrategy;
    GraphicsDevice device; // To set the screen full screen
    Window window;
    DisplayMode oldDisplayMode;
    DisplayMode newDisplayMode;
    GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();

    public void init() {

        setSize(1024, 768);
//        device = environment.getDefaultScreenDevice();
//        oldDisplayMode = device.getDisplayMode();



//        System.out.println(device.isFullScreenSupported());
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setVisible(true);
//        createBufferStrategy(2);
//        bufferStrategy = getBufferStrategy();
        initialize();
        addKeyListener(this);
        gameThread.start();
        requestFocus();
//        device.setFullScreenWindow();
    }

    private void initialize() {
        leftPressed = rightPressed = upPressed = downPressed = false;
        isGameRunning = true;
        gameThread = new Thread(this);
    }

    public abstract void update();

    public abstract void render(Graphics g);

    private void updateGraphics() {
        // Use a buffer strategy class to update the graphics on the screen.
        do {
            do {
                Graphics g = bufferStrategy.getDrawGraphics();
                g.clearRect(0, 0, getWidth(), getHeight());
                render(g);
            } while (bufferStrategy.contentsRestored());
            bufferStrategy.show();
        }while (bufferStrategy.contentsLost());
    }

    public void paint(Graphics g){
        render(g);
    }

    public void run() {
        while(isGameRunning) {
            try {
                update();
                repaint();
//                updateGraphics();
                Thread.sleep(15);
            } catch (InterruptedException e){}
        }
        System.exit(0);
    }

    private void setUnsetKey(int keyCode, boolean val) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                leftPressed = val;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = val;
                break;
            case KeyEvent.VK_UP:
                upPressed = val;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = val;
                break;
            case KeyEvent.VK_ESCAPE:
                isGameRunning = false;
                break;
        }
    }

    private void setKey(int keyCode) {
        setUnsetKey(keyCode, true);
    }

    private void unsetKey(int keyCode) {
        setUnsetKey(keyCode, false);
    }


    public void keyTyped(KeyEvent e) {}


    public void keyPressed(KeyEvent e) {
        setKey(e.getKeyCode());
    }


    public void keyReleased(KeyEvent e) {
        unsetKey(e.getKeyCode());
    }

    public boolean isUpPressed(){
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }
}