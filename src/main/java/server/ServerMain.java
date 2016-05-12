package server;

import route.Gameplay;
import route.Move;
import route.ClearGameOption;
import route.StartApp;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ServerMain {
    public static void main(String[] args) {

        Spark.port(8080);

        HandlebarsTemplateEngine handlebarsTemplateEngine = new HandlebarsTemplateEngine();

        Spark.get("/", new StartApp());

        Spark.post("/play", new ClearGameOption());
        Spark.get("/play", new Gameplay(handlebarsTemplateEngine));

        Spark.post("/move", new Move());

        Spark.exception(Exception.class, ((e, request, response) -> {
            response.redirect("/");
        }));

    }
}
