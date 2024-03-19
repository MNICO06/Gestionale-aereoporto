package mainFolder;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import mainFolder.model.gestioneAerei;

/**
 *
 * @author molte
 */
public class MainApp extends Application {
    private Stage primaryStage;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Aeroporto");
        
        inizializza();

    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
     
     
    private void inizializza(){
        
        try{
            gestioneAerei gestione = new gestioneAerei();
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("./guiFolder/userGui.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene (rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            // PROVA COMMENTO

        }catch(IOException e){
            e.printStackTrace();
        }        
    }

    
}
