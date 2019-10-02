package spacetrader.ui;

import spacetrader.backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * This class handles the GUI for the Space Trader game.
 */
public class SpaceTrader {

    private JFrame frame;

    // Configuration values
    /*
    Pilot = 0
    Fighter = 1
    Merchant = 2
    Eng = 3
     */
    private int[] skillPoints;
    private int expendablePoints;

    private Game game;
    private Difficulty difficulty;

    private Player player;

    /**
     * Creates a new Space Trader game by initializing the GUI with the welcome screen
     */
    public SpaceTrader() {
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
     * Creates the welcome panel with all of the content necessary for the welcome screen
     *
     * @return the welcome panel
     */
    private JPanel createWelcomePanel() {
        // Panel that contains all of the content for the welcome screen
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridBagLayout());

        // Creates and adds the welcome header to the panel
        Components.addComponent(welcomePanel, Components.createHeader1("Welcome to Space Trader!"),
                0, 0, new Insets(0, 0, 20, 0));

        // Creates the start button
        JButton startButton = Components.createButton("START");

        // Adds a listener to start the game when the button is pressed
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removes the content from the welcome screen
                frame.getContentPane().removeAll();
                frame.repaint();

                // Sets the content to the configuration screen
                frame.getContentPane().add(createConfigurationPanel(), BorderLayout.CENTER);
                frame.setVisible(true);

                // Focuses the name input
                frame.transferFocusUpCycle();
            }
        });

        // Adds the start button to the panel
        Components.addComponent(welcomePanel, startButton, 0, 1, new Insets(20, 0, 0, 0));

        return welcomePanel;
    }

    /**
     * Creates the initial configuration panel with all of the content necessary for the
     * configuration screen
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
        for (int i = 0; i < skillPoints.length; i++) {
            skillPoints[i] = 0;
        }

        // Creates and adds the label that displays the available points
        JLabel availablePoints = Components.createHeader2("Available Points: " + expendablePoints);
        Components.addComponent(configPanel, availablePoints, 1, 4, new Insets(30, 10, 10, 10));

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
            Components.addComponent(configPanel, skillLabels[i], 1, 5 + i, new Insets(10, 10, 10, 10));
            // Creates and adds the subtract button to the panel
            JButton skillSubtract = Components.createButton("-");
            skillSubtractButtons.put(skillSubtract, i);
            skillSubtract.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = skillSubtractButtons.get((JButton) e.getSource());
                    if (skillPoints[i] > 0) {
                        skillPoints[i]--;
                        skillLabels[i].setText(skillLabels[i].getText().split(":")[0]
                                + ": " + skillPoints[i]);
                        expendablePoints++;
                        availablePoints.setText("Points: " + expendablePoints);
                    }
                }
            });
            Components.addComponent(configPanel, skillSubtract, 0, 5 + i, new Insets(10, 10, 10, 10));
            // Creates and adds the add button to the panel
            JButton skillAdd = Components.createButton("+");
            skillAddButtons.put(skillAdd, i);
            skillAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = skillAddButtons.get((JButton) e.getSource());
                    if (expendablePoints > 0) {
                        skillPoints[i]++;
                        skillLabels[i].setText(skillLabels[i].getText().split(":")[0]
                                + ": " + skillPoints[i]);
                        expendablePoints--;
                        availablePoints.setText("Points: " + expendablePoints);
                    }
                }
            });
            Components.addComponent(configPanel, skillAdd, 2, 5 + i, new Insets(10, 10, 10, 10));
        }

        // Creates and adds the label for the difficulty selction
        JLabel difficultyLabel = Components.createHeader2("Select Difficulty: ");
        Components.addComponent(configPanel, difficultyLabel, 1, 2, new Insets(30, 10, 10, 10));

        // Creates the difficulty buttons
        JButton easyButton = Components.createButton("EASY");
        JButton mediumButton = Components.createButton("MEDIUM");
        JButton hardButton = Components.createButton("HARD");

        // Sets the default difficulty to medium
        mediumButton.setBorderPainted(true);
        setDifficulty(Difficulty.MEDIUM, availablePoints, skillLabels);

        // Adds functionality to the easy button and adds it to the panel
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easyButton.setBorderPainted(true);
                mediumButton.setBorderPainted(false);
                hardButton.setBorderPainted(false);
                setDifficulty(Difficulty.EASY, availablePoints, skillLabels);
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
                setDifficulty(Difficulty.MEDIUM, availablePoints, skillLabels);
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
                setDifficulty(Difficulty.HARD, availablePoints, skillLabels);
            }
        });
        Components.addComponent(configPanel, hardButton, 2, 3, new Insets(10, 10, 10, 10));

        // Creates and adds the confirm button that takes the user to the next page
        JButton confirm = Components.createButton("CONFIRM");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Creates the player with its name and stats
                player = new Player(nameInput.getText(), skillPoints[0], skillPoints[1], skillPoints[2], skillPoints[3]);

                // Creates the game with the selected difficulty
                game = new Game(player, difficulty);

                // Removes the content from the configuration screen
                frame.getContentPane().removeAll();
                frame.repaint();

                // Sets the content to the configuration display screen
                frame.getContentPane().add(createConfigurationDisplayPanel(), BorderLayout.CENTER);
                frame.setVisible(true);
            }
        });
        Components.addComponent(configPanel, confirm, 1, 10, new Insets(30, 10, 10, 10));

        return configPanel;
    }

    /**
     * Creates the final panel for the screen that displays the player's configuration
     *
     * @return the configuration display panel
     */

    private JPanel createConfigurationDisplayPanel() {

        // Creates the panel that will contain all of the content for the configuration display
        // panel
        JPanel configDisplayPanel = new JPanel();
        configDisplayPanel.setLayout(new GridBagLayout());

        // Creates and adds the configuration display header to the panel
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 20, 0);
        c.gridwidth = 2;
        Components.addComponent(configDisplayPanel, Components.createHeader1("Player Configuration"), c);

        // Creates and adds the configuration labels to the panel
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 10);
        c.anchor = GridBagConstraints.LINE_START;
        Components.addComponent(configDisplayPanel, Components.createHeader2("Name:"), c);
        c.gridy = 2;
        Components.addComponent(configDisplayPanel, Components.createHeader2("Difficulty:"), c);
        c.gridy = 4;
        c.gridheight = 2;
        Components.addComponent(configDisplayPanel, Components.createHeader2("Skill Points:"), c);
        c.gridy = 7;
        c.gridheight = 1;
        Components.addComponent(configDisplayPanel, Components.createHeader2("Credits:"), c);

        // Creates and adds the configuration values to the panel
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 10);
        c.anchor = GridBagConstraints.LINE_END;
        Components.addComponent(configDisplayPanel, Components.createHeader2(player.getName(), Font.PLAIN), c);
        c.gridy = 2;
        Components.addComponent(configDisplayPanel, Components.createHeader2(game.getGameDifficulty().toString().charAt(0)
                + game.getGameDifficulty().toString().substring(1).toLowerCase(), Font.PLAIN), c);
        c.gridy = 3;
        Components.addComponent(configDisplayPanel, Components.createHeader2("Pilot: " + player.getPilotPoints(), Font.PLAIN), c);
        c.gridy = 4;
        c.insets = new Insets(2, 0, 0, 10);
        Components.addComponent(configDisplayPanel, Components.createHeader2("Fighter: " + player.getFighterPoints(),
                Font.PLAIN), c);
        c.gridy = 5;
        Components.addComponent(configDisplayPanel, Components.createHeader2("Merchant: " + player.getMerchantPoints(),
                Font.PLAIN), c);
        c.gridy = 6;
        Components.addComponent(configDisplayPanel, Components.createHeader2("Engineer: " + player.getEngineerPoints(),
                Font.PLAIN), c);
        c.gridy = 7;
        c.insets = new Insets(10, 0, 0, 10);
        Components.addComponent(configDisplayPanel, Components.createHeader2("$" + player.getCurrentCredits(), Font.PLAIN), c);

        JButton startGame = Components.createButton("START GAME");
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Starts the game
                game.startGame();

                // Removes the content from the configuration screen
                frame.getContentPane().removeAll();
                frame.repaint();

                // Sets the content to the configuration display screen
                frame.getContentPane().add(createRegionPanel(), BorderLayout.CENTER);
                frame.setVisible(true);
            }
        });
        Components.addComponent(configDisplayPanel, startGame, 0, 8, new Insets(30, 10, 10, 10), 2, 1);

        return configDisplayPanel;
    }

    private JPanel createRegionPanel() {
        JPanel regionPanel = new JPanel();
        regionPanel.setLayout(new GridBagLayout());

        Region[] regions = game.getRegions();
        Region currentRegion = player.getCurrentRegion();

        Components.addComponent(regionPanel, Components.createRegion(currentRegion), 0, 0, new Insets(0, 0, 20, 0), 9, 1);

        Components.addComponent(regionPanel, Components.createHeader1("Travel To:"), 0, 1, new Insets(0, 0, 0, 0), 9, 1);
        int i = 0;
        for (Region region : regions) {
            if (region != currentRegion) {
                JButton regionButton = Components.createButton(region.getName() + " (" + (int) (Coordinate.distance(region.getCoordinate(), currentRegion.getCoordinate())) + ")");
                regionButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Moves to a new region
                        game.goToRegion(region);

                        // Removes the content from the configuration screen
                        frame.getContentPane().removeAll();
                        frame.repaint();

                        // Sets the content to the configuration display screen
                        frame.getContentPane().add(createRegionPanel(), BorderLayout.CENTER);
                        frame.setVisible(true);
                    }
                });
                Components.addComponent(regionPanel, regionButton, i++, 2, new Insets(0, 5, 0, 5));
            }
        }

        return regionPanel;
    }

    /**
     * Sets the difficulty and updates all necessary values and labels
     *
     * @param difficulty the difficulty selected
     * @param availablePoints the label which displays the available points
     * @param skills the labels that display the points in each skill
     */
    private void setDifficulty(Difficulty difficulty, JLabel availablePoints, JLabel[] skills) {
        if (difficulty == Difficulty.EASY) {
            expendablePoints = 16;
        } else if (difficulty == Difficulty.MEDIUM) {
            expendablePoints = 12;
        } else if (difficulty == Difficulty.HARD) {
            expendablePoints = 8;
        }
        this.difficulty = difficulty;
        availablePoints.setText("Points: " + expendablePoints);
        for (int i = 0; i < skillPoints.length; i++) {
            skillPoints[i] = 0;
            skills[i].setText(skills[i].getText().split(":")[0] + ": " + 0);
        }
    }

    /**
     * Starts the Space Trader GUI
     *
     * @param args unused
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new SpaceTrader());
    }
}
