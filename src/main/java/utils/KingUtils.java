package utils;

import java.util.ArrayList;
import java.util.List;

public class KingUtils {

    public static List<PositionVector> generatePossibleKingMoves(PositionVector positionVector) {

        //todo
        List<PositionVector> positionVectors = new ArrayList<>();

        int horizontal = positionVector.getY();
        int vertical = positionVector.getX();

        positionVectors.add(new PositionVector(vertical + 1, horizontal + 1));
        positionVectors.add(new PositionVector(vertical + 1, horizontal));
        positionVectors.add(new PositionVector(vertical, horizontal + 1));
        positionVectors.add(new PositionVector(vertical, horizontal));

        return positionVectors;
    }
}
