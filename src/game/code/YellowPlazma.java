package game.code;


public class YellowPlazma extends Weapon {
    public static final String NAME = "yellow-plazma.png";

    public YellowPlazma(int x, int y, int angle, GameImage[] images){
        super(x, y, angle, images);
    }

    public int getFireRate(){
        return 10;
    }
}
