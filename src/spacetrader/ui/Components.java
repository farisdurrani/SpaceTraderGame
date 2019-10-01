package spacetrader.ui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Components {

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

    /**
     * Adds a component to the panel using the given Grid Bag Constants
     *
     * @param panel the panel to add the component to
     * @param component the component to add
     * @param gridx the x position in the grid
     * @param gridy the y position in  the grid
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
     * Adds a component to the panel using the given custom Grid Bag constraints
     *
     * @param panel the panel to add the component to
     * @param component the component to add
     * @param constraints the custom grid bag constraints
     */
    public static void addComponent(JPanel panel, JComponent component, GridBagConstraints constraints) {
        panel.add(component, constraints);
    }
}
