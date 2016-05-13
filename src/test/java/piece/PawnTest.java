package piece;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import game.Board;
import server.Cell;
import utils.Color;
import utils.KingUtils;
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
    public void shouldtWork() {

        PositionVector to = new PositionVector(2, 1);
        PositionVector from = new PositionVector(3, 1);

        Board.move(Color.WHITE, from, to);

        Cell cell = Board.getCellAt(to).get();

        Assert.assertTrue(cell.getPiece() instanceof Pawn);
    }


    @Test
    public void killOthersRight() {

        PositionVector horse = new PositionVector(6, 6);

        Cell cell = Board.getCellAt(horse).get();
        cell.setPiece(new Horse(Color.WHITE));

        PositionVector from = new PositionVector(7, 5);
        Board.move(Color.BLACK, from, horse);

        Assert.assertTrue(Board.getCellAt(horse).get().getPiece() instanceof Pawn);
    }

    @Test
    public void killOthersLeft() {

        PositionVector horse = new PositionVector(6, 6);

        Cell cell = Board.getCellAt(horse).get();

        cell.setPiece(new Horse(Color.WHITE));

        PositionVector from = new PositionVector(7, 7);
        Board.move(Color.BLACK, from, horse);

        Assert.assertTrue(Board.getCellAt(horse).get().getPiece() instanceof Pawn);
    }


}
