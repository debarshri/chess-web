package test;

import org.junit.Test;
import piece.Board;
import utils.Color;
import utils.PositionException;
import utils.PositionVector;

public class GameTests {

    @Test
    public void shouldWork() throws PositionException {

        Board.create();
        Board.move(Color.WHITE,new PositionVector(1,2), new PositionVector(3,1));
    }




}
