package game;

import piece.Empty;
import piece.King;
import piece.Piece;
import utils.PositionVector;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static game.Initializer.createCells;
import static utils.Color.getOtherColor;
import static utils.KingUtils.generatePossibleKingMoves;

public class Board {

    private Board() {
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

        if (piece.color().equalsIgnoreCase(getOtherColor(piece1.color()))) {
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
                .filter(c -> c.getY() == y && c.getX() == x)
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

        getAllCellsForColor(getOtherColor(kingCell.getPiece().color()))
                .forEach(m -> m.getPiece()
                        .step(m.getPostionVector(), kingCell.getPostionVector())
                        .ifPresent(p ->
                        {
                            if (checkForMate(kingCell, p)) {
                                gameStatus[0] = GameStatus.CHECK_AND_MATE;
                            } else {
                                gameStatus[0] = GameStatus.CHECK;
                            }
                        }));

        return gameStatus[0];

    }

    private static boolean canCheckingPieceBeKilled(PositionVector position, String color) {
        Cell checkingCell = getCellAt(position).get();

        return getAllCellsForColor(color).stream()
                .map(cell -> checkingCell.getPiece().step(position, cell.getPostionVector()))
                .findAny()
                .isPresent();
    }


    private static List<Cell> getAllCellsForColor(String color) {

        return cells.stream()
                .filter(cell -> cell.getPiece().color()
                        .equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    static boolean checkForMate(Cell king, PositionVector positionVector) {

        Collection<PositionVector> kingMoves = generatePossibleKingMoves(king.getPostionVector(), king.getPiece().color());

        if (kingMoves.isEmpty()) {
            return !canCheckingPieceBeKilled(positionVector, king.getPiece().color());
        } else {
            return kingCannotMoveSafely(king, kingMoves);
        }
    }

    static boolean kingCannotMoveSafely(Cell king, Collection<PositionVector> kingMoves) {

        List<Cell> allCellsForColor = getAllCellsForColor(getOtherColor(king.getPiece().color()));

        Collection<Optional<PositionVector>> collect = kingMoves.stream()
                .map(cell -> getCellAt(cell).get())
                .flatMap(cell -> allCellsForColor.stream()
                        .map(f -> f.getPiece().step(f.getPostionVector(), cell.getPostionVector())))
                .filter(Optional::isPresent)
                .collect(Collectors.toSet());

        return kingMoves.size() == collect.size();
    }

    public static Optional<Cell> getKingForColor(String color) {

        return cells.stream()
                .filter(c -> c.getPiece() instanceof King)
                .filter(c -> c.getPiece().color().equals(color))
                .findFirst();
    }
}
