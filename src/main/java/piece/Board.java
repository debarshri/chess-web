package piece;

import server.Cell;
import utils.Color;
import utils.PositionException;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 1 2 3 4 5 6 7 8
A
B
C
D
E
F
G
H
 */
public class Board {

    private static List<Piece> pieces;
    private static List<Cell> cells;

    public Board() {

        if(pieces == null)
        {
            pieces = new ArrayList<>();
        }


        if(cells == null)
        {
            cells = new ArrayList<>();
        }
    }

    public List<Piece> getPieces()
    {
        return pieces;
    }

    public List<Piece> create(String color) throws PositionException {

        if(pieces.size() == 0 && cells.size() == 0)
        {
            cells.add(new Cell(1,1, new Boat(color)));

            pieces.add(new Elephant(color, 1,2));
            pieces.add(new Horses(color, 1,3));

            pieces.add(new Boat(color,1,1));
            pieces.add(new Elephant(color, 1,2));
            pieces.add(new Horses(color, 1,3));

            pieces.add(new King(color, 1,4));
            pieces.add(new Queen(color, 1,5));

            pieces.add(new Horses(color, 1,6));
            pieces.add(new Elephant(color, 1,7));
            pieces.add(new Boat(color, 1,8));

            for(int i=0; i < 8; i++)
            {
                pieces.add(new Pawn(color, 2,i+1));
            }

                for(int j=2; j < 6; j++)
                {
                    for(int i=0; i < 8; i++)
                    {
                    pieces.add(new Empty("", j+1,i+1));
                }
            }

            for(int i=0; i < 8; i++)
            {
                pieces.add(new Pawn(Color.getOther(color), 7,i+1));
            }

            pieces.add(new Boat(Color.getOther(color),8,1));
            pieces.add(new Elephant(Color.getOther(color), 8,2));
            pieces.add(new Horses(Color.getOther(color), 8,3));

            pieces.add(new King(Color.getOther(color), 8,4));
            pieces.add(new Queen(Color.getOther(color), 8,5));

            pieces.add(new Horses(Color.getOther(color), 8,6));
            pieces.add(new Elephant(Color.getOther(color), 8,7));
            pieces.add(new Boat(Color.getOther(color), 8,8));

        }

        return pieces;

    }

    public void move(String color, PositionVector from, PositionVector to) throws PositionException {
        List<Piece> collect = pieces.stream()
                .filter(p -> p.color().equalsIgnoreCase(color))
                .filter(p -> p.currentPosition().getHorizontal() == from.getHorizontal() &&
                        p.currentPosition().getVertical() == from.getVertical())
                .collect(Collectors.toList());

        System.out.println("Pieces found"+collect.size());
        if(collect.size() == 1)
        {
            Piece piece = collect.get(0);
            piece.steps(from, to).ifPresent( p -> {
                try {

                    System.out.println("There,.,,");
                    piece.setPostion(p);

                    pieces.stream()
                            .filter( k -> k.currentPosition() == p && !k.color().equals(piece.color()))
                            .findFirst()
                            .ifPresent(k -> pieces.remove(k));

                } catch (PositionException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) throws PositionException {
        Board board = new Board();
        board.create(Color.WHITE);

    }

}
