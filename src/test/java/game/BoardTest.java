package game;

import junit.framework.Assert;
import org.junit.Test;
import piece.Boat;
import piece.Elephant;
import piece.Empty;
import piece.Queen;
import utils.Color;
import utils.PositionVector;

import static game.Board.getCellAt;
import static game.Board.getKingForColor;

public class BoardTest {

    @Test
    public void isCheckWorking() throws Exception {

        Board.create();

        Board.move(Color.WHITE, new PositionVector(1, 2), new PositionVector(3, 3));
        Board.move(Color.WHITE, new PositionVector(3, 3), new PositionVector(4, 5));
        Board.move(Color.WHITE, new PositionVector(4, 5), new PositionVector(6, 4));

        String[] status = new String[]{""};

        getKingForColor(Color.BLACK)
                .ifPresent(king -> status[0] = Board.checkStatus(king));

        Assert.assertTrue(status[0].equals(GameStatus.CHECK));

    }

    @Test
    public void isCheckMateWorking() throws Exception {

        Board.create();

        getCellAt(6, 7).get().setPiece(new Elephant(Color.WHITE));
        getCellAt(6, 3).get().setPiece(new Elephant(Color.WHITE));
        getCellAt(6, 5).get().setPiece(new Queen(Color.WHITE));
        getCellAt(6, 4).get().setPiece(new Boat(Color.WHITE));
        getCellAt(6, 6).get().setPiece(new Boat(Color.WHITE));

        for (int i = 1; i < 8; i++) {
            getCellAt(7, i).get().setPiece(new Empty(""));
            getCellAt(7, i).get().setPiece(new Empty(""));
            getCellAt(7, i).get().setPiece(new Empty(""));
        }

        for (int i = 1; i < 8; i++) {
            if (i != 5) {
                getCellAt(8, i).get().setPiece(new Empty(""));
                getCellAt(8, i).get().setPiece(new Empty(""));
                getCellAt(8, i).get().setPiece(new Empty(""));
            }
        }

        String[] status = new String[]{""};
        getKingForColor(Color.BLACK).ifPresent(king -> {
            status[0] = Board.checkStatus(king);
        });

        Assert.assertTrue(status[0].equals(GameStatus.CHECK_AND_MATE));
    }

    @Test
    public void isCheckMateWorkingWhenNotCheckMate() throws Exception {

        Board.create();

        getCellAt(6, 7).get().setPiece(new Elephant(Color.WHITE));
        getCellAt(6, 3).get().setPiece(new Elephant(Color.WHITE));
        getCellAt(6, 5).get().setPiece(new Queen(Color.WHITE));
        getCellAt(6, 4).get().setPiece(new Boat(Color.WHITE));

        for (int i = 1; i < 8; i++) {
            getCellAt(7, i).get().setPiece(new Empty(""));
            getCellAt(7, i).get().setPiece(new Empty(""));
            getCellAt(7, i).get().setPiece(new Empty(""));
        }

        for (int i = 1; i < 8; i++) {
            if (i != 5) {
                getCellAt(8, i).get().setPiece(new Empty(""));
                getCellAt(8, i).get().setPiece(new Empty(""));
                getCellAt(8, i).get().setPiece(new Empty(""));
            }
        }

        String[] status = new String[]{""};
        getKingForColor(Color.BLACK).ifPresent(king -> status[0] = Board.checkStatus(king));

        Assert.assertTrue(status[0].equals(GameStatus.CHECK));
    }


}