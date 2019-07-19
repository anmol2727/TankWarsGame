package TankGame;

import java.awt.*;

public class Dynamics {
    private int a;
    private int b;

    //variables used to take into account the hindrance of the window, tally it, and store the updated variable
    private int windowHinder;
    private int windowTally;
    private int utdWindow;

    //the span of time
    private int timeTerm;

    //GraphicIMG constructor
    private GraphicIMG component;

    //to loop and to stop
    private boolean sequence;
    boolean halt;

    //Dynamics constructor
    public Dynamics(GraphicIMG component, int a, int b, int windowHinder, boolean sequence) {
        this.a = a;
        this.b = b;
        this.windowHinder = windowHinder;
        this.windowTally = 0;
        this.utdWindow = 0;
        this.sequence = sequence;
        this.component = component;
        this.halt = false;
    }

    //function that illustrates the graphics
    public void illustrate(Graphics g) {
        //conditional statement
        if (!halt) {
            timeTerm = timeTerm + 1;
            windowTally = windowTally + 1;
            if (windowHinder >= windowTally) {
            }
            else {
                windowTally = 0;
                halt = !(!(this.finalSpan() < timeTerm) || sequence);
                utdWindow = component.windowTallyFx() % (utdWindow + 1);
            }
            g.drawImage(component.acqWindow(utdWindow), a, b, null);
        }
    }

    //returns the final span
    public int finalSpan() {
        int fS = this.windowHinder * component.windowTallyFx();
        return fS;
    }

    //stops program
    boolean terminated() {
        boolean t = this.halt;
        return t;
    }
}