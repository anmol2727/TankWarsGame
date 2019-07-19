package TankGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

//Wall class that creates a subclass of GraphicOBJ
public class Wall extends GraphicOBJ {
    //variables used to set layout of map
    private int OUTLINE_WIDTH = 40;
    private int OUTLINE_HEIGHT = 30;

    //multidimensional array utilizes previous variables to create layout of map
    private String[][] wallOutline = new String [OUTLINE_HEIGHT][OUTLINE_WIDTH];

    //BufferedImage used to describe image of indestructibleWall, BufferedReader to read txt file for map
    private BufferedImage indestructibleWall;
    private static BufferedReader outline;

    //Wall constructor used to read txt file and indestructibleWall
    public Wall(String wallPath) throws IOException {
        super(wallPath);
        outline = new BufferedReader(new FileReader("Resources/BackgroundOutline.txt"));
        indestructibleWall = ImageIO.read(new File("Resources/Wall2.gif"));
    }

    //function to position the walls on map
    public void positionWallOutline() throws IOException {
        int a = 0;
        String ln;

        //loop which executes only when the array is in bounds of what is declared above
        if (!((ln = outline.readLine()) == null)) {
            do {
                int b = 0;
                while (wallOutline[a].length > b) {
                    wallOutline[a][b] = String.valueOf(ln.charAt(b));
                    b = b + 1;
                }
                a = a + 1;
            }
            while (!((ln = outline.readLine()) == null));
        }
    }

    //function to return walls on map
    public String[][] acqWallOutline() {
        String[][] awo = this.wallOutline;
        return awo;
    }

    //Overridden function used to render the graphics
    @Override
    public void render(Graphics g) {
        //nested while loops that set text to be read as walls within outline
        a = 0;
        while (OUTLINE_WIDTH > a) {
            b = 0;
            while (OUTLINE_HEIGHT > b) {
                if (!Objects.equals(wallOutline[b][a], "1")) {
                    if (!Objects.equals(wallOutline[b][a], "2")) {
                    }
                    else {
                        g.drawImage(graphic, a * graphic.getWidth(), b * graphic.getHeight(), overlook);
                    }
                }
                else {
                    g.drawImage(indestructibleWall,
                            a * indestructibleWall.getWidth(),
                            b * indestructibleWall.getHeight(),
                            overlook);
                }
                b = b + 1;
            }
            a = a + 1;
        }
    }

    //function to position the present wall map
    public void positionUtdWallOutline(String[][] utdWallOutline) {
        this.wallOutline = utdWallOutline;
    }
}