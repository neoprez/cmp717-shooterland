package game.code;

import java.awt.*;

/**
 *
 */
public class MainGame extends GameCore {
    private CowBoy cowBoy = new CowBoy(500, 500);

    private void readInput() {
        if(isLeftPressed()){
            cowBoy.moveLeft();
        }

        if(isUpPressed()){
            cowBoy.moveUp();
        }

        if(isDownPressed()){
            cowBoy.moveDown();
        }

        if(isRightPressed()){
            cowBoy.moveRight();
        }
    }

    public void update() {
        if(cowBoy != null){
            readInput();
        }
    }

    @Override
    public void render(Graphics g) {
        if(cowBoy != null){
            cowBoy.draw(g);
        }
    }

//    public static void main(String[] args) {
//        new MainGame();
//    }
}
