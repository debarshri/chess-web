package piece.white;

import piece.CommonMoves;
import piece.Piece;
import utils.PositionVector;

import java.util.List;
import java.util.Optional;

public class Horse implements Piece {
    private final String color;

    public Horse(String color)  {
        this.color = color;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> steps(PositionVector from, PositionVector to) {

        List<PositionVector> positionVectors = CommonMoves.allMovesHorse(color, from);

        if (positionVectors.contains(to)) {
            return Optional.of(to);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return "♘";
    }
}
