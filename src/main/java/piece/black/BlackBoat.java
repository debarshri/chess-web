package piece.black;

import piece.Board;
import piece.Piece;
import server.Cell;
import utils.PositionException;
import utils.PositionVector;

import java.util.Optional;

public class BlackBoat implements Piece {
    private final String color;

    public BlackBoat(String color) {
        this.color = color;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> steps(PositionVector from, PositionVector to) {

        if(from.getHorizontal() == to.getHorizontal())
        {
            for(int i = from.getVertical()-1; i > to.getVertical()+1; i--)
            {

                System.out.println(i);

                    Cell cell = Board.getCellAt(new PositionVector(i, to.getHorizontal())).get();

                    System.out.println(cell.getPiece().getClass());

                    if(!cell.getPiece().color().isEmpty())
                    {
                        //cannot move

                        return Optional.empty();
                    }

            }
            return Optional.of(to);
        }
        else if(to.getVertical() == from.getVertical())
        {
            //todo
            return Optional.of(to);
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        return "♜";
    }
}


