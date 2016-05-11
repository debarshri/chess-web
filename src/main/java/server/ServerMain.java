package server;

import route.Gamplay;
import route.Move;
import route.SetOption;
import route.StartApp;
import spark.Spark;

public class ServerMain {
    public static void main(String[] args) {

        Spark.port(8080);

        Spark.get("/", new StartApp());

        Spark.post("/play", new SetOption());
        Spark.get("/play", new Gamplay());

        Spark.post("/move", new Move());


    }
}
