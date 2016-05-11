package piece.black;

import org.junit.Assert;
import org.junit.Test;
import piece.Board;
import server.Cell;
import utils.Color;
import utils.PositionException;
import utils.PositionVector;

import java.util.Optional;

public class BlackHorseTest {

    @Test
    public void shouldWork() throws PositionException {
        Board.create();

        PositionVector from = new PositionVector(8,2);
        Optional<Cell> horse = Board.getCellAt(from);

        Assert.assertTrue(horse.get().getPiece() instanceof BlackHorse);

        PositionVector to = new PositionVector(6, 4);
        Board.move(Color.BLACK, from, to);
        Optional<Cell> cellAt = Board.getCellAt(to);

        Assert.assertTrue(cellAt.get().getPiece() instanceof BlackHorse);
    }

}