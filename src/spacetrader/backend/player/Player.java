package spacetrader.backend.player;

public class Player {

    private String name;

    private int credits;

    private int pilotPoints;
    private int fighterPoints;
    private int merchantPoints;
    private int engineerPoints;

    private Ship ship;

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
     * Public getter that returns the player's name
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Public getter that returns the current amount of credits
     * @return number of credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Public getter that returns the pilot points
     * @return pilot points
     */
    public int getPilotPoints() {
        return this.pilotPoints;
    }

    /**
     * Public getter that returns the fighter points
     * @return fighter points
     */
    public int getFighterPoints() {
        return this.fighterPoints;
    }

    /**
     * Public getter that returns the merchant points
     * @return merchant points
     */
    public int getMerchantPoints() {
        return this.merchantPoints;
    }

    /**
     * Public getter that returns engineer points
     * @return engineer points
     */
    public int getEngineerPoints() {
        return engineerPoints;
    }
    //</editor-fold>

}
