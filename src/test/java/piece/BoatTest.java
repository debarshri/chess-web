package piece;

import game.Board;
import junit.framework.Assert;
import org.junit.Test;
import utils.Color;
import utils.PositionVector;

public class BoatTest {

    @Test
    public void boatShouldWork() {
        Board.create();

        Board.move(Color.WHITE, new PositionVector(2, 1), new PositionVector(3, 1));
        Board.move(Color.WHITE, new PositionVector(3, 1), new PositionVector(4, 1));
        Board.move(Color.WHITE, new PositionVector(4, 1), new PositionVector(5, 1));
        Board.move(Color.WHITE, new PositionVector(5, 1), new PositionVector(6, 1));
        Board.move(Color.WHITE, new PositionVector(6, 1), new PositionVector(7, 1));

        PositionVector to = new PositionVector(4, 1);
        PositionVector from = new PositionVector(1, 1);

        Board.move(Color.WHITE, from, to);

        Assert.assertTrue(Board.getCellAt(to).get().getPiece() instanceof Boat);

        Board.move(Color.WHITE, to, from);

        Assert.assertTrue(Board.getCellAt(from).get().getPiece() instanceof Boat);

        Board.move(Color.WHITE, from, to);
        PositionVector to1 = new PositionVector(4, 8);
        Board.move(Color.WHITE, to, to1);

        Assert.assertTrue(Board.getCellAt(new PositionVector(4, 8)).get().getPiece() instanceof Boat);

        Board.move(Color.WHITE, to1, to);
        Assert.assertTrue(Board.getCellAt(to).get().getPiece() instanceof Boat);

    }

}