package spacetrader.backend.player;

import spacetrader.backend.locations.Inventory;

public class Ship {
    private String type;
    private int currentUsedSpace;
    private int maxCargoSpace;
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
            maxCargoSpace = 100;
            maxFuelCapacity = 100;
            maxHealth = 50;
            type = "Starter";
            break;
        case STARSHIP:
            maxCargoSpace = 500;
            maxFuelCapacity = 500;
            maxHealth = 500;
            type = "Starship";
            break;
        case JET:
            maxCargoSpace = 100;
            maxFuelCapacity = 100;
            maxHealth = 100;
            type = "Jet";
            break;
        case WASP:
            maxCargoSpace = 100;
            maxFuelCapacity = 200;
            maxHealth = 50;
            type = "Wasp";
            break;
        case LADYBUG:
            maxCargoSpace = 200;
            maxFuelCapacity = 50;
            maxHealth = 150;
            type = "Ladybug";
            break;
        default:
            maxCargoSpace = 0;
            maxFuelCapacity = 0;
            maxHealth = 0;
        }
        currentUsedSpace = 0;
        currentFuel = maxFuelCapacity;
        currentHealth = maxHealth;
    }

    /**
     * Current space getter function
     * @return current space as an int
     */
    public int getCurrentUsedSpace() {
        return currentUsedSpace;
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
        return maxCargoSpace;
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
     * Function to alter the fuel; can add or subtract
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
     * Function to alter the health; can add or subtract
     * @param variable amount that the health should be affected
     * @return boolean based on whether successfully added health or not
     * can be used as a check to determine whether the player has died
     * or made it max health
     */
    public boolean alterCurrentHealth(int variable) {
        if (currentHealth + variable > maxHealth) {
            currentHealth = maxHealth;
            return true;
        } else if (currentHealth + variable < 0) {
            return false;
        } else {
            currentHealth += variable;
            return true;
        }
    }

    /**
     * Function to alter the space; can add or subtract
     * @param variable amount that the space should be affected
     * @return boolean based on whether successfully affected the amount of
     * space that is left or not can be used as a check to determine whether
     * the player cannot add any more items or there's max space available
     */
    public boolean alterCurrentSpace(int variable) {
        if (currentUsedSpace + variable > maxCargoSpace) {
            currentUsedSpace = maxCargoSpace;
            return true;
        } else if (currentUsedSpace + variable < 0) {
            currentUsedSpace = 0;
            return false;
        } else {
            currentUsedSpace += variable;
            return true;
        }
    }
}
