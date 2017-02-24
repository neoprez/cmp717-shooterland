package notusing;

/**
 *
 */
public class CowBoyHat extends Polygon2D {
    private static int startX = 300;
    private static int startY = 300;
    private static final int[] xPoints = {  startX, startX+200, startX+210,
                                            startX+220, startX+240, startX+250,
                                            startX+250, startX+240, startX+220,
                                            startX+210, startX+200, startX
                                         };
    private static final int[] yPoints = { startY, startY, startY+20,
                                           startY+40, startY+60, startY+80,
                                           startY+140, startY+160, startY+180,
                                           startY+200, startY+220, startY+220
                                         };

    public int[] getXPoints() {
        return xPoints;
    }

    public int[] getYPoints() {
        return yPoints;
    }

    public int getNVertices() {
        return xPoints.length;
    }
}
