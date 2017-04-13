package game.code;

import java.awt.*;

/**
 *
 */
public class Rect extends PolygonModel implements Drawable{
    private int w;
    private int h;

    public Rect(int x, int y, int w, int h) {
        super(x, y, 0);
        this.w = w;
        this.h = h;
    }

    public Rect(Rect r){
        this(r.x, r.y, r.w, r.h);
        this.angle = r.angle;
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

    public void draw(Graphics2D g) {
        g.drawRect(x, y, w, h);
    }

    @Override
    public void update(long elapsedTime) {
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public int[][] getxStruct() {
        return new int[0][];
    }

    @Override
    public int[][] getyStruct() {
        return new int[0][];
    }

    @Override
    public Color[] getColors() {
        return new Color[0];
    }
}
