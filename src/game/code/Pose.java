package game.code;

/**
 *
 */
public enum Pose {
    DOWN(0),
    LEFT(1),
    UP(2),
    RIGHT(3);

    public final int index;
    Pose(int index) {
        this.index = index;
    }
}