package piece;

import game.Board;
import game.Piece;
import utils.Color;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        int horizontal = from.getHorizontal();
        int vertical = from.getVertical();

        Board.getCellAt(vertical + stepMultiplier, horizontal)
                .filter(c -> c.getPiece().color().isEmpty())
                .ifPresent(c -> positionVectors.add(c.getPostionVector()));

        Board.getCellAt(vertical + stepMultiplier, horizontal + stepMultiplier)
                .filter(c -> !c.getPiece().color().isEmpty())
                .filter(c -> color.equalsIgnoreCase(Color.getOther(c.getPiece().color())))
                .ifPresent(p -> positionVectors.add(p.getPostionVector()));

        Board.getCellAt(vertical - stepMultiplier, horizontal - stepMultiplier)
                .filter(c -> !c.getPiece().color().isEmpty())
                .filter(c -> color.equalsIgnoreCase(Color.getOther(c.getPiece().color())))
                .ifPresent(c -> positionVectors.add(c.getPostionVector()));

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
