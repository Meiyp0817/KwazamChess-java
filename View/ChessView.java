package View;

import Controller.ChessController;
import View.Dialog.GameOverDialog;
import View.Dialog.QuitDialog;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class ChessView extends JFrame {
    private final ChessBoard board;
    private final ChessMenuBar menubar;
    private final ChessMenu chessMenu;
    private final QuitDialog quitDialog;
    private final GameOverDialog gameOverDialog;

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Initializes the ChessView, with the board,chessMenu,menubar,quitdialogs,gameOverDialog.
    */
    public ChessView() {
        board = new ChessBoard();
        menubar = new ChessMenuBar();
        chessMenu = new ChessMenu();
        quitDialog = new QuitDialog();
        gameOverDialog = new GameOverDialog();
        initView();
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Set up the initial view of the chess game,Start with chessMenu
    2)add action listener when click the right top exit then the game will save
    */
    public void initView() {
        setTitle("Kwazam Chess Game");
        setLayout(new BorderLayout());

        showChessMenu();
        addMenuBar();

        setSize(500, 800); // Initial size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Save the game before exiting
                saveGameBeforeRightTopExit();
            }
        });
        setVisible(true);
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Returns the chess board componemt
    */
    public ChessBoard getBoard() {
        return board;
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Returns menubar component
    */
    public ChessMenuBar getChessMenubar() {
        return menubar;
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Returns the chessMenu component
    */
    public ChessMenu getChessMenu() {
        return chessMenu;
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)add the chessboard to the center,bc iam using borderlayout
    */
    public void addChessBoard() {
        add(board, BorderLayout.CENTER);
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Add the menubar to the view
    */
    public void addMenuBar() {
        setJMenuBar(menubar);
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Flips the board based on the current color
    */
    public void flipBoard(int currentColor) {
        board.flipBoard(currentColor);
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)I got create a Dialog package, display the quitDialog
    */
    public boolean showQuitDialog() {
        return quitDialog.showDialog(this);
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Same I got create a Dialog package, diaplay the gameOverDialog based on who(red or blue) win
    */
    public void showGameOverDialog(String winnerColor){
        gameOverDialog.showDialog(this, winnerColor);
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Display the chessMenu in the view
    */
    public void showChessMenu() {
        getContentPane().removeAll(); // Clear the current content
        add(chessMenu, BorderLayout.CENTER); // Add the chess menu
        revalidate();
        repaint();
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Display the gameboard in the view
    */
    public void showGameBoard() {
        getContentPane().removeAll(); // Clear the current content
        add(board, BorderLayout.CENTER); // Add the game board
        revalidate();
        repaint();
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Saves the game state b4 exiting the application
    2)setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    */
     private void saveGameBeforeRightTopExit() {
        // Get the controller instance and save the game
        ChessController controller = ChessController.getInstance(null, this);
        controller.saveGameBeforeExit();
    }
}