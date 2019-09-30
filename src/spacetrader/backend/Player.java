package spacetrader.backend;

public class Player {

    private Region currentRegion;

    private int currentCredits = 0;

    private int pilotPoints = 0;
    private int fighterPoints = 0;
    private int merchantPoints = 0;
    private int engineerPoints = 0;

    //<editor-fold desc="Getters and Setters">
    public void setCurrentCredits(int currentCredits) {
        this.currentCredits = currentCredits;
    }

    public void setCurrentRegion(Region currentRegion) {
        this.currentRegion = currentRegion;
    }

    public void setPilotPoints(int pilotPoints) {
        this.pilotPoints = pilotPoints;
    }

    public void setFighterPoints(int fighterPoints) {
        this.fighterPoints = fighterPoints;
    }

    public void setMerchantPoints(int merchantPoints) {
        this.merchantPoints = merchantPoints;
    }

    public void setEngineerPoints(int engineerPoints) {
        this.engineerPoints = engineerPoints;
    }

    public Region getCurrentRegion() {
        return currentRegion;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public int getPilotPoints() {
        return this.pilotPoints;
    }

    public int getFighterPoints() {
        return this.fighterPoints;
    }

    public int getMerchantPoints() {
        return this.merchantPoints;
    }

    public int getEngineerPoints() {
        return engineerPoints;
    }
    //</editor-fold>

}
