package Model;

import Model.Movement.MovementStrategy;

/*
 Author: Lo Shia Yang 1211108901
 Purpose: 1) In our game of Kwazam chess, it represents a "Sau" piece. 
          2) This class initializes a Sau piece with a certain color, position, and movement strategy, extending the KwazamPiece class.
 */
public class Sau extends KwazamPiece {

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Creates a new Sau piece using the chosen movement method, color, and position.
              2) Initializes the piece by using the superclass constructor.
     */
    public Sau(int color, int col, int row, MovementStrategy movementStrategy) {
        super(color, "Sau", col, row, movementStrategy);
    }
}