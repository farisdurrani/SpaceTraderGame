package spacetrader.backend;

import spacetrader.backend.locations.Region;
import spacetrader.backend.locations.Universe;
import spacetrader.backend.player.Player;

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
     * Returns the game difficulty
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

        universe.goToRegion((int) (Math.random() * universe.getNumberOfRegions()));
    }

    /**
     * Returns the game regions
     *
     * @return the game regions
     */
    public Region[] getRegions() {
        return universe.getRegionList();
    }

    public Region getCurrentRegion() {
        return universe.getCurrentRegion();
    }

    /**
     * Moves the player to a new region
     *
     * @param region the new region
     */
    public void goToRegion(int region) {
        double distance = universe.getDistance(region);
        double fuelCost = distance;
        universe.goToRegion(region);
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getPilotPoints() {
        return player.getPilotPoints();
    }

    public int getFighterPoints() {
        return player.getFighterPoints();
    }

    public int getMerchantPoints() {
        return player.getMerchantPoints();
    }

    public int getEngineerPoints() {
        return player.getEngineerPoints();
    }

    public int getCredits() {
        return player.getCredits();
    }

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
