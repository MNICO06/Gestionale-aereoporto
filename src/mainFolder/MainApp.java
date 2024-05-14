package mainFolder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainFolder.model.Controllo;
import mainFolder.model.GestioneAerei;

/**
 *
 * @author molte
 */
public class MainApp extends Application {
    private Controllo controllo;

    @Override
    public void start(Stage primaryStage) throws Exception {

        GestioneAerei gestioneAerei = GestioneAerei.getInstance();;
        controllo = new Controllo(gestioneAerei.getElencoLista());
        controllo.start();

        // Carica la prima GUI (FXML)
        Parent root = FXMLLoader.load(getClass().getResource("./guiFolder/userGui.fxml"));
        primaryStage.setTitle("Aeroporto - Gestione voli");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    @Override
    public void stop() {
       // Thread stop
        if (controllo != null) {
                controllo.stopRunning();
                try {
                    controllo.join();  // Attendi che il thread finisca
                } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
