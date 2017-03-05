package game.code;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ResourcesLoader {
    private Pyro pyro;
    private Heavy heavy;

    public Image loadImage(String imageName){
        return new ImageIcon(this.getClass().getResource(Globals.resourcesFolder + imageName)).getImage();
    }

    public void loadResources() {
        Globals.setLoading(true);
        loadHeroes();
        Globals.setLoading(false);
    }

    private void loadHeroes() {
        loadPyro();
        loadHeavy();
    }

    private void loadPyro(){
        pyro = new Pyro(0, 0, 0, getRotatedImages(Pyro.NAME));
    }

    private void loadHeavy(){
        heavy = new Heavy(0, 0, 0, getRotatedImages(Heavy.NAME));
    }

    private GameImage[] getRotatedImages(String imageName){
        GameImage[] images = new GameImage[360];
        Image image = loadImage(imageName);

        images[0] = rotate(image, 90);
        for(int i = 1; i < images.length; i++) {
            images[i] = rotate(image, (i+90)%360);
        }

        return images;
    }

    public Pyro getPyro(){
        return (Pyro)pyro.clone();
    }

    public Heavy getHeavy(){
        return (Heavy)heavy.clone();
    }

    /**
     * Rotates an image. Actually rotates a new copy of the image.
     *
     * @param img The image to be rotated
     * @param angle The angle in degrees
     * @return The rotated image
     */
    private static GameImage rotate(Image img, double angle)
    {
        double sin = Math.abs(Math.sin(Math.toRadians(angle))),
                cos = Math.abs(Math.cos(Math.toRadians(angle)));

        int w = img.getWidth(null), h = img.getHeight(null);

        int neww = (int) Math.floor(w*cos + h*sin),
                newh = (int) Math.floor(h*cos + w*sin);

        BufferedImage bimg = getEmptyImage(neww, newh);
        Graphics2D g = bimg.createGraphics();

        g.translate((neww-w)/2, (newh-h)/2);
        g.rotate(Math.toRadians(angle), w/2, h/2);
        g.drawRenderedImage(toBufferedImage(img), null);
        g.dispose();

        return new GameImage(bimg);
    }

    private static BufferedImage getEmptyImage(int w, int h){
        return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    }

    private static BufferedImage toBufferedImage(Image img){
        BufferedImage empty = getEmptyImage(img.getWidth(null), img.getHeight(null));
        Graphics2D g = (Graphics2D)empty.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return empty;
    }
}
