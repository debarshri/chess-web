package piece;

import spark.Request;
import spark.Response;
import spark.Route;
import utils.PositionException;
import utils.PositionVector;

public class Move implements Route {
    private Board board;

    public Move(Board board) {
        this.board = board;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        try {

            String from = request.queryParams("from");
            String to = request.queryParams("to");

            String[] fromSplit = from.split("-");
            String[] toSplit = to.split("-");

            board.move(request.cookie("color"),
                    new PositionVector(Integer.valueOf(fromSplit[0]), Integer.valueOf(fromSplit[1])),
                    new PositionVector(Integer.valueOf(toSplit[0]), Integer.valueOf(toSplit[1])));

            response.redirect("/play");

        } catch (PositionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
