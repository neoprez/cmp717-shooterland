package game.code;

import java.awt.*;

public class Line {
    int x1;
    int y1;

    int x0;
    int y0;

    double xv, yv;

    double xn, yn; // normal vector to V.

    public Line(int x1, int y1, int x0, int y0) {
        this.x1 = x1;
        this.y1 = y1;

        this.x0 = x0;
        this.y0 = y0;

        double magnitude = Math.sqrt((x0-x1)*(x0-x1) + (y0-y1)*(y0-y1));
        xv = (x0 - x1)/magnitude;
        yv = (y0 - y1)/magnitude;

        xn = yv;
        yn = -xv;
    }

    public double distanceTo(int x, int y) {
        int xu = x - x1;
        int yu = y - y1;

        return yv * xu - xv * yu;
    }

    public void draw(Graphics g){
        g.drawLine(x1, y1, x0, y0);
    }
}
