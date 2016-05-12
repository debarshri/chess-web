package game;


import piece.*;
import server.Cell;
import utils.Color;
import utils.KingUtils;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Board {

    private static List<Cell> cells;

    public static List<Cell> create() {

        cells = new ArrayList<>();

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

    /**
     * Check if cell from and to exists
     * IF yes, then check if the piece is allowed to go to that cell
     * if yes then swap
     */
    public static void move(String color, PositionVector from, PositionVector to) {

        getCellAt(to).ifPresent(cellTo ->
                getCellAt(from)
                        .filter(p -> p.getPiece()
                                .color()
                                .equalsIgnoreCase(color))
                        .ifPresent(cellFrom ->
                                cellFrom.getPiece()
                                        .step(from, to)
                                        .ifPresent(p -> swap(cellTo, cellFrom))));
    }

    private static void swap(Cell cellTo, Cell cellFrom) {
        Piece piece = cellTo.getPiece();
        Piece piece1 = cellFrom.getPiece();

        if (piece.color().equalsIgnoreCase(Color.getOther(piece1.color()))) {
            cellTo.setPiece(piece1);
            cellFrom.setPiece(new Empty(""));
        } else {
            cellTo.setPiece(piece1);
            cellFrom.setPiece(piece);
        }
    }


    public static Optional<Cell> getCellAt(PositionVector to) {

        return cells.stream()
                .filter(c -> c.getY() == to.getHorizontal() &&
                        c.getX() == to.getVertical())
                .findAny();
    }

    public static Optional<Cell> getCellAt(int x, int y) {

        return cells.stream()
                .filter(c -> c.getY() == y &&
                        c.getX() == x)
                .findAny();
    }

    public static String show() {

        final String[] s = {"<table class=\"table table-bordered table-striped \"><tr>"};

        String th = "";

        for (int i = 0; i <= 8; i++) {
            if (i == 0) {
                th = th + "<td></td>";
            } else {
                th = th + "<td>" + i + "</td>";
            }
        }

        final int[] changeRow = {1};

        s[0] = s[0] + th + "</tr>";
        s[0] = s[0] + "<td>" + changeRow[0] + "</td>";

        if (cells == null) {
            create();
        }

        cells.stream().map(p -> {

            if (changeRow[0] != p.getX()) {
                changeRow[0] = p.getX();

                String color = p.getPiece().color();

                if (color.isEmpty()) {
                    return "</tr><tr><td>" + changeRow[0] + "</td><td></td>";
                }
                return "</tr><tr><td>" + changeRow[0] + "</td><td>" + p.getPiece() + " </td>";
            } else {
                String color = p.getPiece().color();

                if (color.isEmpty()) {
                    return "<td></td>";
                }
                return "<td>" + p.getPiece() + "</td>";
            }
        }).forEach(p -> s[0] = s[0] + p);
        s[0] = s[0] + "</tr></table>";

        return s[0];

    }

    public static IntStream revRange(int from, int to) {
        return IntStream.rangeClosed(from, to).map(i -> to - i + from);
    }

    /**
     * For a given color, find the king of other color.
     * Check if any horse, boat, pawn, king, queen or elephant can access the king.
     * if yes then check.
     * If check then check for check-mate.
     * <p>
     * for checkmate, given the king of other color, check the accessibility of pieces from all possible king moves
     */

    public static String ifCheck(String color) {


        Optional<Cell> kingForColor = Board.getKingForColor(color);
        String[] gameStatus = new String[]{GameStatus.OK};

        cells.stream().filter(c -> !c.getPiece().color().isEmpty())
                .filter(c -> c.getPiece().color().equals(Color.getOther(color)))
                .forEach(m -> m.getPiece().step(m.getPostionVector(), kingForColor.get().getPostionVector())
                        .ifPresent(p -> gameStatus[0] = GameStatus.CHECK));

        if(gameStatus[0].equals(GameStatus.CHECK))
        {
            if(checkForMate(kingForColor))
            {
                gameStatus[0] = GameStatus.CHECK_AND_MATE;
            }
        }

        return gameStatus[0];

    }

    private static boolean checkForMate(Optional<Cell> king) {

        List<Boolean> status = new ArrayList<>();
        List<PositionVector> kingMoves = KingUtils.generatePossibleKingMoves(king.get().getPostionVector());

        kingMoves.stream()
                .forEach(c -> Board.getCellAt(c).ifPresent(k ->
                cells.stream().filter(f -> !f.getPiece().color().isEmpty())
                        .filter(f -> f.getPiece().color().equals(Color.getOther(k.getPiece().color())))
                        .forEach(p -> status.add(p.getPiece().step(k.getPostionVector(),
                                p.getPostionVector()).isPresent()))));

        return !status.contains(false);
    }

    private static Optional<Cell> getKingForColor(String color) {

        return cells.stream()
                .filter(c -> c.getPiece() instanceof King)
                .filter(c -> c.getPiece().color().equals(color))
                .findFirst();
    }
}
