package TankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Console extends JPanel implements Runnable {
    //setting screen dims
    private final int SCREEN_WIDTH = 1280;
    private final int SCREEN_HEIGHT = 960;
    private final int CONSOLE_HEIGHT = 700;

    //Dynamics ArrayList and Graphics subclass
    private ArrayList <Dynamics> dynamics;
    private Graphics sG;

    //providing path of resources
    private final String backgroundGraphic = "Resources/Background.bmp";
    private final String tankGraphic = "Resources/Tank1.png";

    //Dimension subclass used with constructors of GraphicOBJ, Tank, and User
    private Dimension dim;
    private GraphicOBJ background;
    Wall breakableWall;
    Tank t1, t2;
    User u1, u2;

    //BufferedImage used to describe image of sGraphic as well as the two tanks
    private BufferedImage sGraphic = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage t1Graphic = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage t2Graphic = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);

    //Console constrictor processes the graphics of breakable wall and positions tanks
    Console() {
        try {
            this.background = new GraphicOBJ(backgroundGraphic);
            this.t1 = new Tank(tankGraphic);
            this.t2 = new Tank(tankGraphic);
            this.breakableWall = new Wall("Resources/Wall1.gif");
            this.breakableWall.positionWallOutline();
            u1 = new User(t1, breakableWall, "3");
            u2 = new User(t2, breakableWall, "4");
            u1.positionInitialSetting();
            u2.positionInitialSetting();
        }
        catch (IOException e) {
            System.err.println("Error loading images");
            e.printStackTrace();
        }
        this.dynamics = new ArrayList<>();
        this.dim = new Dimension(SCREEN_WIDTH, CONSOLE_HEIGHT);
    }

    //dynamics are executed
    public void initiateDynamics(Dynamics d) {
        dynamics.add(d);
    }

    //acquires setting of tank a
    public int acqTankSettingA(Tank t) {
        int settingA;
        settingA = t.acqGraphicA() - SCREEN_WIDTH / 4;

        if (0 <= settingA) {
        }
        else {
            settingA = 0;
        }
        if ((SCREEN_WIDTH / 2) >= settingA) {
            return settingA;
        }
        settingA = (SCREEN_WIDTH /2);
        return settingA;
    }

    //acquires setting of tank b
    public int acqTankSettingB(Tank t) {
        int settingB;
        settingB = t.acqGraphicB() - CONSOLE_HEIGHT / 2;

        if (0 <= settingB) {
        }
        else {
            settingB = 0;
        }
        if ((SCREEN_HEIGHT - CONSOLE_HEIGHT) >= settingB) {
            return settingB;
        }
        settingB = (SCREEN_HEIGHT - CONSOLE_HEIGHT);
        return settingB;
    }

    @Override
    public void paintComponent(Graphics g) {
        //background is set as back image with rest rendering over it
        super.paintComponent(g);
        sG = sGraphic.createGraphics();
        int a = 0;
        while (SCREEN_WIDTH > a) {
            int b = 0;
            while (SCREEN_HEIGHT > b) {
                background.positionGraphicA(a);
                background.positionGraphicB(b);
                background.render(sG);
                b = b + background.acqH();
            }
            a = a + background.acqW();
        }

        //Dynamics constructor used in while loop with conditional statements to terminate program
        Dynamics dyn;
        int tally = 0;
        while (dynamics.size() > tally) {
            dyn = dynamics.get(tally);
            if (!dyn.terminated()) {
                dyn.illustrate(sG);
            }
            else {
                dynamics.remove(tally);
            }
            tally = tally + 1;
        }

        //renders the breakable wall
        breakableWall.render(sG);
        t1.render(sG);
        t2.render(sG);
        sG = g;

        //used to create minimap
        t1Graphic = sGraphic.getSubimage(acqTankSettingA(t1), acqTankSettingB(t1), SCREEN_WIDTH / 2, CONSOLE_HEIGHT);
        t2Graphic = sGraphic.getSubimage(acqTankSettingA(t2), acqTankSettingB(t2), SCREEN_WIDTH / 2, CONSOLE_HEIGHT);

        //positions tanks and minimap on console
        sG.drawImage(t1Graphic, 0, 0, SCREEN_WIDTH / 2 - 1, CONSOLE_HEIGHT, this);
        sG.drawImage(t2Graphic, SCREEN_WIDTH / 2, 0, SCREEN_WIDTH / 2, CONSOLE_HEIGHT, this);
        sG.drawImage(sGraphic, SCREEN_HEIGHT / 2, 450, 320, 250, this);

        //positions score on console
        u1.presentScore(sG, 340, CONSOLE_HEIGHT - 675);
        u2.presentScore(sG, SCREEN_WIDTH - 25, CONSOLE_HEIGHT - 675);

        //positions condition on console
        u1.presentCondition(sG, 5, CONSOLE_HEIGHT - 695);
        u2.presentCondition(sG, SCREEN_WIDTH / 2 + 280, CONSOLE_HEIGHT - 695);

        //positions lifes on console
        u1.presentLifes(sG, 230, CONSOLE_HEIGHT - 695);
        u2.presentLifes(sG, SCREEN_WIDTH / 2 + 505, CONSOLE_HEIGHT - 695);

    }

    //Overridden function that returns the gps
    @Override
    public Dimension getPreferredSize() {
        Dimension gps = this.dim;
        return gps;
    }
}