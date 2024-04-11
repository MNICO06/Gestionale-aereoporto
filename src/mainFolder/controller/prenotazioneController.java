package mainFolder.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mainFolder.model.GestioneUtenti;

public class prenotazioneController {
    
    @FXML private Label lblOrologio;
    @FXML private Button btnHome;
    @FXML private Button btnAccedi;

    // Ricerca volo
    @FXML private DatePicker dpDataPartenza;
    @FXML private TextField txtDestinazione;
    // Prenota volo
    @FXML private Label lblAdulti;
    @FXML private Button btnAdultiPlus;
    @FXML private Button btnAdultiMinus;
    @FXML private Label lblBambini;
    @FXML private Button btnBambiniPlus;
    @FXML private Button btnBambiniMinus;
    @FXML private Label lblBagagli;
    @FXML private Button btnBagagliPlus;
    @FXML private Button btnBagagliMinus;
    @FXML private ComboBox<String> cbClasse;
    @FXML private Button btnPrenota;

    // Tabella voli
    @FXML private TableView<?> tblVoli;
    // Le colonne sono: Orario, ritardo, destinazione, n° volo, gate, compagnia aerea, stato
    @FXML private TableColumn<?, ?> colOrario;
    @FXML private TableColumn<?, ?> colRitardo;
    @FXML private TableColumn<?, ?> colDestinazione;
    @FXML private TableColumn<?, ?> colNVolo;
    @FXML private TableColumn<?, ?> colGate;
    @FXML private TableColumn<?, ?> colCompagnia;
    @FXML private TableColumn<?, ?> colStato;


    //per tenere controllato se si è loggati
    GestioneUtenti gestioneUtenti = GestioneUtenti.getInstance();

    public void initialize() {
        startClockUpdateAnimation();
    }

    
    private void startClockUpdateAnimation() {
        // Animazione per l'orologio
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Deve avere questo formato: ore:minuti:secondi
                lblOrologio.setText(String.format("%02d:%02d:%02d", 
                        java.time.LocalTime.now().getHour(),
                        java.time.LocalTime.now().getMinute(), 
                        java.time.LocalTime.now().getSecond()));
            }
        };
        // Avvio l'animazione
        timer.start();

        checkLogin();
    }

    private void checkLogin() {
        if (gestioneUtenti.isLogged()) {
            btnAccedi.setText(gestioneUtenti.getUtenti().get(gestioneUtenti.getIndice()).getNome());
            btnAccedi.setDisable(true);
            btnAccedi.setOpacity(1);
        }
    }

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

    @FXML
    private void handleBtnAccedi() {
        try {
            gestioneUtenti.setSchermataPrecedente("PrenotaPage");
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
