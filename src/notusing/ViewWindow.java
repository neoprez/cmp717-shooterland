package notusing;

import java.awt.*;

/**
 * The ViewWindow class represents the geometry of a view window
 * for 3D viewing.
 */
public class ViewWindow {
    private Rectangle bounds;
    private float angle;
    private float distanceToCamera;

    public ViewWindow(int left, int top, int width, int height, float angle) {
        bounds = new Rectangle();
        this.angle = angle;
        setBounds(left, top, width, height);
    }

    /**
     * Sets the bounds for this ViewWindow on the screen.
     */
    public void setBounds(int left, int top, int width, int height) {
        bounds.x = left;
        bounds.y = top;
        bounds.width = width;
        bounds.height = height;
        distanceToCamera = (bounds.width/2)/(float)Math.tan(angle/2);
    }

    /**
     * Sets the horizontal view angle for this ViewWindow.
     */
    public void setAngle(float angle) {
        this.angle = angle;
        distanceToCamera = (bounds.width/2) / (float)Math.tan(angle/2);
    }

    public float getAngle() {
        return angle;
    }

    /**
     * Gets the width of this view window.
     */
    public int getWidth() {
        return bounds.width;
    }

    public int getHeight() {
        return bounds.height;
    }

    /**
     * Gets the y offset of this view window on the screen.
     */
    public int getTopOffset() {
        return bounds.y;
    }

    /**
     * Gets the x offset of this view window on the screen.
     */
    public int getLeftOffset() {
        return bounds.x;
    }

    /**
     * Gets the distance from the camera to this view window.
     */
    public float getDistance() {
        return distanceToCamera;
    }

    /**
     * Converts an x coordinate on this view window tothe corresponding
     * x coordinate on the screen.
     */
    public float convertFromViewXToScreenX(float x) {
        return x + bounds.x + bounds.width/2;
    }

    public float convertFromViewYToScreenY(float y) {
        return -y + bounds.y + bounds.height/2;
    }

    /**
     * Converts an x coordinate on the screen to the
     * corresponding x coordinate on this view window.
     */
    public float convertFromScreenXToViewX(float x) {
        return x - bounds.x - bounds.width/2;
    }

    public float convertFromScreenYToViewY(float y) {
        return -y + bounds.y + bounds.height/2;
    }

    /**
     * Projects the specified vector to the screen.
     */
    public void project(Vector3D v) {
        v.x = distanceToCamera * v.x / -v.z;
        v.y = distanceToCamera * v.y / -v.z;

        // convert to screen coordinates
        v.x = convertFromViewXToScreenX(v.x);
        v.y = convertFromViewYToScreenY(v.y);
    }
}
