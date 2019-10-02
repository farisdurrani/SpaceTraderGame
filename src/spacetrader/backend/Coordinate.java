package spacetrader.backend;

public class Coordinate {

    private int x;
    private int y;

    /**
     * Creates a new coordinate object with random coordinates
     */
    public Coordinate() {
        this((int) (Math.random() * 400) - 200, (int) (Math.random() * 400) - 200);
    }

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

    @Override
    public String toString() {
        return x + ", " + y;
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

    /**
     * Calculates the distance between two coordinates along the x-axis
     *
     * @param coordinate1 the first coordinate
     * @param coordinate2 the second coordinate
     * @return the distance between two coordinates along the x-axis
     */
    public static double distanceX(Coordinate coordinate1, Coordinate coordinate2) {
        return Math.abs(coordinate1.x - coordinate2.x);
    }

    /**
     * Calculates the distance between two coordinates along the y-axis
     *
     * @param coordinate1 the first coordinate
     * @param coordinate2 the second coordinate
     * @return the distance between two coordinates along the y-axis
     */
    public static double distanceY(Coordinate coordinate1, Coordinate coordinate2) {
        return Math.abs(coordinate1.y - coordinate2.y);
    }
}
