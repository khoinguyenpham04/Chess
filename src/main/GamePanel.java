package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
    }

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();             //this call the run() method, starting a Thread means calling a run()
    }

    @Override
    public void run() {
        //Game loop - Responsible for managing the game state and rendering
        double drawInterval = 1000000000/FPS; //time interval in nanoseconds for each frame
        double delta = 0;  //keep track of the time that has passed
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval; //measure of how many frame intervals have passed since the last update
            lastTime = currentTime;

            //if delta (timepassed) is greater or equal to 1, it's time to update the game state and redraw the frame
            if(delta >= 1) {
                update(); //call update() method
                repaint(); //call paintComponent(), meaning repainting it 60 times per second
                delta--;
            }
        }
    }

    //method to update the game's state, such as moving piecies.
    private void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        board.draw(g2);
    }


}
