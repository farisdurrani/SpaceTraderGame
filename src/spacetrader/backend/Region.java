package spacetrader.backend;

public class Region {
    private static int nextID = 0;

    private String name;
    private int id;
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
        this.id = nextID++;
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

    /**
     * Gets the name of the region
     *
     * @return the name of the region
     */
    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    /**
     * Gets the coordinates of the region
     *
     * @return the coordinates of the region
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Gets the tech level of the region
     *
     * @return the tech level of the region
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }
}
