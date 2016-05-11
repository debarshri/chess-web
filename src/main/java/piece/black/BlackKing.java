package piece.black;

import piece.Piece;
import utils.PositionException;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlackKing implements Piece {

    private String color;

    public BlackKing(String white) {
        this.color = white;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> steps(PositionVector from, PositionVector to) {

        List<PositionVector> positionVectors = generatePossibleMoves(from);

        if (positionVectors.contains(to)) {
            return Optional.of(to);
        }

        return Optional.empty();
    }

    private List<PositionVector> generatePossibleMoves(PositionVector positionVector) {

        List<PositionVector> positionVectors = new ArrayList<>();


            int horizontal = positionVector.getHorizontal();
            int vertical = positionVector.getVertical();

            positionVectors.add(new PositionVector(vertical + 1, horizontal + 1));
            positionVectors.add(new PositionVector(vertical + 1, horizontal));
            positionVectors.add(new PositionVector(vertical, horizontal + 1));
            positionVectors.add(new PositionVector(vertical, horizontal));


        return positionVectors;
    }

    @Override
    public String toString() {
        return "♚";
    }
}
