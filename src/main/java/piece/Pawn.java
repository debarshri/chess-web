package piece;

import utils.PositionException;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pawn implements Piece {

    private final PositionVector postion;
    private String color;

    public Pawn(String color, int vertical, int horizontal) throws PositionException {
        this.color = color;
        this.postion = new PositionVector(vertical, horizontal);
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> steps(PositionVector to, PositionVector from) {
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
        return postion;
    }

    @Override
    public void setPostion(int horizontal, int vertical) throws PositionException {

    }

    @Override
    public void setPostion(PositionVector postionVector) throws PositionException {

    }
}
