package notusing;

import java.awt.*;

/**
 *
 */
public class Circle {
    private int x;
    private int y;
    private int r;
    private int diameter;

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.diameter = 2*r;
    }

    public void draw(Graphics g) {
        g.drawOval(x, y, x+diameter, y+diameter);
    }
}
