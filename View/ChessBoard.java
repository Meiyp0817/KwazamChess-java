package View;

import Model.KwazamBoard;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ChessBoard extends JPanel {
    private int cols = 5; // Number of columns
    private int rows = 8; // Number of rows
    private ChessPiece[][] boardPieces; // To keep track of pieces on the board
    private boolean isFlipped = false;//private boolean isInitialized;
    private boolean imagesLoaded;

    /* 
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Initializes the chessboard with grid layout
    2)set up boardPieces <- button 
    */
    public ChessBoard() {
        this.setLayout(new GridLayout(rows, cols)); // Use GridLayout for buttons
        boardPieces = new ChessPiece[rows][cols]; // Initialize the boardPieces array
        initializeBoard();
    }

    /* 
    Author:Chan Ka Ken 1211109440
    1）Purpose of the method: custom the chessboard, including size, background color, blue borders.
    2）Add boardPieces buttons on the chessboard
    */
private void initializeBoard() {
    this.setPreferredSize(new Dimension(500, 800));

    // Define custom colors
    Color customBpurple = new Color(152, 125, 182); // Custom Darkpurple color
    Color customLpurple = new Color(216, 191, 216);  // Custom Lightpurple color
    Color customYellow = new Color(255, 223, 0); // Custom yellow color

    // Use a pattern to alternate colors
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            boardPieces[i][j] = new ChessPiece();
            
            // Alternate colors in a checkerboard pattern
            if ((i + j) % 2 == 0) {
                boardPieces[i][j].setBackground(customBpurple); // Set background to custom Dpurple
            } else {
                boardPieces[i][j].setBackground(customLpurple); // Set background to custom Lpurple
            }

            // Add a border for visual appeal
            boardPieces[i][j].setBorder(BorderFactory.createLineBorder(customYellow, 3)); // Green border
            boardPieces[i][j].setOpaque(true);
            boardPieces[i][j].setFocusPainted(false);
            this.add(boardPieces[i][j]);
        }
    }
}

    /* 
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Check pieceData null or not , 
    if not null then put pieces image into 
    if null then no put image
    */
    public void updateBoard(String[][] gameState) {
        for (int i = 0; i < gameState.length; i++) {
            for (int j = 0; j < gameState[i].length; j++) {
                String pieceData = gameState[i][j];
                ChessPiece button = boardPieces[i][j];

                if (pieceData != null && !pieceData.isEmpty()) {
                    String[] pieceInfo = pieceData.split("-");
                    String color = pieceInfo[0];
                    String type = pieceInfo[1];

                    ChessPiece piece = new ChessPiece(color.equals("r") ? 0 : 1, type);
                    ImageIcon icon = new ImageIcon(piece.getImage());
                    if (icon.getImage() != null) {
                        button.setIcon(icon);
                        button.setType(type);
                        button.setColor(color.equals("r") ? 0 : 1);
                    }
                } else {
                    button.setIcon(null);
                    button.setType("");
                    button.setColor(-1);
                }
            }
        }
         imagesLoaded = true;
    }

    /* 
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)Flips the boards and rotates the piece images based on current color.
    */
    public void flipBoard(int currentColor) {
        if (!imagesLoaded) {
            return; // Do nothing if the images are not loaded
        }

        // Determine flip and rotation logic
        boolean shouldFlip = (currentColor == KwazamBoard.BLUE);
        boolean shouldRotate180 = (currentColor == KwazamBoard.RED);

        if (isFlipped != shouldFlip || shouldRotate180) {
            isFlipped = shouldFlip;

            this.removeAll(); // Clear the board

            if (shouldFlip) {
                // Flip the board for BLUE
                for (int i = rows - 1; i >= 0; i--) {
                    for (int j = cols - 1; j >= 0; j--) {
                        rotateButtonIcon(boardPieces[i][j], 360); // Rotate for BLUE
                        this.add(boardPieces[i][j]);
                    }
                }
            } else if (shouldRotate180) {
                // Rotate pieces for RED without flipping
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        rotateButtonIcon(boardPieces[i][j], 180); // Rotate 180 degrees immediately
                        this.add(boardPieces[i][j]);
                    }
                }
            } else {
                // Default orientation (for other colors)
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        rotateButtonIcon(boardPieces[i][j], 0); // Reset rotation to default
                        this.add(boardPieces[i][j]);
                    }
                }
            }

            this.revalidate(); // Refresh the layout
            this.repaint(); // Redraw the board
        }
    }

    /* 
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)rotate the piece image when flip board
    */
    private void rotateButtonIcon(JButton button, int angle) {
        ImageIcon icon = (ImageIcon) button.getIcon();
        if (icon != null) {
            Image originalImage = icon.getImage();
            BufferedImage bufferedImage = new BufferedImage(
                originalImage.getWidth(null),
                originalImage.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
            );
    
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.rotate(Math.toRadians(angle), bufferedImage.getWidth() / 2.0, bufferedImage.getHeight() / 2.0);
            g2d.drawImage(originalImage, 0, 0, null);
            g2d.dispose();
    
            button.setIcon(new ImageIcon(bufferedImage));
        }
    }

    /* 
    Author:Chan Ka Ken 1211109440
    Purpose of the method: 
    1)return 2D array of chess pieces representing the current state of the board
    */
    public ChessPiece[][] getBoardPieces() {
        return boardPieces;
    }

}