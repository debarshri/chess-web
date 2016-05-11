package piece.white;

import piece.Piece;
import utils.PositionVector;

import java.util.Optional;

public class Horses implements Piece {
    private final String color;

    public Horses(String color)  {
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
        return "♘";
    }
}
