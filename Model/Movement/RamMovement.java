package Model.Movement;

import java.util.ArrayList;
import java.util.List;

public class RamMovement implements MovementStrategy {
    private int direction; // -1 for moving up, 1 for moving down

    /*
     Author: Fong Kai Chun 1211108901
     Purpose: 1) Based on the color of the ram, creates a new RamMovement instance with a direction.
              2) The Blue Rams travel downward (direction = -1) and the Red Rams ascend (direction = 1).
     */
    public RamMovement(int color) {
        if (color == 1)
            this.direction = 1; // Red Rams move up
        else {
            this.direction = -1; // Blue Rams move down
        }
    }

    /*
     Author: Fong Kai Chun 1211108901
     Purpose: 1) Identifyes the moves that a Ram piece from a specific position can make.
              2) The Ram moves forward in its current direction, and if it reaches the end of the board, it reverses direction immediately and continues moving.
     */
    @Override
    public List<int[]> getValidMoves(int startX, int startY, String[][] boardState) {
        List<int[]> validMoves = new ArrayList<>();

        // Forward move
        int newY = startY + direction;
        if (newY >= 0 && newY < boardState.length) {
            // Check if the forward position is empty
            if (boardState[newY][startX] == null) {
                validMoves.add(new int[]{startX, newY});
            }
            // Check if the forward position has an opponent's piece (capture)
            else if (isOpponentPiece(boardState[newY][startX], boardState[startY][startX])) {
                validMoves.add(new int[]{startX, newY});
            }
        } else {
            // If the Ram reaches the end of the board, reverse direction immediately
            direction *= -1;
            newY = startY + direction; // Recalculate newY with the reversed direction

            // Check if the new position is valid after reversing direction
            if (newY >= 0 && newY < boardState.length) {
                if (boardState[newY][startX] == null || isOpponentPiece(boardState[newY][startX], boardState[startY][startX])) {
                    validMoves.add(new int[]{startX, newY});
                }
            }
        }

        return validMoves;
    }

    /*
     Author: Fong Kai Chun 1211108901
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