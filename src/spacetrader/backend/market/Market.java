package spacetrader.backend.market;

import spacetrader.backend.locations.TechLevel;
import spacetrader.backend.player.Player;

import java.util.ArrayList;

/** Provides a market for players to buy or sell. Prices vary depending on
 * technology level of place, Merchant skillPoints and intrinsic value i.e. base
 * price of item e.g. gold costs more than games. */

public class Market {

    /** Each region has different priceMultiplier numbers referencing to
     * the inflation effects of goods in those regions. */
    private double regionPriceMultiplier;

    /** A worse (lower) social credit will increase overall prices of all
     * markets. */
    private double socialCreditPenalty;

    /** Used to determine availability of the MarketItem in different tech
     * levels. For example, phase beams are only available in Futuristic
     * regions. */
    private int techLevelIndex;

    /** Contains the market items in this region, restricted by the region's
     * tech level. */
    private ArrayList<MarketItem> marketItemsInRegion = new ArrayList<>();

    /**
     * Creates a Market object based on the current region's tech level.
     *
     * @param techLevel the tech level of the region the player is currently in
     * @param player the player
     * */
    public Market(TechLevel techLevel, Player player) {
        if (techLevel.equals(TechLevel.PRE_AG)) {
            regionPriceMultiplier = 0.4
                    + calculateSocialCreditPenalty(player);
            techLevelIndex = 0;
            addMarketItems();
        } else if (techLevel.equals(TechLevel.AGRICULTURE)) {
            regionPriceMultiplier = 0.5
                    + calculateSocialCreditPenalty(player);
            techLevelIndex = 1;
            addMarketItems();
        } else if (techLevel.equals(TechLevel.MEDIEVAL)) {
            regionPriceMultiplier = 0.6
                    + calculateSocialCreditPenalty(player);
            techLevelIndex = 2;
            addMarketItems();
        } else if (techLevel.equals(TechLevel.RENAISSANCE)) {
            regionPriceMultiplier = 0.7
                    + calculateSocialCreditPenalty(player);
            techLevelIndex = 3;
            addMarketItems();
        } else if (techLevel.equals(TechLevel.INDUSTRIAL)) {
            regionPriceMultiplier = 0.8
                    + calculateSocialCreditPenalty(player);
            techLevelIndex = 4;
            addMarketItems();
        } else if (techLevel.equals(TechLevel.MODERN)) {
            regionPriceMultiplier = 0.9
                    + calculateSocialCreditPenalty(player);
            techLevelIndex = 5;
            addMarketItems();
        } else if (techLevel.equals(TechLevel.FUTURISTIC)) {
            regionPriceMultiplier = 1.0
                    + calculateSocialCreditPenalty(player);
            techLevelIndex = 6;
            addMarketItems();
        }
    }

    private void addMarketItems() {
        for (MarketGoods good : MarketGoods.values()) {
            MarketItem newItem = new MarketItem(good);
            // adds the market item if it exists in region's techLevel
            if (newItem.getPossibleTechLevels()[techLevelIndex]) {
                marketItemsInRegion.add(newItem);
            }
        }
    }

    public void addUniverseDeed(String playerName) {
        marketItemsInRegion.add(new UniverseDeed(playerName));
    }

    private double calculateSocialCreditPenalty(Player player) {
        return 1 - (double) (player.getSocialCredits())
                / player.getMaxSocialCredits();
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