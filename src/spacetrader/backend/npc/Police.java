package spacetrader.backend.npc;

import spacetrader.backend.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Set;

public class Police {

    /**List of items that the Police are trying to confiscate*/
    private String demandingItem;

    private int fleeThreshold;
    private int fightThreshold;
    public static final int PENALTY_FLEE_POLICE = 100;
    public static final int PENALTY_FIGHT_POLICE = 200;

    private int damage;

    private BufferedImage policeIcon;

    public Police(Game game) {
        Set<String> itemList = game.getInventoryItems().keySet();
        String[] hold = new String[itemList.size()];
        int i = 0;
        for (String key : itemList) {
            hold[i] = key;
        }
        Random rand = new Random();
        demandingItem = hold[rand.nextInt(hold.length)];

        fleeThreshold = rand.nextInt(10) + 10;
        fightThreshold = rand.nextInt(10) + 15;
        damage = rand.nextInt(5) + 5;


        try {
            policeIcon = ImageIO.read(this.getClass().getResource("Police_Icon.png"));
        } catch (Exception e) {
            policeIcon = null;
        }


    }

    public int getFightThreshold() {
        return fightThreshold;
    }

    public int getFleeThreshold() {
        return fleeThreshold;
    }

    public String getDemandingItem() {
        return demandingItem;
    }

    public int getDamage() {
        return damage;
    }

    public BufferedImage getIcon() {
        return policeIcon;
    }
}
