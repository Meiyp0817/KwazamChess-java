package Model;

import Model.Movement.BizMovement;
import Model.Movement.MovementStrategy;
import Model.Movement.RamMovement;
import Model.Movement.SauMovement;
import Model.Movement.TorMovement;
import Model.Movement.XorMovement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ChessModel {

    private KwazamBoard board;
    private boolean running;
    private int currentColor; 
    private int moveCount = 0; // Track the number of moves to determine when to swap Tor and Xor

    /*
    Author: Mei Yong Peng 1211109159
    Purpose: 1) Initialize the board and pieces from KwazamBoard.java
    2) Set Blue color go first 
    */
    public ChessModel() {
        board = new KwazamBoard(); 
        running = true;
        currentColor = KwazamBoard.BLUE; 
    }

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Returns whether if the game is active.
     */
    public boolean isRunning() {
        return running;
    }

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Set the running to true in order to start the game.
     */
    public void initGame() {
        running = true;
    }

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Gives back a 2D array of strings representing the board's current state.
     */
    public String[][] getBoardState() {
        return board.getBoardState();
    }

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Releases the board's current state to the console and updates it.
     */
    public void updateBoardState() {
        String[][] boardState = board.getBoardState();
        for (int i = 0; i < boardState.length; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < boardState[i].length; j++) {
                String cell = (boardState[i][j] == null) ? " [ ] " : boardState[i][j];
                row.append(cell).append("  ");
            }
            System.out.println(row.toString().trim());
        }
    }

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Returns the piece to the selected board row and column.
     */
    public KwazamPiece getPieceAt(int row, int col) {
        for (KwazamPiece piece : board.getPieces()) {
            if (piece.getY() == row && piece.getX() == col) {
                return piece;
            }
        }
        return null;
    }

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Gives back the current player's color (BLUE or RED).
     */
    public int getCurrentColor() {
        return currentColor;
    }

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Moves a piece between the beginning (startY, startX) and ending (endY, endX) positions.
              2) Switches the player turn, changes the board state, and validates the action.
     */
    public boolean movePiece(int startY, int startX, int endY, int endX) {
        KwazamPiece piece = getPieceAt(startY, startX);
        if (piece == null || piece.getColor() != currentColor) {
            System.out.println("Invalid selection: No piece or wrong color.");
            return false; // No piece at the starting position or wrong color
        }

        System.out.println("Selected piece: " + piece.getType() + " at (" + startX + ", " + startY + ")");
        System.out.println("Current turn: " + (currentColor == KwazamBoard.RED ? "Blue" : "Red"));

        List<int[]> validMoves = piece.getMovementStrategy().getValidMoves(startX, startY, board.getBoardState());

        boolean isValidMove = validMoves.stream().anyMatch(move -> move[0] == endX && move[1] == endY);

        if (isValidMove) {
            KwazamPiece targetPiece = getPieceAt(endY, endX);
            if (targetPiece != null) {
                if (targetPiece.getType().equals("r-Sau") || targetPiece.getType().equals("b-Sau")) {
                    running = false;
                    System.out.println("Game over! " + (piece.getColor() == KwazamBoard.RED ? "Red" : "Blue") + " wins!");
                } else {
                    System.out.println("Capturing piece: " + targetPiece.getType() + " at (" + endX + ", " + endY + ")");
                    targetPiece.capture();
                }
            }

            piece.setPosition(endX, endY);
            board.getBoardState()[startY][startX] = null;
            board.getBoardState()[endY][endX] = piece.getColor() == KwazamBoard.RED ? "r-" + piece.getType() : "b-" + piece.getType();

            // Increment move count after each move
            moveCount++;

            // Swap Tor and Xor after every 2 moves (1 Red + 1 Blue)
            if (moveCount % 2 == 0) {
                board.swapTorAndXor();
            }

            // Switch player turn
            currentColor = (currentColor == KwazamBoard.RED) ? KwazamBoard.BLUE : KwazamBoard.RED;
            System.out.println("Next turn: " + (currentColor == KwazamBoard.RED ? "Blue" : "Red"));

            // Update the board state
            updateBoardState();
            return true;
        }

        System.out.println("Invalid move!");
        return false; // Invalid move
    }

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Returns a list of moves allowed for the piece in the given row and column.
     */
    public List<int[]> getValidMoves(int row, int col) {
        KwazamPiece piece = getPieceAt(row, col);
        if (piece != null && piece.getColor() == currentColor) {
            return piece.getMovementStrategy().getValidMoves(col, row, board.getBoardState());
        }
        return List.of(); // Return empty list if no valid piece
    }

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Determine whether the Sau piece has been captured or not, marking the game's conclusion.
     */
    public boolean isSauCaptured() {
        for (KwazamPiece piece : board.getPieces()) {
            if (piece.getType().equals("Sau") && piece.isCaptured()) {
                return true;
            }
        }
        return false;
    }

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Returns the game's winner according to the color of the active player.
     */
    public String getWinner() {
        return currentColor == KwazamBoard.BLUE ? "Red" : "Blue";
    }

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Saves the current game state, including the board state, player's turn, and number of moves, to a text file.
     */
    public void saveGame(String filePath) {
        File saveDir = new File("Save_game");
        if (!saveDir.exists()) {
            saveDir.mkdir(); // Create the directory if it doesn't exist
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Add a title and game details
            writer.write("Kwazam Chess Game - Saved State");
            writer.newLine();
            writer.write("===============================");
            writer.newLine();
            writer.write("Current Player Turn: " + (currentColor == KwazamBoard.RED ? "Red" : "Blue"));
            writer.newLine();
            writer.write("Move Count: " + moveCount);
            writer.newLine();
            writer.newLine();

            // Add a gameboard title
            writer.write("Game Board State:");
            writer.newLine();
            writer.write("-----------------");
            writer.newLine();

            // Save the board state with proper alignment
            String[][] boardState = board.getBoardState();
            for (int i = 0; i < boardState.length; i++) {
                StringBuilder row = new StringBuilder();
                for (int j = 0; j < boardState[i].length; j++) {
                    String cell = (boardState[i][j] == null) ? "[ ]" : boardState[i][j];
                    row.append(String.format("%-8s", cell)); // Use fixed-width formatting for alignment
                }
                writer.write(row.toString().trim()); // Write the row to the file
                writer.newLine(); // Move to the next line
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save the game.");
        }
    }

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Loads a game state from a file, which includes the move count, board state, and current player's turn.
     */
    public void loadGame(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;

            // Skip the header and separator
            reader.readLine(); // Skip the title line
            reader.readLine(); // Skip the separator line

            // Read the current player turn
            String turnLine = reader.readLine(); // Read the line containing the current turn
            if (turnLine != null && turnLine.contains("Current Player Turn:")) {
                String turn = turnLine.split(":")[1].trim();
                currentColor = turn.equals("Red") ? KwazamBoard.RED : KwazamBoard.BLUE;
                System.out.println("Loaded current turn: " + turn);
            }

            // Read the move count
            String moveCountLine = reader.readLine(); // Read the line containing the move count
            if (moveCountLine != null && moveCountLine.contains("Move Count:")) {
                String moveCountStr = moveCountLine.split(":")[1].trim();
                moveCount = Integer.parseInt(moveCountStr);
                System.out.println("Loaded move count: " + moveCount);
            }

            // Skip the empty line and the game board state title
            reader.readLine(); // Skip the empty line
            reader.readLine(); // Skip the "Game Board State:" line
            reader.readLine(); // Skip the separator line

            // Clear the existing pieces and board state
            board.clearPieces(); // Clear the list of pieces
            for (int i = 0; i < board.getBoardState().length; i++) {
                for (int j = 0; j < board.getBoardState()[i].length; j++) {
                    board.getBoardState()[i][j] = null; // Clear the board state
                }
            }

            // Load the board state from the file
            while ((line = reader.readLine()) != null && row < board.getBoardState().length) {
                String[] cells = line.trim().split("\\s{2,}"); // Split by multiple spaces
                for (int col = 0; col < cells.length && col < board.getBoardState()[row].length; col++) {
                    String cell = cells[col].replace("[", "").replace("]", "").trim();
                    if (cell.isEmpty() || cell.equals(" ")) {
                        board.getBoardState()[row][col] = null; // Empty cell
                    } else {
                        // Create a new KwazamPiece and add it to the board
                        String[] pieceInfo = cell.split("-");
                        if (pieceInfo.length >= 2) {
                            String color = pieceInfo[0];
                            String type = pieceInfo[1];
                            int pieceColor = color.equals("r") ? KwazamBoard.RED : KwazamBoard.BLUE;

                            // Create the piece and set its position
                            MovementStrategy movementStrategy = getMovementStrategy(type);
                            if (type.equals("Ram")) {
                                movementStrategy = new RamMovement(pieceColor); // Pass the correct color to RamMovement
                            }
                            KwazamPiece piece = new KwazamPiece(pieceColor, type, col, row, movementStrategy);
                            board.getPieces().add(piece);

                            // Update the board state
                            board.getBoardState()[row][col] = cell;
                        }
                    }
                }
                row++;
            }

            System.out.println("Game loaded successfully from " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load the game.");
        }

        System.out.println("Board state after loading:");
        for (int i = 0; i < board.getBoardState().length; i++) {
            for (int j = 0; j < board.getBoardState()[i].length; j++) {
                System.out.print(board.getBoardState()[i][j] == null ? "[ ] " : board.getBoardState()[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Gives the proper movement technique for a specific piece type.
     */
    private MovementStrategy getMovementStrategy(String type) {
        switch (type) {
            case "Tor":
                return new TorMovement();
            case "Xor":
                return new XorMovement();
            case "Biz":
                return new BizMovement();
            case "Sau":
                return new SauMovement();
            case "Ram":
                return new RamMovement(currentColor);
            default:
                throw new IllegalArgumentException("Unknown piece type: " + type);
        }
    }

    /*
    Author:Mei Yong Peng 1211109159
    Purpose: 1) Clears the board, resets the game state, and reinitializes the pieces to restart the game.
    */
    public void resetGame() {
        board.clearPieces();
        board.initializeBoard();
        currentColor = KwazamBoard.BLUE;
        moveCount = 0;
        running = true;
    }
}