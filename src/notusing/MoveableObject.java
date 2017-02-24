package notusing;

/**
 *
 */
public class MoveableObject {
    protected int x;
    protected int y;

    public void moveBy(int dx, int dy) {
        x += dx;
        y += dy;
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

    public void moveDownBy(int dy) {
        moveBy(0, dy);
    }
}
