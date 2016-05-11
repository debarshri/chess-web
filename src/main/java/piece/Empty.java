package piece;

import utils.PositionVector;

import java.util.Optional;

public class Empty implements Piece {

    private final String color;

    public Empty(String s){

        this.color = s;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> steps(PositionVector from, PositionVector to) {
        return Optional.empty();
    }

}
