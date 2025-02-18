package Model.Movement;

import java.util.ArrayList;
import java.util.List;

public class TorMovement implements MovementStrategy {

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Determines the valid moves for a Tor piece from a given position.
              2) The Tor can move any number of squares horizontally or vertically.
    */
    @Override
    public List<int[]> getValidMoves(int x, int y, String[][] boardState) {
        List<int[]> validMoves = new ArrayList<>();

        // Horizontal moves (left and right)
        for (int i = x - 1; i >= 0; i--) { // Left
            if (boardState[y][i] == null) {
                validMoves.add(new int[]{i, y});
            } else {
                // Check if the piece is an opponent's piece
                if (isOpponentPiece(boardState[y][i], boardState[y][x])) {
                    validMoves.add(new int[]{i, y}); // Can capture opponent's piece
                }
                break; // Stop if a piece is blocking
            }
        }
        for (int i = x + 1; i < boardState[0].length; i++) { // Right
            if (boardState[y][i] == null) {
                validMoves.add(new int[]{i, y});
            } else {
                // Check if the piece is an opponent's piece
                if (isOpponentPiece(boardState[y][i], boardState[y][x])) {
                    validMoves.add(new int[]{i, y}); // Can capture opponent's piece
                }
                break; // Stop if a piece is blocking
            }
        }

        // Vertical moves (up and down)
        for (int j = y - 1; j >= 0; j--) { // Up
            if (boardState[j][x] == null) {
                validMoves.add(new int[]{x, j});
            } else {
                // Check if the piece is an opponent's piece
                if (isOpponentPiece(boardState[j][x], boardState[y][x])) {
                    validMoves.add(new int[]{x, j}); // Can capture opponent's piece
                }
                break; // Stop if a piece is blocking
            }
        }
        for (int j = y + 1; j < boardState.length; j++) { // Down
            if (boardState[j][x] == null) {
                validMoves.add(new int[]{x, j});
            } else {
                // Check if the piece is an opponent's piece
                if (isOpponentPiece(boardState[j][x], boardState[y][x])) {
                    validMoves.add(new int[]{x, j}); // Can capture opponent's piece
                }
                break; // Stop if a piece is blocking
            }
        }

        return validMoves;
    }

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Helper method to check if the target piece is an opponent's piece.
              2) This is used to determine if a move is valid for capturing an opponent's piece.
     */
    private boolean isOpponentPiece(String targetPiece, String currentPiece) {
        if (targetPiece == null || currentPiece == null) {
            return false;
        }
        // Check if the colors are different (e.g., "r-" vs "b-")
        return targetPiece.charAt(0) != currentPiece.charAt(0);
    }
}