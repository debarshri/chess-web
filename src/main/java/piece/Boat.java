package piece;

import game.Board;
import game.Piece;
import utils.Color;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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

        if (from.getHorizontal() == to.getHorizontal()) {
            horizontalForward(from, to, positionVectors);
            horizontalBackward(from, to, positionVectors);

        } else if (to.getVertical() == from.getVertical()) {
            verticalForward(from, to, positionVectors);
            verticalBackward(from, to, positionVectors);
        }

        return positionVectors;
    }

    private void verticalBackward(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        Board.revRange(to.getHorizontal(), from.getHorizontal())
                .forEach(i ->
                        Board.getCellAt(to.getVertical(), i)
                                .filter(f -> !f.getPiece().color().equalsIgnoreCase(color))
                                .ifPresent(f -> positionVectors.add(f.getPostionVector())));
    }

    private void verticalForward(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        IntStream.rangeClosed(from.getHorizontal(), to.getHorizontal())
                .forEach(i ->
                        Board.getCellAt(to.getVertical(), i)
                                .filter(f -> !f.getPiece().color().equalsIgnoreCase(color))
                                .ifPresent(f -> positionVectors.add(f.getPostionVector())));

    }

    private void horizontalBackward(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        Board.revRange(to.getVertical(), from.getVertical()).forEach(i ->
                Board.getCellAt(new PositionVector(i, to.getHorizontal()))
                        .filter(f -> !f.getPiece().color().equalsIgnoreCase(color))
                        .ifPresent(f -> positionVectors.add(f.getPostionVector())));
    }

    private void horizontalForward(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        IntStream.rangeClosed(from.getVertical(), to.getVertical())
                .forEach(i ->
                        Board.getCellAt(new PositionVector(i, to.getHorizontal()))
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