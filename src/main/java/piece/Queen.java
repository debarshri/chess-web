package piece;

import game.Piece;
import utils.Color;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Queen implements Piece {

    private final Elephant elephant;
    private final Boat boat;
    private String color;

    public Queen(String color) {
        this.color = color;
        this.elephant = new Elephant(color);
        this.boat = new Boat(color);
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> step(PositionVector from, PositionVector to) {

        List<PositionVector> positionVectors = generateAllPossibleMoves(from, to);

        if (positionVectors.contains(to)) {
            return Optional.of(to);
        }

        return Optional.empty();
    }

    @Override
    public List<PositionVector> generateAllPossibleMoves(PositionVector from, PositionVector to) {

        List<PositionVector> positionVectors = new ArrayList<>();

        positionVectors.addAll(elephant.generateAllPossibleMoves(from, to));
        positionVectors.addAll(boat.generateAllPossibleMoves(from, to));

        return positionVectors;
    }


    @Override
    public String toString() {
        if (color.equalsIgnoreCase(Color.BLACK)) {
            return "♛";
        } else {
            return "♕";
        }
    }
}
