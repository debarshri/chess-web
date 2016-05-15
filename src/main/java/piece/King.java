package piece;

import utils.Color;
import utils.PositionVector;

import java.util.Collection;
import java.util.Optional;

import static utils.KingUtils.generatePossibleKingMoves;

public class King implements Piece {

    private String color;

    public King(String white) {
        this.color = white;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> step(PositionVector from, PositionVector to) {

        Collection<PositionVector> positionVectors = generatePossibleKingMoves(from, color);

        if (positionVectors.contains(to)) {
            return Optional.of(to);
        }

        return Optional.empty();
    }

    @Override
    public Collection<PositionVector> generateAllPossibleMoves(PositionVector from, PositionVector to) {
        return generatePossibleKingMoves(from, color);
    }


    @Override
    public String toString() {

        if (color.equalsIgnoreCase(Color.BLACK)) {
            return "♚";
        } else {
            return "♔";
        }
    }
}
