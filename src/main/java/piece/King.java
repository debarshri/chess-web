package piece;

import piece.Piece;
import utils.PositionException;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class King implements Piece {

    private PositionVector position;
    private String color;

    public King(String white, int vertical, int horizontal) throws PositionException {
        this.color = white;
        this.position = new PositionVector(vertical,horizontal);
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> steps(PositionVector from, PositionVector to) {

        PositionVector positionVector = currentPosition();

        List<PositionVector> positionVectors = generatePossibleMoves(positionVector);

        if (positionVectors.contains(to)) {
            return Optional.of(to);
        }

        return Optional.empty();
    }

    private List<PositionVector> generatePossibleMoves(PositionVector positionVector) {

        List<PositionVector> positionVectors = new ArrayList<>();

        try {

            int horizontal = positionVector.getHorizontal();
            int vertical = positionVector.getVertical();

            positionVectors.add(new PositionVector(vertical + 1, horizontal + 1));
            positionVectors.add(new PositionVector(vertical + 1, horizontal));
            positionVectors.add(new PositionVector(vertical, horizontal + 1));
            positionVectors.add(new PositionVector(vertical, horizontal));

        } catch (PositionException e) {
            e.printStackTrace();
        }

        return positionVectors;
    }

    @Override
    public PositionVector currentPosition() {
        return position;
    }

    @Override
    public void setPostion(int vertical, int horizontal) throws PositionException {

        this.position = new PositionVector(vertical,horizontal);

    }

    @Override
    public void setPostion(PositionVector postionVector) throws PositionException {
        this.position = postionVector;
    }
}
