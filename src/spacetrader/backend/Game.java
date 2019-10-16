package spacetrader.backend;

import spacetrader.backend.locations.Region;
import spacetrader.backend.locations.Universe;
import spacetrader.backend.player.Player;

public class Game {
    private Player player;
    private Difficulty gameDifficulty;
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
     * @param gameDifficulty the game difficulty
     */
    public Game(Player player, Difficulty gameDifficulty) {
        this.player = player;
        this.gameDifficulty = gameDifficulty;

        // Sets the player's credits based on the game difficulty
        switch (gameDifficulty) {
            case EASY:
                this.player.setCurrentCredits(1000);
            case MEDIUM:
                this.player.setCurrentCredits(500);
            case HARD:
                this.player.setCurrentCredits(100);
        }
    }

    /**
     * Returns the game difficulty
     *
     * @return the game difficulty
     */
    public Difficulty getGameDifficulty() {
        return gameDifficulty;
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

    public int getCurrentCredits() {
        return player.getCurrentCredits();
    }

}
