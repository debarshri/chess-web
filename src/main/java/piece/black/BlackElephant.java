package piece.black;

import piece.Piece;
import utils.PositionVector;

import java.util.Optional;

public class BlackElephant implements Piece {
    private String color;

    public BlackElephant(String color) {
        this.color = color;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> steps(PositionVector from, PositionVector to) {

        return null;
    }

    @Override
    public String toString() {
        return "♝";
    }
}
