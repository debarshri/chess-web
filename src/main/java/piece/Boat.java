package piece;

import game.Board;
import game.Piece;
import utils.Color;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static game.Initializer.reverseRange;
import static java.util.stream.IntStream.rangeClosed;

public class Boat implements Piece {
    private final String color;

    public Boat(String color) {
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

        if (from.getY() == to.getY()) {
            horizontalForward(from, to, positionVectors);
            horizontalBackward(from, to, positionVectors);

        } else if (to.getX() == from.getX()) {
            verticalForward(from, to, positionVectors);
            verticalBackward(from, to, positionVectors);
        }

        return positionVectors;
    }

    private void verticalBackward(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        reverseRange(to.getY(), from.getY())
                .forEach(i ->
                        Board.getCellAt(to.getX(), i)
                                .filter(f -> !f.getPiece().color().equalsIgnoreCase(color))
                                .ifPresent(f -> positionVectors.add(f.getPostionVector())));
    }

    private void verticalForward(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        rangeClosed(from.getY(), to.getY())
                .forEach(i ->
                        Board.getCellAt(to.getX(), i)
                                .filter(f -> !f.getPiece().color().equalsIgnoreCase(color))
                                .ifPresent(f -> positionVectors.add(f.getPostionVector())));

    }

    private void horizontalBackward(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        reverseRange(to.getX(), from.getX()).forEach(i ->
                Board.getCellAt(new PositionVector(i, to.getY()))
                        .filter(f -> !f.getPiece().color().equalsIgnoreCase(color))
                        .ifPresent(f -> positionVectors.add(f.getPostionVector())));
    }

    private void horizontalForward(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        rangeClosed(from.getX(), to.getX())
                .forEach(i ->
                        Board.getCellAt(new PositionVector(i, to.getY()))
                                .filter(f -> !f.getPiece().color().equalsIgnoreCase(color))
                                .ifPresent(f -> positionVectors.add(f.getPostionVector())));
    }

    @Override
    public String toString() {
        if (color.equalsIgnoreCase(Color.BLACK)) {
            return "♜";
        } else {
            return "♖";
        }
    }
}