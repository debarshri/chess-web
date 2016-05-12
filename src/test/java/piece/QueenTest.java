package piece;

import game.Board;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.Color;
import utils.PositionVector;

public class QueenTest {

    @Before
    public void setup()
    {
        Board.create();

    }

    @Test
    public void QueenShouldWork() {

        PositionVector to1 = new PositionVector(3, 3);
        Board.move(Color.WHITE, new PositionVector(2, 3), to1);

        PositionVector to = new PositionVector(4, 1);
        PositionVector from = new PositionVector(1, 4);

        Board.move(Color.WHITE, from, to);


        System.out.println(Board.getCellAt(to).get().getPiece());
        Assert.assertTrue(Board.getCellAt(to).get().getPiece() instanceof Queen);

    }
}