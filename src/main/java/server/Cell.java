package server;

import piece.Piece;

public class Cell {
    private int vertical;
    private int horizontal;
    private Piece piece;

    public Cell(int vertical, int horizontal, Piece piece) {
        this.vertical = vertical;
        this.horizontal = horizontal;
        this.piece = piece;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public int getHorizontal() {
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
}
