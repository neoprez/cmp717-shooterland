package game.code;


public class Heavy extends Character {
    public static final String NAME = "heavy.png";

    public Heavy(int x, int y, int startAngle, GameImage[] images) {
        super(x, y, startAngle, images);
        weaponOffset = 250;
    }
}
