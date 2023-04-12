package test;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Skand
 */
public class Test extends Application {
    
    
    
   @Override
   public void start(Stage stage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AfficherMatiere.fxml"));

        // Create a scene with the root node and set the scene on the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Set the title of the stage and show it
     
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



