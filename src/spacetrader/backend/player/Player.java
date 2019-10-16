package spacetrader.backend.player;

public class Player {

    private String name;

    private int currentCredits;

    private int pilotPoints;
    private int fighterPoints;
    private int merchantPoints;
    private int engineerPoints;

    public Player(String name, int pilotPoints, int fighterPoints, int merchantPoints,
                  int engineerPoints) {
        this.name = name;

        this.pilotPoints = pilotPoints;
        this.fighterPoints = fighterPoints;
        this.merchantPoints = merchantPoints;
        this.engineerPoints = engineerPoints;

        this.currentCredits = 0;
    }

    //<editor-fold desc="Getters and Setters">

    /**
     * Public setter to set the players credits
     * @param currentCredits credits
     */
    public void setCurrentCredits(int currentCredits) {
        this.currentCredits = currentCredits;
    }

    /**
     * Public setter to set the players pilot points
     *
     * @param pilotPoints pilot points
     */
    public void setPilotPoints(int pilotPoints) {
        this.pilotPoints = pilotPoints;
    }

    /**
     * Public setter to set the players fighter points
     *
     * @param fighterPoints fighter points
     */
    public void setFighterPoints(int fighterPoints) {
        this.fighterPoints = fighterPoints;
    }

    /**
     * Public setter to set the players merchant points
     *
     * @param merchantPoints merchant points
     */
    public void setMerchantPoints(int merchantPoints) {
        this.merchantPoints = merchantPoints;
    }

    /**
     * Public setter to set the players engineer points
     *
     * @param engineerPoints engineer points
     */
    public void setEngineerPoints(int engineerPoints) {
        this.engineerPoints = engineerPoints;
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
    public int getCurrentCredits() {
        return currentCredits;
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
