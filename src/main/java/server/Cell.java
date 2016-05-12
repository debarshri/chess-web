package server;

import game.Piece;
import utils.PositionVector;

public class Cell {
    private final PositionVector postionVector;
    private int vertical;
    private int horizontal;
    private Piece piece;

    public Cell(int x, int y, Piece piece) {
        this.vertical = x;
        this.horizontal = y;
        this.postionVector = new PositionVector(x, y);
        this.piece = piece;
    }

    public int getX() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public int getY() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public PositionVector getPostionVector() {
        return postionVector;
    }
}
