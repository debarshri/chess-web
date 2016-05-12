package piece;

import game.Board;
import game.Piece;
import utils.Color;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        List<PositionVector> positionVectors = generateAllPossibleMoves(from, to);

        if (positionVectors.contains(to)) {
            return Optional.of(to);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<PositionVector> generateAllPossibleMoves(PositionVector from, PositionVector to) {
        List<PositionVector> positionVectorList = new ArrayList<>();

        int horizontal = from.getHorizontal();
        int vertical = from.getVertical();

        calculateSteps(color, positionVectorList, horizontal, vertical, 2, 1);
        calculateSteps(color, positionVectorList, horizontal, vertical, 1, 2);

        return positionVectorList;
    }

    private void calculateSteps(String color, List<PositionVector> positionVectorList, int horizontal, int vertical, int x, int y) {

        Board.getCellAt(vertical + x, horizontal + y)
                .filter(c -> !c.getPiece().color().equalsIgnoreCase(color))
                .ifPresent(c -> positionVectorList.add(c.getPostionVector()));

        Board.getCellAt(vertical + x, horizontal - y)
                .filter(c -> !c.getPiece().color().equalsIgnoreCase(color))
                .ifPresent(c -> positionVectorList.add(c.getPostionVector()));

        Board.getCellAt(vertical - x, horizontal - y)
                .filter(c -> !c.getPiece().color().equalsIgnoreCase(color))
                .ifPresent(c -> positionVectorList.add(c.getPostionVector()));

        Board.getCellAt(vertical - x, horizontal + y)
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
