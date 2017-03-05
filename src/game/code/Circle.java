package game.code;

import java.awt.*;

public class Circle extends PolygonModel {
    int r;
    double d;
    int diameter;

    public Circle(int x, int y, int r, int angle){
        super(x, y, angle);
        this.r = r;
        diameter = r << 1;
    }

    public int[][] getxStruct(){
        return null;
    }
    public int[][] getyStruct(){
        return null;
    }
    public Color[] getColors(){
        return null;
    }

    public void turnToward(PolygonModel p) {
        double d = (p.x - x) * sinA - (p.y - y) * cosA;

        if( d > -4) {
            rotateBy(-2);
        }

        if( d < 4 ) {
            rotateBy(2);
        }
    }

    public void turnAwayFrom(PolygonModel p) {
        double d = (p.x - x) * sinA - (p.y - y) * cosA;

        if( d > -4) {
            rotateBy(2);
        }

        if( d < 4 ) {
            rotateBy(-2);
        }
    }

    public boolean hasCollidedWith(Line L) {
        d = L.distanceTo(x ,y);

        return d < r;
    }


    public void pushedBackFrom(Line L) {
        x += (r-d) * L.xn;
        y += (r-d) * L.yn;
    }

    public boolean hasCollidedWith(Circle c) {
        int dx = x - c.x;
        int dy = y - c.y;
        int d = r + c.r;
        return dx * dx + dy * dy < d * d; // true if a collision is found
    }


    public void draw(Graphics g){
        g.setColor(Color.white);
        g.drawOval(x-r, y-r, diameter, diameter);
        g.drawLine(x, y, (int)(x + r * cosA), (int)(y + r * sinA) );
    }

    public int computedX(){
        return (int)(x + r * cosA);
    }

    public int computedY() {
        return (int)(y + r * sinA);
    }
}
