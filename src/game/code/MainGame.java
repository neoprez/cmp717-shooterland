package game.code;

import javax.swing.*;
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
                        exitKey  = new GameAction("exit", GameAction.DETECT_INITIAL_PRESS_ONLY),
                        showPoly = new GameAction("show polygons", GameAction.DETECT_INITIAL_PRESS_ONLY);
    private GameMap gameMap = new GameMap();
    private Pyro pyro = new Pyro(600, 400, 0);

    public void init() {
        super.init();
//        setFullScreen();
        setWindowMode(1440, 900);
        inputManager = new InputManager(screen.getCurrentWindow());
        mapKeysToActions();
    }

    private void mapKeysToActions() {
        inputManager.mapToKey(leftKey, KeyEvent.VK_LEFT);
        inputManager.mapToKey(rightKey, KeyEvent.VK_RIGHT);
        inputManager.mapToKey(upKey, KeyEvent.VK_UP);
        inputManager.mapToKey(downKey, KeyEvent.VK_DOWN);
        inputManager.mapToKey(exitKey, KeyEvent.VK_ESCAPE);
        inputManager.mapToKey(showPoly, KeyEvent.VK_P);
    }

    public void draw(Graphics2D g) {
        g.clearRect(0, 0, screen.getWidth(), screen.getHeight());
        gameMap.draw(g);
        pyro.draw(g);
//        cowBoy.draw(g);
    }

    public void update(long elapsedTime) {
        checkSystemInput();
        checkGameInput();
//        cowBoy.update();
    }

    private void checkSystemInput() {
        if(exitKey.isPressed()){
            stop();
        }

        if(showPoly.isPressed()){
            Globals.togglePolygons();
        }
    }

    private void checkGameInput() {
        if(leftKey.isPressed()){
            pyro.rotateLeft();
        }

        if(rightKey.isPressed()){
            pyro.rotateRight();
        }

        if(upKey.isPressed()){
            pyro.moveForward();
        }

        if(downKey.isPressed()){
        }
    }

    public static void main(String[] args) {
        MainGame g = new MainGame();
        g.run();
    }
}
