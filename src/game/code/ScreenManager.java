package game.code;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * The ScreenManager class manages initializing and displaying
 * full screen graphics modes.
 */
public class ScreenManager {
    private GraphicsDevice device;
    private JFrame currentWindow;
    private static final int FULL_SCREEN = 0;
    private static final int WINDOW = 1;
    private int currentMode = WINDOW;

    /**
     * Creates a new ScreenManager object.
     */
    public ScreenManager() {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = environment.getDefaultScreenDevice();
    }

    /**
     * Returns a list of compatible display modes for the
     * default device on the system.
     */
    public DisplayMode[] getCompatibleDisplayModes() {
        return device.getDisplayModes();
    }

    /**
     * Returns the first compatible mode in a list of modes.
     * Returns null if no modes are compatible.
     */
    public DisplayMode findFirstCompatibleMode(DisplayMode[] modes) {
        DisplayMode[] goodModes = device.getDisplayModes();

        for(int i = 0; i < modes.length; i++) {
            for(int j = 0; j < goodModes.length; j++){
                if(displayModesMatch(modes[i], goodModes[j])){
                    return modes[i];
                }
            }
        }

        return null;
    }

    /**
     * Returns the current display mode.
     */
    public DisplayMode getCurrentDisplayMode() {
        return device.getDisplayMode();
    }

    /**
     * Determines if two display modes "match". Two display
     * modes match if they have the same resolution, bit depth,
     * and refresh rate. The bit depth is ignored if one of the
     * modes has a bit depth of DisplayMode.BIT_DEPTH_MULTI.
     * Likewise, the refresh rate is ignored if one of the
     * modes has a refresh rate of
     * DisplayMode.REFRESH_RATE_UNKNOWN.
     */
    public boolean displayModesMatch(DisplayMode mode1, DisplayMode mode2) {
        if(mode1.getWidth() != mode2.getWidth() || mode1.getHeight() != mode2.getHeight()) {
            return false;
        }

        if(mode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
                mode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
                mode1.getBitDepth() != mode2.getBitDepth() ) {
            return false;
        }

        if(mode1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN &&
                mode2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN &&
                mode1.getRefreshRate() != mode2.getRefreshRate() ) {
            return false;
        }

        return true;
    }

    /**
     * Enters full screen mode and changes the display mode.
     * If the specified display mode is null or not compatible
     * with this device, or ifthe display mode cannot be
     * changed on this system, the current diplay mode is used.
     * <p>
     *  The display uses a BufferStrategy with 2 buffers.
     * </p>
     * Double buffering, page flipping, and waiting for the monitor
     * refresh all are handled by the BufferStrategy class.
     * BufferStrategy chooses the best buffering method based on
     * capabilities of the system.
     */
    public void setFullScreen(DisplayMode displayMode) {
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setIgnoreRepaint(true);
        frame.setResizable(false);

        device.setFullScreenWindow(frame);
        if(displayMode != null && device.isDisplayChangeSupported()) {
            try {
                device.setDisplayMode(displayMode);
            } catch (IllegalArgumentException ex) {}
        }
        frame.createBufferStrategy(2);
        currentWindow = frame;
    }

    public void setWindowMode(int width, int height){
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setIgnoreRepaint(true);
        frame.setVisible(true);
        frame.createBufferStrategy(2);
        currentWindow = frame;
    }

    public JFrame getCurrentWindow() {
        return currentWindow;
    }

    /**
     * Gets the graphics context for the display. The
     * ScreenManager uses double buffering, so applications must
     * call update() to show any graphics drawn.
     * <p>
     *     The application must dispose of the graphics object.
     * </p>
     */
    public Graphics2D getGraphics() {
        if(currentMode == WINDOW)
            return (Graphics2D)currentWindow.getGraphics();

        Window window = device.getFullScreenWindow();
        if(window != null) {
            BufferStrategy strategy = window.getBufferStrategy();
            return (Graphics2D)strategy.getDrawGraphics();
        } else {
            return null;
        }
    }

    /**
     * Updates the display.
     *
     * This method makes sure the display is synchronized with the window system.
     * Without calling this method, some Linux systems might experience
     * delayed mouse and keyboard input events
     */
    public void update() {
        Window window = device.getFullScreenWindow();
        if(window != null) {
            BufferStrategy strategy = window.getBufferStrategy();
            if(!strategy.contentsLost()) {
                strategy.show();
            }
        }
        // Sync the display on some systems.
        // (on Linux, this fixes event queue problems)
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Returns the window currently used in full screen mode.
     * Returns null if the device is not in full screnn mode.
     */
    public Window getFullScreenWindow() {
        return device.getFullScreenWindow();
    }

    /**
     * Returns the width of the window currently used in full
     * screnn mode. Returns 0 if the device is not in full
     * screen mode.
     */
    public int getWidth() {
        Window window = device.getFullScreenWindow();
        if(window != null) {
            return window.getWidth();
        } else {
            return 0;
        }
    }

    /**
     * Returns the height of the window currently used in full
     * screen mode. Returns 0 if the device is not in full
     * screen mode.
     */
    public int getHeight() {
        Window window = device.getFullScreenWindow();
        if(window != null) {
            return window.getHeight();
        } else  {
            return 0;
        }
    }

    /**
     * Restores the screen's display mode.
     */
    public void restoreScreen() {
        if(currentMode == WINDOW) {
            currentWindow.dispose();
            return;
        }

        Window window = device.getFullScreenWindow();
        if(window != null) {
            window.dispose();
        }
        device.setFullScreenWindow(null);
    }

    /**
     * Creates an image compatible with the current display.
     */
    public BufferedImage createCompatibleImage(int w, int h, int transparency) {
        Window window = device.getFullScreenWindow();
        if(window != null) {
            GraphicsConfiguration gc = window.getGraphicsConfiguration();
            return gc.createCompatibleImage(w, h, transparency);
        }
        return null;
    }
}
