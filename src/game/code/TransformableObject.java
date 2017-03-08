package game.code;

import java.awt.*;
import java.util.Arrays;

public class TransformableObject implements Drawable, Cloneable {
    private GameImage[] images;
    private int currentImage;
    private Circle c;

    public TransformableObject(int x, int y, int startAngle, GameImage[] images){
        currentImage = startAngle;
        this.images = images;
        c = new Circle(x, y, getCurrentImage().getPreferredRadius(), startAngle);
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

    public int getComputedX() {
        return c.computedX();
    }

    public int getComputedY() {
        return c.computedY();
    }

    public int getComputedX(int x){
        return c.computedX(x);
    }

    public int getComputedY(int y){
        return c.computedY(y);
    }

    public int getRadius() {
        return c.r;
    }

    public int getAngle() {
        return c.angle;
    }

    public int getDiameter() {
        return c.diameter;
    }

    public void setRadius(int r){
        c.r = r;
    }

    public void setAngle(int angle) {
        c.updateAngle(angle);
        currentImage = c.angle;
    }

    public int getWidth(){
        return getCurrentImage().getWidth();
    }

    public int getHeight(){
        return getCurrentImage().getHeight();
    }

    public double getCosA() {
        return c.cosA;
    }

    public double getSinA(){
        return c.sinA;
    }

    public GameImage[] getImages(){
        return images;
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

    public int getPaintX(){
        return getX() - getCurrentImage().getWidth2();
    }

    public int getPaintY() {
        return getY() - getCurrentImage().getHeight2();
    }

    public void draw(Graphics2D g) {
        g.drawImage(getCurrentImage(), getPaintX(), getPaintY(), null);

        if(Globals.drawPolygons) {
            c.draw(g);
        }
    }

    public void update(long elapsedtime){
        // If any animations, this can be used to update the animations
    }

    public Object clone(){
        TransformableObject obj = null;
        try {
            obj = (TransformableObject)super.clone();
            obj.images = Arrays.copyOf(images, images.length);
            obj.c = new Circle(c);
            obj.currentImage = currentImage;
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        return obj;
    }
}
