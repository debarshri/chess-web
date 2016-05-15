package piece;

import game.Board;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import game.Cell;
import utils.Color;
import utils.PositionVector;

import java.util.Optional;

public class HorseTest {

    @Before
    public void setup() {
        Board.create();
    }

    @Test
    public void whiteHorseShouldWork() {

        Board.create();

        PositionVector from = new PositionVector(1, 2);
        PositionVector to = new PositionVector(3, 3);

        Board.move(Color.WHITE, from, to);
        Optional<Cell> cellAt = Board.getCellAt(to);

        Assert.assertTrue(cellAt.get().getPiece() instanceof Horse);
    }

    @Test
    public void shouldWorkHorizontally() {

        PositionVector from = new PositionVector(8, 7);
        PositionVector to = new PositionVector(6, 6);

        Board.move(Color.BLACK, from, to);
        Optional<Cell> cellAt2 = Board.getCellAt(to);

        Assert.assertTrue(cellAt2.get().getPiece() instanceof Horse);
    }

}