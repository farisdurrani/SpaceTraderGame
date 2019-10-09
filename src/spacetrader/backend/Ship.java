package spacetrader.backend;

import java.util.Random;

public class Ship {
    private String type;
    private int currentSpace;
    private int maxCargoSpace = 100;
    private int currentFuel;
    private int maxFuelCapacity = 500;
    private int health = 100;

    private String[] typeList = {
            "Starship",
            "Jet",
            "Wasp",
            "Ladybug"
    };

    /**
     *
     */
    public Ship() {
        Random ran = new Random();
        type = typeList[ran.nextInt(3)];
        currentSpace = 0;
        currentSpace = 0;
    }

}
