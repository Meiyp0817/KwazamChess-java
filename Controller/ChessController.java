package Controller;

import Model.*;
import View.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.util.List;

 
public class ChessController {
    private ChessModel model;
    private ChessView view;
    private static ChessController instance;

    String saveFilePath = "Save_game/saved.txt";
    File saveFile = new File(saveFilePath);

    private int selectedX = -1, selectedY = -1; // Track the selected piece's position

    /*
    Author:Fong Kai Chun 1211108901
    Purpose of the method: 
    1)Initializes the controller with model and view, and sets up all the listeners and mouse handling
    */
    private ChessController(ChessModel model, ChessView view) {
        this.model = model;
        this.view = view;

        // Initialize necessary listeners (other than mouse handlers)
        initMenuListeners();
        setupMouseHandling(); // Add mouse handling
    }

     /*
    Author:Fong Kai Chun 1211108901
    Purpose of the method: 
    1)Implements the Singeton pattern to ensure only one instance of the controller exists
    */
    public static ChessController getInstance(ChessModel model, ChessView view) {
        if (instance == null) {
            instance = new ChessController(model, view);
        }
        return instance;
    }

    /*
    Author:Fong Kai Chun 1211108901
    Purpose of the method: 
    1)Set up action listeners for the chessMenu buttons to "Start Game" and "Load Game".
    */
public void MenuWindow() {
    // Add action listeners to the chess menu buttons
    view.getChessMenu().getPlayGameButton().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MenustartGame(); // Start a new game
            view.showGameBoard(); // Show the game board
        }
    });

    view.getChessMenu().getLoadGameButton().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MenuloadGame(); // Load the saved game
            view.showGameBoard(); // Show the game board
        }
    });
}

         /*
    Author:Fong Kai Chun 1211108901
    Purpose of the method: 
    1)chessMenu "Start Gamne" button
    2)update the view and reflect the new game
    */
    public void MenustartGame() {
        // Start a new game
        model.initGame(); // Reset the game state to initial setup
        updateView(); // Update the view to reflect the new game state
        System.out.println("Started a new game.");
    }

    /*
    Author:Fong Kai Chun 1211108901
    Purpose of the method: 
    1)chessMenu "Load Game" button
    2)if saved.txt exists then load back the saved game state
    3)if no saved.txt exists then open new game state
    */
    public void MenuloadGame() {
        // Load the saved game

        if (saveFile.exists()) {
            model.loadGame(saveFilePath); // Load the saved game
            System.out.println("Game loaded successfully.");
            updateView(); // Update the view to reflect the loaded game state
        } else {
            System.out.println("No saved game found.");
            updateView();
        }
    }

    /*
    Author:Fong Kai Chun 1211108901
    Purpose of the method: 
    1)set up the action listner for the quitMenuItem
    */
    private void initMenuListeners() {
        // Setting up quit action for the menu
        view.getChessMenubar().getQuitMenuItem().addActionListener(e -> quitGame());
    }

    /*
    Author:Fong Kai Chun 1211108901
    Purpose of the method: 
    1)when user click the menubar -> quitGame then it will saves the game state
    */
    public void quitGame() {
        // Save the game before quitting
        model.saveGame(saveFilePath);
        System.out.println("Game saved before quitting.");

        boolean confirmQuit = view.showQuitDialog();
        if (confirmQuit) {
            System.exit(0);
        }
    }

    /*
    Author:Fong Kai Chun 1211108901
    Purpose of the method: 
    1)updates current game state of the board
    2)flip board based on the current player
    */
    public void updateView() {
        // Retrieve pieces from the model and update the view
        String[][] pieces = model.getBoardState();
        view.getBoard().updateBoard(pieces);

        // Flip the board based on the current player's turn
        view.flipBoard(model.getCurrentColor());
    }

    /*
    Author:Fong Kai Chun 1211108901
    Purpose of the method: 
    1)Allows user to interact with the board by clicking on pieces
    2)when move the piece check for valid moves
    3)highlight the valid moves for every type pieces
    4)when Game Over(sau was captured) then will reset the game state 
    */
    private void setupMouseHandling() {
        for (int i = 0; i < view.getBoard().getBoardPieces().length; i++) {
            for (int j = 0; j < view.getBoard().getBoardPieces()[i].length; j++) {
                ChessPiece button = view.getBoard().getBoardPieces()[i][j];
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        // Get the row and column of the clicked button
                        int row = -1, col = -1;
                        for (int i = 0; i < view.getBoard().getBoardPieces().length; i++) {
                            for (int j = 0; j < view.getBoard().getBoardPieces()[i].length; j++) {
                                if (view.getBoard().getBoardPieces()[i][j] == e.getSource()) {
                                    row = i;
                                    col = j;
                                    break;
                                }
                            }
                        }

                        if (selectedX == -1 && selectedY == -1) {
                            // First click: select a piece
                            KwazamPiece piece = model.getPieceAt(row, col);
                            if (piece != null && piece.getColor() == model.getCurrentColor()) {
                                // Only select pieces of the current player's color
                                selectedX = col;
                                selectedY = row;

                                 // Highlight valid moves
                            List<int[]> validMoves = model.getValidMoves(row, col);
                            for (int[] move : validMoves) {
                                int x = move[0];
                                int y = move[1];
                                if (x >= 0 && x < view.getBoard().getBoardPieces()[0].length && y >= 0 && y < view.getBoard().getBoardPieces().length) {
                                    // Check if the move is a capture move
                                    KwazamPiece targetPiece = model.getPieceAt(y, x);
                                    if (targetPiece != null && targetPiece.getColor() != model.getCurrentColor()) {
                                        // Highlight in red for capturing enemy pieces
                                        view.getBoard().getBoardPieces()[y][x].setBackground(Color.RED);
                                    } else {
                                        // Highlight in green for regular moves
                                        view.getBoard().getBoardPieces()[y][x].setBackground(Color.GREEN);
                                    }
                                }
                            }
                        } else {
                            System.out.println("No valid piece selected.");
                        }
                    } else {
                        // Second click: attempt to move the selected piece
                        System.out.println("Attempting to move piece to: (" + col + ", " + row + ")");

                            boolean moveSuccessful = model.movePiece(selectedY, selectedX, row, col);
                            if (moveSuccessful) {
                                //model.updateBoardState();
                                updateView(); // Update the board display

                                if(model.isSauCaptured()){
                                    String winner = model.getWinner();
                                    view.showGameOverDialog(winner);
                                    model.resetGame();
                                    // String saveFilePath = "Save_game/saved.txt";
                                    model.saveGame(saveFilePath);

                                    System.exit(0);
                                }
                            } else {
                                System.out.println("Invalid move!");
                            }

                            // Reset selection and highlights
                            view.getBoard().getBoardPieces()[selectedY][selectedX].setBackground(
                                (selectedY + selectedX) % 2 == 0 ? new Color(128, 0, 128) : new Color(216, 191, 216));
                            for (int i = 0; i < view.getBoard().getBoardPieces().length; i++) {
                                for (int j = 0; j < view.getBoard().getBoardPieces()[i].length; j++) {
                                    if ((i + j) % 2 == 0) {
                                            view.getBoard().getBoardPieces()[i][j].setBackground(new Color(152,125, 182)); // Custom DarkPurple
                                        } else {
                                            view.getBoard().getBoardPieces()[i][j].setBackground(new Color(216, 191, 216)); // Custom LightPurple
                                        }
                                }
                            }
                            selectedX = -1;
                            selectedY = -1;
                        }
                    }
                });
            }
        }
    }

    /*
    Author:Fong Kai Chun 1211108901
    Purpose of the method: 
    1)save game function then called by ChessView
    */
    public void saveGameBeforeExit() {
        model.saveGame(saveFilePath);
        System.out.println("Game saved before exiting.");
    }

}