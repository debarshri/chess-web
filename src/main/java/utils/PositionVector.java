package utils;

public class PositionVector {

    public int getVertical() {
        return vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    private final int vertical;
    private final int horizontal;

    public PositionVector(int vertical, int horizontal) {

        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PositionVector)) return false;

        PositionVector that = (PositionVector) o;

        if (vertical != that.vertical) return false;
        return horizontal == that.horizontal;

    }

    @Override
    public int hashCode() {
        int result = vertical;
        result = 31 * result + horizontal;
        return result;
    }

    @Override
    public String toString() {
        return "PositionVector{" +
                "vertical=" + vertical +
                ", horizontal=" + horizontal +
                '}';
    }
}
