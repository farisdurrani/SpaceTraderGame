package spacetrader.backend;

/** Provides a market for players to buy or sell. Prices vary depending on
 * technology level of place, Merchant skillPoints and intrinsic value i.e. base
 * price of item e.g. gold costs more than games. */

public class Market {

    /** Each region has different priceMultiplier numbers referencing to
     * purchasing power and cost of living in those regions. */
    private double regionPriceMultiplier;

    /** Used to determine availability of the MarketItem in different tech
     * levels. For example, phase beams are only available in Futuristic
     * regions.*/
    private int techLevelIndex;

    /**
     * Creates a Market object based on the current region's tech level.
     *
     * @param currentRegion the name of the region player is currently in
     * */
    public Market(Region currentRegion) {
        TechLevel techlevel = currentRegion.getTechLevel();
        if (techlevel.equals(TechLevel.PRE_AG)) {
            regionPriceMultiplier = 0.4;
            techLevelIndex = 0;
        } else if (techlevel.equals(TechLevel.AGRICULTURE)) {
            regionPriceMultiplier = 0.5;
            techLevelIndex = 1;
        } else if (techlevel.equals(TechLevel.MEDIEVAL)) {
            regionPriceMultiplier = 0.6;
            techLevelIndex = 2;
        } else if (techlevel.equals(TechLevel.RENAISSANCE)) {
            regionPriceMultiplier = 0.7;
            techLevelIndex = 3;
        } else if (techlevel.equals(TechLevel.INDUSTRIAL)) {
            regionPriceMultiplier = 0.8;
            techLevelIndex = 4;
        } else if (techlevel.equals(TechLevel.MODERN)) {
            regionPriceMultiplier = 0.9;
            techLevelIndex = 5;
        } else if (techlevel.equals(TechLevel.FUTURISTIC)) {
            regionPriceMultiplier = 1.0;
            techLevelIndex = 6;
        }
    }

    /**
     * Calculates the price effect of player's Merchant Points on final price
     * of item. The price of item will be multiplied by 1 / sqrt (Merchant
     * Points) to get the final price.
     *
     * E.g. if Merchant Points = 16, then final price = price * 1/4
     *
     * @param player the player
     * @return the price effect of player's Merchant Skills on item final price
     * */
    private double merchantPointsInflationIndex(Player player) {
        return 1 / Math.sqrt(player.getMerchantPoints());
    }

    /**
     * Final price of good = base price * region price multiplier *
     * merchantPointsInflationIndex.
     *
     * @param item item to be purchased from market
     * @param player the player
     * @return the final price of item to be bought
     * */
    private double calculateItemPrice(MarketItem item, Player player) {
        return item.getBasePrice() * regionPriceMultiplier
                * merchantPointsInflationIndex(player);
    }
}