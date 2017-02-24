package game.code;


public class Globals {
    public static boolean drawPolygons = false;

    public static void hidePolygons(){
        drawPolygons = false;
    }

    public static void showPolygons() {
        drawPolygons = true;
    }
}
