package piece;

import utils.Color;
import utils.PositionVector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static game.Board.getCellAt;
import static game.Initializer.calculateAbsoluteSlope;
import static java.lang.Math.abs;

public class Elephant implements Piece {
    private String color;

    public Elephant(String color) {
        this.color = color;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public Optional<PositionVector> step(PositionVector from, PositionVector to) {

        if (generateAllPossibleMoves(from, to).contains(to)) {
            return Optional.of(to);
        }

        return Optional.empty();
    }

    @Override
    public Collection<PositionVector> generateAllPossibleMoves(PositionVector from, PositionVector to) {
        return addSteps(from, to);
    }

    private List<PositionVector> addSteps(PositionVector from, PositionVector to) {

        List<PositionVector> positionVectors = new ArrayList<>();

        if (abs(calculateAbsoluteSlope(from, to)) == 1.0d) {

            moveForwardRight(from, to, positionVectors);
            moveBackwardRight(from, to, positionVectors);
            moveForwardLeft(from, to, positionVectors);
            moveBackwardLeft(from, to, positionVectors);
        }

        return positionVectors;
    }


    private void moveBackwardRight(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        if ((to.getX() < from.getX()) && (to.getY() > from.getY())) {

            int currentX = from.getX();
            int currentY = from.getY();

            while (currentX >= to.getX()) {

                currentX += -1;
                currentY += 1;

                addPosition(positionVectors, currentX, currentY);
            }
        }
    }

    private void moveBackwardLeft(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        if (to.getY() < from.getX() && (to.getY() < from.getY())) {

            int currentX = from.getX();
            int currentY = from.getY();


            while (currentX >= to.getX()) {

                currentX += -1;
                currentY += -1;

                addPosition(positionVectors, currentX, currentY);
            }
        }
    }

    private void moveForwardLeft(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        if ((to.getX() > from.getX()) && (to.getY() < from.getY())) {

            int currentX = from.getX();
            int currentY = from.getY();

            while (currentX <= to.getX()) {

                currentX += 1;
                currentY += -1;

                addPosition(positionVectors, currentX, currentY);
            }
        }
    }

    private void moveForwardRight(PositionVector from, PositionVector to, List<PositionVector> positionVectors) {

        if ((to.getX() > from.getX()) && to.getY() > from.getY()) {

            int currentX = from.getX();
            int currentY = from.getY();

            while (currentX <= to.getX()) {

                currentX += 1;
                currentY += 1;

                addPosition(positionVectors, currentX, currentY);
            }
        }
    }

    private void addPosition(List<PositionVector> positionVectors, int currentX, int currentY) {
        getCellAt(currentX, currentY)
                .filter(c -> !c.getPiece().color().equalsIgnoreCase(color))
                .ifPresent(c -> positionVectors.add(c.getPostionVector()));
    }

    @Override
    public String toString() {

        if (color.equalsIgnoreCase(Color.BLACK)) {
            return "♝";
        } else {
            return "♗";
        }
    }
}
