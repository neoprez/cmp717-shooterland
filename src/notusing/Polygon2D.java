package notusing;

import java.awt.*;

/**
 *
 */
public class Polygon2D {
    private int[] xPoints;
    private int[] yPoints;
    private int nVertices;

    public Polygon2D(int[] xPoints, int[] yPoints, int nVertices) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.nVertices = nVertices;
    }

    public Polygon2D(){
        this.xPoints = getXPoints();
        this.yPoints = getYPoints();
        this.nVertices = getNVertices();
    }

    public int[] getXPoints(){
        return null;
    }

    public int[] getYPoints(){
        return null;
    }

    public int getNVertices() {
        return 0;
    }


    public void draw(Graphics g) {
        g.drawPolygon(xPoints, yPoints, nVertices);
    }
}
