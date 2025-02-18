package Model.Movement;

import java.util.List;

/*
Author: Lo Shia Yang 1211108901
Purpose: 1) Helper method to check if the target piece is an opponent's piece.
        2) This is used to determine if a move is valid for capturing an opponent's piece.
*/
public interface MovementStrategy {
    /**
     * Determines the valid moves for a piece from a given position.
     *
     * @param startX The starting X position of the piece.
     * @param startY The starting Y position of the piece.
     * @param board The current state of the board.
     * @return A list of valid positions the piece can move to.
     */
    List<int[]> getValidMoves(int startX, int startY, String[][] board);
    
}



