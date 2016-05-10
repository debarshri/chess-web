package utils;

public class Color {
    public static final String WHITE = "WHITE";
    public static final String BLACK = "BLACK";

    public static String getOther(String color) {
        if (color.equals("WHITE")) {
            return BLACK;
        } else {
            return WHITE;
        }
    }
}
