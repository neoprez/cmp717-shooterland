package game.code;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Character {
    private static final String imagesPath = "./images/";
    private static final int MOVE_SPEED = 6;
    private static final int ROTATE_SPEED = 6;
    private Animation deadAnimation;
    private Image image;
    private Circle c;
    private int width;
    private int height;
    AffineTransform t;

    public Character(int x, int y, String imageName, int startAngle) {
        image = new ImageIcon(this.getClass().getResource(imagesPath + imageName)).getImage();
        width = image.getWidth(null)/2;
        height = image.getHeight(null)/2;
        c = new Circle(x, y, width, startAngle);
    }

    public void update(long elapsedtime){

    }

    private void transform(){
        t = new AffineTransform();
        t.translate(c.x, c.y);
        t.rotate(Math.toRadians(c.angle+90));
        t.translate(-width, -height);
    }

    private void rotateBy(int degrees) {
        c.rotateBy(degrees);
    }

    public void rotateLeft(){
        rotateBy(-ROTATE_SPEED);
    }

    public void rotateRight() {
        rotateBy(ROTATE_SPEED);
    }

    public void moveForward() {
        c.moveForwardBy(MOVE_SPEED);
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, t, null);
        transform();
        if(Globals.drawPolygons) {
            c.draw(g);
        }
    }

    public void setDeadAnimation(String fileName, String fileExt, int count, int duration) {
        deadAnimation = new Animation(fileName, fileExt, count, duration);
    }
}
