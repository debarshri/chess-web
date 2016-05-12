package piece;

import game.Piece;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
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
    public List<PositionVector> generateAllPossibleMoves(PositionVector from, PositionVector to) {
        return new ArrayList<>();
    }

}
