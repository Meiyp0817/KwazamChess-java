package Model;

import Model.Movement.MovementStrategy;

/*
 Author: 
 Purpose: 1) In our Kwazam chess game, it stands in for a "Xor" piece. 
          2) This class initializes a Xor piece with a certain color, position, and movement strategy by extending the KwazamPiece class.
 */
public class Xor extends KwazamPiece {

    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Creates a new Xor piece using the chosen movement strategy, color, and position.
              2) Initializes the piece by using the superclass constructor.
     */
    public Xor(int color, int col, int row, MovementStrategy movementStrategy) {
        super(color, "Xor", col, row, movementStrategy);
    }
}