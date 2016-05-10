package route;

import piece.Board;
import piece.Piece;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.PositionException;

import java.util.List;
import java.util.function.Function;


public class Gamplay implements Route {
    private Board board;
    private Function<Piece, String> pieceStringFunction;

    public Gamplay(Board board) {
        this.board = board;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String color = request.cookie("color");
        final String[] s = {"<table class=\"table\"><tr>"};

        String th = "";

        for (int i = 0; i <= 8; i++) {
            if (i == 0) {
                th = th + "<td></td>";
            } else {
                th = th + "<td>" + i + "</td>";
            }
        }

        try {
            List<Piece> pieces = board.create(color);

            final int[] changeRow = {1};
            s[0] = s[0] + th + "</tr>";
            s[0] = s[0] + String.format("<td>%s</td>", changeRow[0]);

            pieceStringFunction = p -> {
                if (changeRow[0] != p.currentPosition().getVertical()) {
                    changeRow[0] = p.currentPosition().getVertical();
                    return "</tr><tr><td>" + changeRow[0] + "</td><td>" + p.getClass().getName() + " (" + p.color() + ") </td>";
                } else {
                    return "<td>" + p.getClass().getName() + "(" + p.color() + ")</td>";
                }
            };

            pieces.stream().map(pieceStringFunction).forEach(p -> s[0] = s[0] + p);
            s[0] = s[0] + "</tr></table>";

        } catch (PositionException e) {
            e.printStackTrace();
        }

        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\n" +
                "    <title>Chess</title>\n" +
                "\n" +
                "    <!-- Bootstrap -->\n" +
                "<!-- Latest compiled and minified CSS -->\n" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">\n" +
                "\n" +
                "<!-- Optional theme -->\n" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css\" integrity=\"sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r\" crossorigin=\"anonymous\">\n" +
                "\n" +
                "<!-- Latest compiled and minified JavaScript -->\n" +
                "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\" integrity=\"sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS\" crossorigin=\"anonymous\"></script>" +
                "      <script src=\"https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js\"></script>\n" +
                "      <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\n" +
                "    <![endif]-->\n" +
                "  </head>\n" +
                "  <body>\n" +
                s[0] +
                "\n" +
                "" +
                "<hr />" +
                "" +
                "<form class=\"form-group col-xs-2\" method=\"POST\" action=\"/move\">" +
                "<legend>From</legend>" +
                "<input type=\"text\" class=\"form-control\" name=\"from\" placeholder=\"vertical-horizontal\" required/>" +

                "<legend>To</legend>" +
                "<input type=\"text\" class=\"form-control\" name=\"to\" placeholder=\"vertical-horizontal\" required/>" +
                "<br />" +
                "<button class=\"btn btn-default\">Submit</button>" +
                "</form>" +
                "    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>\n" +
                "    <!-- Include all compiled plugins (below), or include individual files as needed -->\n" +
                "    <script src=\"js/bootstrap.min.js\"></script>\n" +
                "  </body>\n" +
                "</html>";
    }
}
