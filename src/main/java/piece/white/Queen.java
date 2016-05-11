package piece.white;

import piece.Piece;
import utils.PositionException;
import utils.PositionVector;

import java.util.Optional;

public class Queen implements Piece {

    private String color;

    public Queen(String color) throws PositionException {
        this.color = color;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> steps(PositionVector to, PositionVector from) {
        return null;
    }


    @Override
    public String toString() {
        return "♕";
    }
}
