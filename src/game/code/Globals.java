package game.code;


public class Globals {
    public static boolean drawPolygons = false;
    public static final String resourcesFolder = "./images/";
    public static boolean loaded = false;
    public static final int ORIGIN_X = 800;
    public static final int ORIGIN_Y = 500;
    public static final int ORIGIN_ANGLE = 0;
    public static final int ROTATE_SPEED = 6;
    public static final int CAMERA_SPEED = 12;

    public static void hidePolygons(){
        drawPolygons = false;
    }

    public static void showPolygons() {
        drawPolygons = true;
    }

    public static void togglePolygons() {
        drawPolygons = !drawPolygons;
    }

    public static void setLoaded(boolean loading){
        Globals.loaded = loading;
    }

    public static boolean isGameLoaded(){
        return loaded;
    }
}
