/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainFolder;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import mainFolder.controller.mainController;
import mainFolder.model.gestioneAerei;

/**
 *
 * @author molte
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private mainController mainController;
    
    
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
            loader.setLocation(MainApp.class.getResource("mainGui.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene (rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            

        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

    
}
