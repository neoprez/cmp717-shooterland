package game.code;

import java.awt.*;

public abstract class PolygonModel implements Drawable {
    protected int x;
    protected int y;

    protected int angle;

    protected double cosA;
    protected double sinA;

//    int[][] xStruct = getxStruct();
//    int[][] yStruct = getyStruct();
//    Color[] colors = getColors();

    public PolygonModel(int x, int y, int angle){
        this.x = x;
        this.y = y;
        this.angle = angle;
        cosA = Lookup.cos[this.angle];
        sinA = Lookup.sin[this.angle];
    }

    public abstract int[][] getxStruct();
    public abstract int[][] getyStruct();
    public abstract Color[] getColors();

    public void moveForwardBy(int dist){
        /*
         * Translation in the direction theta by a distance d
         *
         * dx = d * cos theta
         * dy = d * sin theta
         */
        moveBy((int)(dist * cosA),(int)(dist * sinA));
    }

    public void moveBy(int dx, int dy) {

        x += dx;
        y += dy;
    }

    public void updateAngle(int angle) {
        this.angle = angle;
        sinA = Lookup.sin[angle];
        cosA = Lookup.cos[angle];
    }

    public void rotateBy(int degrees) {
        angle += degrees;

        if( angle > 359) angle -= 360;
        if( angle <    0) angle += 360;
        /*
		* x' = x*cosA - y*sinA + x
		* y' = x*sinA + y*cosA + y
		*/
        cosA = Lookup.cos[angle];
        sinA = Lookup.sin[angle];
    }

    public void draw(Graphics g) {
//        int[] xpoints = new int[4];
//        int[] ypoints = new int[4];
//
//        for (int poly = 0; poly < xStruct.length; poly++) {
//            for (int vert=0; vert < xStruct[poly].length; vert++) {
//                xpoints[vert] = (int) (xStruct[poly][vert] * cosA - yStruct[poly][vert] * sinA) + this.x;
//                ypoints[vert] = (int) (xStruct[poly][vert] * sinA + yStruct[poly][vert] * cosA) + this.y;
//            }
//            g.setColor(colors[poly]);
//            g.fillPolygon(xpoints, ypoints, xStruct[poly].length);
//            g.setColor(Color.black);
//            g.drawPolygon(xpoints, ypoints, xStruct[poly].length);
//        }
    }
}
