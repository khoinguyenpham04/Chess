package main;

import java.awt.*;;

public class Board {

    final int MAX_COL = 8;
    final int MAX_ROW = 8;
    public static final int SQUARESIZE = 100;
    public static final int HALF_SQUARE_SIZE = SQUARESIZE/2;

    //draw the chess board using this class
    public void draw(Graphics2D g2) {

        int c = 0; //value of c initially

        for(int row = 0; row < MAX_ROW; row++) {

            for(int col = 0; col < MAX_COL; col++) {

                //every time we draw a square, we switch color
                if(c == 0) {
                    g2.setColor(new Color(211,166, 124));
                    c = 1; //update value of c for alternation
                }
                else {
                    g2.setColor(new Color(176,116,69));
                    c = 0; 
                }

                g2.fillRect(col*SQUARESIZE, row*SQUARESIZE, SQUARESIZE, SQUARESIZE);
            }

            //this if statement is to make the square's color alternate
            if (c == 0) {
                c = 1;
            }
            else {
                c = 0;
            }
        }

        
    }

}
