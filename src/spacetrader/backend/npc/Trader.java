package spacetrader.backend.npc;

import spacetrader.backend.Game;
import spacetrader.backend.market.MarketGoods;
import spacetrader.backend.market.MarketItem;
import spacetrader.backend.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Trader {

    private MarketItem item;
    private int itemCount;
    private int itemCost;
    private double negotiationChance;
    private double robChance;
    private int damageCaused;
    private BufferedImage icon;
    private Player player;

    public Trader(Game game) {
        item = new MarketItem(MarketGoods.values()[(int) (Math.random()
                * MarketGoods.values().length)]);
        itemCount = (int) (Math.random() * 25);
        itemCost = item.calculateItemPrice(game.getPlayer(),
                game.getCurrentRegion().getMarket().getRegionPriceMultiplier());

        negotiationChance = (double) game.getMerchantPoints() / (1.0 + (double) game.getMerchantPoints());
        robChance = (double) game.getFighterPoints() / (1.0 + (double) game.getFighterPoints());

        damageCaused = (int) (Math.random() * (game.getPlayer().getShip().getMaxHealth()));

        try {
            icon = ImageIO.read(this.getClass().getResource("trader_icon.png"));
        } catch (Exception e) {
            icon = null;
        }
    }

    public MarketItem getItem() {
        return item;
    }

    public String getItemName() {
        return item.getOfficialItemName();
    }

    public int getItemCount() {
        return itemCount;
    }

    public int getItemCost() {
        return itemCost * itemCount;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public String getNegotiationChance() {
        return (int) (negotiationChance * 100) + "%";
    }

    public boolean negotiate() {
        boolean negotiated = Math.random() >= negotiationChance;
        if (negotiated) {
            itemCost /= 2;
        } else {
            itemCost *= 2;
        }
        return negotiated;
    }

    public String getRobChance() {
        return (int) (robChance * 100) + "%";
    }

    public boolean rob() {
        boolean negotiated = Math.random() >= negotiationChance;
        if (negotiated) {
            itemCost /= 2;
        } else {
            itemCost *= 2;
        }
        return negotiated;
    }

    public int getDamageCaused() {
        return damageCaused;
    }
}
