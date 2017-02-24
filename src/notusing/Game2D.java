package notusing;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 */
public class Game2D extends Applet implements KeyListener, Runnable {
    private CowBoyHat hat;

    public void init(){
        hat = new CowBoyHat();
        Thread t = new Thread(this);
        t.start();
    }


    public void paint(Graphics g){
        hat.draw(g);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while(true) {
            repaint();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {}
        }
    }
}
