package mainFolder.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mainFolder.model.Aerei;
import mainFolder.model.GestioneAerei;
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
    @FXML private TableView<Aerei> tblVoli;
    // Le colonne sono: Orario, ritardo, destinazione, n° volo, gate, compagnia aerea, stato
    @FXML private TableColumn<Aerei, LocalTime> colOrario;
    @FXML private TableColumn<Aerei, Integer> colRitardo;
    @FXML private TableColumn<Aerei, String> colDestinazione;
    @FXML private TableColumn<Aerei, String> colNVolo;
    @FXML private TableColumn<Aerei, Integer> colGate;
    @FXML private TableColumn<Aerei, String> colCompagnia;
    @FXML private TableColumn<Aerei, String> colStato;


    //per tenere controllato se si è loggati
    GestioneUtenti gestioneUtenti = GestioneUtenti.getInstance();
    GestioneAerei gestioneAerei = GestioneAerei.getInstance();

    public void initialize() {
        startClockUpdateAnimation();
        initializeTable();

        if (dpDataPartenza.getValue() == null) {
            dpDataPartenza.setValue(LocalDate.now());
        }
        
        changeData();

        dpDataPartenza.valueProperty().addListener((observable, oldValue, newValue) -> {
            changeData();
        });
        
    }

    public void initializeTable() {
        colOrario.setCellValueFactory(cellData -> cellData.getValue().getOraPartenzaProperty());
        colRitardo.setCellValueFactory(cellData -> cellData.getValue().getRitardoProperty().asObject());
        colDestinazione.setCellValueFactory(cellData -> cellData.getValue().getDestinazioneProperty());
        colNVolo.setCellValueFactory(cellData -> cellData.getValue().getCodiceProperty());
        colGate.setCellValueFactory(cellData -> cellData.getValue().getGateProperty().asObject());
        colCompagnia.setCellValueFactory(cellData -> cellData.getValue().getCompagniaProperty());
        colStato.setCellValueFactory(cellData -> cellData.getValue().getDestinazioneProperty());

        tblVoli.setItems(gestioneAerei.getElencoListaPartenze());
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

    @FXML
    public void changeData() {
        gestioneAerei.setDataArrivo(dpDataPartenza.getValue());
        gestioneAerei.setDataPartenza(dpDataPartenza.getValue());
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
