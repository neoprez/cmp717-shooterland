package game.code;


import javax.swing.*;
import java.awt.*;

public class GameMap implements Drawable {
    private Image img = new ImageIcon(this.getClass().getResource("./images/world/texture1.png")).getImage();
    private int w1 = img.getWidth(null);
    private int w2 = w1*2;
    private int w3 = w1*3;

    private int h1 = img.getHeight(null);
    private int h2 = h1*2;
    private int h3 = h1*3;

    public void draw(Graphics2D g) {
        g.drawImage(img, 0, 0, null);
        g.drawImage(img, 0, h1, null);
        g.drawImage(img, 0, h2, null);
        g.drawImage(img, 0, h3, null);

        g.drawImage(img, w1, 0, null);
        g.drawImage(img, w1, h1, null);
        g.drawImage(img, w1, h2, null);
        g.drawImage(img, w1, h3, null);

        g.drawImage(img, w2, 0, null);
        g.drawImage(img, w2, h1, null);
        g.drawImage(img, w2, h2, null);
        g.drawImage(img, w2, h3, null);

        g.drawImage(img, w3, 0, null);
        g.drawImage(img, w3, h1, null);
        g.drawImage(img, w3, h2, null);
        g.drawImage(img, w3, h3, null);
    }

    public void update(long elapsedTime){

    }
}
