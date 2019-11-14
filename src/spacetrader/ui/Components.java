package spacetrader.ui;

import spacetrader.backend.Game;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Components {

    /** Number of credits available. Made class-local to be accessed by
     * playerPanel and modified by ShipPanel after repairing ship. */
    private static JLabel credits;

    /**
     * Adds a component to the panel using the given Grid Bag Constants
     *
     * @param panel the panel to add the component to
     * @param component the component to add
     * @param gridx the x position in the grid
     * @param gridy the y position in the grid
     * @param padding the grid padding
     */
    public static void addComponent(JPanel panel, JComponent component, int gridx, int gridy,
                                    Insets padding) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.insets = padding;
        panel.add(component, c);
    }

    /**
     * Adds a component to the panel using the given Grid Bag Constants
     *
     * @param panel the panel to add the component to
     * @param component the component to add
     * @param gridx the x position in the grid
     * @param gridy the y position in the grid
     * @param padding the grid padding
     * @param gridwidth the width in the grid
     * @param gridheight the height in the grid
     */
    public static void addComponent(JPanel panel, JComponent component, int gridx, int gridy,
                                    Insets padding, int gridwidth, int gridheight) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.insets = padding;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        panel.add(component, c);
    }

    /**
     * Adds a component to the panel using the given Grid Bag Constants
     *
     * @param panel the panel to add the component to
     * @param component the component to add
     * @param gridx the x position in the grid
     * @param gridy the y position in the grid
     * @param padding the grid padding
     * @param gridwidth the width in the grid
     * @param gridheight the height in the grid
     * @param anchor the location to anchor the component
     */
    public static void addComponent(JPanel panel, JComponent component, int gridx, int gridy,
                                    Insets padding, int gridwidth, int gridheight, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.insets = padding;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.anchor = anchor;
        panel.add(component, c);
    }

    /**
     * Adds a component to the panel using the given Grid Bag Constants
     *
     * @param panel the panel to add the component to
     * @param component the component to add
     * @param gridx the x position in the grid
     * @param gridy the y position in the grid
     * @param padding the grid padding
     * @param anchor the location to anchor the component
     */
    public static void addComponent(JPanel panel, JComponent component, int gridx, int gridy,
                                    Insets padding, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.insets = padding;
        c.anchor = anchor;
        panel.add(component, c);
    }

    /**
     * Adds a component to the panel using the given Grid Bag Constants
     *
     * @param panel the panel to add the component to
     * @param component the component to add
     * @param gridx the x position in the grid
     * @param gridy the y position in the grid
     * @param padding the grid padding
     */
    public static void addComponent(JScrollPane panel, JComponent component, int gridx, int gridy,
                                    Insets padding) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.insets = padding;
        panel.add(component, c);
    }

    /**
     * Adds a component to the panel using the given Grid Bag Constants
     *
     * @param panel the panel to add the component to
     * @param component the component to add
     * @param gridx the x position in the grid
     * @param gridy the y position in the grid
     * @param padding the grid padding
     * @param gridwidth the width in the grid
     * @param gridheight the height in the grid
     */
    public static void addComponent(JScrollPane panel, JComponent component, int gridx, int gridy,
                                    Insets padding, int gridwidth, int gridheight) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.insets = padding;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        panel.add(component, c);
    }

    /**
     * Adds a component to the panel using the given Grid Bag Constants
     *
     * @param panel the panel to add the component to
     * @param component the component to add
     * @param gridx the x position in the grid
     * @param gridy the y position in the grid
     * @param padding the grid padding
     * @param gridwidth the width in the grid
     * @param gridheight the height in the grid
     * @param anchor the location to anchor the component
     */
    public static void addComponent(JScrollPane panel, JComponent component, int gridx, int gridy,
                                    Insets padding, int gridwidth, int gridheight, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.insets = padding;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.anchor = anchor;
        panel.add(component, c);
    }

    /**
     * Adds a component to the panel using the given Grid Bag Constants
     *
     * @param panel the panel to add the component to
     * @param component the component to add
     * @param gridx the x position in the grid
     * @param gridy the y position in the grid
     * @param padding the grid padding
     * @param anchor the location to anchor the component
     */
    public static void addComponent(JScrollPane panel, JComponent component, int gridx, int gridy,
                                    Insets padding, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.insets = padding;
        c.anchor = anchor;
        panel.add(component, c);
    }

    /**
     * Adds a component to the panel using the given custom Grid Bag constraints
     *
     * @param panel the panel to add the component to
     * @param component the component to add
     * @param constraints the custom grid bag constraints
     */
    public static void addComponent(JPanel panel, JComponent component,
                                    GridBagConstraints constraints) {
        panel.add(component, constraints);
    }

    /**
     * Creates a primary header with consistent formatting
     *
     * @param text the text to use for the header
     * @return a JLabel with the primary header formatting
     */
    public static JLabel createHeader1(String text) {
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
    public static JLabel createHeader2(String text) {
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
    public static JLabel createHeader2(String text, int style) {
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
    public static JLabel createHeader3(String text) {
        // Creates the label
        JLabel header = new JLabel(text);

        // Sets the label's font
        header.setFont(new Font(header.getFont().getName(), Font.PLAIN, 20));

        return header;
    }

    public static JLabel createError(String text) {
        // Creates the label
        JLabel error = createHeader1(text);

        // Sets the label's color
        error.setForeground(Color.RED);

        return error;
    }

    /**
     * Creates a button with consistent formatting
     *
     * @param text the text to use for the button
     * @return a JButton with the button formatting
     */
    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBorder(new CompoundBorder(new LineBorder(Color.BLUE, 5),
                new EmptyBorder(5, 10, 5, 10)));
        button.setBorderPainted(false);
        button.setFont(new Font(button.getFont().getName(), Font.BOLD, 20));
        return button;
    }

    public static JPanel createPlayerPanel(Game game) {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridBagLayout());
        credits = Components.createHeader2("$" + game.getCredits(),
                Font.PLAIN);

        // Creates and adds the player display header to the panel
        addComponent(playerPanel, createHeader1("Player"), 0, 0, new Insets(0, 0, 20, 0), 2, 1);

        // Creates and adds the configuration labels to the panel
        addComponent(playerPanel, createHeader2("Name:"), 0, 1, new Insets(10, 0, 0, 10),
                GridBagConstraints.LINE_START);
        addComponent(playerPanel, createHeader2("Difficulty:"), 0, 2, new Insets(10, 0, 0, 10),
                GridBagConstraints.LINE_START);
        addComponent(playerPanel, createHeader2("Skill Points:"), 0, 4, new Insets(10, 0, 0, 10),
                1, 2, GridBagConstraints.LINE_START);
        addComponent(playerPanel, createHeader2("Credits:"), 0, 7, new Insets(10, 0, 0, 10),
                GridBagConstraints.LINE_START);

        // Creates and adds the configuration values to the panel
        addComponent(playerPanel, createHeader2(game.getPlayerName(), Font.PLAIN), 1, 1,
                new Insets(10, 0, 0, 10), 1, 1, GridBagConstraints.LINE_END);
        addComponent(playerPanel, createHeader2(game.getDifficulty().toString().charAt(0)
                                + game.getDifficulty().toString().substring(1).toLowerCase(),
                        Font.PLAIN), 1, 2, new Insets(10, 0, 0, 10),
                GridBagConstraints.LINE_END);
        addComponent(playerPanel, createHeader2("Pilot: "
                        + game.getPilotPoints(), Font.PLAIN), 1, 3, new Insets(10, 0, 0, 10),
                GridBagConstraints.LINE_END);
        addComponent(playerPanel, createHeader2("Fighter: "
                        + game.getFighterPoints(), Font.PLAIN), 1, 4, new Insets(2, 0, 0, 10),
                GridBagConstraints.LINE_END);
        addComponent(playerPanel, createHeader2("Merchant: "
                        + game.getMerchantPoints(), Font.PLAIN), 1, 5, new Insets(2, 0, 0, 10),
                GridBagConstraints.LINE_END);
        addComponent(playerPanel, createHeader2("Engineer: "
                        + game.getEngineerPoints(), Font.PLAIN), 1, 6, new Insets(2, 0, 0, 10),
                GridBagConstraints.LINE_END);
        addComponent(playerPanel, credits, 1, 7, new Insets(10, 0, 0, 10),
                GridBagConstraints.LINE_END);

        return playerPanel;
    }

    /**
     * Creates the display for a given region
     *
     * @param game the game
     * @return a JPanel with the content for the given region
     */
    public static JPanel createRegionPanel(Game game) {
        JPanel regionPanel = new JPanel();
        regionPanel.setLayout(new GridBagLayout());

        addComponent(regionPanel, createHeader1("Region"), 0, 0, new Insets(0, 0, 20, 0), 2, 1);

        addComponent(regionPanel, createHeader2("Name:"), 0, 1, new Insets(10, 0, 0, 10),
                GridBagConstraints.LINE_START);
        addComponent(regionPanel, createHeader2("Tech Level:"), 0, 2, new Insets(10, 0, 0, 10),
                GridBagConstraints.LINE_START);
        addComponent(regionPanel, createHeader2("Location:"), 0, 3, new Insets(10, 0, 0, 10),
                GridBagConstraints.LINE_START);

        addComponent(regionPanel, createHeader2(game.getCurrentRegion().getName(), Font.PLAIN),
                1, 1, new Insets(10, 0, 0, 10), GridBagConstraints.LINE_END);
        addComponent(regionPanel, createHeader2(game.getCurrentRegion().getTechLevel().toString(),
                Font.PLAIN), 1, 2, new Insets(10, 0, 0, 10), GridBagConstraints.LINE_END);
        addComponent(regionPanel, createHeader2(game.getCurrentRegion().getCoordinate().toString(),
                Font.PLAIN), 1, 3, new Insets(10, 0, 0, 10), GridBagConstraints.LINE_END);

        return regionPanel;
    }

    /**
     * Creates the ship panel for the current ship. Includes button to
     * repair ship.
     * Cost to repair = approx. health amount to be restored / Math.sqrt
     * (Engineer skill) current region's inflation index * difficulty level
     * (0.5 or 1.0 or 1.5)
     *
     * @param game the current game
     * @param frame the current frame
     * */
    public static JPanel createShipPanel(Game game, JFrame frame) {
        JPanel gamePanel = new JPanel();
        JLabel health = Components.createHeader2(game.getHealth(), Font.PLAIN);
        gamePanel.setLayout(new GridBagLayout());

        JButton repairShip = Components.createButton("Repair");
        repairShip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double currentHealth =
                        game.getPlayer().getShip().getCurrentHealth();
                double maxHealth = game.getPlayer().getShip().getMaxHealth();
                double healthToRestore = maxHealth - currentHealth;
                double difficultyIndex;
                switch (game.getDifficulty()) {
                    case HARD:
                        difficultyIndex = 1.5;
                        break;
                    case MEDIUM:
                        difficultyIndex = 1.0;
                        break;
                    case EASY:
                        difficultyIndex = 0.5;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: "
                                + game.getDifficulty());
                }
                double engSkill;
                if (game.getPlayer().getEngineerPoints() == 0) {
                    // if engSkill is 0, multiply costRepairShip by 1.5
                    engSkill = Math.pow(2 / 3.0, 2);
                } else {
                    engSkill = game.getPlayer().getEngineerPoints();
                }
                double regionInflationIndex =
                        game.getCurrentMarket().getRegionPriceMultiplier() + 0.3;
                int costRepairShip = (int) (
                        (Math.random() + 0.5) * (healthToRestore
                        / Math.sqrt(engSkill) * regionInflationIndex
                        * difficultyIndex));

                int repairShipDialog = JOptionPane.showConfirmDialog(frame,
                        "Do you want to repair ship for $"
                                + costRepairShip + "?", "Repair Ship?",
                        JOptionPane.YES_NO_OPTION);
                if (repairShipDialog == JOptionPane.YES_OPTION) {
                    if (game.getCredits() >= costRepairShip) {
                        game.getPlayer().getShip().alterCurrentHealth((int)
                                (maxHealth - currentHealth));
                        game.getPlayer().changeCredits(-1 * costRepairShip);
                        JOptionPane.showMessageDialog(frame,
                                "You have successfully repaired your ship.");
                        health.setText(game.getHealth());
                        credits.setText("$" + game.getCredits());
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "You do not have enough credits.",
                                "Not enough credits",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        addComponent(gamePanel, createHeader1("Ship"), 0, 0, new Insets(0, 0, 20, 0), 2, 1);

        addComponent(gamePanel, createHeader2("Type:"), 0, 1,
                new Insets(10, 0, 0, 10), GridBagConstraints.LINE_START);
        addComponent(gamePanel, createHeader2("Fuel:"), 0, 2,
                new Insets(10, 0, 0, 10), GridBagConstraints.LINE_START);
        addComponent(gamePanel, createHeader2("Health:"), 0, 3,
                new Insets(10, 0, 0, 10), GridBagConstraints.LINE_START);
        addComponent(gamePanel, createHeader2("Capacity:"), 0, 4,
                new Insets(10, 0, 0, 10), GridBagConstraints.LINE_START);
        addComponent(gamePanel, repairShip, 0, 5,
                new Insets(10, 0, 0, 10), 2, 1, GridBagConstraints.CENTER);

        addComponent(gamePanel, createHeader2(game.getShipType(), Font.PLAIN), 1, 1,
                new Insets(10, 0, 0, 10), GridBagConstraints.LINE_END);
        addComponent(gamePanel, createHeader2(game.getFuel(), Font.PLAIN), 1, 2,
                new Insets(10, 0, 0, 10), GridBagConstraints.LINE_END);
        addComponent(gamePanel, health, 1, 3,
                new Insets(10, 0, 0, 10), GridBagConstraints.LINE_END);
        addComponent(gamePanel, createHeader2(game.getCapacity(), Font.PLAIN), 1, 4,
                new Insets(10, 0, 0, 10), GridBagConstraints.LINE_END);

        return gamePanel;
    }
}
