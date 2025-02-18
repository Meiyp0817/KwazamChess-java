package View.Dialog;

import javax.swing.*;

/*
Author:Chan Ka Ken 1211109440
Purpose of the method: 
1)Is a Quit Game frame when u want to quit the game.
*/
public class QuitDialog {
    public boolean showDialog(JFrame parentFrame) {
        int comfirm = JOptionPane.showConfirmDialog(
                parentFrame,
                "Are you sure you want to quit",
                "Quit Game",
                JOptionPane.YES_NO_OPTION);

        return comfirm == JOptionPane.YES_NO_OPTION;
    }
}