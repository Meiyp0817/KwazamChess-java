package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class ChessPiece extends JButton {
    private int color;
    private String type;
    private BufferedImage originalImage; // Store the original image
    private BufferedImage rotatedImage; // Store the rotated image
    private boolean isSelected; // Track if the piece is selected

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)set default value 
    */
    public ChessPiece(){
        this.isSelected = false;    
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Intialize a chess pieces with color(red or blue) ,type(name) 
    2)load the piece image 
    */
    public ChessPiece(int color, String type) {
        this.color = color;
        this.type = type;
        //this.isSelected = false;

        // Load the image
        String imgPath = "/image/" + (color == 0 ? "r-" : "b-") + type + "Piece.png";
        this.originalImage = loadImage(imgPath);
        this.rotatedImage = originalImage; // Initially, no rotation
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Read the piece image file path
    */
    private BufferedImage loadImage(String imagePath) {
        try {
            // Use absolute path starting from resources
            return ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Draw the piece images at the specified coordinates
    */
    public void drawImage(Graphics g, int x, int y, int width, int height) {
        if (rotatedImage != null) {
            g.drawImage(rotatedImage, x, y, width, height, null);
        }
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Return the rotated image of the piece images
    */
    public BufferedImage getImage() {
        return rotatedImage;
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)reutrn the piece name
    */
    public String getType() {
        return type;
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)return the piece color(red or blue)
    */
    public int getColor() {
        return color;
    }
    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)set the piece name
    */
    public void setType(String type) {
        this.type = type;
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)set the piece color
    */
    public void setColor(int color) {
        this.color = color;
    }

    /*
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Returns whether the chess piece is currently selected.
    */
    public boolean isSelected() {
        return isSelected;
    }

}