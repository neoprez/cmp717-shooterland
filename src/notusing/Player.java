package notusing;

import java.awt.*;

public class Player {
    private Polygon polygon;
    private int[] xPoints = new int[]{100, 150, 150, 100, 80};
    private int[] yPoints = new int[]{80, 160, 80, 100, 60};
    private int x;
    private int y;
    private Rectangle rect;

    public Player() {
        polygon = new Polygon(xPoints, yPoints, xPoints.length);
    }

    public int[] getxPoints() {
        return this.polygon.xpoints;
    }

    public int[] getyPoints() {
        return this.polygon.ypoints;
    }

    public void draw(Graphics g) {
        g.drawPolygon(polygon);
    }

    private void moveBy(int dx, int dy){
        polygon.translate(dx, dy);
    }

    public void moveLeftBy(int dx) {
        moveBy(-dx, 0);
    }

    public void moveRightBy(int dx) {
        moveBy(dx, 0);
    }

    public void moveUpBy(int dy) {
        moveBy(0, -dy);
    }

    public void moveDownBy(int dy){
        moveBy(0, dy);
    }
}
