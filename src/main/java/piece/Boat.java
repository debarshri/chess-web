package piece;

import piece.Piece;
import utils.PositionException;
import utils.PositionVector;

import java.util.Optional;

public class Boat implements Piece {
    private final String color;

    public Boat(String color) throws PositionException {
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
    public void setPostion(int horizontal, int vertical) throws PositionException {
    }

    @Override
    public void setPostion(PositionVector postionVector) throws PositionException {

    }
}
