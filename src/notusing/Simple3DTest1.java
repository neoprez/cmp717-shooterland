package notusing;

import game.code.GameCore;

import java.awt.*;
import java.awt.geom.GeneralPath;

/**
 * A 3D test to demonstrate drawing polygons.
 */
public class Simple3DTest1 extends GameCore {
    public static void main(String[] args) {
        new Simple3DTest1();
    }

    private SolidPolygon3D treeLeaves = new SolidPolygon3D(
            new Vector3D(-50, -35, 0),
            new Vector3D(50, -35, 0),
            new Vector3D(0, 150, 0)
    );

    private SolidPolygon3D treeTrunk = new SolidPolygon3D (
            new Vector3D(-5, -50, 0),
            new Vector3D(5, -50, 0),
            new Vector3D(5, -35, 0),
            new Vector3D(-5, -35, 0)
    );

    private Transform3D treeTransform = new Transform3D(0, 0, -500);
    private Polygon3D transformedPolygon = new Polygon3D();
    private ViewWindow viewWindow;

    public Simple3DTest1() {
        super();
        initialize();
    }

    public void initialize() {
        viewWindow = new ViewWindow(0, 0, getWidth(),
                getHeight(), (float)Math.toRadians(75));

        // give the polygons color
        treeLeaves.setColor(new Color(0x008000));
        treeTrunk.setColor(new Color(0x714311));
    }

    public void update() {
        long elapsedTime = 100;
        // elapsedTime
        elapsedTime = Math.min(elapsedTime, 100);

        // rotate around the y-axis
        if(treeTransform != null) {
            treeTransform.rotateAngleY(0.002f * elapsedTime);

            // allow user to zoom in/out
            if (isUpPressed()) {
                treeTransform.getLocation().z += 0.5f * elapsedTime;
            }

            if (isDownPressed()) {
                treeTransform.getLocation().z -= 0.5f * elapsedTime;
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.white);
        g.drawString("Press up/down to zoom. Press Esc to exit.", 20, 50);

        // draw the tree polygons
        transformAndDraw((Graphics2D)g, treeTrunk);
        transformAndDraw((Graphics2D)g, treeLeaves);
    }

    /**
     * Projects and draws a polygon onto the view window.
     */
    private void transformAndDraw(Graphics2D g, SolidPolygon3D poly) {
        transformedPolygon.setTo(poly);

        // translate and rotate the polygon
        transformedPolygon.add(treeTransform);

        // project the polygon to the screen
        transformedPolygon.project(viewWindow);

        // convert the polygon to a Java2D GeneralPath and draw it
        GeneralPath path = new GeneralPath();
        Vector3D v = transformedPolygon.getVertex(0);
        path.moveTo(v.x, v.y);

        for(int i = 1; i < transformedPolygon.getNumVertices(); i++) {
            v = transformedPolygon.getVertex(i);
            path.lineTo(v.x, v.y);
        }

        g.setColor(poly.getColor());
        g.fill(path);
    }


}
