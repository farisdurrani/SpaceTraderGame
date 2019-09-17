package team78.spacetrader.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Provides options for difficulties
 */
enum Difficulty {
    EASY, MEDIUM, HARD;
}

/**
 * This class handles the GUI for the Space Trader game.
 */
public class SpaceTrader {

    private JFrame frame;

    // Configuration values
    private String name;
    private Difficulty difficulty;
    private int[] skillPoints;
    private int credits;

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
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;

        // Creates and adds the welcome header to the panel
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 20, 0);
        welcomePanel.add(createHeader("Welcome to Space Trader!"), c);

        // Creates the start button
        JButton startButton = new JButton("START");

        // Sets the button's font
        startButton.setFont(new Font(startButton.getFont().getName(), Font.BOLD, 20));

        // Adds a listener to start the game when the button is pressed
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Removes the content from the welcome screen
                frame.getContentPane().removeAll();
                frame.repaint();

                // Sets the content to the configuration screen
                frame.getContentPane().add(createConfigurationPanel());
            }
        });

        // Adds the start button to the panel
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(20, 0, 0, 0);
        welcomePanel.add(startButton, c);

        return welcomePanel;
    }

    /**
     * Creates the initial configuration panel with all of the content necessary for the
     * configuration screen
     *
     * @return the initial configuration panel
     */
    private JPanel createConfigurationPanel() {
        //TODO Create configuration panel where the user can configure the following options:
        // - Name
        // - Difficulty
        // - Skill Points

        return null;
    }

    /**
     * Creates the final panel for the screen that displays the player's configuration
     *
     * @return the configuration display panel
     */
    private JPanel createConfigurationDisplayPanel() {
        //TODO Create panel that displays the player's configuration from the previous frame:
        // - Name
        // - Difficulty
        // - Skill Points
        // - Credits (calculated based on the difficulty setting)

        return null;
    }

    /**
     * Creates a header with consistent formatting
     *
     * @param text the text to use for the header
     * @return a JLabel with the header formatting
     */
    private JLabel createHeader(String text) {
        // Creates the label
        JLabel header = new JLabel(text);

        // Sets the label's font
        header.setFont(new Font(header.getFont().getName(), Font.BOLD, 36));

        return header;
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
