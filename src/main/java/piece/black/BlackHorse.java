package piece.black;

import piece.CommonMoves;
import piece.Piece;
import utils.PositionVector;

import java.util.List;
import java.util.Optional;

public class BlackHorse implements Piece {
    private final String color;

    public BlackHorse(String color) {
        this.color = color;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> steps(PositionVector from, PositionVector to) {

        System.out.println(from);

        List<PositionVector> positionVectors = CommonMoves.allMovesHorse(color, from);

        System.out.println(positionVectors);

        if (positionVectors.contains(to)) {
            return Optional.of(to);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return "♞";
    }
}
