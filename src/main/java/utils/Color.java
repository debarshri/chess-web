package utils;

public class Color {
    public static final String WHITE = "WHITE";
    public static final String BLACK = "BLACK";

    public static String getOtherColor(String color) {
        if (color.equalsIgnoreCase("WHITE")) {
            return BLACK;
        } else {
            return WHITE;
        }
    }
}
