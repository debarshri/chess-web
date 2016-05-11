package piece.white;

import org.junit.Assert;
import org.junit.Test;
import piece.Board;
import server.Cell;
import utils.Color;
import utils.PositionException;
import utils.PositionVector;

import java.util.Optional;

public class HorseTest {

    @Test
    public void whiteHorseShouldWork() throws PositionException {
        Board.create();

        PositionVector from = new PositionVector(1, 2);
        Optional<Cell> horse = Board.getCellAt(from);

        Assert.assertTrue(horse.get().getPiece() instanceof Horse);

        Board.move(Color.WHITE, from, new PositionVector(3,4));
        Optional<Cell> cellAt = Board.getCellAt(new PositionVector(3, 4));

        Assert.assertTrue(cellAt.get().getPiece() instanceof Horse);
    }

}