package route;

import game.Board;
import game.GameStatus;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.Color;
import utils.PositionVector;

import java.util.function.Consumer;

import static game.Board.getKingForColor;
import static java.lang.Integer.valueOf;
import static utils.Color.getOtherColor;

public class Move implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String from = request.queryParams("from");
        String to = request.queryParams("to");

        Parse parse = new Parse(from, to).invoke();

        String[] fromSplit = parse.getFromSplit();
        String[] toSplit = parse.getToSplit();

        String color = request.queryParams("color");

        Board.move(color,
                new PositionVector(valueOf(fromSplit[0]), valueOf(fromSplit[1])),
                new PositionVector(valueOf(toSplit[0]), valueOf(toSplit[1])));

        response.cookie("turn", getOtherColor(color));

        getKingForColor(Color.BLACK)
                .map(Board::checkStatus)
                .ifPresent(setGameStatus(response, "black_status"));

        getKingForColor(Color.WHITE)
                .map(Board::checkStatus)
                .ifPresent(setGameStatus(response, "white_status"));

        response.redirect("/play");

        return null;
    }

    private Consumer<String> setGameStatus(Response response, String color_status) {
        return value -> {
            if (value.equals(GameStatus.CHECK_AND_MATE)) {
                response.cookie("turn", GameStatus.CHECK_AND_MATE);
            }

            response.cookie(color_status, value);
        };
    }

    private class Parse {
        private String from;
        private String to;
        private String[] fromSplit;
        private String[] toSplit;

        public Parse(String from, String to) {
            this.from = from;
            this.to = to;
        }

        public String[] getFromSplit() {
            return fromSplit;
        }

        public String[] getToSplit() {
            return toSplit;
        }

        public Parse invoke() {
            fromSplit = from.split(",");
            toSplit = to.split(",");
            return this;
        }
    }
}
