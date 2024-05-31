package piece;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Board;

public class Piece {

    public BufferedImage image;
    public int x, y;
    public int col, row, preCol, preRow;
    public int color;

    public Piece(int color, int col, int row) {

        this.color = color;
        this.col = col;
        this.row = row;

        x = getX(col);
        y = getY(row);

        preCol = col;
        preRow = row;

    }

    //In order to get and use image, we need to import the images as Buffered Image
    public BufferedImage getImage(String imagePath) {

        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

    //getX() and getY() method is a seperate class here cause we are likely to calculate this alot.
    public int getX(int col) {
        return col * Board.SQUARESIZE;
    }

    public int getY(int row) {
        return row * Board.SQUARESIZE;
    }

}
