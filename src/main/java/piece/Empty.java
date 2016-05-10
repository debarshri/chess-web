package piece;

import utils.PositionException;
import utils.PositionVector;

import java.util.Optional;

public class Empty implements Piece{

    private final String color;
    private final PositionVector postion;

    public Empty(String s, int vertical, int horizontal) throws PositionException {

        this.color = s;
        this.postion = new PositionVector(vertical, horizontal);
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
        return postion;
    }

    @Override
    public void setPostion(int horizontal, int vertical) throws PositionException {

    }

    @Override
    public void setPostion(PositionVector postionVector) throws PositionException {

    }
}
