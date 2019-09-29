package team78.spacetrader.backend;

public class Region {
    private String name;
    private Coordinate coordinate;
    private TechLevel techLevel;

    /**
     * Creates a new Region object
     *
     * @param name the name of the region
     * @param x the x-coordinate of the region
     * @param y the y-coordinate of the region
     * @param techLevel the tech level of the region
     */
    public Region(String name, int x, int y, TechLevel techLevel) {
        this.name = name;
        this.coordinate = new Coordinate(x, y);
        this.techLevel = techLevel;
    }

    /**
     * Creates a new Region object
     *
     * @param name the name of the region
     * @param coordinate the coordinate of the region
     * @param techLevel the tech level of the region
     */
    public Region(String name, Coordinate coordinate, TechLevel techLevel) {
        this.name = name;
        this.coordinate = coordinate;
        this.techLevel = techLevel;
    }
}
