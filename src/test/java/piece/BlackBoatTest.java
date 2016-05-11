package piece;

import junit.framework.Assert;
import org.junit.Test;
import piece.black.BlackBoat;
import server.Cell;
import utils.Color;
import utils.PositionException;
import utils.PositionVector;

import java.util.Optional;

public class BlackBoatTest {

    @Test
    public void boatVerticalShowWork() throws PositionException {
        Board.create();

        Board.move(Color.BLACK,new PositionVector(7,1), new PositionVector(6,1));
        Board.move(Color.BLACK,new PositionVector(6,1), new PositionVector(5,1));

        Board.move(Color.BLACK, new PositionVector(8,1), new PositionVector(6,1));

        Optional<Cell> cellAt = Board.getCellAt(new PositionVector(6, 1));
        Assert.assertTrue(cellAt.get().getPiece() instanceof BlackBoat);
    }

    @Test
    public void boatHorizontalShowWork() throws PositionException {
        Board.create();

        Board.move(Color.BLACK,new PositionVector(7,1), new PositionVector(6,1));
        Board.move(Color.BLACK,new PositionVector(6,1), new PositionVector(5,1));

        Board.move(Color.BLACK, new PositionVector(8,1), new PositionVector(6,1));
        Board.move(Color.BLACK, new PositionVector(6,1), new PositionVector(6,5));

        Optional<Cell> cellAt = Board.getCellAt(new PositionVector(6, 5));
        Assert.assertTrue(cellAt.get().getPiece() instanceof BlackBoat);
    }

    @Test
    public void boatHorizontalKillsShowWork() throws PositionException {
        Board.create();

        Board.move(Color.BLACK,new PositionVector(7,1), new PositionVector(6,1));
        Board.move(Color.BLACK,new PositionVector(6,1), new PositionVector(5,1));

        Board.move(Color.BLACK,new PositionVector(6,1), new PositionVector(5,1));
        Board.move(Color.BLACK,new PositionVector(6,1), new PositionVector(5,1));

        Board.move(Color.BLACK, new PositionVector(8,1), new PositionVector(6,1));
        Board.move(Color.BLACK, new PositionVector(6,1), new PositionVector(6,5));

        Optional<Cell> cellAt = Board.getCellAt(new PositionVector(6, 5));
        Assert.assertTrue(cellAt.get().getPiece() instanceof BlackBoat);
    }

}