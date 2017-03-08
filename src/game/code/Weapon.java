package game.code;

import java.awt.*;

/**
 *
 */
public class Weapon extends TransformableObject {
    private int fireRate = 2;
    private int currentFireRate = 0;

    /**
     * The Bullet for this weapon.
     */
    private Bullet weaponBullet;

    public Weapon(int x, int y, int angle, GameImage[] images){
        super(x, y, angle, images);
        weaponBullet = null;
    }

    public void setFireRate(int fireRate){
        this.fireRate = fireRate;
    }

    public int getFireRate() {
        return fireRate;
    }

    public boolean hasBullet() {
        return currentFireRate < getFireRate();
    }

    /**
     * Returns a new bullet for this weapon.
     * This method must be overwritten otherwise null is returned.
     * @return a new Bullet object.
     */
    public Bullet getWeaponBullet(){
        currentFireRate++;
        Bullet b = (Bullet)weaponBullet.clone();
        return b;
    }

    public void returnBullet(){
        --currentFireRate;
    }

    public void setWeaponBullet(Bullet weaponBullet) {
        this.weaponBullet = weaponBullet;
        weaponBullet.setOriginWeapon(this);
    }

    public void draw(Graphics2D g) {
        if(hasBullet()){
            super.draw(g);
        }
    }

    public Object clone() {
        Weapon w = (Weapon)super.clone();
        w.setFireRate(getFireRate());
        w.setWeaponBullet(getWeaponBullet());
        return w;
    }
}
