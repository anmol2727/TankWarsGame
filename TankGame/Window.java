package TankGame;

import javax.swing.*;

//Window class that creates a subclass of JFrame
public class Window extends JFrame {
    //constructor used to create window and add functionality
    public Window(Console c) {
        setTitle("Tank Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(c);
        pack();
        Thread thread = new Thread(c);
        thread.start();
        setVisible(true);
    }

    //main method to run program
    public static void main(String[] args) {
        new Window(new TankControl());
    }
}