package piece;

import game.Board;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.Color;
import utils.PositionVector;

public class QueenTest {

    @Before
    public void setup() {
        Board.create();
    }

    @Test
    public void QueenShouldWork() {

        PositionVector to1 = new PositionVector(3, 3);
        Board.move(Color.WHITE, new PositionVector(2, 3), to1);

        PositionVector to = new PositionVector(4, 1);
        PositionVector from = new PositionVector(1, 4);

        Board.move(Color.WHITE, from, to);

        Assert.assertTrue(Board.getCellAt(to).get().getPiece() instanceof Queen);
    }

    @Test
    public void queenShouldWorkWithStatusCheck() {
        Board.create();

        Board.getCellAt(4, 6).get().setPiece(new Queen(Color.WHITE));
        Board.move(Color.WHITE, new PositionVector(4, 6), new PositionVector(8, 2));
        Board.checkStatus(Board.getKingForColor(Color.BLACK).get());

        Assert.assertTrue(Board.getCellAt(new PositionVector(8, 2)).get().getPiece() instanceof Queen);
    }
}