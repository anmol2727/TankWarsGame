package TankGame;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

//Bullet class that creates a subclass of Dynamics
public class Bullet extends Dynamics {
    //variables used to set layout of map
    private int OUTLINE_WIDTH = 40;
    private int OUTLINE_HEIGHT = 30;

    //Widths and heights of each plane
    private int PLANE_WIDTH_ONE = 10;
    private int PLANE_HEIGHT_ONE = 10;
    private int PLANE_WIDTH_TWO = 15;
    private int PLANE_HEIGHT_TWO = 15;

    //Constructors of Console, Tank, User, GraphicIMG, and Wall
    private Console c;
    private Tank tA, tB;
    private User u1;
    private User u2;
    private GraphicIMG detonation, bulletManagement;
    private Wall w;

    //1D array for position of bullet along with the rate
    private int[] position;
    private final int rate = 20;
    private int indicator;
    private int a;
    private int b;

    //Bullet constructor
    public Bullet(Console c, Tank tA, Tank tB, User u1, User u2, Wall w, GraphicIMG bulletManagement, int a, int b, int windowHinder, boolean sequence) {
        super(bulletManagement, a, b, windowHinder, sequence);

        this.c = c;
        this.a = a;
        this.b = b;
        this.u1 = u1;
        this.u2 = u2;
        this.bulletManagement = bulletManagement;
        this.position = tA.acqPosition();
        this.indicator = tA.acqIndicator();
        this.tA = tA;
        this.tB = tB;
        this.w = w;

        try {
            //providing path to small explosion graphic
            detonation = new GraphicIMG("Resources/Explosion_small.png", 0b11000);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //bullets collision with tank
    public boolean tankCollision() {
        //rectangle created around tanks to detect collisions
        Rectangle bullets;
        bullets = new Rectangle(a, b, PLANE_WIDTH_ONE, PLANE_HEIGHT_ONE);
        Rectangle tA_o;
        tA_o = new Rectangle(tA.acqGraphicA(), tA.acqGraphicB(), tA.acqW() - 10, tA.acqH() - 10);
        Rectangle tB_t;
        tB_t = new Rectangle(tB.acqGraphicA(), tB.acqGraphicB(), tB.acqW() - 10, tB.acqH() - 10);

        //conditional statements for when intersection occurs
        if (bullets.intersects(tA_o)) {
            return true;
        }
        if (bullets.intersects(tB_t)) {
            return true;
        }
        return false;
    }

    //when bullet impacts the tank
    public void impactTank() {
        //rectangle created around tanks to detect collisions
        Rectangle bullets;
        bullets = new Rectangle(a, b, PLANE_WIDTH_ONE, PLANE_HEIGHT_ONE);
        Rectangle tA_o;
        tA_o = new Rectangle(tA.acqGraphicA(), tA.acqGraphicB(), tA.acqW() - 10, tA.acqH() - 10);
        Rectangle tB_t;
        tB_t = new Rectangle(tB.acqGraphicA(), tB.acqGraphicB(), tB.acqW() - 10, tB.acqH() - 10);

        //conditional statements for when intersection occurs
        if (bullets.intersects(tA_o)) {
            u1.tankContact();
            if (u1.deceased()) {
                u2.boostScore();
                detonationFx(tA);
                u1.positionInitialSetting();
                tA.positionIndicator(0);
            }
        }

        if (bullets.intersects(tB_t)) {
            u2.tankContact();
            if (u2.deceased()) {
                u1.boostScore();
                detonationFx(tB);
                u2.positionInitialSetting();
                tB.positionIndicator(0);
            }
        }
    }

    //bullets collision with the wall
    public boolean wallCollision() {
        try {
            w.positionWallOutline();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //rectangle created around tanks to detect collisions
        Rectangle bullets;
        bullets = new Rectangle(a, b, PLANE_WIDTH_TWO, PLANE_HEIGHT_TWO);

        //nested while loops to detect collision based on Strings provided
        int y = 0;
        while (OUTLINE_HEIGHT > y) {
            int z = 0;
            while (OUTLINE_WIDTH > z) {
                if (!Objects.equals(w.acqWallOutline()[y][z], "1") && !Objects.equals(w.acqWallOutline()[y][z], "2")) {
                }
                else {
                    Rectangle w2;
                    w2 = new Rectangle(z * w.acqW(), y * w.acqH(), w.acqW(), w.acqH());
                    if (!bullets.intersects(w2)) {
                    }
                    else {
                        return true;
                    }
                }
                z = z + 1;
            }
            y = y + 1;
        }
        return false;
    }

    //when bullet impacts the tank
    public void impactWall() {
        try {
            w.positionWallOutline();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //rectangle created around tanks to detect collisions
        Rectangle bullets;
        bullets = new Rectangle(a, b, PLANE_WIDTH_TWO, PLANE_HEIGHT_TWO);
        //nested while loops to detect collision based on Strings provided
        int y = 0;
        while (OUTLINE_HEIGHT > y) {
            int z = 0;
            while (z < OUTLINE_WIDTH) {
                if (!Objects.equals(w.acqWallOutline()[y][z], "2")) {
                }
                else {
                    Rectangle w2;
                    w2 = new Rectangle(z * w.acqW(), y * w.acqH(), w.acqW(), w.acqH());
                    if (!bullets.intersects(w2)) {
                    }
                    else {
                        w.acqWallOutline()[y][z] = "0";
                        w.positionUtdWallOutline(w.acqWallOutline());
                    }
                }
                z = z + 1;
            }
            y = y + 1;
        }
    }

    //Overridden function to illustrate graphics
    @Override
    public void illustrate(Graphics g) {
        //stops bullet when in collision with wall and tank
        if (!halt) {
            northMotion();
            g.drawImage(bulletManagement.acqWindow(indicator), a, b, null);
            if (!wallCollision()) {
            }
            else {
                impactWall();
                halt = true;
            }
            if (tankCollision()) {
                impactTank();
                halt = true;
            }
        }
    }

    //forward movement of bullet
    public void northMotion() {
        switch (a = a + (int) (Math.cos(Math.toRadians(position[indicator])) * rate)) {
        }
        switch (b = b - (int) (Math.sin(Math.toRadians(position[indicator])) * rate)) {
        }
    }

    //when explosion occurs
    public void detonationFx(Tank deceasedTank) {
        c.initiateDynamics(new Dynamics(this.detonation, deceasedTank.acqGraphicA(), deceasedTank.acqGraphicB(), 5, false));
    }
}