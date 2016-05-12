package route;

import game.Board;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.Color;
import utils.PositionVector;

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


        response.cookie("turn", Color.getOther(color));
        response.redirect("/play");

        return null;
    }
}
