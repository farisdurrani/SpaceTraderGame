package spacetrader.ui;

import spacetrader.backend.*;
import spacetrader.backend.locations.Coordinate;
import spacetrader.backend.locations.Region;
import spacetrader.backend.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        Components.addComponent(configDisplayPanel,
                Components.createHeader1("Player Configuration"), 2, 0,
                new Insets(0, 0, 20, 0), 2, 1);

        // Creates and adds the configuration labels to the panel
        Components.addComponent(configDisplayPanel, Components.createHeader2("Name:"),
                2, 1, new Insets(10, 0, 0, 10), 1, 1, GridBagConstraints.LINE_START);
        Components.addComponent(configDisplayPanel, Components.createHeader2("Difficulty:"),
                2, 2, new Insets(10, 0, 0, 10), 1, 1, GridBagConstraints.LINE_START);
        Components.addComponent(configDisplayPanel, Components.createHeader2("Skill Points:"),
                2, 4, new Insets(10, 0, 0, 10), 1, 2, GridBagConstraints.LINE_START);
        Components.addComponent(configDisplayPanel, Components.createHeader2("Credits:"),
                2, 7, new Insets(10, 0, 0, 10), 1, 1, GridBagConstraints.LINE_START);

        // Creates and adds the configuration values to the panel
        Components.addComponent(configDisplayPanel,
                Components.createHeader2(game.getPlayerName(), Font.PLAIN), 3, 1,
                new Insets(10, 0, 0, 10), 1, 1, GridBagConstraints.LINE_END);
        Components.addComponent(configDisplayPanel,
                Components.createHeader2(game.getDifficulty().toString().charAt(0)
                        + game.getDifficulty().toString().substring(1).toLowerCase(),
                        Font.PLAIN), 3, 2, new Insets(10, 0, 0, 10), 1, 1,
                GridBagConstraints.LINE_END);
        Components.addComponent(configDisplayPanel, Components.createHeader2("Pilot: "
                        + game.getPilotPoints(), Font.PLAIN), 3, 3, new Insets(10, 0, 0, 10),
                1, 1, GridBagConstraints.LINE_END);
        Components.addComponent(configDisplayPanel, Components.createHeader2("Fighter: "
                        + game.getFighterPoints(), Font.PLAIN), 3, 4, new Insets(2, 0, 0, 10),
                1, 1, GridBagConstraints.LINE_END);
        Components.addComponent(configDisplayPanel, Components.createHeader2("Merchant: "
                        + game.getMerchantPoints(), Font.PLAIN), 3, 5, new Insets(2, 0, 0, 10),
                1, 1, GridBagConstraints.LINE_END);
        Components.addComponent(configDisplayPanel, Components.createHeader2("Engineer: "
                        + game.getEngineerPoints(), Font.PLAIN), 3, 6, new Insets(2, 0, 0, 10),
                1, 1, GridBagConstraints.LINE_END);
        Components.addComponent(configDisplayPanel, Components.createHeader2("$"
                        + game.getCredits(), Font.PLAIN), 3, 7, new Insets(10, 0, 0, 10),
                1, 1, GridBagConstraints.LINE_END);

        JButton goBack = Components.createButton("GO BACK");
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Displays the configuration screen
                displayPanel(createConfigurationPanel());
            }
        });
        Components.addComponent(configDisplayPanel, goBack, 1, 12,
                new Insets(30, 1, 10, 10), 2, 1);

        JButton startGame = Components.createButton("START GAME");
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Starts the game
                game.startGame();

                // Displays the first region screen
                displayPanel(createRegionPanel());
            }
        });
        Components.addComponent(configDisplayPanel, startGame, 3, 12,
                new Insets(30, 10, 10, 30), 2, 1);

        return configDisplayPanel;
    }

    /**
     * Creates the region panel for the current region
     *
     * @return the region panel for the current region
     */
    private JPanel createRegionPanel() {
        JPanel regionPanel = new JPanel();
        regionPanel.setLayout(new GridBagLayout());

        Region[] regions = game.getRegions();
        Region currentRegion = game.getCurrentRegion();

        Components.addComponent(regionPanel, Components.createRegion(currentRegion), 0, 0,
                new Insets(0, 0, 20, 0), 9, 1);

        Components.addComponent(regionPanel, Components.createHeader1("Travel To:"), 0, 1,
                new Insets(0, 0, 0, 0), 9, 1);
        int i = 0;
        for (Region region : regions) {
            if (region != currentRegion) {
                JButton regionButton = Components.createButton(region.getName() + " ("
                        + (int) (Coordinate.distance(region.getCoordinate(),
                        currentRegion.getCoordinate())) + ")");
                regionButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Moves to a new region
                        game.goToRegion(region.getID());

                        // Displays the next region screen
                        displayPanel(createRegionPanel());
                    }
                });
                Components.addComponent(regionPanel, regionButton, i++, 2, new Insets(0, 5, 0, 5));
            }
        }

        return regionPanel;
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
