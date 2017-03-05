package game.code;

public class YellowPlazma extends Weapon {
    public YellowPlazma(int x, int y, int angle){
        super(x, y, "yellow-plazma.png", angle);
        setOffsetX(-20);
        setOffsetY(-240);
    }
}
