package route;

import game.Board;
import spark.Request;
import spark.Response;
import spark.Route;


public class StartApp implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {

        response.removeCookie("turn");
        Board.create();
        response.redirect("/play");
        return null;
    }
}
