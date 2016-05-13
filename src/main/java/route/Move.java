package route;

import game.Board;
import server.Cell;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.Color;
import utils.PositionVector;

import java.util.Optional;

public class Move implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String from = request.queryParams("from");
        String to = request.queryParams("to");

        String[] fromSplit = from.split(",");
        String[] toSplit = to.split(",");

        String color = request.queryParams("color");

        Board.move(color,
                new PositionVector(Integer.valueOf(fromSplit[0]), Integer.valueOf(fromSplit[1])),
                new PositionVector(Integer.valueOf(toSplit[0]), Integer.valueOf(toSplit[1])));

        String other = Color.getOther(color);

        Optional<Cell> blackKing = Board.getKingForColor(Color.BLACK);
        blackKing.ifPresent( k -> response.cookie("white_status", Board.checkStatus(k)));

        Optional<Cell> whiteking = Board.getKingForColor(Color.WHITE);
        whiteking.ifPresent( k -> response.cookie("black_status", Board.checkStatus(k)));

        response.cookie("turn", other);
        response.redirect("/play");

        return null;
    }
}
