package game;

import piece.*;
import server.Cell;
import utils.Color;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Initializer {

    public static List<Cell> createCells() {
        List<Cell> cells = new ArrayList<>();

        cells.add(new Cell(1, 1, new Boat(Color.WHITE)));
        cells.add(new Cell(1, 2, new Horse(Color.WHITE)));
        cells.add(new Cell(1, 3, new Elephant(Color.WHITE)));

        cells.add(new Cell(1, 4, new Queen(Color.WHITE)));
        cells.add(new Cell(1, 5, new King(Color.WHITE)));

        cells.add(new Cell(1, 6, new Elephant(Color.WHITE)));
        cells.add(new Cell(1, 7, new Horse(Color.WHITE)));
        cells.add(new Cell(1, 8, new Boat(Color.WHITE)));

        for (int i = 0; i < 8; i++) {
            cells.add(new Cell(2, i + 1, new Pawn(Color.WHITE)));
        }

        for (int j = 2; j < 6; j++) {
            for (int i = 0; i < 8; i++) {
                cells.add(new Cell(j + 1, i + 1, new Empty("")));
            }
        }

        for (int i = 0; i < 8; i++) {
            cells.add(new Cell(7, i + 1, new Pawn(Color.BLACK)));
        }

        cells.add(new Cell(8, 1, new Boat(Color.BLACK)));
        cells.add(new Cell(8, 2, new Horse(Color.BLACK)));
        cells.add(new Cell(8, 3, new Elephant(Color.BLACK)));

        cells.add(new Cell(8, 4, new Queen(Color.BLACK)));
        cells.add(new Cell(8, 5, new King(Color.BLACK)));


        cells.add(new Cell(8, 6, new Elephant(Color.BLACK)));
        cells.add(new Cell(8, 7, new Horse(Color.BLACK)));
        cells.add(new Cell(8, 8, new Boat(Color.BLACK)));

        return cells;
    }

    public static IntStream reverseRange(int from, int to) {
        return IntStream.rangeClosed(from, to).map(i -> to - i + from);
    }

    public static double calculateAbsoluteSlope(PositionVector from, PositionVector to) {
        return (to.getY() - from.getY()) / (to.getX() - from.getX());
    }
}
