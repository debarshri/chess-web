package piece;

import game.Board;
import junit.framework.Assert;
import org.junit.Test;
import utils.Color;
import utils.PositionVector;

public class ElephantTest {

    @Test
    public void elephantMustWork() {
        Board.create();
        Board.move(Color.WHITE, new PositionVector(2, 2), new PositionVector(3, 2));
        PositionVector to = new PositionVector(3, 1);
        Board.move(Color.WHITE, new PositionVector(1, 3), new PositionVector(2, 2));
        Board.move(Color.WHITE, new PositionVector(2, 2), to);

        System.out.println(Board.getCellAt(to).get().getPiece());

        Assert.assertTrue(Board.getCellAt(to).get().getPiece() instanceof Elephant);

    }

}