package piece;

import utils.Color;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static game.Board.getCellAt;

public class Horse implements Piece {
    private final String color;

    public Horse(String color) {
        this.color = color;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> step(PositionVector from, PositionVector to) {

        if (generateAllPossibleMoves(from, to).contains(to)) {
            return Optional.of(to);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Collection<PositionVector> generateAllPossibleMoves(PositionVector from, PositionVector to) {
        List<PositionVector> positionVectorList = new ArrayList<>();

        int horizontal = from.getY();
        int vertical = from.getX();

        calculateSteps(color, positionVectorList, horizontal, vertical, 2, 1);
        calculateSteps(color, positionVectorList, horizontal, vertical, 1, 2);

        return positionVectorList;
    }

    private void calculateSteps(String color, List<PositionVector> positionVectorList, int horizontal, int vertical, int x, int y) {

        getCellAt(vertical + x, horizontal + y)
                .filter(c -> !c.getPiece().color().equalsIgnoreCase(color))
                .ifPresent(c -> positionVectorList.add(c.getPostionVector()));

        getCellAt(vertical + x, horizontal - y)
                .filter(c -> !c.getPiece().color().equalsIgnoreCase(color))
                .ifPresent(c -> positionVectorList.add(c.getPostionVector()));

        getCellAt(vertical - x, horizontal - y)
                .filter(c -> !c.getPiece().color().equalsIgnoreCase(color))
                .ifPresent(c -> positionVectorList.add(c.getPostionVector()));

        getCellAt(vertical - x, horizontal + y)
                .filter(c -> !c.getPiece().color().equalsIgnoreCase(color))
                .ifPresent(c -> positionVectorList.add(c.getPostionVector()));
    }

    @Override
    public String toString() {
        if (color.equalsIgnoreCase(Color.BLACK)) {
            return "♞";
        } else {
            return "♘";
        }
    }
}
