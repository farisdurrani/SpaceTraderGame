package spacetrader.backend.player;

public class Player {

    private String name;

    private int credits;
    private int socialCredits;
    public static final int MAXSOCIALCREDITS = 850;

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
        this.socialCredits = 0;

        this.ship = new Ship(ShipType.STARTER);
    }

    //<editor-fold desc="Getters and Setters">

    /**
     * Modify the player's credits
     *
     * @param variable credits to add to the player's credits
     * @return boolean based on whether successfully added / subtracted
     * credits or not can be used as a check to determine whether the player
     * has enough credits to buy a selected good
     */
    public boolean changeCredits(int variable) {
        if (variable + credits >= 0) {
            credits += variable;
            return true;
        } else {
            credits = 0;
            return false;
        }
    }

    /**
     * Modify the player's social credits
     *
     * @param variable credits to add to the player's social credits
     * @return boolean based on whether successfully added / subtracted
     * credits or not can be used as a check to determine whether the player
     * has enough credits to buy a selected good
     */
    public boolean changeSocialCredits(int variable) {
        int finalSocialCredits = variable + socialCredits;
        if (finalSocialCredits >= 0) {
            if (finalSocialCredits > MAXSOCIALCREDITS) {
                socialCredits = MAXSOCIALCREDITS;
                return true;
            } else {
                socialCredits += variable;
                return true;
            }
        } else {
            socialCredits = 0;
            return false;
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
     * Gets the current amount of social credits
     *
     * @return number of social credits
     */
    public int getSocialCredits() {
        return socialCredits;
    }

    /**
     * Gets the maximum amount of social credits
     *
     * @return maximum number of social credits
     */
    public int getMaxSocialCredits() {
        return MAXSOCIALCREDITS;
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
