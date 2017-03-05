package game.code;

public class Lookup {
    public static double oneRadian = Math.PI/180.0;
    public static double[] radians = getRadians();
    public static double[] cos = getCos();
    public static double[] sin = getSin();

    public static double[] getCos(){
        double[] cos = new double[360];

        for (int i = 0; i < 360; i++){
            cos[i] = Math.cos(radians[i]);
        }

        return cos;
    }

    public static double[] getSin(){
        double[] sin = new double[360];

        for (int i = 0; i < 360; i++){
            sin[i] = Math.sin(radians[i]);
        }

        return sin;
    }

    public static double[] getRadians() {
        double[] radians = new double[360];

        for(int i = 0; i < 360; i++){
            radians[i] = i * oneRadian;
        }

        return radians;
    }
}
