package piece;

import game.Piece;
import utils.Color;
import utils.KingUtils;
import utils.PositionVector;

import java.util.List;
import java.util.Optional;

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

        List<PositionVector> positionVectors = KingUtils.generatePossibleKingMoves(from);

        if (positionVectors.contains(to)) {
            return Optional.of(to);
        }

        return Optional.empty();
    }

    @Override
    public List<PositionVector> generateAllPossibleMoves(PositionVector from, PositionVector to) {
        return  KingUtils.generatePossibleKingMoves(from);
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
