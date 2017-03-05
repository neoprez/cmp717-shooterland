package game.code;

import java.awt.*;
import java.util.ArrayList;

public class Character extends TransformableObject {
    public static final int MAIN_WEAPON = 0;
    private static final int MOVE_SPEED = 6;
    private static final int ROTATE_SPEED = 6;
    private Animation deadAnimation;
    private ArrayList<Weapon> weapons;
    private int currentWeapon = MAIN_WEAPON;
    private boolean shooting = false;
    protected int weaponOffset = 0;

    /**
     * Creates a character with many images[]. Must be used for rotating characters.
     * @param x
     * @param y
     * @param startAngle
     * @param images
     */
    public Character(int x, int y, int startAngle, GameImage[] images) {
        super(x, y, startAngle, images);
        weapons = new ArrayList<>(2);
    }

    public void addWeapon(Weapon weapon) {
        weapon.setX(getX());
        weapon.setY(getY());
        weapons.add(weapon);
    }

    public void setCurrentWeapon(int currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public Weapon getCurrentWeapon(){
        return weapons.get(currentWeapon);
    }

    public void rotateBy(int degrees) {
        super.rotateBy(degrees);
        if(hasWeapon())
            getCurrentWeapon().rotateBy(degrees);
    }

    public void rotateLeft(){
        rotateBy(-ROTATE_SPEED);
    }

    public void rotateRight() {
        rotateBy(ROTATE_SPEED);
    }

    public void moveForward() {
        super.moveForwardBy(MOVE_SPEED);
        if(hasWeapon())
            getCurrentWeapon().moveForwardBy(MOVE_SPEED);
    }

    public boolean hasWeapon(){
        return weapons.size() > 0;
    }

    public void shoot(){
        shooting = true;
    }

    public int getWeaponOffset() {
        return weaponOffset;
    }

    private void updateWeaponPosition(){
        Weapon c = getCurrentWeapon();
        c.setX(getX());
        c.setY(getY());
        c.moveForwardBy(getWeaponOffset());
    }

    public void update(long elapsedTime) {
        super.update(elapsedTime);
        if(shooting){
            getCurrentWeapon().update(elapsedTime);
            updateWeaponPosition();
        }
    }

    public void draw(Graphics2D g) {
        super.draw(g);
        if(hasWeapon() && shooting){
            getCurrentWeapon().draw(g);
            shooting = false;
        }
    }

    public void setDeadAnimation(String fileName, String fileExt, int count, int duration) {
        deadAnimation = new Animation(fileName, fileExt, count, duration);
    }
}
