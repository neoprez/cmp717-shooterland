package game.code;

import notusing.MoveableObject;

import java.awt.*;

/**
 *
 */
public class Rect extends MoveableObject {
    private int w;
    private int h;

    public Rect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean contains(int mx, int my) {
        return ((mx < x+w) && (mx > x) && (my > y) && (my < y+h));
    }

    private boolean isLeftOf(Rect r) {
        return r.x + r.w >= this.x;
    }

    private boolean isRightOf(Rect r) {
        return r.x <= this.x + this.w;
    }

    private boolean isAboveOf(Rect r) {
        return r.y + r.h >= this.y;
    }

    private boolean isBelowOf(Rect r) {
        return r.y <= this.y + this.h;
    }

    public boolean hasCollidedWith(Rect r) {
        return (isRightOf(r) && isLeftOf(r) && isAboveOf(r) && isBelowOf(r));
    }

    public void draw(Graphics g) {
        g.drawRect(x, y, w, h);
    }
}
