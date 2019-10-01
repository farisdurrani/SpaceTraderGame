package spacetrader.backend;

import spacetrader.ui.Difficulty;

import java.util.Random;

public class Game {
    private Difficulty gameDifficulty;
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
     * @param gameDifficulty the game difficulty
     */
    public Game(Difficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    public Difficulty getGameDifficulty() {
        return gameDifficulty;
    }

    /**
     * Method that instantiates the universe and sets the players credits and
     * starting universe
     * @param mainPlayer player from the
     */
    public void StartGame(Player mainPlayer) {
        Universe universe = new Universe(regionNames);
        Random rand = new Random();

        mainPlayer.setCurrentRegion(universe.getRegionList()[rand.nextInt(10)]);
        if (gameDifficulty == Difficulty.EASY) {
            mainPlayer.setCurrentCredits(1000);
        } else if (gameDifficulty == Difficulty.MEDIUM) {
            mainPlayer.setCurrentCredits(500);
        } else if (gameDifficulty == Difficulty.HARD) {
            mainPlayer.setCurrentCredits(100);
        }
    }
}
