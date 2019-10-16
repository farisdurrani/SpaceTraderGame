package spacetrader.backend;

import spacetrader.backend.TechLevel;

/** Items in Market*/

public class MarketItem {

    /** amount in ship cargo*/
    private int amountInShip = 0;
    /** shows which Tech Levels can this item be found. Indexes in array:
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

    public int getBasePrice() {
        return basePrice;
    }

    /** Base price of item before merchant skill and region influences.*/
    private int basePrice;

    private MarketItem(String name) {
        switch (name) {
            case "food":
                basePrice = 10;
                possibleTechLevels[0] = 1;
                possibleTechLevels[1] = 1;
                possibleTechLevels[2] = 1;
                possibleTechLevels[3] = 1;
                possibleTechLevels[4] = 1;
                possibleTechLevels[5] = 1;
                possibleTechLevels[6] = 1;
                break;
            case "medicine":
                basePrice = 20;
                possibleTechLevels[0] = 0;
                possibleTechLevels[1] = 0;
                possibleTechLevels[2] = 1;
                possibleTechLevels[3] = 1;
                possibleTechLevels[4] = 1;
                possibleTechLevels[5] = 1;
                possibleTechLevels[6] = 1;
                break;
            case "weapons":
                basePrice = 50;
                possibleTechLevels[0] = 1;
                possibleTechLevels[1] = 1;
                possibleTechLevels[2] = 1;
                possibleTechLevels[3] = 1;
                possibleTechLevels[4] = 1;
                possibleTechLevels[5] = 1;
                possibleTechLevels[6] = 1;
                break;
            case "gold":
                basePrice = 150;
                possibleTechLevels[0] = 0;
                possibleTechLevels[1] = 0;
                possibleTechLevels[2] = 1;
                possibleTechLevels[3] = 1;
                possibleTechLevels[4] = 1;
                possibleTechLevels[5] = 1;
                possibleTechLevels[6] = 1;
                break;
            case "wood":
                basePrice = 10;
                possibleTechLevels[0] = 1;
                possibleTechLevels[1] = 1;
                possibleTechLevels[2] = 1;
                possibleTechLevels[3] = 1;
                possibleTechLevels[4] = 1;
                possibleTechLevels[5] = 1;
                possibleTechLevels[6] = 0;
                break;
            case "computers":
                basePrice = 60;
                possibleTechLevels[0] = 0;
                possibleTechLevels[1] = 0;
                possibleTechLevels[2] = 0;
                possibleTechLevels[3] = 0;
                possibleTechLevels[4] = 0;
                possibleTechLevels[5] = 1;
                possibleTechLevels[6] = 1;
                break;
            case "robots":
                basePrice = 70;
                possibleTechLevels[0] = 0;
                possibleTechLevels[1] = 0;
                possibleTechLevels[2] = 0;
                possibleTechLevels[3] = 0;
                possibleTechLevels[4] = 0;
                possibleTechLevels[5] = 1;
                possibleTechLevels[6] = 1;
                break;
            case "cotton":
                basePrice = 15;
                possibleTechLevels[0] = 0;
                possibleTechLevels[1] = 0;
                possibleTechLevels[2] = 0;
                possibleTechLevels[3] = 1;
                possibleTechLevels[4] = 1;
                possibleTechLevels[5] = 1;
                possibleTechLevels[6] = 0;
                break;
            case "machineGuns":
                basePrice = 80;
                possibleTechLevels[0] = 0;
                possibleTechLevels[1] = 0;
                possibleTechLevels[2] = 0;
                possibleTechLevels[3] = 0;
                possibleTechLevels[4] = 1;
                possibleTechLevels[5] = 1;
                possibleTechLevels[6] = 1;
                break;
            case "lasers":
                basePrice = 100;
                possibleTechLevels[0] = 0;
                possibleTechLevels[1] = 0;
                possibleTechLevels[2] = 0;
                possibleTechLevels[3] = 0;
                possibleTechLevels[4] = 0;
                possibleTechLevels[5] = 1;
                possibleTechLevels[6] = 1;
                break;
            case "phaseBeams":
                basePrice = 200;
                possibleTechLevels[0] = 0;
                possibleTechLevels[1] = 0;
                possibleTechLevels[2] = 0;
                possibleTechLevels[3] = 0;
                possibleTechLevels[4] = 0;
                possibleTechLevels[5] = 0;
                possibleTechLevels[6] = 1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }
    }

    public int getAmountInShip() {
        return amountInShip;
    }

    public void setAmountInShip(int amountInShip) {
        this.amountInShip = amountInShip;
    }
}
