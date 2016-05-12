package route;

import game.Board;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class Gameplay implements Route {

    private HandlebarsTemplateEngine handlebarsTemplateEngine;

    public Gameplay(HandlebarsTemplateEngine handlebarsTemplateEngine) {

        this.handlebarsTemplateEngine = handlebarsTemplateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String show = Board.show();
        String turn = request.cookie("turn");

        String black;
        String white;
        if (turn == null) {
            black = "disabled";
            white = "";

        } else if (turn.equalsIgnoreCase("WHITE")) {
            black = "disabled";
            white = "";
        } else {
            white = "disabled";
            black = "";
        }

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("white", white);
        parameters.put("black", black);
        parameters.put("show", show);

        return handlebarsTemplateEngine.render(new ModelAndView(parameters, "game.hbs"));
    }
}
