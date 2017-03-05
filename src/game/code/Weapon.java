package game.code;

import java.awt.*;

/**
 *
 */
public class Weapon extends TransformableObject {
    private int fireRate = 2;
    private int currentFireRate = 0;

    public Weapon(int x, int y, String filename, int angle){
        super(x, y, filename, angle);
    }

    public void setFireRate(int fireRate){
        this.fireRate = fireRate;
    }

    public void update(long elapsedTime){
        super.update(elapsedTime);
        currentFireRate++;
    }

    public void draw(Graphics2D g) {
        if(currentFireRate < fireRate){
            super.draw(g);
        } else {
            currentFireRate = 0;
        }
    }
}
