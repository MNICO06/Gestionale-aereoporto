package mainFolder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author molte
 */
public class MainApp extends Application {        
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Carica la prima GUI (FXML)
        Parent root = FXMLLoader.load(getClass().getResource("./guiFolder/userGui.fxml"));
        primaryStage.setTitle("Aeroporto - Gestione voli");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
