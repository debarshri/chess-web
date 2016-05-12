package piece;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import game.Board;
import server.Cell;
import utils.Color;
import utils.PositionException;
import utils.PositionVector;

public class PawnTest {

    @Before
    public void setUp() {
        Board.create();
    }

    @Test
    public void shouldNotWork() {

        PositionVector to = new PositionVector(3, 2);
        PositionVector from = new PositionVector(2, 1);

        Board.move(Color.WHITE, from, to);

        Cell cell = Board.getCellAt(to).get();

        Assert.assertTrue(cell.getPiece() instanceof Empty);
    }

    @Test
    public void shouldtWork() throws PositionException {

        PositionVector to = new PositionVector(2, 1);
        PositionVector from = new PositionVector(3, 1);

        Board.move(Color.WHITE, from, to);

        Cell cell = Board.getCellAt(to).get();

        Assert.assertTrue(cell.getPiece() instanceof Pawn);
    }


}
