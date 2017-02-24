package game.code;

/**
 *
 */
public class CowBoy extends Sprite {
    private static final String[] names = {
                                            "cowboy_dn_walk_",
                                            "cowboy_lt_walk_",
                                            "cowboy_up_walk_",
                                            "cowboy_rt_walk_"
                                          };
    private static final int[] counts = { 9, 9, 9, 9 };
    private static final int[] durations = { 5, 5, 5, 5 };
    private static final String ext = ".png";
    private static final int moveSpeed = 5;

    public CowBoy(int x, int y) {
        super(x, y, names, ext, counts, durations);
    }

    public void moveLeft() {
        moveLeftBy(moveSpeed);
    }

    public void moveRight() {
        moveRightBy(moveSpeed);
    }

    public void moveDown() {
        moveDownBy(moveSpeed);
    }

    public void moveUp() {
        moveUpBy(moveSpeed);
    }
}
