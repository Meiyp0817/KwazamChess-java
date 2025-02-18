package Model;

import Model.Movement.MovementStrategy;

/*
 Author: Lo Shia Yang 1211108901
 Purpose: 1) In our Kwazam chess game, it stands for a "Biz" piece. 
          2) This class setting a Biz piece with a certain color (RED and BLUE), positions, and movement strategy, extending on the KwazamPiece class.
*/
public class Biz extends KwazamPiece {

/*
 Author: Lo Shia Yang 1211108901
 Purpose: 1) Creating a new Biz piece with the chosen movement method, color, and position.
          2) Set the piece by using the superclass constructor.

*/
    public Biz(int color, int col, int row, MovementStrategy movementStrategy) {
        super(color, "Biz", col, row, movementStrategy);
    }
}