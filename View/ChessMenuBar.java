package View;

import javax.swing.*;

public class ChessMenuBar extends JMenuBar {
    private final JMenu GameMenu;
    private final JMenuItem QuitMenuItem;

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Initializes the menubar with a "Game" menu and a "Quit Game" menu item.
    */
    public ChessMenuBar() {

        // Create the "Game" menu
        GameMenu = new JMenu("Game");
        // Add "Quit Game" menu item
        QuitMenuItem = new JMenuItem("Quit Game");

        GameMenu.add(QuitMenuItem);
        // Add the "Game" menu to the menu bar
        this.add(GameMenu);
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Return the "Quit Game"
    */
    public JMenuItem getQuitMenuItem(){
        return QuitMenuItem;
    }
}
