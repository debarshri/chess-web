package piece.black;

import piece.Piece;
import utils.PositionException;
import utils.PositionVector;

import java.util.Optional;

/**
 * Created by dbasak on 5/11/16.
 */
public class BlackQueen implements Piece {

    private String color;
    public BlackQueen(String color) throws PositionException {
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
        return "♛";
    }
}
