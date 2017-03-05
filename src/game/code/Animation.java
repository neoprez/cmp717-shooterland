package game.code;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class Animation {
    private static final String stand = "stand";
    private ImageIcon[] images;
    private int currentImage = 0;
    private int duration;
    private int timeElapsed = 0;
    private ImageIcon standingImage;

    public Animation(String poseName, String fileNameExt, int count, int duration){
        this.images = new ImageIcon[count];
        for(int i = 0; i < count; i++) {
            images[i] = new ImageIcon(this.getClass().getResource(Globals.resourcesFolder + poseName + i + fileNameExt));
        }
        this.duration = duration;
        this.standingImage = new ImageIcon(this.getClass().getResource(Globals.resourcesFolder + poseName + stand + fileNameExt));
    }

    public void update() {
        if(++timeElapsed >= this.duration){
            currentImage++;
            if(currentImage == images.length-1){
                currentImage = 0;
            }
            timeElapsed = 0;
        }
    }

    public Image getCurrentImage(){
        return images[currentImage].getImage();
    }

    public Image getStandingImage() {
        return standingImage.getImage();
    }
}
