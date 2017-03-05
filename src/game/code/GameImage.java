package game.code;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 */
public class GameImage extends BufferedImage {
    private int w;
    private int h;
    private int w2;
    private int h2;

    public GameImage(Image img){
        super(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        w = img.getWidth(null);
        h = img.getHeight(null);
        w2 = w/2;
        h2 = h/2;
        Graphics2D g = (Graphics2D)getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
    }

    public int getWidth2(){
        return w2;
    }

    public int getHeight2(){
        return h2;
    }

    public int getPreferredRadius(){
        return w2 > h2 ? w2 : h2;
    }

}
