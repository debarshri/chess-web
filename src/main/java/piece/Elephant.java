package piece;

import game.Board;
import game.Piece;
import utils.Color;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Elephant implements Piece {
    private String color;

    public Elephant(String color) {
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
        }

        return Optional.empty();
    }

    @Override
    public List<PositionVector> generateAllPossibleMoves(PositionVector from, PositionVector to) {

        List<PositionVector> positionVectors = new ArrayList<>();

        moveForwardRightSide(from, to, positionVectors);
        moveBackwardRight(from, to, positionVectors);

        moveBackwardLeftSide(from, to, positionVectors);
        moveBackwardRightSide(from, to, positionVectors);

        return positionVectors;
    }

    private void moveBackwardRightSide(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        outerloop:
        for(int x=from.getVertical()-1; x >= to.getVertical(); x--)
        {
            for(int y = x; y <= to.getHorizontal(); y++)
            {
                Board.getCellAt(new PositionVector(x, y))
                        .filter( c -> !c.getPiece().color().equalsIgnoreCase(color))
                        .ifPresent(c ->positionVectors.add(c.getPostionVector()));

                break outerloop;
            }
        }
    }

    private void moveBackwardLeftSide(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        outerloop:
        for (int y = from.getHorizontal()-1; y >= to.getHorizontal(); y--) {
            for (int x = y; x >= to.getVertical(); x--) {

                Board.getCellAt(x, y)
                        .filter(c -> !c.getPiece().color().equalsIgnoreCase(color)).ifPresent(c ->
                        positionVectors.add(c.getPostionVector()));

                break outerloop;
            }
        }
    }

    private void moveBackwardRight(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {
        outerloop:
        for (int x = from.getVertical()+1; x <= to.getVertical(); x++) {
            for (int y = to.getHorizontal(); y >= to.getHorizontal(); y--) {

                Board.getCellAt(x, y)
                        .filter(c -> !c.getPiece().color().equalsIgnoreCase(color)).ifPresent(c ->
                        positionVectors.add(c.getPostionVector()));
                break outerloop;
            }
        }
    }

    private void moveForwardRightSide(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        outerloop:
        for (int x = from.getVertical()+1; x <= to.getVertical(); x++) {
            for (int y = x; y <= to.getHorizontal(); y++) {
                PositionVector to1 = new PositionVector(x, y);

                Board.getCellAt(to1)
                        .filter(c -> !c.getPiece().color().equalsIgnoreCase(color))
                        .ifPresent(c ->
                        positionVectors.add(to1));

                break outerloop;
            }
        }
    }

    @Override
    public String toString() {

        if (color.equalsIgnoreCase(Color.BLACK)) {
            return "♝";
        } else {
            return "♗";
        }
    }
}
