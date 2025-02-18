package View;

import java.awt.*;
import javax.swing.*;

public class ChessMenu extends JPanel {
    private JButton playGameButton;
    private JButton loadGameButton;

    /* 
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Initializes the chess menu with buttons for "play game" and "load game"
    */
    public ChessMenu() {
        setLayout(new BorderLayout()); // Use BorderLayout to place components in different regions
        setPreferredSize(new Dimension(300, 300)); // Set panel size

        // Load the logo as an ImageIcon
        ImageIcon logoIcon = new ImageIcon("image/Logo.png"); // Replace with your logo file path
        JLabel logoLabel = new JLabel(logoIcon, JLabel.CENTER); // Center-align the logo
        add(logoLabel, BorderLayout.NORTH); // Add the logo to the top of the panel

        // Panel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1)); // Vertical layout for buttons

        // Create the "Play Game" button
        playGameButton = new JButton("Play Game");
        playGameButton.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel.add(playGameButton);

        // Create the "Load Game" button
        loadGameButton = new JButton("Load Game");
        loadGameButton.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel.add(loadGameButton);

        // Add the buttons panel to the center
        add(buttonPanel, BorderLayout.CENTER);
    }

    /* 
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Add action listener to the "Play Game" button
    */
    public JButton getPlayGameButton() {
        return playGameButton;
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Add action listener to the "Load Game" button
    */
    public JButton getLoadGameButton() {
        return loadGameButton;
    }
}
