package game.code;

import java.awt.*;

/**
 *
 */
public class Weapon extends TransformableObject {
    private int fireRate = 2;
    private int currentFireRate = 0;

    public Weapon(int x, int y, int angle, GameImage[] images){
        super(x, y, angle, images);
    }

    public void setFireRate(int fireRate){
        this.fireRate = fireRate;
    }

    public int getFireRate() {
        return fireRate;
    }

    public void update(long elapsedTime){
        super.update(elapsedTime);
        if(currentFireRate++ == getFireRate()){
            currentFireRate = 0;
        }
    }

    public void draw(Graphics2D g) {
        if(currentFireRate == 0){
            super.draw(g);
        }
    }
}
