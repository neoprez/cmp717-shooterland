package game.code;

import java.awt.*;

public class Sprite {
    private Pose currentPose = Pose.DOWN;
    private boolean isMoving = false;
    private Animation[] animations;
    private int x;
    private int y;

    public Sprite(int x, int y, String[] names, String fileNameExt, int[] counts, int[] durations) {
        animations = new Animation[names.length];

        for(int i = 0; i < names.length; i++){
            animations[i] = new Animation(names[i], fileNameExt, counts[i], durations[i]);
        }

        this.x = x;
        this.y = y;
    }

    public void update() {
        animations[currentPose.index].update();
    }

    public void draw(Graphics g) {
        Image currentImage = animations[currentPose.index].getStandingImage();

        if(isMoving) {
            currentImage = animations[currentPose.index].getCurrentImage();
            isMoving = false;
        }

        g.drawImage(currentImage, x, y, null);
    }

    public void moveBy(int dx, int dy) {
        x += dx;
        y += dy;
        isMoving = true;
        update();
    }

    public void moveDownBy(int dy) {
        currentPose = Pose.DOWN;
        moveBy(0, dy);
    }

    public void moveUpBy(int dy) {
        currentPose = Pose.UP;
        moveBy(0, -dy);
    }

    public void moveLeftBy(int dx) {
        currentPose = Pose.LEFT;
        moveBy(-dx, 0);
    }

    public void moveRightBy(int dx) {
        currentPose = Pose.RIGHT;
        moveBy(dx, 0);
    }
}
