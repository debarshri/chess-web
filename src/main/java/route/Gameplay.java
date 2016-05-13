package route;

import game.Board;
import game.GameStatus;
import org.apache.commons.lang3.StringUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utils.Color;

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

        Swap swap = new Swap(turn).invoke();

        white = swap.getWhite();
        black = swap.getBlack();

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("white", white);
        parameters.put("black", black);
        parameters.put("show", show);
        parameters.put("game-status", "Black is "+ StringUtils.defaultString(request.cookie("black_status"), GameStatus.OK)
                +"\n"+"white is "+StringUtils.defaultString(request.cookie("white_status"), GameStatus.OK));

        return handlebarsTemplateEngine.render(new ModelAndView(parameters, "game.hbs"));
    }

    private class Swap {
        private String turn;
        private String black;
        private String white;

        public Swap(String turn) {
            this.turn = turn;
        }

        public String getBlack() {
            return black;
        }

        public String getWhite() {
            return white;
        }

        public Swap invoke() {
            if (turn == null) {
                black = "disabled";
                white = "";

            } else if (turn.equalsIgnoreCase(Color.WHITE)) {
                black = "disabled";
                white = "";
            } else {
                white = "disabled";
                black = "";
            }
            return this;
        }
    }
}
