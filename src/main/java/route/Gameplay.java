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

    private static final String WHITE = "white";
    private static final String BLACK = "black";
    private static final String SHOW = "show";
    private static final String BLACK_STATUS = "black_status";
    private static final String WHITE_STATUS = "white_status";
    private static final String GAME_HBS = "game.hbs";
    private static final String BLACK_IS_CHECK_AND_MATE = "Black is Check and Mate";
    private static final String GAME_STATUS_WHITE = "game-status-white";
    private static final String GAME_STATUS_BLACK = "game-status-black";
    private static final String WHITE_IS_HAS_WON = "White is has won";
    private static final String BLACK_IS_S = "Black is %s";
    private static final String WHITE_IS_S = "White is %s";
    private static final String BLACK_IS_CHECK = "Black is Check";
    private static final String WHITE_IS_CHECK_AND_MATE = "White is Check and Mate";

    private HandlebarsTemplateEngine handlebarsTemplateEngine;

    public Gameplay(HandlebarsTemplateEngine handlebarsTemplateEngine) {

        this.handlebarsTemplateEngine = handlebarsTemplateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String show = Board.show();
        String turn = request.cookie("turn");

        Map<String, Object> parameters = new HashMap<>();

        String black;
        String white;

        Swap swap = new Swap(turn).invoke();

        white = swap.getWhite();
        black = swap.getBlack();

        parameters.put(WHITE, white);
        parameters.put(BLACK, black);
        parameters.put(SHOW, show);

        String black_status = StringUtils.defaultString(request.cookie(BLACK_STATUS), GameStatus.OK);
        String white_status = StringUtils.defaultString(request.cookie(WHITE_STATUS), GameStatus.OK);

        Map<String, Object> statues = statusChecker(black_status, white_status);

        parameters.putAll(statues);

        return handlebarsTemplateEngine.render(new ModelAndView(parameters, GAME_HBS));
    }

    private Map<String, Object> statusChecker(String black_status, String white_status) {

        Map<String, Object> parameters = new HashMap<>();
        if (black_status.equals(GameStatus.CHECK_AND_MATE)) {
            parameters.put(GAME_STATUS_BLACK, BLACK_IS_CHECK_AND_MATE);
            parameters.put(GAME_STATUS_WHITE, WHITE_IS_HAS_WON);
        } else if (white_status.equals(GameStatus.CHECK_AND_MATE)) {
            parameters.put(GAME_STATUS_BLACK, BLACK_IS_CHECK);
            parameters.put(GAME_STATUS_WHITE, WHITE_IS_CHECK_AND_MATE);
        } else {
            parameters.put(GAME_STATUS_BLACK, String.format(BLACK_IS_S, black_status));
            parameters.put(GAME_STATUS_WHITE, String.format(WHITE_IS_S, white_status));
        }

        return parameters;
    }

    private static final class Swap {
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
            } else if (turn.equalsIgnoreCase(Color.BLACK)) {
                white = "disabled";
                black = "";
            } else if (turn.equalsIgnoreCase(GameStatus.CHECK_AND_MATE)) {
                white = "disabled";
                black = "disabled";
            }
            return this;
        }
    }
}
