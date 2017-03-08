package game.code;

import java.awt.*;

public interface Drawable extends Comparable {
    void update(long elapsedTime);
    void draw(Graphics2D g);
    int compareTo(Object o);
}
