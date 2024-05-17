package mainFolder.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    @FXML private Label lblPrezzo;
    @FXML private Label segnala;
    private final String testoConferma = "prenotazione confermata";
    private final String testoErroreLoggato = "ERRORE! bisogna essere loggati";
    private final String testoErrorePersone = "ERRORE! bisogna scegliere le persone";
    private final String testoErroreClasse = "ERRORE! bisogna segliere la classe";
    private final String testoErroreSelezionato = "ERRORE! bisogna segliere un aereo";
    private final String testoErrorePosti = "ERRORE! non ci sono abbastanza posti";
    private boolean isSelected = false;
    private Aerei aereoSelezionato = null;
    private int nBagagli;
    private int nAdulti;
    private int nBambini;
    private int prezzoTot;
    private final int ristrettoSeconda = 50;
    private final int interoSeconda = 70;
    private final int ristrettoPrima = 80;
    private final int interoPrima = 160;
    private final int bagaglio = 25;

    


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
    

    private Stage infoStage;
    private boolean isLogged;

    //per tenere controllato se si è loggati
    GestioneUtenti gestioneUtenti = GestioneUtenti.getInstance();
    GestioneAerei gestioneAerei = GestioneAerei.getInstance();

    public void initialize() {
        startClockUpdateAnimation();
        initializeTable();
        setupRowSelectionListener();
        cbClasse.getItems().addAll("Prima classe", "Seconda classe");
        
        if (dpDataPartenza.getValue() == null) {
            dpDataPartenza.setValue(LocalDate.now());
        }
        
        changeData();

        dpDataPartenza.valueProperty().addListener((observable, oldValue, newValue) -> {
            changepartenza(txtDestinazione.getText());
        });
        
        cbClasse.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cambioBox(newValue.toString());
            }
        });

        txtDestinazione.textProperty().addListener((observable, oldValue, newValue) -> {
            changepartenza(newValue);
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(60), event -> {
            // Chiamare il metodo per aggiornare le tabelle
            aggiornaStato();
        }));
        timeline.setCycleCount(Animation.INDEFINITE); // Esegui all'infinito
        timeline.play();
        
    }

    public void initializeTable() {
        colOrario.setCellValueFactory(cellData -> cellData.getValue().getOraPartenzaProperty());
        colRitardo.setCellValueFactory(cellData -> cellData.getValue().getRitardoProperty().asObject());
        colDestinazione.setCellValueFactory(cellData -> cellData.getValue().getDestinazioneProperty());
        colNVolo.setCellValueFactory(cellData -> cellData.getValue().getCodiceProperty());
        colGate.setCellValueFactory(cellData -> cellData.getValue().getGateProperty().asObject());
        colCompagnia.setCellValueFactory(cellData -> cellData.getValue().getCompagniaProperty());
        colStato.setCellValueFactory(cellData -> cellData.getValue().getStatoProperty());

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

    private void setupRowSelectionListener() {
        // Aggiunge un listener di selezione alla tabella degli arrivi
        tblVoli.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleDoubleClick(newSelection);
            }
        });
    }

    public void aggiornaStato() {
        synchronized (gestioneAerei.getElencoLista()) {
            Platform.runLater(() -> {
                for (Aerei aerei : gestioneAerei.getElencoLista()) {
                    if (aerei.isInVolo()) {
                        aerei.setStato("In arrivo");
                    } else if (aerei.isInPartenza()) {
                        aerei.setStato("In partenza");
                    } else if (aerei.isInAttesa()) {
                        aerei.setStato("In attesa");
                    } else if (aerei.isInManutenzione()) {
                        aerei.setStato("In manutenzione");
                    }
                    if (aerei.isAggiornato()) {
                        changepartenza(txtDestinazione.getText());
                        aerei.setAggiornato(false);
                        //riaggiorno la tabella
                    }
                }
            });
        }
    }

    @FXML
    public void changeData() {
        gestioneAerei.setDataPartenza(dpDataPartenza.getValue());
    }

    public void changepartenza(String newValue) {
        gestioneAerei.setDataPartenza(dpDataPartenza.getValue());
        gestioneAerei.aggiornaPartenza(newValue.toLowerCase());
    }

    private void checkLogin() {
        if (gestioneUtenti.isLogged()) {
            btnAccedi.setText(gestioneUtenti.getUtenti().get(gestioneUtenti.getIndice()).getNome());
            btnAccedi.setDisable(true);
            btnAccedi.setOpacity(1);
        }
    }

    @FXML
    public void confermaPrenotazione() {
        //da resettare poi tutti i campi
        if (controllaTutto()) {
            segnala.setText(testoConferma);
            aereoSelezionato.setNumeroPostiOccupati(aereoSelezionato.getNumeroPostiOccupatiInt() + nAdulti + nBambini);
            segnala.setStyle("-fx-text-fill: black;");
            resetCampi();
            gestioneAerei.scriviDati();
            
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            lblPrezzo.setText("0");
            pause.setOnFinished(event -> segnala.setText(""));
            pause.play();
        }
    }

    public void resetCampi() {
        lblAdulti.setText("0");
        lblBambini.setText("0");
        lblBagagli.setText("0");
        nBagagli = 0;
        nAdulti = 0;
        nBambini = 0;
        prezzoTot = 0;
    }

    public boolean controllaTutto() {
        if (isLogged && isSelected) {
            if (nAdulti > 0 || nBambini > 0) {
                if (cbClasse.getValue() != null) {
                    if (aereoSelezionato.getPostiMassimi() >= (aereoSelezionato.getNumeroPostiOccupatiInt() + nAdulti + nBambini)) {
                        return true;
                    }
                    else {
                        segnala.setText(testoErrorePosti);
                        segnala.setStyle("-fx-text-fill: red;");
                        rimuoviErrore();
                    }
                }
                else {
                    segnala.setText(testoErroreClasse);
                    segnala.setStyle("-fx-text-fill: red;");
                    rimuoviErrore();
                }
            } else {
                segnala.setText(testoErrorePersone);
                segnala.setStyle("-fx-text-fill: red;");
                rimuoviErrore();
            }
            
        }
        else if (!isLogged){
            segnala.setText(testoErroreLoggato);
            segnala.setStyle("-fx-text-fill: red;");
            rimuoviErrore();
        }
        else if (!isSelected) {
            segnala.setText(testoErroreSelezionato);
            segnala.setStyle("-fx-text-fill: red;");
            rimuoviErrore();
        }
        return false;
    }

    private void rimuoviErrore() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> segnala.setText(""));
        pause.play();
    }

    private void cambioBox(String value) {
        aggiornaPrezzo();
    }

    private void aggiornaPrezzo() {
        if (cbClasse.getValue() != null) {
            if (cbClasse.getValue().equals("Prima classe")) {
                prezzoTot = (nAdulti * interoPrima) + (nBambini * ristrettoPrima);
            } else if (cbClasse.getValue().equals("Seconda classe")) {
                prezzoTot = (nAdulti * interoSeconda) + (nBambini * ristrettoSeconda);
            }
            prezzoTot += (nBagagli * bagaglio);
        }
        lblPrezzo.setText(convertiIntToString(prezzoTot));
    }

    @FXML
    public void aggiungiAdulto() {
        nAdulti++;
        lblAdulti.setText(convertiIntToString(nAdulti));
        aggiornaPrezzo();
    }
    
    @FXML
    public void rimuoviAdulto() {
        if (nAdulti != 0) {
            nAdulti--;
            lblAdulti.setText(convertiIntToString(nAdulti));
            aggiornaPrezzo();
        }
    }

    @FXML
    public void aggiungiBambino() {
        nBambini++;
        lblBambini.setText(convertiIntToString(nBambini));
        aggiornaPrezzo();
    }

    @FXML
    public void rimuoviBambino() {
        if (nBambini != 0) {
            nBambini--;
            lblBambini.setText(convertiIntToString(nBambini));
            aggiornaPrezzo();
        }
    }

    @FXML
    public void aggiungiBagaglio() {
        nBagagli++;
        lblBagagli.setText(convertiIntToString(nBagagli));
        aggiornaPrezzo();
    }

    @FXML
    public void rimuoviBagaglio() {
        if (nBagagli != 0) {
            nBagagli--;
            lblBagagli.setText(convertiIntToString(nBagagli));
            aggiornaPrezzo();
        }
    }

    private void handleDoubleClick(Aerei aereo) {
        isSelected = true;
        aereoSelezionato = aereo;
        if (infoStage != null && infoStage.isShowing()) {
            // Chiudi la finestra delle informazioni esistente se è già aperta
            infoStage.close();
        }

        try {
            // Carica la seconda GUI (FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/dettagliAereoGui.fxml"));
            Parent root = loader.load();
            infoStage = new Stage();
            infoStage.setTitle("Dettagli Aereo");
            infoStage.setScene(new Scene(root));

            // Passo l'aereo alla seconda GUI
            dettagliAereoController controller = loader.getController();
            controller.setAereo(aereo);

            // Aggiungi un listener per gestire la chiusura della finestra delle
            // informazioni
            infoStage.setOnCloseRequest(event -> infoStage = null);

            // Imposta le coordinate della finestra
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            double xOffset = 20; // spostamento orizzontale
            double yOffset = 20; // spostamento verticale
            infoStage.setX(primaryScreenBounds.getMinX() + xOffset);
            infoStage.setY(primaryScreenBounds.getMinY() + yOffset);

            infoStage.show();
        } catch (Exception e) {
            e.printStackTrace();
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

    public void setLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public static String convertiIntToString(int numero) {
        String stringa = String.valueOf(numero);
        return stringa;
    }
}
