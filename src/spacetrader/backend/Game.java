package spacetrader.backend;

import spacetrader.ui.Difficulty;

import java.util.Random;

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

        if (gameDifficulty == Difficulty.EASY) {
            player.setCurrentCredits(1000);
        } else if (gameDifficulty == Difficulty.MEDIUM) {
            player.setCurrentCredits(500);
        } else if (gameDifficulty == Difficulty.HARD) {
            player.setCurrentCredits(100);
        }

        player.setCurrentRegion(universe.getRegionList()[(int)(Math.random() * regionNames.length)]);
    }

    /**
     * Returns the game regions
     *
     * @return the game regions
     */
    public Region[] getRegions() {
        return universe.getRegionList();
    }

    /**
     * Moves the player to a new region
     *
     * @param region the new region
     */
    public void goToRegion(Region region) {
        player.setCurrentRegion(region);
    }

}
