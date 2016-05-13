package piece;

import game.Piece;
import utils.Color;
import utils.PositionVector;

import java.util.List;
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

        List<PositionVector> positionVectors = generatePossibleKingMoves(from);

        if (positionVectors.contains(to)) {
            return Optional.of(to);
        }

        return Optional.empty();
    }

    @Override
    public List<PositionVector> generateAllPossibleMoves(PositionVector from, PositionVector to) {
        return generatePossibleKingMoves(from);
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
