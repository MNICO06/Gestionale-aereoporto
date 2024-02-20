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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import mainFolder.controller.MainController;
import mainFolder.model.GestioneAerei;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author molte
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private MainController mainController;
    
    
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
            GestioneAerei gestione = new GestioneAerei();
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("./guiFolder/mainGui.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene (rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            

        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

    
}
