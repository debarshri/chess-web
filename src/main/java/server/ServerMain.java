package server;

import piece.*;
import route.Gamplay;
import route.StartApp;
import spark.Spark;

public class ServerMain {
    public static void main(String[] args) {

        Spark.port(8080);

        Board board = new Board();

        Spark.get("/", new StartApp());

        Spark.post("/play", new SetOption());
        Spark.get("/play", new Gamplay(board));

        Spark.post("/move", new Move(board));


    }
}
