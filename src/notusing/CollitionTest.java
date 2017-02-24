package notusing;

import game.code.Rect;

import java.awt.*;

/**
 *
 */
public class CollitionTest extends GameCore {
    Rect r = new Rect(200, 200, 50, 50);
    Rect r2 = new Rect(300, 300, 100, 100);
    // d = (x -x1)Vy - (y-y1)Vx
    // |UxV| = |U| sin theta



    @Override
    public void update() {
        if(isLeftPressed()){
            r.moveLeftBy(10);
        }
        if(isRightPressed()){
            r.moveRightBy(10);
        }

        if(isUpPressed()) {
            r.moveUpBy(10);
        }

        if(isDownPressed()) {
            r.moveDownBy(10);
        }
    }

    @Override
    public void render(Graphics g) {
        if(r != null)
            r.draw(g);

        if(r2 != null)
            r2.draw(g);

        if(r != null && r.hasCollidedWith(r2)) {
            g.drawString("Collided", 40, 40);
        } else {
            g.drawString("Not Collided", 40, 40);
        }
    }

    public static void main(String[] args) {
        new CollitionTest();
    }
}
