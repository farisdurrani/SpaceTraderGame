package spacetrader.backend.locations;

import spacetrader.backend.market.Market;
import spacetrader.backend.player.Player;

public class Region {
    private String name;
    private Coordinate coordinate;
    private TechLevel techLevel;
    private Market market;
    private Player player;

    /**
     * Creates a new Region object
     *
     * @param name the name of the region
     * @param x the x-coordinate of the region
     * @param y the y-coordinate of the region
     * @param techLevel the tech level of the region
     * @param player the player
     */
    public Region(String name, int x, int y, TechLevel techLevel,
                  Player player) {
        this.name = name;
        this.coordinate = new Coordinate(x, y);
        this.techLevel = techLevel;
        this.player = player;
    }

    /**
     * Creates a new Region object
     *
     * @param name the name of the region
     * @param coordinate the coordinate of the region
     * @param techLevel the tech level of the region
     * @param player the player
     */
    public Region(String name, Coordinate coordinate, TechLevel techLevel,
                  Player player) {
        this.name = name;
        this.coordinate = coordinate;
        this.techLevel = techLevel;
        this.player = player;
    }

    /**
     * Gets the name of the region
     *
     * @return the name of the region
     */
    public String getName() {
        return name;
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

    public Market getMarket(Player player) {
        if (market == null) {
            market = new Market(techLevel, player);
        }
        return market;
    }

    /**
     * Calculates the distance from this region to another region
     *
     * @param region the region to measure the distance to
     * @return the distance to the given region
     */
    public double distanceTo(Region region) {
        return Coordinate.distance(this.coordinate, region.coordinate);
    }
}
