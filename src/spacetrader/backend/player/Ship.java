package spacetrader.backend.player;

import spacetrader.backend.market.MarketItem;

import java.util.HashMap;

public class Ship {
    private String type;
    private Inventory inventory;
    private int currentFuel;
    private int maxFuelCapacity;
    private int currentHealth;
    private int maxHealth;

    private String[] typeList = {
        "Starship",
        "Jet",
        "Wasp",
        "Ladybug"
    };
    /**
     * Constructor for Ship that sets the type along with the variable changes
     * that the type entails
     * @param shipType ShipType enum for the switch statement
     */
    public Ship(ShipType shipType) {
        switch (shipType) {
        case STARTER:
            inventory = new Inventory(100);
            maxFuelCapacity = 200;
            maxHealth = 50;
            type = "Starter";
            break;
        case STARSHIP:
            inventory = new Inventory(500);
            maxFuelCapacity = 1000;
            maxHealth = 500;
            type = "Starship";
            break;
        case JET:
            inventory = new Inventory(100);
            maxFuelCapacity = 200;
            maxHealth = 100;
            type = "Jet";
            break;
        case WASP:
            inventory = new Inventory(100);
            maxFuelCapacity = 400;
            maxHealth = 50;
            type = "Wasp";
            break;
        case LADYBUG:
            inventory = new Inventory(200);
            maxFuelCapacity = 100;
            maxHealth = 150;
            type = "Ladybug";
            break;
        default:
            inventory = new Inventory(0);
            maxFuelCapacity = 0;
            maxHealth = 0;
        }
        currentFuel = maxFuelCapacity;
        currentHealth = maxHealth;
    }

    /**
     * Current space getter function
     * @return current space as an int
     */
    public int getCurrentUsedSpace() {
        return inventory.getUsedSpace();
    }

    /**
     * Current fuel getter function
     * @return current fuel as an int
     */
    public int getCurrentFuel() {
        return currentFuel;
    }

    /**
     * Maximum Fuel Capacity getter function
     * @return maximum fuel capacity as an int
     */
    public int getMaxFuelCapacity() {
        return maxFuelCapacity;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Maximum Cargo Space getter function
     * @return maximum cargo space as an int
     */
    public int getMaxCargoSpace() {
        return inventory.getSize();
    }

    /**
     * Current health getter function
     * @return current health as an int
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    public String getType() {
        return type;
    }

    /**
     * Function to alter the fuel; can add or subtract.
     * @param variable amount that the fuel should be affected
     * @return boolean based on whether successfully fueled up or not
     * can be used as a check to determine whether the player can travel
     * given their current fuel supply
     */
    public boolean alterCurrentFuel(int variable) {
        if (currentFuel + variable > maxFuelCapacity) {
            currentFuel = maxFuelCapacity;
            return true;
        } else if (currentFuel + variable < 0) {
            return false;
        } else {
            currentFuel += variable;
            return true;
        }
    }

    /**
     * Function to alter the health; can add or subtract.
     * @param variable amount that the health should be affected
     * @return boolean based on whether successfully altered health up or not
     * can be used as a check to determine whether the player is still alive
     */
    public boolean alterCurrentHealth(int variable) {
        if (currentHealth + variable > maxHealth) {
            currentHealth = maxHealth;
            return true;
        } else if (currentHealth +  variable < 0) {
            return false;
        } else {
            currentHealth += variable;
            return true;
        }
    }

    public int getCurrentCount(MarketItem item) {
        if (item.getOfficialItemName().equals("Fuel")) {
            return currentFuel;
        }
        return inventory.getCurrentCount(item);
    }

    public HashMap<String, Integer> getInventoryItems() {
        return inventory.getItems();
    }

    public boolean addItem(MarketItem item, int quantity) {
        if (item.getOfficialItemName().equals("Fuel")) {
            if (currentFuel + quantity <= maxFuelCapacity) {
                currentFuel += quantity;
                return true;
            }
            return false;
        }
        return inventory.addItem(item, quantity);
    }

    public boolean removeItem(MarketItem item, int quantity) {
        if (item.getOfficialItemName().equals("Fuel")) {
            if (currentFuel - quantity >= 0) {
                currentFuel -= quantity;
                return true;
            }
            return false;
        }
        return inventory.removeItem(item, quantity);
    }

    public boolean removeAllItems() {
        return inventory.removeAllItems();
    }
}
