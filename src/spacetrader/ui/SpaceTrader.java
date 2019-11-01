package spacetrader.ui;

import spacetrader.backend.*;
import spacetrader.backend.npc.Bandit;
import spacetrader.backend.market.Market;
import spacetrader.backend.market.MarketItem;
import spacetrader.backend.locations.Region;
import spacetrader.backend.npc.Trader;
import spacetrader.backend.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class handles the GUI for the Space Trader game.
 */
public class SpaceTrader {

    // The frame of the window
    private JFrame frame;

    // Variables needed to setup configuration for a new player
    private int[] skillPoints; // Pilot, Fighter, Merchant, Engineer
    private int expendablePoints;
    private Difficulty difficulty;

    // Game class that should be called for all game logic
    private Game game;

    /**
     * Creates a new Space Trader game by initializing the GUI with the
     * welcome screen.
     */
    private SpaceTrader() {
        // Creates a new frame
        frame = new JFrame("Space Trader");

        // Sets the application to exit when the frame is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets the content to the welcome screen
        frame.getContentPane().add(createWelcomePanel(), BorderLayout.CENTER);

        // Sets the frame to windowed fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Sets the frame to fullscreen
        //frame.setUndecorated(true);

        // Shows the frame
        frame.setVisible(true);
    }

    /**
     * Clears the current window and displays a panel as the new content
     *
     * @param panel panel to be displayed
     */
    private void displayPanel(JPanel panel) {
        // Removes the content from the previous screen
        frame.getContentPane().removeAll();
        frame.repaint();

        // Sets the content to the next screen
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Focuses the name input
        frame.transferFocusUpCycle();
    }

    /**
     * Creates the welcome panel with all of the content necessary for the
     * welcome screen.
     *
     * @return the welcome panel
     */
    private JPanel createWelcomePanel() {
        // Panel that contains all of the content for the welcome screen
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridBagLayout());

        // Creates and adds the welcome header to the panel
        Components.addComponent(welcomePanel, Components.createHeader1(
                "Welcome to Space Trader!"), 0, 0, new Insets(0, 0, 20, 0));

        // Creates the start button
        JButton startButton = Components.createButton("START");

        // Adds a listener to start the game when the button is pressed
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Displays the configuration screen
                displayPanel(createConfigurationPanel());
            }
        });

        // Adds the start button to the panel
        Components.addComponent(welcomePanel, startButton, 0, 1,
                new Insets(20, 0, 0, 0));

        return welcomePanel;
    }

    /**
     * Creates the initial configuration panel with all of the content necessary
     * for the configuration screen.
     *
     * @return the initial configuration panel
     */
    private JPanel createConfigurationPanel() {
        // Creates the panel that will contain all of the content for the configuration panel
        JPanel configPanel = new JPanel();
        configPanel.setLayout(new GridBagLayout());

        // Creates and adds the configuration header to the panel
        Components.addComponent(configPanel, Components.createHeader1("Player Configuration"),
                1, 0, new Insets(0, 0, 20, 0));

        // Creates and adds the player name input label
        JLabel nameLabel = Components.createHeader2("Player Name:");
        Components.addComponent(configPanel, nameLabel, 0, 1, new Insets(10, 10, 10, 10));

        // Creates and adds the player name input field
        JTextField nameInput = new JTextField(30);
        nameInput.setFont(new Font(nameInput.getFont().getName(), Font.PLAIN, 20));
        Components.addComponent(configPanel, nameInput, 1, 1, new Insets(10, 10, 10, 10));

        // Initializes the configuration values
        expendablePoints = 0;
        skillPoints = new int[4];

        // Creates and adds the label that displays the credits the player will receive
        JLabel availableCredits = Components.createHeader2("Credits: $0");
        Components.addComponent(configPanel, availableCredits, 1, 4, new Insets(30, 10, 10, 10));

        // Creates and adds the label that displays the available points
        JLabel availablePoints = Components.createHeader2("Points: " + expendablePoints);
        Components.addComponent(configPanel, availablePoints, 1, 5, new Insets(10, 10, 10, 10));

        // Creates the labels for each skill
        JLabel[] skillLabels = new JLabel[skillPoints.length];
        skillLabels[0] = Components.createHeader3("Pilot: 0");
        skillLabels[1] = Components.createHeader3("Fighter: 0");
        skillLabels[2] = Components.createHeader3("Merchant: 0");
        skillLabels[3] = Components.createHeader3("Engineer: 0");
        // Holds a mapping of each button to the corresponding skill index for future use
        HashMap<JButton, Integer> skillSubtractButtons = new HashMap<>();
        HashMap<JButton, Integer> skillAddButtons = new HashMap<>();
        // Loops through each skill to add the label and subtract/add buttons to the panel
        for (int i = 0; i < skillLabels.length; i++) {
            // Adds the skill label to the panel
            Components.addComponent(configPanel, skillLabels[i], 1, 6 + i,
                    new Insets(10, 10, 10, 10));
            // Creates and adds the subtract button to the panel
            JButton skillSubtract = Components.createButton("-");
            skillSubtractButtons.put(skillSubtract, i);
            skillSubtract.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = skillSubtractButtons.get((JButton) e.getSource());
                    if (skillPoints[i] > 0) {
                        skillPoints[i]--;
                        skillLabels[i].setText(skillLabels[i].getText().split(":")[0] + ": "
                                + skillPoints[i]);
                        expendablePoints++;
                        availablePoints.setText("Points: " + expendablePoints);
                    }
                }
            });
            Components.addComponent(configPanel, skillSubtract, 0, 6 + i,
                    new Insets(10, 10, 10, 10));
            // Creates and adds the add button to the panel
            JButton skillAdd = Components.createButton("+");
            skillAddButtons.put(skillAdd, i);
            skillAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = skillAddButtons.get((JButton) e.getSource());
                    if (expendablePoints > 0) {
                        skillPoints[i]++;
                        skillLabels[i].setText(skillLabels[i].getText().split(":")[0] + ": "
                                + skillPoints[i]);
                        expendablePoints--;
                        availablePoints.setText("Points: " + expendablePoints);
                    }
                }
            });
            Components.addComponent(configPanel, skillAdd, 2, 6 + i, new Insets(10, 10, 10, 10));
        }

        // Creates and adds the label for the difficulty selection
        JLabel difficultyLabel = Components.createHeader2("Select Difficulty: ");
        Components.addComponent(configPanel, difficultyLabel, 1, 2, new Insets(30, 10, 10, 10));

        // Creates the difficulty buttons
        JButton easyButton = Components.createButton("EASY");
        JButton mediumButton = Components.createButton("MEDIUM");
        JButton hardButton = Components.createButton("HARD");

        // Sets the default difficulty to medium
        mediumButton.setBorderPainted(true);
        setDifficulty(Difficulty.MEDIUM, availablePoints, availableCredits, skillLabels);

        // Adds functionality to the easy button and adds it to the panel
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easyButton.setBorderPainted(true);
                mediumButton.setBorderPainted(false);
                hardButton.setBorderPainted(false);
                setDifficulty(Difficulty.EASY, availablePoints, availableCredits, skillLabels);
            }
        });
        Components.addComponent(configPanel, easyButton, 0, 3, new Insets(10, 10, 10, 10));

        // Adds functionality to the medium button and adds it to the panel
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easyButton.setBorderPainted(false);
                mediumButton.setBorderPainted(true);
                hardButton.setBorderPainted(false);
                setDifficulty(Difficulty.MEDIUM, availablePoints, availableCredits, skillLabels);
            }
        });
        Components.addComponent(configPanel, mediumButton, 1, 3, new Insets(10, 10, 10, 10));

        // Adds functionality to the hard button and adds it to the panel
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easyButton.setBorderPainted(false);
                mediumButton.setBorderPainted(false);
                hardButton.setBorderPainted(true);
                setDifficulty(Difficulty.HARD, availablePoints, availableCredits, skillLabels);
            }
        });
        Components.addComponent(configPanel, hardButton, 2, 3, new Insets(10, 10, 10, 10));

        // Creates and adds the confirm button that takes the user to the next page
        JButton confirm = Components.createButton("CONFIRM");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Creates the game with the selected difficulty
                game = new Game(new Player(nameInput.getText(), skillPoints[0], skillPoints[1],
                        skillPoints[2], skillPoints[3]), difficulty);

                // Displays the configuration display screen
                displayPanel(createConfigurationDisplayPanel());
            }
        });
        Components.addComponent(configPanel, confirm, 1, 11, new Insets(30, 10, 10, 10));

        return configPanel;
    }

    /**
     * Creates the final panel for the screen that displays the player's
     * configuration.
     *
     * @return the configuration display panel
     */

    private JPanel createConfigurationDisplayPanel() {

        // Creates the panel that will contain all of the content for the
        // configuration display panel
        JPanel configDisplayPanel = new JPanel();
        configDisplayPanel.setLayout(new GridBagLayout());

        // Creates and adds the configuration display header to the panel
        Components.addComponent(configDisplayPanel, Components.createPlayerPanel(game), 0, 0,
                new Insets(0, 0, 20, 0), 2, 1);

        JButton goBack = Components.createButton("GO BACK");
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Displays the configuration screen
                displayPanel(createConfigurationPanel());
            }
        });
        Components.addComponent(configDisplayPanel, goBack, 0, 1,
                new Insets(30, 1, 10, 10));

        JButton startGame = Components.createButton("START GAME");
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Starts the game
                game.startGame();

                // Displays the first region screen
                displayPanel(createMainGamePanel());
            }
        });
        Components.addComponent(configDisplayPanel, startGame, 1, 1,
                new Insets(30, 10, 10, 30));

        return configDisplayPanel;
    }

    /**
     * Creates the region panel for the current region
     *
     * @return the region panel for the current region
     */
    private JPanel createMainGamePanel() {
        JPanel regionPanel = new JPanel();
        regionPanel.setLayout(new GridBagLayout());

        Region[] regions = game.getRegions();
        Region currentRegion = game.getCurrentRegion();

        Components.addComponent(regionPanel, Components.createPlayerPanel(game), 0, 0,
                new Insets(0, 0, 20, 10), 1, 1, GridBagConstraints.PAGE_START);

        Components.addComponent(regionPanel, Components.createRegionPanel(game), 1, 0,
                new Insets(0, 10, 20, 10), 1, 1, GridBagConstraints.PAGE_START);

        Components.addComponent(regionPanel, Components.createShipPanel(game), 2, 0,
                new Insets(0, 10, 20, 0), 1, 1, GridBagConstraints.PAGE_START);

        JButton marketButton = Components.createButton("Market");
        marketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Displays the market screen
                displayPanel(createMarketPanel());
            }
        });
        Components.addComponent(regionPanel, marketButton, 0, 1,
                new Insets(30, 0, 0, 0), 1, 1);

        JButton inventoryButton = Components.createButton("Inventory");
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Displays the inventory screen
                displayPanel(createInventoryPanel());
            }
        });
        Components.addComponent(regionPanel, inventoryButton, 1, 1,
                new Insets(30, 0, 0, 0), 1, 1);

        JButton travelButton = Components.createButton("Travel");
        travelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Displays the travel screen
                displayPanel(createTravelPanel());
            }
        });
        Components.addComponent(regionPanel, travelButton, 2, 1,
                new Insets(30, 0, 0, 0), 1, 1);

        return regionPanel;
    }

    private JPanel createTravelPanel() {
        JPanel regionPanel = new JPanel();
        regionPanel.setLayout(new GridBagLayout());

        Region[] regions = game.getRegions();
        Region currentRegion = game.getCurrentRegion();

        Components.addComponent(regionPanel, Components.createRegionPanel(game), 0, 0,
                new Insets(0, 0, 0, 10), GridBagConstraints.PAGE_START);

        Components.addComponent(regionPanel, Components.createShipPanel(game), 1, 0,
                new Insets(0, 10, 0, 10), 1, 1, GridBagConstraints.PAGE_START);

        JPanel travelPanel = new JPanel();
        travelPanel.setLayout(new GridBagLayout());

        JLabel notEnoughFuelError = Components.createError("Not Enough Fuel!");

        Components.addComponent(travelPanel, Components.createHeader1("Travel To:"), 0, 0,
                new Insets(0, 0, 10, 0));
        int i = 1;
        for (Region region : game.getRegions()) {
            if (region != game.getCurrentRegion()) {
                JButton regionButton = Components.createButton(region.getName() + " (Fuel Cost: "
                        + game.getFuelCost(region) + ")");
                regionButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 0 - 0.25: Proceed without encountering any NPC
                        // 0.25 - 0.50: Encounters Bandits
                        // 0.50 - 0.75: Encounters Trader
                        // 0.75 - 1.00: ...
                        double encounterNPCProbability = Math.random();
                        // Moves to a new region
                        if (game.goToRegion(region)) {
                            if (encounterNPCProbability >= 0.25
                                    && encounterNPCProbability < 0.5) {
                                try {
                                    displayPanel(createBanditPanel(currentRegion));
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            } else if (encounterNPCProbability >= 0.5
                                    && encounterNPCProbability < 0.5) {
                                displayPanel(createTraderPanel());
                            } else {
                                // Displays the next region screen
                                displayPanel(createMainGamePanel());
                            }
                        } else {
                            // Displays an error message
                            Components.addComponent(regionPanel, notEnoughFuelError, 1, 1,
                                    new Insets(30, 0, 0, 0));
                            displayPanel(regionPanel);
                        }
                    }
                });
                Components.addComponent(travelPanel, regionButton, 0, i++, new Insets(5, 0, 5, 0));
            }
        }

        Components.addComponent(regionPanel, travelPanel, 2, 0, new Insets(0, 10, 0, 0),
                GridBagConstraints.PAGE_START);

        JButton backButton = Components.createButton("BACK");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Displays the region screen
                displayPanel(createMainGamePanel());
            }
        });
        Components.addComponent(regionPanel, backButton, 1, 2, new Insets(30, 0, 0, 0));

        return regionPanel;
    }

    private JPanel createBanditPanel(Region previousRegion) throws IOException {

        JPanel banditPanel = new JPanel();
        banditPanel.setLayout(new GridBagLayout());

        Bandit bandit = new Bandit(game);

        JLabel currentCredits = Components.createHeader3("Credits: $" + game.getCredits());
        JLabel currentHealth = Components.createHeader3("Health: " + game.getHealth());
        JLabel banditEncountered = Components.createHeader1("Warning! Bandit encountered!");
        JLabel banditWantsMoney = Components.createHeader2("Bandit is demanding $"
                + bandit.getMoneyDemanded() + ". If you don't have enough money,"
                + "you can surrender all of the items you bought.");
        JLabel gasMaskLabel = new JLabel(new ImageIcon(bandit.getGasMask()));

        JButton payBandit = Components.createButton("Pay Bandit");
        payBandit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getCredits() >= bandit.getMoneyDemanded()) {
                    game.getPlayer().changeCredits(-1 * bandit.getMoneyDemanded());
                    JOptionPane.showMessageDialog(frame, "You have paid the Bandit $"
                            + bandit.getMoneyDemanded());
                } else if (game.getPlayer().getShip().getCurrentUsedSpace() > 0) {
                    game.getPlayer().getShip().removeAllItems();
                    JOptionPane.showMessageDialog(frame, "Because you are not able to pay the"
                            + "Bandit, you have lost all of your inventory.");
                } else {
                    // ship gets damaged
                    int damage = (int) (Math.sqrt(bandit.getDamageCaused()));
                    game.getPlayer().getShip().alterCurrentHealth(-1 * damage);
                    JOptionPane.showMessageDialog(frame, "You are unable to give anything to "
                            + "Bandit. Your ship has been damaged by " + damage + "points.");
                }
                // Displays the next region screen
                displayPanel(createMainGamePanel());
            }
        });

        JButton escapeBandit = Components.createButton("Attempt to Flee");
        escapeBandit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.goToPreviousRegion(previousRegion);
                if (game.getPilotPoints() > bandit.getFlyLevel()) {
                    // successfully evades Bandit
                    displayPanel(createMainGamePanel());
                    JOptionPane.showMessageDialog(frame,
                            "You have successfully evaded the Bandit.");
                } else {
                    // ship gets damaged
                    game.getPlayer().getShip().alterCurrentHealth(-1 * bandit.getDamageCaused());
                    // player loses all credits
                    game.getPlayer().changeCredits(-1 * game.getCredits());
                    displayPanel(createMainGamePanel());
                    JOptionPane.showMessageDialog(frame, "You failed to evade. Bandit damaged your "
                            + "ship by " + bandit.getDamageCaused()
                            + " points and you've lost all credits.", "Failed to Evade",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton fightBandit = Components.createButton("Attempt to Fight");
        fightBandit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getFighterPoints() > bandit.getStrengthLevel()) {
                    // successfully evades Bandit
                    game.getPlayer().changeCredits(bandit.getCreditsAwarded());
                    displayPanel(createMainGamePanel());
                    JOptionPane.showMessageDialog(frame, "You have successfully defeated the "
                            + "Bandit and earned $" + bandit.getCreditsAwarded()
                            + " as a reward.");
                } else {
                    // ship gets damaged
                    game.getPlayer().getShip().alterCurrentHealth(-1 * bandit.getDamageCaused());
                    // player loses all credits
                    game.getPlayer().changeCredits(-1 * game.getCredits());
                    displayPanel(createMainGamePanel());
                    JOptionPane.showMessageDialog(frame, "You lost. Bandit damaged your ship by "
                            + bandit.getDamageCaused() + " points and you've lost all credits.",
                            "Failed to Evade", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        Components.addComponent(banditPanel, gasMaskLabel, 0, 0, new Insets(0, 0, 0, 0));
        Components.addComponent(banditPanel, banditEncountered, 0, 1, new Insets(0, 0, 0, 0));
        Components.addComponent(banditPanel, banditWantsMoney, 0, 2, new Insets(0, 0, 0, 0));
        Components.addComponent(banditPanel, payBandit, 0, 3, new Insets(10, 0, 10, 0));
        Components.addComponent(banditPanel, escapeBandit, 0, 4, new Insets(10, 0, 10, 0));
        Components.addComponent(banditPanel, fightBandit, 0, 5, new Insets(10, 0, 10, 0));
        Components.addComponent(banditPanel, currentCredits, 0, 6, new Insets(0, 0, 0, 0));
        Components.addComponent(banditPanel, currentHealth, 0, 7, new Insets(0, 0, 0, 0));

        return banditPanel;
    }

    private JPanel createTraderPanel() {

        JPanel traderPanel = new JPanel();
        traderPanel.setLayout(new GridBagLayout());

        Trader trader = new Trader(game);

        Components.addComponent(traderPanel, new JLabel(new ImageIcon(trader.getIcon())), 0, 0,
                new Insets(0, 0, 10, 0));
        Components.addComponent(traderPanel, Components.createHeader1("Trader encountered!"), 0, 1,
                new Insets(10, 0, 10, 0));
        JLabel traderOffer = Components.createHeader3("The Trader is offering "
                + trader.getItemCount() + " " + trader.getItemName() + " for $"
                + trader.getItemCost());
        Components.addComponent(traderPanel, traderOffer, 0, 2, new Insets(10, 0, 10, 0));

        JLabel notEnoughCreditsError = Components.createError("Not Enough Credits!");
        JButton buyItems = Components.createButton("Buy Items");
        buyItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.buyItem(trader.getItem(), trader.getItemCount())) {
                    // Displays the next region screen
                    displayPanel(createMainGamePanel());
                } else {
                    // Displays an error message
                    Components.addComponent(traderPanel, notEnoughCreditsError, 0, 8,
                            new Insets(30, 0, 0, 0));
                    displayPanel(traderPanel);
                }
            }
        });
        Components.addComponent(traderPanel, buyItems, 0, 3, new Insets(10, 0, 10, 0));

        JButton negotiate = Components.createButton("Attempt to Negotiate the Price ("
                + trader.getNegotiationChance() + ")");
        negotiate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (trader.negotiate()) {
                    JOptionPane.showMessageDialog(frame, "You have successfully negotiated the"
                            + "price down.");
                } else {
                    JOptionPane.showMessageDialog(frame, "You have failed to negotiate the price "
                            + "down. The trader is now upset and has raised his price.");
                }
                traderPanel.remove(traderOffer);
                traderPanel.remove(negotiate);
                Components.addComponent(traderPanel,
                        Components.createHeader3("The Trader is offering " + trader.getItemCount()
                                + " " + trader.getItemName() + " for $" + trader.getItemCost()),
                        0, 1, new Insets(10, 0, 10, 0));
            }
        });
        Components.addComponent(traderPanel, negotiate, 0, 4, new Insets(10, 0, 10, 0));

        JButton robTrader = Components.createButton("Attempt to Rob the Trader ("
                + trader.getRobChance() + ")");
        robTrader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (trader.rob()) {
                    // successfully evades Bandit
                    displayPanel(createMainGamePanel());
                    JOptionPane.showMessageDialog(frame, "You have successfully robbed "
                            + game.addItem(trader.getItem(), trader.getItemCount()) + " "
                            + trader.getItemName() + " from  the Trader.");
                } else {
                    // ship gets damaged
                    game.getPlayer().getShip().alterCurrentHealth(-1 * trader.getDamageCaused());
                    displayPanel(createMainGamePanel());
                    JOptionPane.showMessageDialog(frame, "You failed to rob the Trader. The Trader"
                                    + "damaged your ship by " + trader.getDamageCaused()
                                    + " health points.");
                }
            }
        });
        Components.addComponent(traderPanel, robTrader, 0, 5, new Insets(10, 0, 10, 0));

        JButton ignore = Components.createButton("Continue to Region");
        ignore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Displays the next region screen
                displayPanel(createMainGamePanel());
            }
        });
        Components.addComponent(traderPanel, ignore, 0, 6, new Insets(10, 0, 10, 0));

        Components.addComponent(traderPanel, Components.createHeader2("Credits: $"
                + game.getCredits()), 0, 7, new Insets(10, 0, 10, 0));

        return traderPanel;
    }

    /**
     * Creates the market panel for the current region displaying items to
     * buy / sell
     *
     * @return the market panel for the current region
     */
    private JPanel createMarketPanel() {
        JPanel marketPanel = new JPanel();
        marketPanel.setLayout(new GridBagLayout());

        Market currentMarket = game.getCurrentMarket();

        Components.addComponent(marketPanel, Components.createPlayerPanel(game), 0, 0,
                new Insets(0, 0, 20, 10), 2, 1, GridBagConstraints.PAGE_START);

        Components.addComponent(marketPanel, Components.createRegionPanel(game), 2, 0,
                new Insets(0, 0, 20, 10), 3, 1, GridBagConstraints.PAGE_START);

        Components.addComponent(marketPanel, Components.createShipPanel(game), 5, 0,
                new Insets(0, 10, 20, 0), 2, 1, GridBagConstraints.PAGE_START);

        int y = 1;
        for (MarketItem marketItem : currentMarket.getMarketItemsInRegion()) {

            JLabel inventoryOfItem = Components.createHeader2("Inventory: "
                    + game.getCurrentCount(marketItem));

            Components.addComponent(marketPanel,
                    Components.createHeader2(marketItem.getOfficialItemName()), 0, y,
                    new Insets(0, 0, 0, 5));
            int itemPrice = game.getCost(marketItem);
            Components.addComponent(marketPanel, Components.createHeader2("$" + itemPrice),
                    1, y, new Insets(0, 5, 0, 5));

            JButton buy1 = Components.createButton("Buy 1");
            buy1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (game.buyItem(marketItem, 1)) {
                        displayPanel(createMarketPanel());
                    }
                }
            });
            Components.addComponent(marketPanel, buy1, 2, y, new Insets(0, 5, 0, 5));

            JButton buy10 = Components.createButton("Buy 10");
            buy10.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (game.buyItem(marketItem, 10)) {
                        displayPanel(createMarketPanel());
                    }
                }
            });
            Components.addComponent(marketPanel, buy10, 3, y, new Insets(0, 5, 0, 5));

            JButton sell1 = Components.createButton("Sell 1");
            sell1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (game.sellItem(marketItem, 1)) {
                        displayPanel(createMarketPanel());
                    }
                }
            });
            Components.addComponent(marketPanel, sell1, 4, y, new Insets(0, 5, 0, 5));

            JButton sell10 = Components.createButton("Sell 10");
            sell10.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (game.sellItem(marketItem, 10)) {
                        displayPanel(createMarketPanel());
                    }
                }
            });
            Components.addComponent(marketPanel, sell10, 5, y, new Insets(0, 5, 0, 5));

            Components.addComponent(marketPanel, inventoryOfItem, 6, y, new Insets(0, 0, 0, 0));

            y++;
        }

        JButton backButton = Components.createButton("BACK");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Displays the region screen
                displayPanel(createMainGamePanel());
            }
        });
        Components.addComponent(marketPanel, backButton, 2, y, new Insets(30, 0, 0, 0), 3, 1);

        return marketPanel;
    }

    /**
     * Creates the market panel for the current region displaying items to
     * buy / sell
     *
     * @return the market panel for the current region
     */
    private JPanel createInventoryPanel() {
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new GridBagLayout());

        Components.addComponent(inventoryPanel, Components.createPlayerPanel(game), 0, 0,
                new Insets(0, 0, 20, 10), 1, 1, GridBagConstraints.PAGE_START);

        Components.addComponent(inventoryPanel, Components.createRegionPanel(game), 1, 0,
                new Insets(0, 0, 20, 10), 1, 1, GridBagConstraints.PAGE_START);

        Components.addComponent(inventoryPanel, Components.createShipPanel(game), 2, 0,
                new Insets(0, 10, 20, 0), 1, 1, GridBagConstraints.PAGE_START);

        int y = 1;
        for (String item : game.getInventoryItems().keySet()) {
            Components.addComponent(inventoryPanel, Components.createHeader2(item + ": "
                    + game.getInventoryItems().get(item)), 0, y, new Insets(0, 0, 0, 0), 3, 1);
            y++;
        }

        JButton backButton = Components.createButton("BACK");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Displays the region screen
                displayPanel(createMainGamePanel());
            }
        });
        Components.addComponent(inventoryPanel, backButton, 1, y, new Insets(30, 0, 0, 0), 1, 1);

        return inventoryPanel;
    }

    /**
     * Sets the difficulty and updates all necessary values and labels.
     *
     * @param difficulty the difficulty selected
     * @param availablePoints the label which displays the available points
     * @param availableCredits the label which displays the available credits
     * @param skills the labels that display the points in each skill
     */
    private void setDifficulty(Difficulty difficulty, JLabel availablePoints,
                               JLabel availableCredits, JLabel[] skills) {
        this.difficulty = difficulty;
        expendablePoints = Game.getSkillPoints(difficulty);
        availablePoints.setText("Points: " + expendablePoints);
        availableCredits.setText("Credits: $" + Game.getCredits(difficulty));
        for (int i = 0; i < skillPoints.length; i++) {
            skillPoints[i] = 0;
            skills[i].setText(skills[i].getText().split(":")[0] + ": " + 0);
        }
    }

    /**
     * Starts the Space Trader GUI.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new SpaceTrader());
    }
}
