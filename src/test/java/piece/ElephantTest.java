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
        Board.move(Color.WHITE, new PositionVector(1, 3), new PositionVector(2, 2));
        Board.move(Color.WHITE, new PositionVector(2, 2), new PositionVector(3, 1));

        Assert.assertTrue(Board.getCellAt(new PositionVector(3, 1)).get().getPiece() instanceof Elephant);
    }

    @Test
    public void elephantMustGoBack() {
        Board.create();

        Board.move(Color.WHITE, new PositionVector(2, 2), new PositionVector(3, 2));
        Board.move(Color.WHITE, new PositionVector(1, 3), new PositionVector(2, 2));
        Board.move(Color.WHITE, new PositionVector(2, 2), new PositionVector(1, 3));

        Assert.assertTrue(Board.getCellAt(new PositionVector(1, 3)).get().getPiece() instanceof Elephant);

    }

    @Test
    public void elephantMustGoRight() {
        Board.create();

        Board.move(Color.WHITE, new PositionVector(2, 2), new PositionVector(3, 2));
        Board.move(Color.WHITE, new PositionVector(1, 3), new PositionVector(2, 2));
        Board.move(Color.WHITE, new PositionVector(2, 2), new PositionVector(7, 7));

        Assert.assertTrue(Board.getCellAt(new PositionVector(7, 7)).get().getPiece() instanceof Elephant);
    }
}