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
