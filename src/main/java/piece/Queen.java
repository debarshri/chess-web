package piece;

import utils.PositionException;
import utils.PositionVector;

import java.util.Optional;

public class Queen implements Piece {

    private final PositionVector position;
    private String color;

    public Queen(String color, int i, int i1) throws PositionException {
        this.color = color;
        this.position = new PositionVector(i,i1);
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
