package piece.black;

import piece.Board;
import piece.Piece;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlackHorse implements Piece {
    private final String color;

    public BlackHorse(String color) {
        this.color = color;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> steps(PositionVector from, PositionVector to) {

        List<PositionVector> positionVectorList = new ArrayList<>();

        int horizontal = from.getHorizontal();
        int vertical = from.getVertical();


        PositionVector posTo1 = new PositionVector(horizontal + 3, vertical + 1);
        PositionVector posTo2 = new PositionVector(horizontal + 1, vertical + 3);
        PositionVector posTo3 = new PositionVector(horizontal - 3, vertical - 1);
        PositionVector posTo4 = new PositionVector(horizontal - 1, vertical - 3);

        Board.getCellAt(posTo1).ifPresent(c -> {
            if (!c.getPiece().color().equalsIgnoreCase(color)) {
                positionVectorList.add(posTo1);
            }
        });

        Board.getCellAt(posTo2).ifPresent(c -> {
            if (!c.getPiece().color().equalsIgnoreCase(color)) {
                positionVectorList.add(posTo2);
            }
        });

        Board.getCellAt(posTo3).ifPresent(c -> {
            if (!c.getPiece().color().equalsIgnoreCase(color)) {
                positionVectorList.add(posTo3);
            }
        });

        Board.getCellAt(posTo4).ifPresent(c -> {
            if (!c.getPiece().color().equalsIgnoreCase(color)) {
                positionVectorList.add(posTo4);
            }
        });

        if (positionVectorList.contains(to)) {
            return Optional.of(to);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return "♞";
    }
}
