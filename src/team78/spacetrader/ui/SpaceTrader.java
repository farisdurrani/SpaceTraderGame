package team78.spacetrader.ui;

import javax.swing.*;

public class SpaceTrader {
    private JPanel rootPanel;
    private JLabel welcomeLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Trader");
        frame.setContentPane(new SpaceTrader().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
