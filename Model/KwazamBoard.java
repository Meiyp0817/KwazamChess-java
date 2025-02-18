package Model;

import Model.Movement.BizMovement;
import Model.Movement.RamMovement;
import Model.Movement.SauMovement;
import Model.Movement.TorMovement;
import Model.Movement.XorMovement;
import java.util.ArrayList;
import java.util.List;

public class KwazamBoard {
    public static final int BOARD_ROWS = 8;
    public static final int BOARD_COLUMNS = 5;
    public static final int RED = 0; // Make RED static
    public static final int BLUE = 1; // Make BLUE static

    private String[][] boardState; // Track board status
    private List<KwazamPiece> pieces; // List of pieces on the board

    public KwazamBoard() {
        boardState = new String[BOARD_ROWS][BOARD_COLUMNS];
        pieces = new ArrayList<>();
        initializeBoard();
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Sets the RED and BLUE pieces in their initial locations to initialize the board.
              2) In addition, the boardState is updated to reflect the pieces' initial locations.
     */
    public void initializeBoard() {
        // Initialize BLUE pieces
        pieces.add(new KwazamPiece(1, "Tor", 0, 0, new TorMovement()));
        pieces.add(new KwazamPiece(1, "Biz", 1, 0, new BizMovement()));
        pieces.add(new KwazamPiece(1, "Sau", 2, 0, new SauMovement()));
        pieces.add(new KwazamPiece(1, "Biz", 3, 0, new BizMovement()));
        pieces.add(new KwazamPiece(1, "Xor", 4, 0, new XorMovement()));
        for (int i = 0; i < 5; i++) {
            pieces.add(new KwazamPiece(1, "Ram", i, 1, new RamMovement(1)));
        }

        // Initialize RED pieces
        pieces.add(new KwazamPiece(0, "Xor", 0, 7, new XorMovement()));
        pieces.add(new KwazamPiece(0, "Biz", 1, 7, new BizMovement()));
        pieces.add(new KwazamPiece(0, "Sau", 2, 7, new SauMovement()));
        pieces.add(new KwazamPiece(0, "Biz", 3, 7, new BizMovement()));
        pieces.add(new KwazamPiece(0, "Tor", 4, 7, new TorMovement()));
        for (int i = 0; i < 5; i++) {
            pieces.add(new KwazamPiece(0, "Ram", i, 6, new RamMovement(0)));
        }

        // Initialize the boardState with pieces' data
        for (KwazamPiece piece : pieces) {
            boardState[piece.getY()][piece.getX()] = piece.getColor() == RED ? "r-" + piece.getType() : "b-" + piece.getType();
        }
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Provides a 2D array of strings representing the board's current state.
     */
    public String[][] getBoardState() {
        return boardState;
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Indicates that the designated spot on the board is empty by setting it to null.
     */
    public void setBoardState(int x, int y) {
        boardState[y][x] = null;
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) All of the Tor and Xor pieces on the board have their types and movement strategies switched.
              2) Reflects the modifications by updating the boardState.
     */
    public void swapTorAndXor() {
        for (KwazamPiece piece : getPieces()) {
            // Skip captured pieces (those with x = -1 or y = -1)
            if (piece.getX() == -1 || piece.getY() == -1) {
                continue;
            }

            if (piece.getType().equals("Tor")) {
                piece.setType("Xor");
                boardState[piece.getY()][piece.getX()] = piece.getColor() == BLUE ? "b-Xor" : "r-Xor";
                piece.setMovementStrategy(new XorMovement());
            } else if (piece.getType().equals("Xor")) {
                piece.setType("Tor");
                boardState[piece.getY()][piece.getX()] = piece.getColor() == BLUE ? "b-Tor" : "r-Tor";
                piece.setMovementStrategy(new TorMovement());
            }
        }
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Gives back the list of pieces that are on the board at the moment.
     */
    public List<KwazamPiece> getPieces() {
        return pieces;
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Resets boardState to null (empty) and removes all pieces from the board.
     */
    public void clearPieces() {
        // Clear the pieces list
        pieces.clear();

        // Reset the board state to null (empty)
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                boardState[i][j] = null;
            }
        }
    }
}