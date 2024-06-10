package piece;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Board;
import main.GamePanel;

public class Piece {

    public BufferedImage image;
    public int x, y;
    public int col, row, preCol, preRow;
    public int color;
    public Piece hittingP;

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

    public int getCol(int x) {
        return (x + Board.HALF_SQUARE_SIZE) / Board.SQUARESIZE;
    }

    public int getRow(int y) {
        return (y + Board.HALF_SQUARE_SIZE) / Board.SQUARESIZE;
    }

    public void updatePosition() {
        x = getX(col);
        y = getY(row);
        preCol = getCol(x);
        preRow = getRow(y);
    }

    public void resetPosition() {
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }

    public boolean canMove(int targetCol, int targetRow) {
        return false;
    }

    public boolean isWithinBoard(int targetCol, int targetRow) {
        if(targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7) {
            return true;
        }
        return false;
    }

    public Piece getHittingP(int targetCol, int targetRow) {
        for(Piece piece : GamePanel.simPieces) {
            if(piece.col == targetCol && piece.row == targetRow && piece != this) {
                return piece;
            }
        }
        return null;
    }

    public boolean isValidSquare(int targetCol, int targetRow) {

        hittingP = getHittingP(targetCol, targetRow);

        if(hittingP == null) { //this square is vacant
            return true;
        }
        else { //This square is occupied with other pieces
            if(hittingP.color != this.color) { //if the color is different, it can be captured
                return true;
            }
            else {
                hittingP = null;
            }
        }

        return false;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, Board.SQUARESIZE, Board.SQUARESIZE, null);
    }

}
