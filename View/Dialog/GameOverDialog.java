package View.Dialog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

/*
Author:Chan Ka Ken 1211109440
Purpose of the method: 
1)Initialize a GameOver Frame when the Sau piece got captured.
2)Feature: Add Image, add sound effect when win
*/
public class GameOverDialog {
    public void showDialog(Frame parent, String winner){
        JDialog dialog = new JDialog(parent, "Game Over", true);

        dialog.setSize(250,250);
        dialog.setLocationRelativeTo(parent);

        dialog.setLayout(new BorderLayout());
        
        ImageIcon icon = new ImageIcon("image/trophys.png");
        JLabel iconLabel = new JLabel(icon);
        dialog.add(iconLabel, BorderLayout.CENTER);

        JPanel wordPanel = new JPanel();
        JLabel wordLabel = new JLabel();
        wordLabel.setText("Good Game!" + winner + "Win!");
        wordLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        wordPanel.add(wordLabel);

        JPanel buttonPanel = new JPanel();
        JButton closeButton = new JButton();
        closeButton.setText("Quit");
        closeButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(closeButton);

        dialog.add(wordPanel,BorderLayout.NORTH);
        dialog.add(buttonPanel,BorderLayout.SOUTH);

        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File("audio/win2.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        dialog.setVisible(true);
    }
}