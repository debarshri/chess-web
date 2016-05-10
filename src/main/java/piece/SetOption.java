package piece;

import spark.Request;
import spark.Response;
import spark.Route;

public class SetOption implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String color = request.queryParams("color");
        response.cookie("color", color);
        response.redirect("/play");
        return null;
    }
}
