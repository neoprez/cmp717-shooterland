package game.code;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 *
 */
public class MainGame extends GameCore {
    private InputManager inputManager;
    private GameAction  leftKey  = new GameAction("left"),
                        rightKey = new GameAction("right"),
                        upKey    = new GameAction("up"),
                        downKey  = new GameAction("down"),
                        rotateKey = new GameAction("rotate"),
                        exitKey  = new GameAction("exit", GameAction.DETECT_INITIAL_PRESS_ONLY),
                        showPoly = new GameAction("show polygons", GameAction.DETECT_INITIAL_PRESS_ONLY),
                        shoot    = new GameAction("shoot", GameAction.DETECT_INITIAL_PRESS_ONLY);
    private GameMap gameMap = new GameMap();
    private Pyro pyro;
    private Heavy heavy;
    private ResourcesLoader resourcesLoader = new ResourcesLoader();
    private Font loadingFont = new Font("Courier New", Font.BOLD, 72);

    public void init() {
        super.init();
//        setFullScreen();
        setWindowMode(1440, 900);
        inputManager = new InputManager(screen.getCurrentWindow());
        mapKeysToActions();
        loadResouces();
    }

    public void loadResouces() {
        new Thread(){
            public void run(){
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e){}
                resourcesLoader.loadResources();
                pyro = resourcesLoader.getPyro();
                pyro.setOrigin(500, 500, 90);
                heavy = resourcesLoader.getHeavy();
                heavy.setOrigin(800, 500);
                heavy.addWeapon(resourcesLoader.getYellowPlazmaWeapon());
                Globals.setLoaded(true);
            }
        }.start();
    }

    private void mapKeysToActions() {
        inputManager.mapToKey(leftKey, KeyEvent.VK_LEFT);
        inputManager.mapToKey(rightKey, KeyEvent.VK_RIGHT);
        inputManager.mapToKey(upKey, KeyEvent.VK_UP);
        inputManager.mapToKey(downKey, KeyEvent.VK_DOWN);
        inputManager.mapToKey(exitKey, KeyEvent.VK_ESCAPE);
        inputManager.mapToKey(showPoly, KeyEvent.VK_P);
        inputManager.mapToKey(shoot, KeyEvent.VK_SPACE);
        inputManager.mapToKey(rotateKey, KeyEvent.VK_R);
    }

    public void draw(Graphics2D g) {
        g.clearRect(0, 0, screen.getWidth(), screen.getHeight());
        if(Globals.isGameLoaded()) {
            gameMap.draw(g);
//            pyro.draw(g);
            heavy.draw(g);
        } else {
            g.setFont(loadingFont);
            g.setBackground(Color.black);
            g.setColor(Color.white);
            g.drawString("Loading...", 500, 500);
        }
    }

    public void update(long elapsedTime) {
        checkSystemInput();
        if(Globals.isGameLoaded()){
            checkGameInput();
            heavy.update(elapsedTime);
            gameMap.update(elapsedTime);
        }
    }

    private void checkSystemInput() {
        if(exitKey.isPressed()){
            stop();
        }

        if(showPoly.isPressed()){
            Globals.togglePolygons();
        }
    }

    int MOVEMENT = 10;

    private void checkGameInput() {

        if (leftKey.isPressed()) {
            Camera2D.rotateLeft();
            heavy.rotateLeft();
//            Camera2D.moveLeftBy(MOVEMENT);

        }

        if (rightKey.isPressed()) {
            Camera2D.rotateRight();
            heavy.rotateRight();
//            Camera2D.moveRightBy(MOVEMENT);

        }


        if(upKey.isPressed()){
//            Camera2D.moveUpBy(MOVEMENT);
//            Camera2D.rotateLeft();
            Camera2D.moveForward();
            heavy.moveForward();
        }

        if(downKey.isPressed()){
//            Camera2D.moveDownBy(MOVEMENT);
//            Camera2D.rotateRight();
        }

        if(shoot.isPressed()){
            if(heavy.canShoot()) {
                gameMap.addWorldObject(heavy.shoot());
            }
        }
    }

    public static void main(String[] args) {
        MainGame g = new MainGame();
        g.run();
    }
}
