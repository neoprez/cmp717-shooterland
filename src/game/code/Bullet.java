package game.code;

import java.awt.*;

public class Bullet extends TransformableObject {
    private int forwardVelocity;
    private int maxDistance = 100;
    private int currentDistance = 0; // 100 pixels max
    private Weapon originWeapon;

    public Bullet(int x, int y, int angle, GameImage[] images){
        super(x, y, angle, images);
        forwardVelocity = 0;
    }

    public int getForwardVelocity(){
        return forwardVelocity;
    }

    public void setForwardVelocity(int forwardVelocity){
        this.forwardVelocity = forwardVelocity;
    }

    public void setOriginWeapon(Weapon originWeapon){
        this.originWeapon = originWeapon;
    }

    public int getMaxDistance(){
        return this.maxDistance;
    }

    public void setMaxDistance(int maxDistance){
        this.maxDistance = maxDistance;
    }

    public Weapon getOriginWeapon(){
        return this.originWeapon;
    }

    public boolean isOriginWeaponSet(){
        return getOriginWeapon() != null;
    }

    public void update(long elapsedTime){
        if(isAlive()) {
            moveForwardBy(getForwardVelocity());
            currentDistance++;
            if (currentDistance > getMaxDistance() && isOriginWeaponSet()) {
                getOriginWeapon().returnBullet();
                setIsAlive(false);
            }
        }
    }

    public void draw(Graphics2D g){
        if(isAlive()){
            super.draw(g);
        }
    }

    public Object clone(){
        Bullet b = (Bullet)super.clone();
        b.forwardVelocity = getForwardVelocity();
        return b;
    }
}
