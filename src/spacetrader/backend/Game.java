package spacetrader.backend;

import spacetrader.backend.market.Market;
import spacetrader.backend.locations.Region;
import spacetrader.backend.locations.Universe;
import spacetrader.backend.market.MarketItem;
import spacetrader.backend.player.Player;

import java.util.HashMap;

public class Game {

    private Player player;
    private Difficulty difficulty;
    private Universe universe;
    private String[] regionNames =
    {
        "Achilles",
        "Icarus",
        "Pandora",
        "Orion",
        "Serpens",
        "Ursa",
        "Cassiopeia",
        "Lyra",
        "Cygnus",
        "Leo"
    };
    /**
     * Creates a new game object with the selected game difficulty
     *
     * @param player the player of the game
     * @param difficulty the game difficulty
     */
    public Game(Player player, Difficulty difficulty) {
        this.player = player;
        this.difficulty = difficulty;

        // Sets the player's credits based on the game difficulty
        this.player.changeCredits(Game.getCredits(difficulty));
    }
    /**
     * Gets the game difficulty
     *
     * @return the game difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Method that instantiates the universe and sets the players credits and
     * starting universe
     */
    public void startGame() {
        universe = new Universe(regionNames);

        universe.goToRegion(universe.getRegionList()[(int) (Math.random()
                * universe.getNumberOfRegions())]);
    }

    /**
     * Gets the game regions.
     *
     * @return the game regions
     */
    public Region[] getRegions() {
        return universe.getRegionList();
    }

    /**
     * Gets the current region.
     *
     * @return the current region
     */
    public Region getCurrentRegion() {
        return universe.getCurrentRegion();
    }

    public Market getCurrentMarket() {
        return universe.getCurrentRegion().getMarket();
    }

    public int getCost(MarketItem item) {
        return item.calculateItemPrice(player,
                universe.getCurrentRegion().getMarket().getRegionPriceMultiplier());
    }

    public int getDistance(Region region) {
        return (int) universe.getDistance(region);
    }

    public int getFuelCost(Region region) {
        return (int) (getDistance(region) * Math.pow(0.9, player.getPilotPoints()));
    }

    /**
     * Moves the player to a new region if they have enough fuel to get there
     *
     * @param region the new region
     * @return true if the player successfully travels to the given region, false otherwise
     */
    public boolean goToRegion(Region region) {
        int fuelCost = getFuelCost(region);
        if (fuelCost < 0) {
            return false;
        }
        if (player.getShip().getCurrentFuel() >= fuelCost) {
            player.getShip().alterCurrentFuel(-1 * fuelCost);
            return universe.goToRegion(region);
        }
        return false;
    }

    /**
     * Moves the player to the previous region after successfully fleeing
     * from NPCs.
     *
     * @param previousRegion the previous region before player encounters NPC
     * @return true if the player successfully travels to the given region, false otherwise
     */
    public boolean goToPreviousRegion(Region previousRegion) {
        int fuelCost = getFuelCost(previousRegion);
        player.getShip().alterCurrentFuel(fuelCost);
        return universe.goToRegion(previousRegion);
    }

    /**
     * Gets the player's name
     *
     * @return the player's name
     */
    public String getPlayerName() {
        return player.getName();
    }

    /**
     * Gets the player's pilot points
     *
     * @return the player's pilot points
     */
    public int getPilotPoints() {
        return player.getPilotPoints();
    }

    /**
     * Gets the player's fighter points
     *
     * @return the player's fighter points
     */
    public int getFighterPoints() {
        return player.getFighterPoints();
    }

    /**
     * Gets the player's merchant points
     *
     * @return the player's merchant points
     */
    public int getMerchantPoints() {
        return player.getMerchantPoints();
    }

    /**
     * Gets the player's engineer points
     *
     * @return the player's engineer points
     */
    public int getEngineerPoints() {
        return player.getEngineerPoints();
    }

    /**
     * Gets the player's credits
     *
     * @return the player's credits
     */
    public int getCredits() {
        return player.getCredits();
    }

    public String getFuel() {
        return player.getShip().getCurrentFuel() + "/" + player.getShip().getMaxFuelCapacity();
    }

    public String getHealth() {
        return player.getShip().getCurrentHealth() + "/" + player.getShip().getMaxHealth();
    }

    public String getCapacity() {
        return player.getShip().getCurrentUsedSpace() + "/" + player.getShip().getMaxCargoSpace();
    }

    public Player getPlayer() {
        return player;
    }

    public String getShipType() {
        return player.getShip().getType();
    }

    public int getCurrentCount(MarketItem item) {
        return player.getShip().getCurrentCount(item);
    }

    public HashMap<String, Integer> getInventoryItems() {
        return player.getShip().getInventoryItems();
    }

    public boolean buyItem(MarketItem item, int quantity) {
        int cost = getCost(item) * quantity;
        if (cost <= player.getCredits() && player.getShip().addItem(item, quantity)) {
            player.changeCredits(-1 * cost);
            return true;
        }
        return false;
    }

    public int addItem(MarketItem item, int quantity) {
        int itemsAdded = 0;
        while (quantity > 0 && player.getShip().addItem(item, quantity)) {
            quantity--;
            itemsAdded++;
        }
        return itemsAdded;
    }

    public boolean sellItem(MarketItem item, int quantity) {
        if (player.getShip().removeItem(item, quantity)) {
            int cost = getCost(item) * quantity;
            player.changeCredits(cost);
            return true;
        }
        return false;
    }

    /**
     * Gets the starting skill points for a given difficulty
     *
     * @param difficulty the selected difficulty
     * @return the starting skill points
     */
    public static int getSkillPoints(Difficulty difficulty) {
        switch (difficulty) {
        case EASY:
            return 16;
        case MEDIUM:
            return 12;
        case HARD:
            return 8;
        default:
            return 0;
        }
    }

    /**
     * Gets the starting credits for a given difficulty
     *
     * @param difficulty the selected difficulty
     * @return the starting credits
     */
    public static int getCredits(Difficulty difficulty) {
        switch (difficulty) {
        case EASY:
            return 1000;
        case MEDIUM:
            return 500;
        case HARD:
            return 100;
        default:
            return 0;
        }
    }

}
