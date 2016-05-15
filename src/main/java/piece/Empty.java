package piece;

import utils.PositionVector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class Empty implements Piece {

    private final String color;

    public Empty(String s) {

        this.color = s;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> step(PositionVector from, PositionVector to) {
        return Optional.empty();
    }

    @Override
    public Collection<PositionVector> generateAllPossibleMoves(PositionVector from, PositionVector to) {
        return new ArrayList<>();
    }

}
