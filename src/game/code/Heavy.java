package game.code;


public class Heavy extends Character {
    public static final String NAME = "heavy.png";
    private Weapon weapon;

    public Heavy(int x, int y, int startAngle, GameImage[] images) {
        super(x, y, startAngle, images);
        weapon = new YellowPlazma(x, y, startAngle);
        addWeapon(weapon);
    }
}
