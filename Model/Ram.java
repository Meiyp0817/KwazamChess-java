package Model;

import Model.Movement.MovementStrategy;

/*
 Author: Fong Kai Chun 1211108430
 Purpose: 1) In our Kwazam chess game, it stands in for a "Ram" piece. 
          2) This class adds functionality unique to the Ram piece, like direction and the ability to toggle its direction, to the KwazamPiece class.
 */
public class Ram extends KwazamPiece {
    public int direction; // The direction of the Ram piece (1 for forward, -1 for backward)

    /*
     Author: Fong Kai Chun 1211108430
     Purpose: 1) Creates a new Ram component using the chosen movement method, color, and position.
              2) Sets the direction according to the color of the item (1 for RED, -1 for BLUE).
     */
    public Ram(int color, int col, int row, MovementStrategy movementStrategy) {
        super(color, "Ram", col, row, movementStrategy);
        this.direction = color == 0 ? 1 : -1; // Set direction based on color
    }

    /*
     Author: Fong Kai Chun 1211108430
     Purpose: 1) Gives back the Ram piece's current direction.
     */
    public int getDirection() {
        return direction;
    }

    /*
     Author: Fong Kai Chun 1211108430
     Purpose: 1) Adjusts the Ram piece's direction to the given value.
     */
    public void setDirection(int d) {
        this.direction = d;
    }

    /*
     Author: Fong Kai Chun 1211108430 
     Purpose: 1) Changes the Ram piece's orientation from forward to backward.
     */
    public void toggleDirection() {
        this.direction = -this.direction; // Reverse the direction
    }
}