package TankGame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicIMG {
    //BufferedImage array used to describe graphics
    private BufferedImage[] graphics;

    //String used to read path of the components
    private String componentsSource;

    //creates wall area
    private int wallArea;

    //GraphicIMG constructor
    public GraphicIMG(String componentsSource, int wallArea) throws IOException {
        this.componentsSource = componentsSource;
        this.wallArea = wallArea;
        this.transferGraphics();
    }

    //function that transfers the graphics set which is called in various classes throughout the program
    private void transferGraphics() throws IOException {
        BufferedImage image = ImageIO.read(new File(componentsSource));
        this.graphics = new BufferedImage[image.getWidth() / wallArea];

        int a = 0;
        while (this.graphics.length > a) {
            this.graphics[a] = image.getSubimage(a * this.wallArea, 0, this.wallArea, this.wallArea);
            a = a + 1;
        }
    }

    //acquires the window and returns it
    public BufferedImage acqWindow(int w) {
        BufferedImage aw = this.graphics[w];
        return aw;
    }

    //returns window count
    public int windowTallyFx() {
        int wt = this.graphics.length;
        return wt;
    }
}