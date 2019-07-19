package TankGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import static java.lang.Thread.sleep;

public class TankControl extends Console implements KeyListener {
    //first set of tank controls
    private boolean upKey;
    private boolean rightKey;
    private boolean downKey;
    private boolean leftKey;
    private boolean enterKey;

    //second set of tank controls
    private boolean wKey;
    private boolean dKey;
    private boolean sKey;
    private boolean aKey;
    private boolean fKey;

    //GraphicIMG constructors for explosion and bullet
    private GraphicIMG detonation;
    private GraphicIMG bullet;

    //TankControl constructor
    public TankControl() {
        super();
        this.setFocusable(true);
        this.addKeyListener(this);
        new Thread(this).start();
        //implementing explosion and rocket images
        try {
            this.detonation = new GraphicIMG("Resources/Explosion_small.png", 32);
            this.bullet = new GraphicIMG("Resources/Rocket.png", 24);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        t1.positionScope();
        t2.positionScope();

        //while loop that calls all the functions to perform proper controls(motion, shooting, etc.)
        while (true) {
            if (u2.gameOver() || u1.gameOver()) {
            }
            else {
                if (!upKey) {
                }
                else {
                    t2.motionNorth();
                    if ((t2.wallCollision(breakableWall)) || (t2.tankCollision(t1))) {
                    }
                    else {
                        t2.positionGraphicA(t2.acqCurrA());
                        t2.positionGraphicB(t2.acqCurrB());
                    }
                }
                if (!rightKey) {
                }
                else {
                    t2.motionEast();
                }
                if (!downKey) {
                }
                else {
                    t2.motionSouth();
                    if ((t2.wallCollision(breakableWall)) || (t2.tankCollision(t1))) {
                    }
                    else {
                        t2.positionGraphicA(t2.acqCurrA());
                        t2.positionGraphicB(t2.acqCurrB());
                    }
                }
                if (!leftKey) {
                }
                else {
                    t2.motionWest();
                }
                if (!enterKey) {
                }
                else {
                    initiateDynamics(new Dynamics(this.detonation, t2.shootA(), t2.shootB(),1, false));
                    initiateDynamics(new Bullet(this, t2, t1, u2, u1, breakableWall, this.bullet, t2.shootA(), t2.shootA(), 5, false));
                }

                if (!wKey) {
                }
                else {
                    t1.motionNorth();
                    if ((t1.wallCollision(breakableWall)) || (t1.tankCollision(t2))) {
                    }
                    else {
                        t1.positionGraphicA(t1.acqCurrA());
                        t1.positionGraphicB(t1.acqCurrB());
                    }
                }
                if (!dKey) {
                }
                else {
                    t1.motionEast();
                }
                if (!sKey) {
                }
                else {
                    t1.motionSouth();
                    if ((t1.wallCollision(breakableWall)) || (t1.tankCollision(t2))) {
                    }
                    else {
                        t1.positionGraphicA(t1.acqCurrA());
                        t1.positionGraphicB(t1.acqCurrB());
                    }
                }
                if (!aKey) {
                }
                else {
                    t1.motionWest();
                }
                if (!fKey) {
                }
                else {
                    initiateDynamics(new Dynamics(this.detonation, t1.shootA(), t1.shootB(),1, false));
                    initiateDynamics(new Bullet(this, t1, t2, u1, u2, breakableWall, this.bullet, t1.shootA(), t1.shootB(), 5, false));
                }
            }
            this.repaint();

            try {
                sleep(100);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //switch statement for each key when it is released
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_UP:
                upKey = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightKey = false;
                break;
            case KeyEvent.VK_DOWN:
                downKey = false;
                break;
            case KeyEvent.VK_LEFT:
                leftKey = false;
                break;
            case KeyEvent.VK_ENTER:
                enterKey = false;
                break;
            case KeyEvent.VK_W:
                wKey = false;
                break;
            case KeyEvent.VK_D:
                dKey = false;
                break;
            case KeyEvent.VK_S:
                sKey = false;
                break;
            case KeyEvent.VK_A:
                aKey = false;
                break;
            case KeyEvent.VK_F:
                fKey = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //switch statement for each key when it is pressed
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_UP:
                upKey = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightKey = true;
                break;
            case KeyEvent.VK_DOWN:
                downKey = true;
                break;
            case KeyEvent.VK_LEFT:
                leftKey = true;
                break;
            case KeyEvent.VK_ENTER:
                enterKey = true;
                break;
            case KeyEvent.VK_W:
                wKey = true;
                break;
            case KeyEvent.VK_D:
                dKey = true;
                break;
            case KeyEvent.VK_S:
                sKey = true;
                break;
            case KeyEvent.VK_A:
                aKey = true;
                break;
            case KeyEvent.VK_F:
                fKey = true;
                break;
        }
    }
}