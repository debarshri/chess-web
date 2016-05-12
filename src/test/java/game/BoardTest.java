package game;

import org.junit.Test;
import utils.Color;
import utils.PositionVector;

public class BoardTest {

    @Test
    public void isCheckWorking() throws Exception {

        Board.create();

        Board.move(Color.WHITE, new PositionVector(1,2), new PositionVector(3,3));
        Board.move(Color.WHITE, new PositionVector(3,3), new PositionVector(4,5));

        Board.move(Color.WHITE, new PositionVector(4,5), new PositionVector(6,4));

        System.out.println(Board.ifCheck(Color.BLACK));

    }
}