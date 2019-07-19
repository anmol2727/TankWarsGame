package TankGame;

import java.awt.*;
import java.io.IOException;

//Tank class that creates a subclass of GraphicOBJ
public class Tank extends GraphicOBJ {
    //GraphicIMG constructor for tanks management
    private GraphicIMG tankManagement;

    //variables for present a and b
    private int currA = 0;
    private int currB = 0;

    //variables used to set layout of map
    private int OUTLINE_WIDTH = 40;
    private int OUTLINE_HEIGHT = 30;

    //1D array for the position of the tank
    private int[] position = new int[60];

    private int tri = 10;
    private int scope = 0;
    private int indicator = 0;

    //Tank constructor
    public Tank(String graphicPath) throws IOException {
        //Implements the tank image
        super(graphicPath);
        tankManagement = new GraphicIMG("Resources/Tank1.png", 0b1000000);
    }

    //Overridden function which renders the image of the tank
    @Override
    public void render(Graphics g) {
        g.drawImage(tankManagement.acqWindow(indicator), a, b, overlook);
    }

    //function that positions the scope of the tank
    public void positionScope() {
        //while loop that sets the angle and increments by 6 while the position is greater than a
        int a = 0;
        while (position.length > a) {
            position[a] = scope;
            scope = scope + 6;
            a = a + 1;
        }
    }

    //function to position the indicator
    public void positionIndicator(int indicator) {
        this.indicator = indicator;
    }

    //when one tank intersects with the other, collision is detected
    public boolean tankCollision(Tank opposingTank) {
        Rectangle tankOne;
        tankOne = new Rectangle(currA, currB, acqW() - tri, acqH() - tri);
        Rectangle tankTwo;
        tankTwo = new Rectangle(opposingTank.acqGraphicA(), opposingTank.acqGraphicB(), opposingTank.acqW() - tri, opposingTank.acqH() - tri);
        boolean tc = tankOne.intersects(tankTwo);
        return tc;
    }

    //when a tank intersects with a wall, collision is detected
    public boolean wallCollision(Wall w) {
        try {
            w.positionWallOutline();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Rectangle tank;
        tank = new Rectangle(currA, currB, acqW() - tri, acqH() - tri);
        //nested while loop that sets and recognizes walls as 1 and 2
        int a = 0;
        while (OUTLINE_HEIGHT > a) {
            int b = 0;
            while (OUTLINE_WIDTH > b) {
                if (!(!w.acqWallOutline()[a][b].equals("1") && !w.acqWallOutline()[a][b].equals("2"))) {
                    Rectangle wall;
                    wall = new Rectangle(b * w.acqW(), a * w.acqH(), w.acqW() - tri, w.acqH() - tri);
                    if (!tank.intersects(wall)) {
                    }
                    else {
                        return true;
                    }
                }
                b = b + 1;
            }
            a = a + 1;
        }
        return false;
    }

    //upward motion
    public void motionNorth() {
        switch (currA = this.acqGraphicA() + (int) (Math.cos(Math.toRadians(position[indicator])) * tri)) {
        }
        switch (currB = this.acqGraphicB() - (int) (Math.sin(Math.toRadians(position[indicator])) * tri)) {
        }
    }

    //right motion
    public void motionEast() {
        indicator = indicator > 0 ? indicator - 1 : 59;
        graphic = tankManagement.acqWindow(indicator);
    }

    //downward motion
    public void motionSouth() {
        switch (currA = this.acqGraphicA() - (int) (Math.cos(Math.toRadians(position[indicator])) * tri)) {
        }
        switch (currB = this.acqGraphicB() + (int) (Math.sin(Math.toRadians(position[indicator])) * tri)) {
        }
    }

    //left motion
    public void motionWest() {
        indicator = indicator < 59 ? indicator + 1 : 0;
        graphic = tankManagement.acqWindow(indicator);
    }

    //shooting
    public int shootA() {
        int sa = this.a + this.acqW() / 2 + (int) ((int) (this.acqW() / 2 * (Math.cos(Math.toRadians(50)))) * 2 * (Math.cos(Math.toRadians(this.position[indicator]))));
        return sa;
    }

    //shooting
    public int shootB() {
        int sa = this.b + this.acqW() / 2 - (int) ((int) (this.acqH() / 2 * (Math.cos(Math.toRadians(50)))) * 2 * (Math.sin(Math.toRadians(this.position[indicator]))));
        return sa;
    }

    //return indicator
    public int acqIndicator() {
        int ai = this.indicator;
        return ai;
    }

    //return position
    public int[] acqPosition() {
        int[] ap = this.position;
        return ap;
    }

    //return present a
    public int acqCurrA() {
        int aca = this.currA;
        return aca;
    }

    //return present b
    public int acqCurrB() {
        int acb = this.currB;
        return acb;
    }
}