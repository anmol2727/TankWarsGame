package TankGame;

import java.awt.*;

class User {
    //Wall and Tank constructors
    private Wall w;
    private Tank t;

    //variables for users score, condition status, and lifes
    private int score = 0;
    private int condition = 300;
    private int lifes = 2;

    //tank 1 and 2
    private int tA;
    private int tB;

    //variables used to set layout of map
    private int OUTLINE_WIDTH = 40;
    private int OUTLINE_HEIGHT = 30;

    //String used to define tank's setting before game begins and when it dies
    private String tankSetting;

    //used to end game
    private boolean termination = false;

    //User constructor
    User(Tank t, Wall w, String tankSetting) {
        this.t = t;
        this.w = w;
        this.tankSetting = tankSetting;
    }

    //function used to return when tank is dead
    public boolean deceased() {
        boolean c = condition == 0;
        return c;
    }

    //function that spawns tanks in set location
    public void positionInitialSetting() {
        //nested while loop to position tanks setting
        int a = 0;
        while (OUTLINE_HEIGHT > a) {
            int b = 0;
            while (OUTLINE_WIDTH > b) {
                if (w.acqWallOutline()[a][b].equals(tankSetting)) {
                    tA = b * w.acqW();
                    tB = a * w.acqH();
                }
                b = b + 1;
            }
            a = a + 1;
        }
        t.positionGraphicA(tA);
        t.positionGraphicB(tB);
    }

    //function to show lifes
    public void presentLifes(Graphics g, int a, int b) {
        int pe = 40;
        //displays life as the color red
        g.setColor(Color.RED);

        //while loop that presents lifes as circle icon
        int lf = 0;
        while (lifes - 1 > lf) {
            g.fillOval(a + lifes * pe, b, 20, 20);
            lf = lf + 1;
        }

        //conditional statement which presents game over when 0 lifes remain
        if (0 != lifes) {}
        else {
            this.termination = true;
            g.setColor(Color.DARK_GRAY);
            Font style = g.getFont().deriveFont(100.0f);
            g.setFont(style);
            g.drawString("Game Over", 450,450);
        }
    }

    //function that shows the users condition
    public void presentCondition(Graphics g, int a, int b) {
        //conditional statements that change color of condition bar as condition decreases
        if (condition <= 220) {
            if (!(condition > 140 && condition <= 220)) {
                if (!(condition > 0 && condition <= 140)) {
                    lifes = lifes - 1;
                    condition = 300;
                }
                else {
                    g.setColor(Color.red);
                    g.fillRect(a, b, condition, 20);
                }
            }
            else {
                g.setColor(Color.yellow);
                g.fillRect(a, b, condition, 20);
            }
        }
        else {
            g.setColor(Color.green);
            g.fillRect(a, b, condition, 20);
        }
    }

    //score is boosted every time you kill the other tank
    public void boostScore() {
        score = score + 70;
    }

    //function that shows the score
    public void presentScore(Graphics g, int a, int b) {
        //score present in black font
        g.setColor(Color.BLACK);
        Font style = g.getFont().deriveFont(25.0f);
        g.setFont(style);
        g.drawString(Integer.toString(score), a, b);
    }

    //function that terminates the game
    public boolean gameOver() {
        boolean t = this.termination;
        return t;
    }

    //when you make contact with the opposing tank your condition is decreased by 60
    public void tankContact() {
        this.condition = this.condition - 20;
    }
}