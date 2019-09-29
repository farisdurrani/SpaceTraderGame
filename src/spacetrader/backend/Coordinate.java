package spacetrader.backend;

public class Coordinate {
    private int x;
    private int y;

    /**
     * Creates a new coordinate object
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Calculates the distance between two coordinates
     *
     * @param coordinate1 the first coordinate
     * @param coordinate2 the second coordinate
     * @return the distance between two coordinates
     */
    public static double distance(Coordinate coordinate1, Coordinate coordinate2) {
        return Math.sqrt(Math.pow(coordinate1.x - coordinate2.x, 2)
                + Math.pow(coordinate1.y - coordinate2.y, 2));
    }
}
