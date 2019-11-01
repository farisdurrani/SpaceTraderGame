package spacetrader.backend.NPC;

import spacetrader.backend.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bandit {

    /** Determines the amount of credits initially demanded by Bandit. Value
     * is between 0 and the initial credits given at start of game. */
    private int moneyDemanded;
    /** Determines strength of Bandit. Player needs to have more Fighter
     * points to defeat Bandit. Value is random between 0 and (x + sqrt
     * (x)), where x is the player's fighter points.*/
    private double strengthLevel;
    /** Determines flying capabilities of Bandit. Player needs to have more
     * Pilot Skills to evade Bandit. Value is between 0 and (y + sqrt
     * (y)), where y is the player's pilot points. */
    private double flyLevel;
    /** Determines amount of damage bandit will put onto ship's health.
     * Varies between 0 and (max possible health of ship). */
    private int damageCaused;
    /** Credits rewarded if player successfully fights Bandit. */
    private int creditsAwarded;
    /** A general image of the bandit. */
    private BufferedImage gasMask;

    public Bandit(Game game) throws IOException {
        moneyDemanded = (int) (Math.random()
                * Game.getCredits(game.getDifficulty()));
        strengthLevel = Math.random() * (game.getFighterPoints()
                        + Math.sqrt(game.getFighterPoints()));
        flyLevel = Math.random() * (game.getPilotPoints()
                        + Math.sqrt(game.getPilotPoints()));
        damageCaused = (int) (Math.random()
                * (game.getPlayer().getShip().getMaxHealth()));
        creditsAwarded = (int) (strengthLevel * 10);
        gasMask = ImageIO.read(this.getClass().getResource(
                "gas_mask.png"));
    }

    public int getMoneyDemanded() {
        return moneyDemanded;
    }

    public double getStrengthLevel() {
        return strengthLevel;
    }

    public double getFlyLevel() {
        return flyLevel;
    }

    public int getDamageCaused() {
        return damageCaused;
    }

    public int getCreditsAwarded() {
        return creditsAwarded;
    }

    public BufferedImage getGasMask() {
        return gasMask;
    }
}
