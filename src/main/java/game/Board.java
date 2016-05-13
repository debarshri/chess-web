package game;


import piece.Empty;
import piece.King;
import server.Cell;
import utils.Color;
import utils.KingUtils;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static game.Initializer.createCells;

public class Board {

    private Board()
    {
        //ignore
    }

    private static List<Cell> cells;

    public static List<Cell> create() {
        cells = createCells();
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
                .filter(c -> c.getY() == to.getY() &&
                        c.getX() == to.getX())
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

    /**
     * For a given color, find the king of other color.
     * Check if any horse, boat, pawn, king, queen or elephant can access the king.
     * if yes then check.
     * If check then check for check-mate.
     * <p>
     * for checkmate, given the king of other color, check the accessibility of pieces from all possible king moves
     */

    public static String checkStatus(Cell kingCell) {

        String[] gameStatus = new String[]{GameStatus.OK};

        cells.stream().filter(c -> !c.getPiece().color().isEmpty())
                .filter(c -> c.getPiece().color().equals(Color.getOther(kingCell.getPiece().color())))
                .forEach(m -> m.getPiece().step(m.getPostionVector(), kingCell.getPostionVector())
                        .ifPresent(p -> gameStatus[0] = GameStatus.CHECK));

        if (gameStatus[0].equals(GameStatus.CHECK)) {
            if (checkForMate(kingCell)) {
                gameStatus[0] = GameStatus.CHECK_AND_MATE;
            }
        }

        return gameStatus[0];

    }

    public static boolean checkForMate(Cell king) {

        List<Boolean> status = new ArrayList<>();
        List<PositionVector> kingMoves = KingUtils.generatePossibleKingMoves(king.getPostionVector());

        kingMoves.stream()
                .forEach(cell -> Board.getCellAt(cell)
                        .ifPresent(k ->
                        cells.stream()
                                .filter(f -> !f.getPiece().color().isEmpty())
                                .filter(f -> f.getPiece().color().equals(Color.getOther(k.getPiece().color())))
                                .forEach(p -> status.add(p.getPiece().step(k.getPostionVector(),
                                        p.getPostionVector()).isPresent()))));

        return !status.contains(false);
    }

    public static Optional<Cell> getKingForColor(String color) {

        return cells.stream()
                .filter(c -> c.getPiece() instanceof King)
                .filter(c -> c.getPiece().color().equals(color))
                .findFirst();
    }
}
