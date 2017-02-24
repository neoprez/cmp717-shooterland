package game.code;

import java.awt.*;

/**
 *
 */
public class MainGame extends GameCore {
    private CowBoy cowBoy = new CowBoy(500, 500);

    public void draw(Graphics2D g) {
        cowBoy.draw(g);
    }

    public void update(long elapsedTime) {

    }

    public static void main(String[] args) {
        new MainGame().run();
    }
}
