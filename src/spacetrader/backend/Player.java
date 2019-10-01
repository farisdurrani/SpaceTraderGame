package spacetrader.backend;

public class Player {

    private String name;

    private Region currentRegion;

    private int currentCredits = 0;

    private int pilotPoints = 0;
    private int fighterPoints = 0;
    private int merchantPoints = 0;
    private int engineerPoints = 0;

    public Player(String name, int pilotPoints, int fighterPoints, int merchantPoints, int engineerPoints) {
        this.name = name;

        this.pilotPoints = pilotPoints;
        this.fighterPoints = fighterPoints;
        this.merchantPoints = merchantPoints;
        this.engineerPoints = engineerPoints;
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
     * Public setter to set the players region
     * @param currentRegion
     */
    public void setCurrentRegion(Region currentRegion) {
        this.currentRegion = currentRegion;
    }

    /**
     * Public setter to set the players pilot points
     * @param pilotPoints
     */
    public void setPilotPoints(int pilotPoints) {
        this.pilotPoints = pilotPoints;
    }

    /**
     * Public setter to set the players fighter points
     * @param fighterPoints
     */
    public void setFighterPoints(int fighterPoints) {
        this.fighterPoints = fighterPoints;
    }

    /**
     * Public setter to set the players merchant points
     * @param merchantPoints
     */
    public void setMerchantPoints(int merchantPoints) {
        this.merchantPoints = merchantPoints;
    }

    /**
     * Public setter to set the players engineer points
     * @param engineerPoints
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
     * Public getter that returns the current region
     * @return current region
     */
    public Region getCurrentRegion() {
        return currentRegion;
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
