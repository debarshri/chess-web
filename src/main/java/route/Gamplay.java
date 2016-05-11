package route;

import piece.Board;
import spark.Request;
import spark.Response;
import spark.Route;


public class Gamplay implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {


        String show = Board.show();

        //Show Game status

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
                show +
                "\n" +
                "" +
                "<hr />" +
                "<div class=\"row\">" +
                "<div class=\"col-xs-6\">" +
                "<form class=\"form-group col-xs-10\" method=\"POST\" action=\"/move?color=white\">" +
                "<h3> White pieces </h3>" +
                "<legend>From</legend>" +
                "<input type=\"text\" class=\"form-control\" name=\"from\" placeholder=\"horizontal,vertical e.g 1,2\" required/>" +

                "<legend>To</legend>" +
                "<input type=\"text\" class=\"form-control\" name=\"to\" placeholder=\"horizontal,vertical\" required/>" +
                "<br />" +
                "<button class=\"btn btn-default " + white + "\">Submit</button>" +
                "</form>" +
                "</div>" +

                "<div class=\"col-xs-6\">" +
                "<form class=\"form-group col-xs-10\" method=\"POST\" action=\"/move?color=black\">" +
                "<h3> Black pieces </h3>" +
                "<legend>From</legend>" +
                "<input type=\"text\" class=\"form-control\" name=\"from\" placeholder=\"horizontal,vertical e.g 2-1\" required/>" +

                "<legend>To</legend>" +
                "<input type=\"text\" class=\"form-control\" name=\"to\" placeholder=\"horizontal,vertical\" required/>" +
                "<br />" +
                "<button class=\"btn btn-default " + black + "\">Submit</button>" +
                "</form>" +
                "<div>" +
                "<a href=\"/\" class=\"btn btn-danger\" >Reset</a>" +
                "</div>"+
                "</div>" +
                "" +

                "</div>" +
                "    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>\n" +
                "    <!-- Include all compiled plugins (below), or include individual files as needed -->\n" +
                "    <script src=\"js/bootstrap.min.js\"></script>\n" +
                "  </body>\n" +
                "</html>";
    }
}
