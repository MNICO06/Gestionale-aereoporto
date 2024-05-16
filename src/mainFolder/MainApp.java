package mainFolder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mainFolder.model.GestioneAerei;

/**
 *
 * @author molte
 */
public class MainApp extends Application {
    GestioneAerei gestioneAerei = GestioneAerei.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception {


        // Carica la prima GUI (FXML)
        Parent root = FXMLLoader.load(getClass().getResource("./guiFolder/userGui.fxml"));

        // Carica l'icona
        Image icon = new Image(getClass().getResourceAsStream("/mainFolder/icona/management.ico"));
        primaryStage.getIcons().add(icon);
        // Carico la scena
        primaryStage.setTitle("Aeroporto - Gestione voli");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
