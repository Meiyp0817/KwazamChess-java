package Model.Movement;

import java.util.ArrayList;
import java.util.List;

public class BizMovement implements MovementStrategy {

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Determines the valid moves for a Biz piece from a given position.
              2) The "L"-shaped movement of the Biz piece recalls that of a knight in chess.
     */
    @Override
    public List<int[]> getValidMoves(int startX, int startY, String[][] boardState) {
        List<int[]> validMoves = new ArrayList<>();
        // All possible "L" shapes: 3x2 or 2x3 in any orientation
        int[][] moves = {
            { startX + 2, startY + 1 }, { startX + 2, startY - 1 },
            { startX - 2, startY + 1 }, { startX - 2, startY - 1 },
            { startX + 1, startY + 2 }, { startX + 1, startY - 2 },
            { startX - 1, startY + 2 }, { startX - 1, startY - 2 }
        };

        for (int[] move : moves) {
            int newX = move[0];
            int newY = move[1];
            // Check if the move is within bounds
            if (newX >= 0 && newX < boardState[0].length && newY >= 0 && newY < boardState.length) {
                // Check if the target position is empty or contains an opponent's piece
                if (boardState[newY][newX] == null || isOpponentPiece(boardState[newY][newX], boardState[startY][startX])) {
                    validMoves.add(move);
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