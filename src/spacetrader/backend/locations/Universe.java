package spacetrader.backend.locations;

public class Universe {
    private Region[] regions;
    private int currentRegion;

    /**
     * Creates a new Universe with regions specified by the region names
     *
     * @param regionNames the names of the regions in the new universe
     */
    public Universe(String[] regionNames) {
        regions = new Region[regionNames.length];

        for (int i = 0; i < regions.length; i++) {
            // Creates coordinates for each region randomly, validating that none of the regions are
            // within 5 in either the X or Y direction
            Coordinate coordinate = new Coordinate();
            for (int j = 0; j < i; j++) {
                if (Coordinate.distanceX(coordinate, regions[j].getCoordinate()) <= 5
                        || Coordinate.distanceY(coordinate, regions[j].getCoordinate()) <= 5) {
                    coordinate = new Coordinate();
                    j = 0;
                }
            }

            // Gets a random tech level for each region
            TechLevel techLevel = TechLevel.values()[(int) (Math.random()
                    * TechLevel.values().length)];

            regions[i] = new Region(regionNames[i], coordinate, techLevel);
        }
    }

    /**
     * Gets all the regions
     *
     * @return regions the array of regions
     */
    public Region[] getRegionList() {
        return regions;
    }

    /**
     * Gets the number of regions in the universe
     *
     * @return the number of regions
     */
    public int getNumberOfRegions() {
        return regions.length;
    }

    /**
     * Gets the current region
     *
     * @return the current region
     */
    public Region getCurrentRegion() {
        return regions[currentRegion];
    }

    /**
     * Travels to the given region
     *
     * @param region the ID of the region to travel to
     */
    public void goToRegion(int region) {
        if (region >= 0 && region < regions.length) {
            currentRegion = region;
        }
    }

    /**
     * Calculates the distance from the current region to a given region
     *
     * @param region the region to measure the distance to
     * @return the distance from the current region to the given region
     */
    public double getDistance(int region) {
        if (region >= 0 && region < regions.length) {
            return regions[currentRegion].distanceTo(regions[region]);
        }
        return -1.0;
    }
}
