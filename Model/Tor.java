package Model;

import Model.Movement.MovementStrategy;

/*
 Author: Mei Yong Peng 1211109159
 Purpose: 1) In our Kwazam chess game, it represents a "Tor" piece. 
          2) This class adds the ability to track turns and adjust moving strategies to the KwazamPiece class.
 */
public class Tor extends KwazamPiece {
    /*
     Author: Mei Yong Peng 1211109159
     Purpose: 1) Creates a new Tor component using the chosen movement method, color, and position.
              2) Initializes the piece by using the superclass constructor.
     */
    public Tor(int color, int col, int row, MovementStrategy movementStrategy) {
        super(color, "Tor", col, row, movementStrategy);
    }
}