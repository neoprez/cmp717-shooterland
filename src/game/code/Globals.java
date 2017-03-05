package game.code;


public class Globals {
    public static boolean drawPolygons = false;
    public static final String resourcesFolder = "./images/";
    public static boolean loaded = false;

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
