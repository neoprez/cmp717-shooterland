package game.code;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ResourcesLoader {
    /**
     * Holds all the characters:
     * 0 - Pyro
     * 1 - Heavy
     */
    private Character[] characters = new Character[2];
    /**
     * Holds all the weapons.
     */
    private Weapon[] weapons = new Weapon[1];

    /**
     * Holds all the bullet objects.
     */
    private Bullet[] bullets = new Bullet[1];

    /**
     * Constants for characters index.
     */
    public static final int PYRO = 0;
    public static final int HEAVY = 1;

    /**
     * Constants for character weapons.
     */
    public static final int YELLOW_PLAZMA = 0;

    /**
     * Constants for weapon bullets.
     */
    public static final int YELLOW_PLAZMA_BULLET = 0;

    public Image loadImage(String imageName){
        return new ImageIcon(this.getClass().getResource(Globals.resourcesFolder + imageName)).getImage();
    }

    public void loadResources() {
        loadHeroes();
        loadBullets();
        loadWeapons();
    }

    private void loadHeroes() {
        loadPyro();
        loadHeavy();
    }

    private void loadWeapons(){
        loadYellowPlazma();
    }

    private void loadPyro(){
        characters[PYRO] = new Pyro(0, 0, 0, getRotatedImages(Pyro.NAME));
    }

    private void loadHeavy(){
        characters[HEAVY] = new Heavy(0, 0, 0, getRotatedImages(Heavy.NAME));
    }

    private void loadYellowPlazma() {
        YellowPlazma w = new YellowPlazma(0, 0, 0, getRotatedImages(YellowPlazma.NAME));
        weapons[YELLOW_PLAZMA] = w;
        w.setWeaponBullet(getYellowPlazmaBullet());
    }

    private void loadBullets() {
        bullets[YELLOW_PLAZMA_BULLET] = new YellowPlazmaBullet(0, 0, 0, getRotatedImages(YellowPlazmaBullet.NAME));
    }


    public Pyro getPyro(){
        return (Pyro)characters[PYRO].clone();
    }

    public Heavy getHeavy(){
        return (Heavy)characters[HEAVY].clone();
    }

    public YellowPlazma getYellowPlazmaWeapon(){
        return (YellowPlazma)weapons[YELLOW_PLAZMA].clone();
    }

    public YellowPlazmaBullet getYellowPlazmaBullet() {
        return (YellowPlazmaBullet)bullets[YELLOW_PLAZMA_BULLET].clone();
    }

    /**
     * Creates an array of 360 images, each image is an image rotated by 1 degree
     * from 0 to 359.
     * @param imageName The name of the file containing the image.
     * @return an array of 360 images.
     */
    private GameImage[] getRotatedImages(String imageName){
        GameImage[] images = new GameImage[360];
        Image image = loadImage(imageName);

        images[0] = rotate(image, 90);
        for(int i = 1; i < images.length; i++) {
            images[i] = rotate(image, (i+90)%360);
        }

        return images;
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
