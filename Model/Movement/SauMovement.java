package Model.Movement;

import java.util.ArrayList;
import java.util.List;

public class SauMovement implements MovementStrategy {

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Identifyes the moves that a Sau piece from a specific position can make.
              2) Like a chess king, the Sau can move one square in either orthogonal or diagonal direction.
     */
    @Override
    public List<int[]> getValidMoves(int startX, int startY, String[][] boardState) {
        List<int[]> validMoves = new ArrayList<>();
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}, // Orthogonal
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1} // Diagonal
        };

        for (int[] dir : directions) {
            int newX = startX + dir[0];
            int newY = startY + dir[1];
            if (newX >= 0 && newX < boardState[0].length && newY >= 0 && newY < boardState.length) {
                // Check if the target position is empty or contains an opponent's piece
                if (boardState[newY][newX] == null || isOpponentPiece(boardState[newY][newX], boardState[startY][startX])) {
                    validMoves.add(new int[]{newX, newY});
                }
            }
        }

        return validMoves;
    }

    /*
     Author: Lo Shia Yang 1211108901
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