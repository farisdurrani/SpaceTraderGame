package spacetrader.backend.player;

public class Player {

    private String name;

    private int credits;

    private int pilotPoints;
    private int fighterPoints;
    private int merchantPoints;
    private int engineerPoints;

    private Ship ship;

    /**
     * Creates a new Player
     *
     * @param name the player's name
     * @param pilotPoints pilot points
     * @param fighterPoints fighter points
     * @param merchantPoints merchant points
     * @param engineerPoints engineer points
     */
    public Player(String name, int pilotPoints, int fighterPoints, int merchantPoints,
                  int engineerPoints) {
        this.name = name;

        this.pilotPoints = pilotPoints;
        this.fighterPoints = fighterPoints;
        this.merchantPoints = merchantPoints;
        this.engineerPoints = engineerPoints;

        this.credits = 0;

        this.ship = new Ship(ShipType.STARTER);
    }

    //<editor-fold desc="Getters and Setters">

    /**
     * Modify the player's credits
     *
     * @param newCredits credits to add to the player's credits
     */
    public void changeCredits(int newCredits) {
        if (newCredits + credits >= 0) {
            credits += newCredits;
        } else {
            credits = 0;
        }
    }

    /**
     * Add an additional skill point to the Pilot category
     */
    public void incrementPilotPoints() {
        this.pilotPoints++;
    }

    /**
     * Add an additional skill point to the Fighter category
     */
    public void setFighterPoints() {
        this.fighterPoints++;
    }

    /**
     * Add an additional skill point to the Merchant category
     */
    public void setMerchantPoints() {
        this.merchantPoints++;
    }

    /**
     * Add an additional skill point to the Engineer category
     */
    public void setEngineerPoints() {
        this.engineerPoints++;
    }

    /**
     * Gets the player's name
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current amount of credits
     *
     * @return number of credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Gets the pilot points
     *
     * @return the pilot points
     */
    public int getPilotPoints() {
        return this.pilotPoints;
    }

    /**
     * Gets the fighter points
     *
     * @return the fighter points
     */
    public int getFighterPoints() {
        return this.fighterPoints;
    }

    /**
     * Gets the merchant points
     *
     * @return the merchant points
     */
    public int getMerchantPoints() {
        return this.merchantPoints;
    }

    /**
     * Gets the engineer points
     *
     * @return the engineer points
     */
    public int getEngineerPoints() {
        return engineerPoints;
    }

    /**
     * Gets the player's ship
     *
     * @return the player's ship
     */
    public Ship getShip() {
        return ship;
    }
    //</editor-fold>

}
