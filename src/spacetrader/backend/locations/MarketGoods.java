package spacetrader.backend.locations;

/** Contains all possible goods sold at all markets. */
public enum MarketGoods {
    FOOD("food"),
    MEDICINE("medicine"),
    WEAPONS("weapons"),
    GOLD("gold"),
    WOOD("wood"),
    COMPUTERS("computers"),
    ROBOTS("robots"),
    COTTON("cotton"),
    MACHINE_GUNS("machineGuns"),
    LASERS("lasers"),
    PHASE_BEAMS("phaseBeams"),
    FUEL("fuel");

    private String camelCaseName;

    /**
     * Provides a camel case version of the enum.
     *
     * @param camelCaseName the enum in camel case
     **/
    MarketGoods(String camelCaseName) {
        this.camelCaseName = camelCaseName;
    }

    /**
     * Camel case names of enums setter.
     *
     * @return camel case names of enums
     **/
    public String getCamelCaseName() {
        return camelCaseName;
    }
}
