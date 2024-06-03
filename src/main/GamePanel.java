package main;

import piece.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();

    //Pieces
    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();

    //Colour of the pieces
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;


    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);

        setPieces();
        copyPieces(pieces, simPieces);
    }

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();             //this call the run() method, starting a Thread means calling a run()
    }

    public void setPieces() {
        //Whiten pieces
        pieces.add(new Pawn(WHITE,0,6));
        pieces.add(new Pawn(WHITE,1,6));
        pieces.add(new Pawn(WHITE,2,6));
        pieces.add(new Pawn(WHITE,3,6));
        pieces.add(new Pawn(WHITE,4,6));
        pieces.add(new Pawn(WHITE,5,6));
        pieces.add(new Pawn(WHITE,6,6));
        pieces.add(new Pawn(WHITE,7,6));
        pieces.add(new Rook(WHITE,0,7));
        pieces.add(new Rook(WHITE,7,7));
        pieces.add(new Knight(WHITE,1,7));
        pieces.add(new Knight(WHITE,6,7));
        pieces.add(new Bishop(WHITE,2,7));
        pieces.add(new Bishop(WHITE,5,7));
        pieces.add(new Queen(WHITE,3,7));
        pieces.add(new Knight(WHITE,4,7));

        //black pieces
        pieces.add(new Pawn(BLACK,0,1));
        pieces.add(new Pawn(BLACK,1,1));
        pieces.add(new Pawn(BLACK,2,1));
        pieces.add(new Pawn(BLACK,3,1));
        pieces.add(new Pawn(BLACK,4,1));
        pieces.add(new Pawn(BLACK,5,1));
        pieces.add(new Pawn(BLACK,6,1));
        pieces.add(new Pawn(BLACK,7,1));
        pieces.add(new Rook(BLACK,0,0));
        pieces.add(new Rook(BLACK,7,0));
        pieces.add(new Knight(BLACK,1,0));
        pieces.add(new Knight(BLACK,6,0));
        pieces.add(new Bishop(BLACK,2,0));
        pieces.add(new Bishop(BLACK,5,0));
        pieces.add(new Queen(BLACK,3,0));
        pieces.add(new Knight(BLACK,4,0));


    }

    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target) {
        target.clear();
        for(int i = 0; i < source.size(); i++) {
            target.add(source.get(i));
        }
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

        //draw the board
        board.draw(g2);

        //draw the pieces
        for (Piece p : simPieces) {
            p.draw(g2);
        }
    }


}
