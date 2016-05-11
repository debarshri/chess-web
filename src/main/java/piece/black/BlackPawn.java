package piece.black;

import piece.Board;
import piece.Piece;
import server.Cell;
import utils.Color;
import utils.PositionException;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlackPawn implements Piece {

    private String color;
    private int stepMultiplier;

    public BlackPawn(String color) throws PositionException {
        this.color = color;
        this.stepMultiplier = -1;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> steps(PositionVector from, PositionVector to) {
        List<PositionVector> positionVectors = generatePossibleMoves(from);

        if (positionVectors.contains(to)) {
            return Optional.of(to);
        }
        return Optional.empty();
    }

    private List<PositionVector> generatePossibleMoves(PositionVector positionVector) {

        System.out.println(positionVector);

        List<PositionVector> positionVectors = new ArrayList<>();


        int horizontal = positionVector.getHorizontal();
        int vertical = positionVector.getVertical();

        Optional<Cell> cellAt = Board.getCellAt(new PositionVector(vertical + stepMultiplier, horizontal));

        cellAt.ifPresent(c -> {
            Piece piece = c.getPiece();

            if (piece.color().isEmpty()) {
                positionVectors.add(new PositionVector(vertical + stepMultiplier, horizontal));
            }
        });

        Optional<Cell> pieceDiagonallyAt = Board.getCellAt(new PositionVector(vertical +
                stepMultiplier, horizontal + stepMultiplier));

        pieceDiagonallyAt.ifPresent(c -> {
            Piece piece = c.getPiece();

            if (color.equalsIgnoreCase(Color.getOther(piece.color()))) {
                positionVectors.add(new PositionVector(vertical + stepMultiplier,
                        horizontal + stepMultiplier));
            }
        });

        Optional<Cell> pieceDiagonallyLeft = Board.getCellAt(new PositionVector(vertical - stepMultiplier,
                horizontal - stepMultiplier));

        pieceDiagonallyLeft.ifPresent(c -> {
            Piece piece = c.getPiece();

            if (color.equalsIgnoreCase(Color.getOther(piece.color()))) {
                positionVectors.add(new PositionVector(vertical - stepMultiplier,
                        horizontal - stepMultiplier));

            }
        });

        return positionVectors;
    }

    @Override
    public String toString() {
        return "♟";
    }
}
