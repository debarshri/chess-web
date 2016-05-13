package piece;

import game.Piece;
import utils.Color;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static game.Board.getCellAt;

public class Pawn implements Piece {

    private String color;
    private int stepMultiplier;

    public Pawn(String color) {
        this.color = color;
        if (color.equalsIgnoreCase(Color.WHITE)) {
            this.stepMultiplier = 1;
        } else {
            this.stepMultiplier = -1;
        }
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

        int y = from.getY();
        int x = from.getX();

        getCellAt(x + stepMultiplier, y)
                .filter(c -> c.getPiece().color().isEmpty())
                .ifPresent(c -> positionVectors.add(c.getPostionVector()));

        getCellAt(x + stepMultiplier, y + stepMultiplier)
                .filter(c -> !c.getPiece().color().isEmpty())
                .filter(c -> color.equalsIgnoreCase(Color.getOther(c.getPiece().color())))
                .ifPresent(p -> positionVectors.add(p.getPostionVector()));

        getCellAt(x - stepMultiplier, y + stepMultiplier)
                .filter(c -> !c.getPiece().color().isEmpty())
                .filter(c -> color.equalsIgnoreCase(Color.getOther(c.getPiece().color())))
                .ifPresent(p -> positionVectors.add(p.getPostionVector()));

        getCellAt(x + stepMultiplier, y - stepMultiplier)
                .filter(cell -> !cell.getPiece().color().isEmpty())
                .filter(cell -> color.equalsIgnoreCase(Color.getOther(cell.getPiece().color())))
                .ifPresent(canMove -> positionVectors.add(canMove.getPostionVector()));

        return positionVectors;
    }


    @Override
    public String toString() {

        if (color.equalsIgnoreCase(Color.BLACK)) {
            return "♟";
        } else {
            return "♙";
        }
    }
}
