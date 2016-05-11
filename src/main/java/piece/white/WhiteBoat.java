package piece.white;

import piece.Board;
import piece.Piece;
import server.Cell;
import utils.PositionVector;

import java.util.Optional;

public class WhiteBoat implements Piece {
    private final String color;

    public WhiteBoat(String color) {
        this.color = color;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> steps(PositionVector from, PositionVector to) {

        if (from.getHorizontal() == to.getHorizontal()) {
            for (int i = from.getVertical() + 1; i < to.getVertical(); i++) {
                Cell cell = Board.getCellAt(new PositionVector(i, to.getHorizontal())).get();

                if (!cell.getPiece().color().isEmpty()) {
                    return Optional.empty();
                }
            }
            return Optional.of(to);
        } else if (to.getVertical() == from.getVertical()) {
            return Optional.of(to);
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        return "♖";
    }
}


/*

5,1

 */