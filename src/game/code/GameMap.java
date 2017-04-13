package game.code;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameMap implements Drawable {
    private Image img = new ImageIcon(this.getClass().getResource("./images/world/texture1.png")).getImage();
    private int w1 = img.getWidth(null);
    private int w2 = w1*2;
    private int w3 = w1*3;

    private int h1 = img.getHeight(null);
    private int h2 = h1*2;
    private int h3 = h1*3;
    private ArrayList<Drawable> worldObjects = new ArrayList<>();
    int[][] mapCoordinates = {
                                {0, w1, w2, w3}, // x's
                                {0, h1, h2, h3}, // y's
                             };

    public void draw(Graphics2D g) {
        for(int r = 0; r < mapCoordinates[0].length; r++){
            for(int c = 0; c < mapCoordinates[1].length; c++){
                g.drawImage(img, mapCoordinates[0][r] - Camera2D.getX(), mapCoordinates[1][c] - Camera2D.getY(), null);
            }
        }

        for(Drawable d : worldObjects){
            d.draw(g);
        }
    }

    public void update(long elapsedTime){
        ArrayList<Integer> toDelete = new ArrayList<>();

        /*
         * Remove not visible objects.
         */
        for(int i = 0; i < worldObjects.size(); i++){
            if(worldObjects.get(i) instanceof TransformableObject && !((TransformableObject)worldObjects.get(i)).isAlive()) {
                toDelete.add(i);
            }
        }

        worldObjects.removeAll(toDelete);

        for(Drawable d : worldObjects){
            d.update(elapsedTime);
        }
        /*
         * Sort by x axis to improve collisions.
         */
        Collections.sort(worldObjects);
    }

    public void addWorldObject(Drawable obj){
        worldObjects.add(obj);
    }

    public int compareTo(Object o){
        return 0;
    }
}
