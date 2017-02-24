package notusing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class Game extends JFrame implements KeyListener, Runnable {
    private boolean leftPressed, rightPressed, upPressed, downPressed;
    private Thread gameThread;
    private boolean isGameRunning;
    private Player p = new Player();
    private BufferStrategy bufferStrategy;

    public Game() {
        setSize(1024, 768);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initialize();
        addKeyListener(this);
        setVisible(true);
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();
        requestFocus();
        gameThread.start();
    }

    private void initialize() {
        leftPressed = rightPressed = upPressed = downPressed = false;
        isGameRunning = true;
        gameThread = new Thread(this);
    }

    private void updateWorld() {
        if(leftPressed){
            p.moveLeftBy(10);
        }

        if(rightPressed) {
            p.moveRightBy(10);
        }

        if(upPressed){
            p.moveUpBy(10);
        }

        if(downPressed){
            p.moveDownBy(10);
        }
    }

    private void render(Graphics g){
        g.clearRect(0, 0, getWidth(), getHeight());
        p.draw(g);
    }

    private void updateGraphics() {
        // Use a buffer strategy class to update the graphics on the screen.
        do {
            do {
                Graphics g = bufferStrategy.getDrawGraphics();
                render(g);
            } while (bufferStrategy.contentsRestored());
            bufferStrategy.show();
        }while (bufferStrategy.contentsLost());
    }

    public void run() {
        while(isGameRunning) {
            try {
                updateWorld();
                updateGraphics();
                Thread.sleep(15);
            } catch (InterruptedException e){}
        }
        System.exit(0);
    }

    public static void main(String[] args ) {
        new Game();
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
}