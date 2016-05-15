package piece;

import utils.PositionVector;

import java.util.Collection;
import java.util.Optional;

public interface Piece {

    String color();

    Optional<PositionVector> step(PositionVector from, PositionVector to);

    Collection<PositionVector> generateAllPossibleMoves(PositionVector from, PositionVector to);
}
