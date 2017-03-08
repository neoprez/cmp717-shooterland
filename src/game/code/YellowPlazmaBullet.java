package game.code;

public class YellowPlazmaBullet extends Bullet {
    public static final String NAME = "yellow-plazma-shot.png";
    public static final int FORWARD_VELOCITY = 10;

    public YellowPlazmaBullet(int x, int y, int angle, GameImage[] images){
        super(x, y, angle, images);
        setForwardVelocity(FORWARD_VELOCITY);
    }

    public int getMaxDistance(){
        return 50;
    }
}
