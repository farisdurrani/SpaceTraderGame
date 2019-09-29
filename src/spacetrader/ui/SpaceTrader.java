package spacetrader.ui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
    private String name;
    private Difficulty difficulty;
    /*
    Pilot = 0
    Fighter = 1
    Merchant = 2
    Eng = 3
     */
    private int[] skillPoints;
    private int credits;
    private int expendablePoints;

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
        addComponent(welcomePanel, createHeader1("Welcome to Space Trader!"),
                0, 0, new Insets(0, 0, 20, 0));

        // Creates the start button
        JButton startButton = createButton("START");

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
        addComponent(welcomePanel, startButton, 0, 1, new Insets(20, 0, 0, 0));

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
        addComponent(configPanel, createHeader1("Player Configuration"),
                1, 0, new Insets(0, 0, 20, 0));

        // Creates and adds the player name input label
        JLabel nameLabel = createHeader2("Player Name:");
        addComponent(configPanel, nameLabel, 0, 1, new Insets(10, 10, 10, 10));

        // Creates and adds the player name input field
        JTextField nameInput = new JTextField(30);
        nameInput.setFont(new Font(nameInput.getFont().getName(), Font.PLAIN, 20));
        addComponent(configPanel, nameInput, 1, 1, new Insets(10, 10, 10, 10));

        // Initializes the configuration values
        expendablePoints = 0;
        skillPoints = new int[4];
        for (int i = 0; i < skillPoints.length; i++) {
            skillPoints[i] = 0;
        }

        // Creates and adds the label that displays the available points
        JLabel availablePoints = createHeader2("Available Points: " + expendablePoints);
        addComponent(configPanel, availablePoints, 1, 4, new Insets(30, 10, 10, 10));

        // Creates the labels for each skill
        JLabel[] skillLabels = new JLabel[skillPoints.length];
        skillLabels[0] = createHeader3("Pilot: 0");
        skillLabels[1] = createHeader3("Fighter: 0");
        skillLabels[2] = createHeader3("Merchant: 0");
        skillLabels[3] = createHeader3("Engineer: 0");

        // Holds a mapping of each button to the corresponding skill index for future use
        HashMap<JButton, Integer> skillSubtractButtons = new HashMap<>();
        HashMap<JButton, Integer> skillAddButtons = new HashMap<>();

        // Loops through each skill to add the label and subtract/add buttons to the panel
        for (int i = 0; i < skillLabels.length; i++) {
            // Adds the skill label to the panel
            addComponent(configPanel, skillLabels[i], 1, 5 + i, new Insets(10, 10, 10, 10));
            // Creates and adds the subtract button to the panel
            JButton skillSubtract = createButton("-");
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
            addComponent(configPanel, skillSubtract, 0, 5 + i, new Insets(10, 10, 10, 10));
            // Creates and adds the add button to the panel
            JButton skillAdd = createButton("+");
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
            addComponent(configPanel, skillAdd, 2, 5 + i, new Insets(10, 10, 10, 10));
        }

        // Creates and adds the label for the difficulty selction
        JLabel difficultyLabel = createHeader2("Select Difficulty: ");
        addComponent(configPanel, difficultyLabel, 1, 2, new Insets(30, 10, 10, 10));

        // Creates the difficulty buttons
        JButton easyButton = createButton("EASY");
        JButton mediumButton = createButton("MEDIUM");
        JButton hardButton = createButton("HARD");

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
        addComponent(configPanel, easyButton, 0, 3, new Insets(10, 10, 10, 10));

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
        addComponent(configPanel, mediumButton, 1, 3, new Insets(10, 10, 10, 10));

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
        addComponent(configPanel, hardButton, 2, 3, new Insets(10, 10, 10, 10));

        // Creates and adds the confirm button that takes the user to the next page
        JButton confirm = createButton("CONFIRM");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameInput.getText();

                // Removes the content from the configuration screen
                frame.getContentPane().removeAll();
                frame.repaint();

                // Sets the content to the configuration display screen
                frame.getContentPane().add(createConfigurationDisplayPanel(), BorderLayout.CENTER);
                frame.setVisible(true);
            }
        });
        addComponent(configPanel, confirm, 1, 10, new Insets(30, 10, 10, 10));

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
        addComponent(configDisplayPanel, createHeader1("Player Configuration"), c);

        // Creates and adds the configuration labels to the panel
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 10);
        c.anchor = GridBagConstraints.LINE_START;
        addComponent(configDisplayPanel, createHeader2("Name:"), c);
        c.gridy = 2;
        addComponent(configDisplayPanel, createHeader2("Difficulty:"), c);
        c.gridy = 4;
        c.gridheight = 2;
        addComponent(configDisplayPanel, createHeader2("Skill Points:"), c);
        c.gridy = 7;
        c.gridheight = 1;
        addComponent(configDisplayPanel, createHeader2("Credits:"), c);

        // Creates and adds the configuration values to the panel
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10, 0, 0, 10);
        c.anchor = GridBagConstraints.LINE_END;
        addComponent(configDisplayPanel, createHeader2(name, Font.PLAIN), c);
        c.gridy = 2;
        addComponent(configDisplayPanel, createHeader2(difficulty.toString().charAt(0)
                + difficulty.toString().substring(1).toLowerCase(), Font.PLAIN), c);
        c.gridy = 3;
        addComponent(configDisplayPanel, createHeader2("Pilot: " + skillPoints[0], Font.PLAIN), c);
        c.gridy = 4;
        c.insets = new Insets(2, 0, 0, 10);
        addComponent(configDisplayPanel, createHeader2("Fighter: " + skillPoints[1],
                Font.PLAIN), c);
        c.gridy = 5;
        addComponent(configDisplayPanel, createHeader2("Merchant: " + skillPoints[2],
                Font.PLAIN), c);
        c.gridy = 6;
        addComponent(configDisplayPanel, createHeader2("Engineer: " + skillPoints[3],
                Font.PLAIN), c);
        c.gridy = 7;
        c.insets = new Insets(10, 0, 0, 10);
        addComponent(configDisplayPanel, createHeader2("$" + credits, Font.PLAIN), c);

        return configDisplayPanel;
    }

    /**
     * Creates a primary header with consistent formatting
     *
     * @param text the text to use for the header
     * @return a JLabel with the primary header formatting
     */
    private JLabel createHeader1(String text) {
        // Creates the label
        JLabel header = new JLabel(text);

        // Sets the label's font
        header.setFont(new Font(header.getFont().getName(), Font.BOLD, 36));

        return header;
    }

    /**
     * Creates a secondary header with consistent formatting
     *
     * @param text the text to use for the header
     * @return a JLabel with the secondary header formatting
     */
    private JLabel createHeader2(String text) {
        // Creates the label
        JLabel header = new JLabel(text);

        // Sets the label's font
        header.setFont(new Font(header.getFont().getName(), Font.BOLD, 28));

        return header;
    }

    /**
     * Creates a secondary header with consistent formatting
     *
     * @param text the text to use for the header
     * @param style the style for the font
     * @return a JLabel with the secondary header formatting
     */
    private JLabel createHeader2(String text, int style) {
        // Creates the label
        JLabel header = new JLabel(text);

        // Sets the label's font
        header.setFont(new Font(header.getFont().getName(), style, 28));

        return header;
    }

    /**
     * Creates a tertiary header with consistent formatting
     *
     * @param text the text to use for the header
     * @return a JLabel with the tertiary header formatting
     */
    private JLabel createHeader3(String text) {
        // Creates the label
        JLabel header = new JLabel(text);

        // Sets the label's font
        header.setFont(new Font(header.getFont().getName(), Font.PLAIN, 20));

        return header;
    }

    /**
     * Creates a button with consistent formatting
     *
     * @param text the text to use for the button
     * @return a JButton with the button formatting
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBorder(new CompoundBorder(new LineBorder(Color.BLUE, 5),
                new EmptyBorder(5, 10, 5, 10)));
        button.setBorderPainted(false);
        button.setFont(new Font(button.getFont().getName(), Font.BOLD, 20));
        return button;
    }

    /**
     * Adds a component to the panel using the given Grid Bag Constants
     *
     * @param panel the panel to add the component to
     * @param component the component to add
     * @param gridx the x position in the grid
     * @param gridy the y position in  the grid
     * @param padding the grid padding
     */
    private void addComponent(JPanel panel, JComponent component, int gridx, int gridy,
                              Insets padding) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.insets = padding;
        panel.add(component, c);
    }

    /**
     * Adds a component to the panel using the given custom Grid Bag constraints
     *
     * @param panel the panel to add the component to
     * @param component the component to add
     * @param constraints the custom grid bag constraints
     */
    private void addComponent(JPanel panel, JComponent component, GridBagConstraints constraints) {
        panel.add(component, constraints);
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
            credits = 1000;
        } else if (difficulty == Difficulty.MEDIUM) {
            expendablePoints = 12;
            credits = 500;
        } else if (difficulty == Difficulty.HARD) {
            expendablePoints = 8;
            credits = 100;
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
