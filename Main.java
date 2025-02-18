import Controller.ChessController;
import Model.ChessModel;
import View.ChessView;

/*
Author:Fong Kai Chun 1211108901
Purpose of the method: 
1)Main launcher to run the gameplay
*/
public class Main {
    public static void main(String[] args) {
        // Initialize the MVC components
        ChessModel model = new ChessModel();
        ChessView view = new ChessView();
        ChessController controller = ChessController.getInstance(model, view);

        // Set up the button actions in the controller
        controller.MenuWindow();

    }
}