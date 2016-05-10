package piece;

import utils.PositionVector;

import java.util.Optional;

public interface Piece {

    String color();
    Optional<PositionVector> steps(PositionVector from, PositionVector to);
}
