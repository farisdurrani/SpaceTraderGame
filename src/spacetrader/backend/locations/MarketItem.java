package spacetrader.backend.locations;

import spacetrader.backend.player.Player;

/** Items in Market*/

public class MarketItem {

    /** Name of item. */
    private String officialItemName;
    /** Amount in ship cargo. */
    private int amountInShip = 0;
    /**
     * Shows which Tech Levels can this item be found. Indexes in array:
     * 0 = pre-ag
     * 1 = agriculture
     * 2 = medieval
     * 3 = renaissance
     * 4 = industrial
     * 5 = modern
     * 6 = futuristic
     *
     * Values of 0 in the specific index indicates non-availability, 1 otherwise
     * */
    private int[] possibleTechLevels = {0,0,0,0,0,0,0};
    /** Base price of item before merchant skill and region influences.*/
    private double basePrice;

    /** Creates market items.
     *
     * @param good the name of the market item to create
     **/
    MarketItem(MarketGoods good) {
        switch (good.getCamelCaseName()) {
            case "food":
                officialItemName = "Food";
                // base price = 1 - 30
                basePrice = produceBasePrice(1, 30);
                // available in all tech levels
                createPossibleTechLevels(1,1,1,1,1,1,1);
                break;
            case "medicine":
                officialItemName = "Medicine";
                // base price = 10 - 50
                basePrice = produceBasePrice(10, 50);
                // not available in PRE_AG, AGRICULTURE
                createPossibleTechLevels(0,0,1,1,1,1,1);
                break;
            case "weapons":
                officialItemName = "Weapons";
                // base price = 30 - 70
                basePrice = produceBasePrice(30, 70);
                // available in all tech levels
                createPossibleTechLevels(1,1,1,1,1,1,1);
                break;
            case "gold":
                officialItemName = "Gold";
                // base price = 100 - 200
                basePrice = produceBasePrice(100, 200);
                // not available in PRE-AG, AGRICULTURE
                createPossibleTechLevels(0,0,1,1,1,1,1);
                break;
            case "wood":
                officialItemName = "Wood";
                // base price = 1 - 30
                basePrice = produceBasePrice(1, 30);
                // not available in FUTURISTIC
                createPossibleTechLevels(1,1,1,1,1,1,0);
                break;
            case "computers":
                officialItemName = "Computers";
                // base price = 40 - 100
                basePrice = produceBasePrice(40, 100);
                // only available in MODERN, FUTURISTIC
                createPossibleTechLevels(0,0,0,0,0,1,1);
                break;
            case "robots":
                officialItemName = "Robots";
                // base price = 60 - 120
                basePrice = produceBasePrice(60, 120);
                // only available in MODERN, FUTURISTIC
                createPossibleTechLevels(0,0,0,0,0,1,1);
                break;
            case "cotton":
                officialItemName = "Cotton";
                // base price = 10 - 30
                basePrice = produceBasePrice(10, 30);
                // only available in MEDIEVAL - FUTURISTIC inclusive
                createPossibleTechLevels(0,0,1,1,1,1, 0);
                break;
            case "machineGuns":
                officialItemName = "Machine Guns";
                // base price = 70 - 100
                basePrice = produceBasePrice(70, 100);
                // only available in INDUSTRIAL, MODERN, FUTURISTIC
                createPossibleTechLevels(0,0,0,0,1,1,1);
                break;
            case "lasers":
                officialItemName = "Lasers";
                // base price = 80  -120
                basePrice = produceBasePrice(80, 120);
                // only available in MODERN, FUTURISTIC
                createPossibleTechLevels(0,0,0,0,0,1,1);
                break;
            case "phaseBeams":
                officialItemName = "Phase Beams";
                // base price = 180 - 250
                basePrice = produceBasePrice(180, 250);
                // only available in FUTURISTIC
                createPossibleTechLevels(0,0,0,0,0,0,1);
                break;
            case "fuel":
                officialItemName = "Fuel";
                // base price = 1 - 30
                basePrice = produceBasePrice(1, 30);
                // available in tech levels AGRICULTURE - MODERN inclusive
                createPossibleTechLevels(0,1,1,1,1,1,0);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + good.getCamelCaseName());
        }
    }

    /**
     * Produces a random base price for good given a minimum price and a
     * maximum price.
     *
     * @param minPrice the minimum possible base price of good
     * @param maxPrice the maximum possible base price of good
     * @return a random base price for the good
     * */
    private double produceBasePrice(int minPrice, int maxPrice) {
        return (Math.random() * ((maxPrice - minPrice) + 1)) + minPrice;
    }

    /**
     * Customizes the tech levels in which the item is present in.
     *
     * Values of 0 in the specific index indicates non-availability, 1 otherwise
     *
     * @param pre_ag whether the item is present in a tech level of PRE_AG
     * @param agriculture whether the item is present in a tech level of
     *                 AGRICULTURE
     * @param medieval whether the item is present in a tech level of MEDIEVAL
     * @param renaissance whether the item is present in a tech level of
     *                 RENAISSANCE
     * @param industrial whether the item is present in a tech level of INDUSTRIAL
     * @param modern whether the item is present in a tech level of MODERN
     * @param futuristic whether the item is present in a tech level of FUTURISTIC
     * */
    private void createPossibleTechLevels(int pre_ag, int agriculture,
                                          int medieval,
                                          int renaissance, int industrial,
                                          int modern,
                                          int futuristic) {
        possibleTechLevels[0] = pre_ag;
        possibleTechLevels[1] = agriculture;
        possibleTechLevels[2] = medieval;
        possibleTechLevels[3] = renaissance;
        possibleTechLevels[4] = industrial;
        possibleTechLevels[5] = modern;
        possibleTechLevels[6] = futuristic;
    }

    /**
     * Calculates the price effect of player's Merchant Points on final price
     * of item. The price of item will be multiplied by 1 / sqrt (Merchant
     * Points) to get the final price. If Merchant Points = 0, price increases.
     *
     * E.g. if Merchant Points = 16, then final price = price * 1/4
     *
     * @param player the player
     * @return the price effect of player's Merchant Skills on item final price
     * */
    private double merchantPointsInflationIndex(Player player) {
        if (player.getMerchantPoints() == 0) {
            return 1.5;
        } else {
            return 1 / Math.sqrt(player.getMerchantPoints());
        }
    }

    /**
     * Final price of good = base price * region price multiplier *
     * merchantPointsInflationIndex.
     *
     * @param player the player
     * @param regionPriceMultiplier the inflation effects of goods in current
     *                             region
     * @return the final price of item to be bought
     * */
    public int calculateItemPrice(Player player, double regionPriceMultiplier) {
        return (int)(basePrice * regionPriceMultiplier
                * merchantPointsInflationIndex(player));
    }

    /**
     * Gets the current amount of the item in ship.
     *
     * @return current amount of item in ship*/
    public int getAmountInShip() {
        return amountInShip;
    }

    /**
     * Alters the current amount of item in ship.
     * NOTE: Fuel can be set in two ways via this class or via Ship class.
     *
     * @param amountInShip the new amount of item in ship*/
    public void setAmountInShip(int amountInShip) {
        this.amountInShip = amountInShip;
    }

    /**
     * Gets the possibleTechLevels int list.
     *
     * @returns possibleTechLevels */
    int[] getPossibleTechLevels() {
        return possibleTechLevels;
    }

    /** Gets item name.
     *
     * @return item name */
    public String getOfficialItemName() {
        return officialItemName;
    }
}
