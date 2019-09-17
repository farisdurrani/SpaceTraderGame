package team78.spacetrader.ui;

import javax.swing.*;
import java.awt.*;

public class SpaceTrader {
    private JFrame frame;

    /**
     * Creates a new Space Trader game by initializing the GUI with the welcome screen
     */
    public SpaceTrader() {
        // Creates a new frame
        frame = new JFrame("Space Trader");

        // Sets the application to exit when the frame is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets the content to the welcome screen
        frame.getContentPane().add(createWelcomePanel());

        // Sets the size of the frame to the smallest that can fit the content
        frame.pack();

        // Shows the frame
        frame.setVisible(true);
    }

    /**
     * Creates the welcome panel
     *
     * @return the welcome panel
     */
    private JPanel createWelcomePanel() {
        // Panel that contains all of the content for the welcome screen
        JPanel welcomePanel = new JPanel();

        // Adds the welcome header
        welcomePanel.add(createHeader("Welcome to Space Trader!"));

        // Adds the start button
        JButton startButton = new JButton("START");
        welcomePanel.add(startButton);

        return welcomePanel;
    }

    /**
     * Creates a header with consistent formatting
     *
     * @param text the text to use for the header
     * @return a JLabel with the header formatting
     */
    private JLabel createHeader(String text) {
        JLabel header = new JLabel(text);
        header.setFont(new Font(header.getFont().getName(), Font.BOLD, 32));
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
