package game.code;


public class Camera2D {
    static int x;
    static int y;
    static Circle c = new Circle(Globals.ORIGIN_X, Globals.ORIGIN_Y, 0, Globals.ORIGIN_ANGLE);

    public static void moveUpBy(int dy){
        y -= dy;
    }

    public static void moveDownBy(int dy){
        y += dy;
    }

    public static void moveLeftBy(int dx){
        x -= dx;
    }

    public static void moveRightBy(int dx){
        x += dx;
    }

    public static void moveForward(){
        c.moveForwardBy(Globals.CAMERA_SPEED);
    }

    public static void rotateRight(){
        c.rotateBy(Globals.ROTATE_SPEED);
    }

    public static void rotateLeft() {
        c.rotateBy(-Globals.ROTATE_SPEED);
    }

    public static int getX() {
        return c.x;
    }

    public static int getY() {
        return c.y;
    }
}
