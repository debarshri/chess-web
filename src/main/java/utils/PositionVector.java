package utils;

public class PositionVector {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private final int x;
    private final int y;

    public PositionVector(int x, int y) {

        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PositionVector)) return false;

        PositionVector that = (PositionVector) o;

        return x == that.x &&
                y == that.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "PositionVector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
