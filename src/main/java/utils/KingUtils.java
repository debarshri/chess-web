package utils;

import java.util.HashSet;
import java.util.Set;

import static game.Board.getCellAt;

public class KingUtils {

    public static Set<PositionVector> generatePossibleKingMoves(PositionVector positionVector, String color) {

        //todo
        Set<PositionVector> positionVectors = new HashSet<>();

        int horizontal = positionVector.getY();
        int vertical = positionVector.getX();

        add(horizontal, vertical, 1, positionVectors, color);
        add(horizontal, vertical, -1, positionVectors, color);

        return positionVectors;
    }

    private static void add(int horizontal, int vertical, int step, Set<PositionVector> positionVectors, String color) {
        getCellAt(vertical + step, horizontal + step)
                .filter(cell1 -> !cell1.getPiece().color().equalsIgnoreCase(color))
                .ifPresent(cell -> positionVectors.add(cell.getPostionVector()));

        getCellAt(vertical + step, horizontal)
                .filter(cell1 -> !cell1.getPiece().color().equalsIgnoreCase(color))
                .ifPresent(cell -> positionVectors.add(cell.getPostionVector()));

        getCellAt(vertical - step, horizontal + step)
                .filter(cell1 -> !cell1.getPiece().color().equalsIgnoreCase(color))
                .ifPresent(cell -> positionVectors.add(cell.getPostionVector()));

        getCellAt(vertical + step, horizontal - step)
                .filter(cell1 -> !cell1.getPiece().color().equalsIgnoreCase(color))
                .ifPresent(cell -> positionVectors.add(cell.getPostionVector()));

        getCellAt(vertical, horizontal + step)
                .filter(cell1 -> !cell1.getPiece().color().equalsIgnoreCase(color))
                .ifPresent(cell -> positionVectors.add(cell.getPostionVector()));
    }
}
