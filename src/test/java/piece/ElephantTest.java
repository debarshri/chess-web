package piece;

import game.Board;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.Color;
import utils.PositionVector;

public class ElephantTest {


    @Before
    public void setup()
    {
        Board.create();
    }

    @Test
    public void elephantMustWork() {
        Board.move(Color.WHITE, new PositionVector(2, 2), new PositionVector(3, 2));
        PositionVector to = new PositionVector(3, 1);
        Board.move(Color.WHITE, new PositionVector(1, 3), new PositionVector(2, 2));
        Board.move(Color.WHITE, new PositionVector(2, 2), to);

        System.out.println(Board.getCellAt(to).get().getPiece());

        Assert.assertTrue(Board.getCellAt(to).get().getPiece() instanceof Elephant);

    }

    @Test
    public void elephantMustGoBack() {
        Board.move(Color.WHITE, new PositionVector(2, 2), new PositionVector(3, 2));
        Board.move(Color.WHITE, new PositionVector(1, 3), new PositionVector(2, 2));
        Board.move(Color.WHITE, new PositionVector(2, 2), new PositionVector(1, 3));

        System.out.println(Board.getCellAt(new PositionVector(1, 3)).get().getPiece());
        Assert.assertTrue(Board.getCellAt(new PositionVector(1, 3)).get().getPiece() instanceof Elephant);

    }

}