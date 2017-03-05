package game.code;


public class Globals {
    public static boolean drawPolygons = false;
    public static final String resourcesFolder = "./images/";
    public static boolean loading = false;

    public static void hidePolygons(){
        drawPolygons = false;
    }

    public static void showPolygons() {
        drawPolygons = true;
    }

    public static void togglePolygons() {
        drawPolygons = !drawPolygons;
    }

    public static void setLoading(boolean loading){
        Globals.loading = loading;
    }

    public static boolean isGameLoading(){
        return loading;
    }
}
