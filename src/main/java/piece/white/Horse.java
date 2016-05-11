package piece.white;

import piece.Board;
import piece.Piece;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Horse implements Piece {
    private final String color;

    public Horse(String color)  {
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

        PositionVector posTo1 = new PositionVector(vertical + 2, horizontal + 2);
        PositionVector posTo2 = new PositionVector(vertical + 2, horizontal - 2);
        PositionVector posTo3 = new PositionVector(vertical - 2, horizontal - 2);
        PositionVector posTo4 = new PositionVector(vertical - 2, horizontal + 2);

        PositionVector posTo5 = new PositionVector(vertical - 3, horizontal - 1);
        PositionVector posTo6 = new PositionVector(vertical - 3, horizontal + 1);
        PositionVector posTo7 = new PositionVector(vertical + 3, horizontal + 1);
        PositionVector posTo8 = new PositionVector(vertical + 3, horizontal - 1);

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

        Board.getCellAt(posTo5).ifPresent(c -> {
            if (!c.getPiece().color().equalsIgnoreCase(color)) {
                positionVectorList.add(posTo5);
            }
        });

        Board.getCellAt(posTo6).ifPresent(c -> {
            if (!c.getPiece().color().equalsIgnoreCase(color)) {
                positionVectorList.add(posTo6);
            }
        });

        Board.getCellAt(posTo7).ifPresent(c -> {
            if (!c.getPiece().color().equalsIgnoreCase(color)) {
                positionVectorList.add(posTo7);
            }
        });

        Board.getCellAt(posTo8).ifPresent(c -> {
            if (!c.getPiece().color().equalsIgnoreCase(color)) {
                positionVectorList.add(posTo8);
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
        return "♘";
    }
}
