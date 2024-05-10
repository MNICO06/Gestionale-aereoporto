/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainFolder.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import mainFolder.model.Aerei;
import mainFolder.model.GestioneAerei;
import mainFolder.model.GestioneUtenti;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author molte
 */
public class mainController {
    private static mainController mainIstance;

    @FXML private Label orologio;
    @FXML private Label titolo;

    GestioneUtenti gestioneUtenti = GestioneUtenti.getInstance();
    GestioneAerei gestioneAerei = GestioneAerei.getInstance();

    @FXML private TableView<Aerei> tabellaArrivo;
    @FXML private TableColumn<Aerei, LocalTime> colonnaOrarioArrivo;
    @FXML private TableColumn<Aerei, Integer> colonnaRitardoArrivo;
    @FXML private TableColumn<Aerei, String> colonnaProvenienzaArrivo;
    @FXML private TableColumn<Aerei, String> colonnaNVoloArrivo;
    @FXML private TableColumn<Aerei, Integer> colonnaGateArrivo;
    @FXML private TableColumn<Aerei, String> colonnaCompagniaArrivo;
    @FXML private TableColumn<Aerei, String> colonnaStatoArrivo;

    @FXML private TableView<Aerei> tabellaPartenza;
    @FXML private TableColumn<Aerei, LocalTime> colonnaOrarioPartenza;
    @FXML private TableColumn<Aerei, Integer> colonnaRitardoPartenza;
    @FXML private TableColumn<Aerei, String> colonnaDestinazionePartenza;
    @FXML private TableColumn<Aerei, String> colonnaNVoloPartenza;
    @FXML private TableColumn<Aerei, Integer> colonnaGatePartenza;
    @FXML private TableColumn<Aerei, String> colonnaCompagniaPartenza;
    @FXML private TableColumn<Aerei, String> colonnaStatoPartenza;

    @FXML private TableView<Aerei> tabellaTerra;
    @FXML private TableColumn<Aerei, LocalTime> colonnaOrarioTerra;
    @FXML private TableColumn<Aerei, Integer> colonnaRitardoTerra;
    @FXML private TableColumn<Aerei, String> colonnaDestinazioneTerra;
    @FXML private TableColumn<Aerei, String> colonnaNVoloTerra;
    @FXML private TableColumn<Aerei, Integer> colonnaGateTerra;
    @FXML private TableColumn<Aerei, String> colonnaCompagniaTerra;
    @FXML private TableColumn<Aerei, String> colonnaStatoTerra;

    @FXML private TableView<Aerei> tabellaManutenzione;
    @FXML private TableColumn<Aerei, String> colonnaNVoloManutenzione;
    @FXML private TableColumn<Aerei, String> colonnaCompagniaManutenzione;
    @FXML private TableColumn<Aerei, LocalDate> colonnaInizioLavoriManutenzione;
    @FXML private TableColumn<Aerei, LocalDate> colonnaFineLavoriManutenzione;
    @FXML private TableColumn<Aerei, String> colonnaHangarManutenzione;

    // Filtri
    // Partenze
    @FXML private DatePicker datePickerPartenze;
    @FXML private TextField destFiltPartenze;
    @FXML private TextField compFiltPartenze;
    @FXML private Button cancPartenze;
    // Arrivi
    @FXML private DatePicker datePickerArrivo;
    @FXML private TextField destFiltArrivi;
    @FXML private TextField compFiltArrivi;
    @FXML private Button cancArrivi;
    // Terra
    @FXML private DatePicker datePickerTerra;
    @FXML private TextField destFiltTerra;
    @FXML private TextField compFiltTerra;
    @FXML private Button cancTerra;
    // Manutenzione
    @FXML private DatePicker datePickerManutenzione;
    @FXML private TextField destFiltManutenzione;
    @FXML private TextField compFiltManutenzione;
    @FXML private Button cancManutenzione;

    private Stage infoStage;

    public static mainController getInstance() {
        if(mainIstance == null) {
            mainIstance = new mainController();
        }
        return mainIstance;
    }

    public void initialize() {
        // Creazione di un Timeline per aggiornare l'orologio ogni secondo
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            orologio.setText(dateFormat.format(new Date()));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        setDataArrivi(LocalDate.now());
        setDataPartenza(LocalDate.now());
        setDataTerra(LocalDate.now());
        setDataManutenzione(LocalDate.now());


        if (datePickerPartenze.getValue() == null) {
            setDataPartenza(LocalDate.now());
        }
        if (datePickerArrivo.getValue() == null) {
            setDataArrivi(LocalDate.now());
        }
        if (datePickerTerra.getValue() == null) {
            setDataTerra(LocalDate.now());
        }
        if (datePickerManutenzione.getValue() == null) {
            setDataManutenzione(LocalDate.now());
        }


        changeDataArrivo();
        changeDataPartenza();
        changeDataTerra();
        changeDataManutenzione();

        datePickerArrivo.valueProperty().addListener((observable, oldValue, newValue) -> {
            cercaAereiGiustoArrivi(destFiltArrivi.getText(), compFiltArrivi.getText());
        });

        datePickerPartenze.valueProperty().addListener((observable, oldValue, newValue) -> {
            cercaAereiGiustoPartenze(destFiltPartenze.getText(), compFiltPartenze.getText());
        });

        datePickerManutenzione.valueProperty().addListener((observable, oldValue, newValue) -> {
            cercaAereiGiustoManutenzione(destFiltManutenzione.getText(), compFiltManutenzione.getText());
        });

        datePickerTerra.valueProperty().addListener((observable, oldValue, newValue) -> {
            cercaAereiGiustoTerra(destFiltTerra.getText(), compFiltTerra.getText());
        });

        destFiltArrivi.textProperty().addListener((observable, oldValue, newValue) -> {
            cercaAereiGiustoArrivi(newValue, compFiltArrivi.getText());
        });

        compFiltArrivi.textProperty().addListener((observable, oldValue, newValue) -> {
            cercaAereiGiustoArrivi(destFiltArrivi.getText(), newValue);
        });

        destFiltPartenze.textProperty().addListener((observable, oldValue, newValue) -> {
            cercaAereiGiustoPartenze(newValue, compFiltPartenze.getText());
        });

        compFiltPartenze.textProperty().addListener((observable, oldValue, newValue) -> {
            cercaAereiGiustoPartenze(destFiltPartenze.getText(), newValue);
        });

        destFiltManutenzione.textProperty().addListener((observable, oldValue, newValue) -> {
            cercaAereiGiustoManutenzione(newValue, compFiltManutenzione.getText());
        });

        compFiltManutenzione.textProperty().addListener((observable, oldValue, newValue) -> {
            cercaAereiGiustoManutenzione(destFiltManutenzione.getText(), newValue);
        });

        destFiltTerra.textProperty().addListener((observable, oldValue, newValue) -> {
            cercaAereiGiustoTerra(newValue, compFiltTerra.getText());
        });

        compFiltTerra.textProperty().addListener((observable, oldValue, newValue) -> {
            cercaAereiGiustoTerra(destFiltTerra.getText(), newValue);
        });

        initializeTable();
        setupRowSelectionListener();
    }

    // Metodo per aggiornare le tabelle
    public void aggiornaTabelle() {
        cercaAereiGiustoArrivi(destFiltArrivi.getText(), compFiltArrivi.getText());
        cercaAereiGiustoPartenze(destFiltPartenze.getText(), compFiltPartenze.getText());
        cercaAereiGiustoTerra(destFiltTerra.getText(), compFiltTerra.getText());
        cercaAereiGiustoManutenzione(destFiltManutenzione.getText(), compFiltManutenzione.getText());
    }

    private void setDataPartenza(LocalDate date) {
        datePickerPartenze.setValue(date);
    }
    private void setDataArrivi(LocalDate date) {
        datePickerArrivo.setValue(date);
    }
    private void setDataTerra(LocalDate date) {
        datePickerTerra.setValue(date);
    }
    private void setDataManutenzione(LocalDate date) {
        datePickerManutenzione.setValue(date);
    }


    public void initializeTable() {
        colonnaOrarioArrivo.setCellValueFactory(cellData -> cellData.getValue().getOraArrivoProperty());
        colonnaRitardoArrivo.setCellValueFactory(cellData -> cellData.getValue().getRitardoProperty().asObject());
        colonnaProvenienzaArrivo.setCellValueFactory(cellData -> cellData.getValue().getProvenienzaProperty());
        colonnaNVoloArrivo.setCellValueFactory(cellData -> cellData.getValue().getCodiceProperty());
        colonnaGateArrivo.setCellValueFactory(cellData -> cellData.getValue().getGateProperty().asObject());
        colonnaCompagniaArrivo.setCellValueFactory(cellData -> cellData.getValue().getCompagniaProperty());
        colonnaStatoArrivo.setCellValueFactory(cellData -> cellData.getValue().getStatoProperty());


        colonnaOrarioPartenza.setCellValueFactory(cellData -> cellData.getValue().getOraPartenzaProperty());
        colonnaRitardoPartenza.setCellValueFactory(cellData -> cellData.getValue().getRitardoProperty().asObject());
        colonnaDestinazionePartenza.setCellValueFactory(cellData -> cellData.getValue().getDestinazioneProperty());
        colonnaNVoloPartenza.setCellValueFactory(cellData -> cellData.getValue().getCodiceProperty());
        colonnaGatePartenza.setCellValueFactory(cellData -> cellData.getValue().getGateProperty().asObject());
        colonnaCompagniaPartenza.setCellValueFactory(cellData -> cellData.getValue().getCompagniaProperty());
        colonnaStatoPartenza.setCellValueFactory(cellData -> cellData.getValue().getStatoProperty());


        colonnaOrarioTerra.setCellValueFactory(cellData -> cellData.getValue().getOraArrivoProperty());
        colonnaRitardoTerra.setCellValueFactory(cellData -> cellData.getValue().getRitardoProperty().asObject());
        colonnaDestinazioneTerra.setCellValueFactory(cellData -> cellData.getValue().getDestinazioneProperty());
        colonnaNVoloTerra.setCellValueFactory(cellData -> cellData.getValue().getCodiceProperty());
        colonnaGateTerra.setCellValueFactory(cellData -> cellData.getValue().getGateProperty().asObject());
        colonnaCompagniaTerra.setCellValueFactory(cellData -> cellData.getValue().getCompagniaProperty());
        colonnaStatoTerra.setCellValueFactory(cellData -> cellData.getValue().getStatoProperty());


        colonnaNVoloManutenzione.setCellValueFactory(cellData -> cellData.getValue().getCodiceProperty());
        colonnaCompagniaManutenzione.setCellValueFactory(cellData -> cellData.getValue().getCompagniaProperty());
        colonnaInizioLavoriManutenzione.setCellValueFactory(cellData -> cellData.getValue().getInizioManutenzione());
        colonnaFineLavoriManutenzione.setCellValueFactory(cellData -> cellData.getValue().getFineManutenzione());
        colonnaHangarManutenzione.setCellValueFactory(cellData -> cellData.getValue().getHangarProperty());

    
        tabellaArrivo.setItems(gestioneAerei.getElencoListaArrivi());
        tabellaPartenza.setItems(gestioneAerei.getElencoListaPartenze());
        tabellaTerra.setItems(gestioneAerei.getElencoListaTerra());
        tabellaManutenzione.setItems(gestioneAerei.getElencoListaManutenzione());

    }

    private void setupRowSelectionListener() {
        // Aggiunge un listener di selezione alla tabella degli arrivi
        tabellaArrivo.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleDoubleClick(newSelection);
            }
        });

        // Aggiunge un listener di selezione alla tabella delle partenze
        tabellaPartenza.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleDoubleClick(newSelection);
            }
        });

    }

    @FXML
    public void cercaAereiGiustoPartenze(String newValue, String compagnia) {
        changeDataPartenza();
        gestioneAerei.aggiornaPartenzaAdmin(newValue.toLowerCase(), compagnia.toLowerCase());
    }

    @FXML
    public void cercaAereiGiustoArrivi(String newValue, String compagnia) {
        changeDataArrivo();
        gestioneAerei.aggiornaArrivoAdmin(newValue.toLowerCase(), compagnia.toLowerCase());
    }

    @FXML
    public void cercaAereiGiustoTerra(String newValue, String compagnia) {
        changeDataTerra();
        gestioneAerei.aggiornaTerraAdmin(newValue.toLowerCase(),compagnia.toLowerCase());
    }

    @FXML
    public void cercaAereiGiustoManutenzione(String newValue, String compagnia) {
        changeDataManutenzione();
        gestioneAerei.aggiornaManutenzioneAdmin(newValue.toLowerCase(),compagnia.toLowerCase());
    }

    @FXML
    public void changeDataArrivo() {
        gestioneAerei.setDataArrivo(datePickerArrivo.getValue());
    }

    @FXML
    public void changeDataPartenza() {
        gestioneAerei.setDataPartenza(datePickerPartenze.getValue());
    }

    @FXML
    public void changeDataTerra() {
        gestioneAerei.setDataTerra(datePickerTerra.getValue());
    }

    @FXML
    public void changeDataManutenzione() {
        gestioneAerei.setDataManutenzione(datePickerManutenzione.getValue());
    }

    @FXML
    public void cancellaRicerca() {
        // Cancella tutti i filtri
        destFiltArrivi.clear();
        compFiltArrivi.clear();
        destFiltPartenze.clear();
        compFiltPartenze.clear();
        destFiltTerra.clear();
        compFiltTerra.clear();
        destFiltManutenzione.clear();
        compFiltManutenzione.clear();
        // azzera le i datepicker
        setDataArrivi(LocalDate.now());
        setDataPartenza(LocalDate.now());
        setDataTerra(LocalDate.now());
        setDataManutenzione(LocalDate.now());
    }

    private void handleDoubleClick(Aerei aereo) {
        if (infoStage != null && infoStage.isShowing()) {
            // Chiudi la finestra delle informazioni esistente se è già aperta
            infoStage.close();
        }

        try {
            // Carica la seconda GUI (FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/dettagliAereoAdmin.fxml"));
            Parent root = loader.load();
            infoStage = new Stage();
            infoStage.setTitle("Dettagli Aereo");
            infoStage.setScene(new Scene(root));

            // Passo l'aereo alla seconda GUI
            dettagliAereoAdminController controller = loader.getController();
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
    public void addAereo() {
        // Carica una schermata vuota per aggiungere un aereo
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/dettagliAereoAdmin.fxml"));
            Parent root = loader.load();
            infoStage = new Stage();
            infoStage.setTitle("Aggiungi Aereo");
            infoStage.setScene(new Scene(root));

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

    // Filtri
    /* -- tab partenze -- */
    // Listener che quando cambia il testo del campo di ricerca aggiorna la tabella
    
}
