package game.code;

public class Lookup {
    public static double oneRadian = Math.PI/180;
    public static double[] cos = getCos();
    public static double[] sin = getSin();

    public static double[] getCos(){
        double[] cos = new double[360];

        for (int i = 0; i < 360; i++){
            cos[i] = Math.cos(i * oneRadian);
        }

        return cos;
    }

    public static double[] getSin(){
        double[] sin = new double[360];

        for (int i = 0; i < 360; i++){
            sin[i] = Math.sin(i * Math.PI/180);
        }

        return sin;
    }
}
