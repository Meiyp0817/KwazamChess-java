package Model;

import Model.Movement.MovementStrategy;

/*
 Author: Lo Shia Yang 1211108901
 Purpose: 1) In our Kwazam chess game, it represents a generic piece. 
          2) The color, kind, position, movement plan, and capture status of the piece are all stored in this class.
 */
public class KwazamPiece {
    protected int color; // The color of the piece (RED or BLUE)
    protected String type; // The type of the piece (e.g., Tor, Biz, Sau, etc.)
    protected int x, y; // The position of the piece on the board
    protected MovementStrategy movementStrategy; // The movement strategy for the piece
    protected boolean isCaptured; // Tracks whether the piece has been captured

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Creates a new KwazamPiece with the desired movement strategy, color, type, and position.
              2) Sets the piece's initial state to "not captured."
     */
    public KwazamPiece(int color, String type, int x, int y, MovementStrategy movementStrategy) {
        this.color = color;
        this.type = type;
        this.x = x;
        this.y = y;
        this.movementStrategy = movementStrategy;
        this.isCaptured = false; // Initialize as not captured
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Returns the piece's color.
     */
    public int getColor() {
        return color;
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Gives back the piece's type.
     */
    public String getType() {
        return type;
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Provides the piece's position's x-coordinate (column).
     */
    public int getX() {
        return x;
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Provides the piece's position's y-coordinate (row).
     */
    public int getY() {
        return y;
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Adjusts the piece's position to the given coordinates.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Defines the piece's position's x-coordinate (column).
     */
    public void setX(int x) {
        this.x = x;
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Determines the piece's position's y-coordinate (row).
     */
    public void setY(int y) {
        this.y = y;
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Establishes the piece's kind.
     */
    public void setType(String type) {
        this.type = type;
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Returns the piece's related movement strategy.
     */
    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Establishes the piece's movement plan.
     */
    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Indicates if the piece has been recorded.
     */
    public boolean isCaptured() {
        return isCaptured;
    }

    /*
     Author: Lo Shia Yang 1211108901
     Purpose: 1) Sets the piece's position to (-1, -1) to mark it as captured and move it off the board.
     */
    public void capture() {
        this.isCaptured = true;
        this.x = -1; // Move the piece off the board
        this.y = -1;
    }
}