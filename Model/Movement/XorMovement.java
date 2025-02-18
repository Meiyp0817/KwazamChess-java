package Model.Movement;

import java.util.ArrayList;
import java.util.List;

public class XorMovement implements MovementStrategy {

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Determines the valid moves for an Xor piece from a given position.
              2) The Xor can move any number of squares diagonally.
     */
    @Override
    public List<int[]> getValidMoves(int startX, int startY, String[][] boardState) {
        List<int[]> validMoves = new ArrayList<>();
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        for (int[] dir : directions) {
            int x = startX + dir[0], y = startY + dir[1];
            while (x >= 0 && x < boardState[0].length && y >= 0 && y < boardState.length) {
                if (boardState[y][x] == null) {
                    validMoves.add(new int[]{x, y});
                } else {
                    // Check if the piece is an opponent's piece
                    if (isOpponentPiece(boardState[y][x], boardState[startY][startX])) {
                        validMoves.add(new int[]{x, y}); // Can capture opponent's piece
                    }
                    break; // Stop when blocked
                }
                x += dir[0];
                y += dir[1];
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