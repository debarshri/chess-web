package game;

import utils.PositionVector;

import java.util.List;
import java.util.Optional;

public interface Piece {

    String color();

    Optional<PositionVector> step(PositionVector from, PositionVector to);

    List<PositionVector> generateAllPossibleMoves(PositionVector from, PositionVector to);
}
