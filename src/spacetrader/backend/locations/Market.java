package spacetrader.backend.locations;

import java.util.ArrayList;

/** Provides a market for players to buy or sell. Prices vary depending on
 * technology level of place, Merchant skillPoints and intrinsic value i.e. base
 * price of item e.g. gold costs more than games. */

public class Market {

    /** Each region has different priceMultiplier numbers referencing to
     * the inflation effects of goods in those regions. */
    private double regionPriceMultiplier;

    /** Used to determine availability of the MarketItem in different tech
     * levels. For example, phase beams are only available in Futuristic
     * regions. */
    private int techLevelIndex = 99;

    private MarketGoods[] marketGoods = MarketGoods.values();

    /** Contains the market items in this region, restricted by the region's
     * tech level. */
    private ArrayList<MarketItem> marketItemsInRegion = new ArrayList<>();

    /**
     * Creates a Market object based on the current region's tech level.
     *
     * @param currentRegion the region the player is currently in
     * */
    public Market(Region currentRegion) {
        TechLevel techlevel = currentRegion.getTechLevel();
        if (techlevel.equals(TechLevel.PRE_AG)) {
            regionPriceMultiplier = 0.4;
            techLevelIndex = 0;
            addMarketItems(techLevelIndex);
        } else if (techlevel.equals(TechLevel.AGRICULTURE)) {
            regionPriceMultiplier = 0.5;
            techLevelIndex = 1;
            addMarketItems(techLevelIndex);
        } else if (techlevel.equals(TechLevel.MEDIEVAL)) {
            regionPriceMultiplier = 0.6;
            techLevelIndex = 2;
            addMarketItems(techLevelIndex);
        } else if (techlevel.equals(TechLevel.RENAISSANCE)) {
            regionPriceMultiplier = 0.7;
            techLevelIndex = 3;
            addMarketItems(techLevelIndex);
        } else if (techlevel.equals(TechLevel.INDUSTRIAL)) {
            regionPriceMultiplier = 0.8;
            techLevelIndex = 4;
            addMarketItems(techLevelIndex);
        } else if (techlevel.equals(TechLevel.MODERN)) {
            regionPriceMultiplier = 0.9;
            techLevelIndex = 5;
            addMarketItems(techLevelIndex);
        } else if (techlevel.equals(TechLevel.FUTURISTIC)) {
            regionPriceMultiplier = 1.0;
            techLevelIndex = 6;
            addMarketItems(techLevelIndex);
        }
    }

    private void addMarketItems(int techLevelIndex) {
        for (MarketGoods good : marketGoods) {
            MarketItem newItem = new MarketItem(good);
            // adds the market item if it exists in region's techLevel
            int possible = newItem.getPossibleTechLevels()[techLevelIndex];
            if (possible == 1) {
                marketItemsInRegion.add(newItem);
            }
        }
    }

    /** Gets the available market items in the region.
     *
     * @return available market items in the region */
    public ArrayList<MarketItem> getMarketItemsInRegion() {
        return marketItemsInRegion;
    }

    public double getRegionPriceMultiplier() {
        return regionPriceMultiplier;
    }
}