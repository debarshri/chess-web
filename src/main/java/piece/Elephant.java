package piece;

import piece.Piece;
import utils.PositionException;
import utils.PositionVector;

import java.util.Optional;

public class Elephant implements Piece {
    private final PositionVector position;
    private String color;

    public Elephant(String color, int vertical, int horizontal) throws PositionException {
        this.color = color;
        this.position = new PositionVector(vertical,horizontal);
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
    public PositionVector currentPosition() {
        return position;
    }

    @Override
    public void setPostion(int horizontal, int vertical) throws PositionException {

    }

    @Override
    public void setPostion(PositionVector postionVector) throws PositionException {

    }
}
