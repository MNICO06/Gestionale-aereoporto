/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainFolder.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import mainFolder.model.GestioneUtenti;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author molte
 */
public class mainController {

    @FXML private Label orologio;
    @FXML private Label titolo;
    @FXML private Button accedi;
    GestioneUtenti gestioneUtenti = GestioneUtenti.getInstance();
    


    public void initialize() {
        // Creazione di un Timeline per aggiornare l'orologio ogni secondo
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            orologio.setText(dateFormat.format(new Date()));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        accedi.setDisable(true);
        accedi.setOpacity(1);
    }

    
}
