package game.code;

public interface Collidable {
    boolean hasCollidedWith(Rect r);
    boolean hasCollidedWith(Circle c);
}
