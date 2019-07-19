package TankGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

public class GraphicOBJ {
    //BufferedImage used to describe graphics and ImageObserver to overlook the graphics
    protected ImageObserver overlook;
    protected BufferedImage graphic;

    protected int a;
    protected int b;

    //GraphicOBJ constructor
    public GraphicOBJ(String graphicPath) throws IOException {
        this(graphicPath, null);
    }

    public GraphicOBJ(String graphicPath, ImageObserver overlook) throws IOException {
        a = 0;
        b = 0;
        graphic = ImageIO.read(new File(graphicPath));
        this.overlook = overlook;
    }

    //renders the graphics
    public void render(Graphics g) {
        g.drawImage(graphic, a, b, overlook);
    }

    //acquires width
    public int acqW() {
        int aw = this.graphic.getWidth();
        return aw;
    }

    //acquires height
    public int acqH() {
        int ah = this.graphic.getHeight();
        return ah;
    }

    //positions a graphic
    public void positionGraphicA(int a) {
        this.a = a;
    }

    //positions b graphic
    public void positionGraphicB(int b) {
        this.b = b;
    }

    //acquires a graphic
    public int acqGraphicA() {
        int aga = this.a;
        return aga;
    }

    //acquired b graphic
    public int acqGraphicB() {
        int agb = this.b;
        return agb;
    }
}