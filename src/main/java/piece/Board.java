package piece;

import piece.black.*;
import piece.white.*;
import server.Cell;
import utils.Color;
import utils.PositionException;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {

    private static List<Cell> cells;

    public static List<Cell> create() throws PositionException {

        if (cells == null) {
            cells = new ArrayList<>();

            cells.add(new Cell(1, 1, new WhiteBoat(Color.WHITE)));
            cells.add(new Cell(1, 2, new Horse(Color.WHITE)));
            cells.add(new Cell(1, 3, new Elephant(Color.WHITE)));

            cells.add(new Cell(1, 4, new Queen(Color.WHITE)));
            cells.add(new Cell(1, 5, new King(Color.WHITE)));

            cells.add(new Cell(1, 6, new Elephant(Color.WHITE)));
            cells.add(new Cell(1, 7, new Horse(Color.WHITE)));
            cells.add(new Cell(1, 8, new WhiteBoat(Color.WHITE)));

            for (int i = 0; i < 8; i++) {
                cells.add(new Cell(2, i + 1, new Pawn(Color.WHITE)));
            }

            for (int j = 2; j < 6; j++) {
                for (int i = 0; i < 8; i++) {
                    cells.add(new Cell(j + 1, i + 1, new Empty("")));
                }
            }

            for (int i = 0; i < 8; i++) {
                cells.add(new Cell(7, i + 1, new BlackPawn(Color.BLACK)));
            }

            cells.add(new Cell(8, 1, new BlackBoat(Color.BLACK)));
            cells.add(new Cell(8, 2, new BlackHorse(Color.BLACK)));
            cells.add(new Cell(8, 3, new BlackElephant(Color.BLACK)));

            cells.add(new Cell(8, 4, new BlackQueen(Color.BLACK)));
            cells.add(new Cell(8, 5, new BlackKing(Color.BLACK)));


            cells.add(new Cell(8, 6, new BlackElephant(Color.BLACK)));
            cells.add(new Cell(8, 7, new BlackHorse(Color.BLACK)));
            cells.add(new Cell(8, 8, new BlackBoat(Color.BLACK)));

        }

        return cells;

    }

    public static void move(String color, PositionVector from, PositionVector to) throws PositionException {

        //  Add logger
        //  System.out.println("From " + from + " to " + to);

        Optional<Cell> optionalTo = getCellAt(to);
        Optional<Cell> optionalFrom = getCellAt(from)
                .filter(p -> p.getPiece().color()
                        .equalsIgnoreCase(color));

        optionalTo.ifPresent(cellTo -> optionalFrom.ifPresent(
                cellFrom -> cellFrom.getPiece()
                        .steps(from, to)
                        .ifPresent(p -> {
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
                        )
        ));
    }

    public static boolean checkForCheck(String color) {
        //todo
        return false;
    }

    public static Optional<Cell> getCellAt(PositionVector to) {

        return cells.stream()
                .filter(c -> c.getHorizontal() == to.getHorizontal() &&
                        c.getVertical() == to.getVertical())
                .findAny();

    }

    public static String show() {

        try {

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

            create().stream().map(p -> {

                if (changeRow[0] != p.getVertical()) {
                    changeRow[0] = p.getVertical();

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

        } catch (PositionException e) {
            e.printStackTrace();
        }
        return "";
    }

}
