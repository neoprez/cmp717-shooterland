package game.code;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 *
 */
public class MainGame extends GameCore {
    private CowBoy cowBoy = new CowBoy(500, 500);
    private InputManager inputManager;
    private GameAction  leftKey  = new GameAction("left"),
                        rightKey = new GameAction("right"),
                        upKey    = new GameAction("up"),
                        downKey  = new GameAction("down"),
                        exitKey  = new GameAction("exit", GameAction.DETECT_INITIAL_PRESS_ONLY);

    public void init() {
        super.init();
        inputManager = new InputManager(screen.getFullScreenWindow());
        mapKeysToActions();
    }

    private void mapKeysToActions() {
        inputManager.mapToKey(leftKey, KeyEvent.VK_LEFT);
        inputManager.mapToKey(rightKey, KeyEvent.VK_RIGHT);
        inputManager.mapToKey(upKey, KeyEvent.VK_UP);
        inputManager.mapToKey(downKey, KeyEvent.VK_DOWN);
        inputManager.mapToKey(exitKey, KeyEvent.VK_ESCAPE);
    }

    public void draw(Graphics2D g) {
        g.clearRect(0, 0, screen.getWidth(), screen.getHeight());
        cowBoy.draw(g);
    }

    public void update(long elapsedTime) {
        checkSystemInput();
        checkGameInput();
        cowBoy.update();
    }

    private void checkSystemInput() {
        if(exitKey.isPressed()){
            stop();
        }
    }

    private void checkGameInput() {
        if(leftKey.isPressed()){
            cowBoy.moveLeft();
        }

        if(rightKey.isPressed()){
            cowBoy.moveRight();
        }

        if(upKey.isPressed()){
            cowBoy.moveUp();
        }

        if(downKey.isPressed()){
            cowBoy.moveDown();
        }
    }

    public static void main(String[] args) {
        MainGame g = new MainGame();
        g.run();
    }
}
