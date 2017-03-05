package game.code;

import javax.swing.*;
import java.awt.*;

public class TransformableObject implements Drawable, Cloneable {
    private Image image;
    private GameImage[] images;
    private int currentImage;
    private Circle c;
    private int w;
    private int h;
    private int offsetX;
    private int offsetY;

    public TransformableObject(int x, int y, String imageName, int startAngle) {
        image = new ImageIcon(this.getClass().getResource(Globals.resourcesFolder + imageName)).getImage();
        w = image.getWidth(null)/2;
        h = image.getHeight(null)/2;
        c = new Circle(x, y, h, startAngle);
        offsetX = 0;
        offsetY = 0;
    }

    public TransformableObject(int x, int y, int startAngle, GameImage[] images){
        currentImage = startAngle;
        this.images = images;
        c = new Circle(x, y, images[currentImage].getPreferredRadius(), startAngle);
    }

    public void rotateBy(int degrees) {
        c.rotateBy(degrees);
        currentImage = c.angle;
    }

    public int getX() {
        return c.x;
    }

    public int getY() {
        return c.y;
    }

    public void setX(int x) {
        c.x = x;
    }

    public void setY(int y) {
        c.y = y;
    }

    public void setOffsetX(int offsetX){
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY){
        this.offsetY = offsetY;
    }

    public void moveForwardBy(int dist) {
        c.moveForwardBy(dist);
    }

    public void setOrigin(int x, int y){
        c.x = x;
        c.y = y;
    }

    public void setOrigin(int x, int y, int angle){
        setOrigin(x, y);
        c.angle = angle;
        c.rotateBy(0);
        currentImage = c.angle;
    }

    public GameImage getCurrentImage(){
        return images[currentImage];
    }

    public void draw(Graphics2D g) {
        GameImage current = getCurrentImage();
        g.drawImage(current, c.x - current.getWidth2() + offsetX, c.y - current.getHeight2() + offsetY, null);

        if(Globals.drawPolygons) {
            c.draw(g);
        }
    }

    public void update(long elapsedtime){
        // If any animations, this can be used to update the animtions
    }

    public Object clone(){
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        return obj;
    }
}
