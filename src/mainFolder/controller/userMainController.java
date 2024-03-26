package mainFolder.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class userMainController {
    
    @FXML private Label orologio;
    @FXML private ToggleButton tglPartenze;
    @FXML private ToggleButton tglArrivi;
    @FXML private Button btnHome;
    @FXML private Button btnAccedi;

    private boolean partenzeSelected;

    // Metodo per impostare quali valori devono essere visualizzati (arrivi o partenze)
    public void setPartenzeSelected(boolean partenzeSelected) {
        this.partenzeSelected = partenzeSelected;
    }

    // Inizializzazione
    public void initialize() {
        startClockUpdateAnimation();

        // Evento ToggleButton Partenze se selezionato disattiva l'altro ToggleButton e se deselezionato lo riattiva
        tglPartenze.setOnAction(e -> {
            if (tglPartenze.isSelected()) {
                tglArrivi.setSelected(false);
                partenzeSelected = true;
            } else {
                tglArrivi.setSelected(true);
                partenzeSelected = false;
            }
        });

        tglArrivi.setOnAction(e -> {
            if (tglArrivi.isSelected()) {
                tglPartenze.setSelected(false);
                partenzeSelected = false;
            } else {
                tglPartenze.setSelected(true);
                partenzeSelected = true;
            }
        });
    }

    private void startClockUpdateAnimation() {
        // Animazione per l'orologio
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Deve avere questo formato: ore:minuti:secondi
                orologio.setText(String.format("%02d:%02d:%02d", 
                        java.time.LocalTime.now().getHour(),
                        java.time.LocalTime.now().getMinute(), 
                        java.time.LocalTime.now().getSecond()));
                
                if (partenzeSelected) {
                    tglPartenze.setSelected(true);
                    tglArrivi.setSelected(false);
                } else {
                    tglPartenze.setSelected(false);
                    tglArrivi.setSelected(true);
                }
            }
        };
        // Avvio l'animazione
        timer.start();
    }

    // Metodo per il tasto Home
    @FXML
    private void handleBtnHome() {
        try {
            // Carica la seconda GUI (FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/userGui.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("User GUI");
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la prima GUI (Finestra)
            Stage primaryStage = (Stage) btnHome.getScene().getWindow();
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo per il tasto Accedi
    @FXML
    private void handleBtnAccedi() {
        try {
            // Carica la seconda GUI (FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/loginGui.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Login GUI");
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la prima GUI (Finestra)
            Stage primaryStage = (Stage) btnHome.getScene().getWindow();
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}