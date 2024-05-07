package mainFolder.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import mainFolder.model.Aerei;

public class dettagliAereoAdminController {

    // Modificatori
    @FXML private ComboBox<String> modelloAereoCmbx;
    @FXML private TextField provenienzaTxF;
    @FXML private TextField destinazioneTxF;
    @FXML private ComboBox<String> terminalCmbx;
    @FXML private TextField gateTxF;
    @FXML private TextField compagniaAereaTxF;
    @FXML private TextField postiTotaliTxF;
    @FXML private TextField postiOccupatiTxF;
    @FXML private ComboBox<String> statoCmbx;
    @FXML private DatePicker giornoArrivoDp;
    @FXML private DatePicker giornoPartenzaDp;
    @FXML private TextField orarioArrivoTxF;
    @FXML private TextField orarioPartenzaTxF;
    @FXML private Spinner<Integer> ritardoSpn;
    // Se è in manutenzione
    @FXML private DatePicker inizioLavoriDp;
    @FXML private DatePicker fineLavoriDp;
    @FXML private TextField hangarTxf;

    @FXML private TextField nomeAereo;

    @FXML private Button salvaBtn;
    @FXML private Button modificaBtn;
    
    @FXML private Label orologio;


    public void setAereo(Aerei aereo) {
        nomeAereo.setText(aereo.getModello());
        modelloAereoCmbx.setValue(aereo.getModello());
        provenienzaTxF.setText(aereo.getProvenienzaString());
        postiTotaliTxF.setText(String.valueOf(aereo.getPostiMassimi()));
        giornoPartenzaDp.setValue(aereo.getGiornoPartenza());
        ritardoSpn.getValueFactory().setValue(aereo.getRitardoInt());
        compagniaAereaTxF.setText(aereo.getCompagnia());
        destinazioneTxF.setText(aereo.getDestinazioneString());
        gateTxF.setText(String.valueOf(aereo.getGateInt()));
        orarioArrivoTxF.setText(aereo.getOraArrivoString());
        giornoArrivoDp.setValue(aereo.getGiornoArrivo());
        orarioPartenzaTxF.setText(aereo.getOraPartenzaString());
        postiOccupatiTxF.setText(String.valueOf(aereo.getNumeroPostiOccupatiInt()));
        terminalCmbx.setValue(String.valueOf(aereo.getTerminalInt()));
        statoCmbx.setValue(aereo.getStato());
        inizioLavoriDp.setValue(aereo.getInizioLavori());
        fineLavoriDp.setValue(aereo.getFineLavori());
        hangarTxf.setText(aereo.getHangar());
    }

    @FXML
    private void initialize() {
        startClockUpdateAnimation();

        // Imposto i combobox
        // Modello aereo
        modelloAereoCmbx.getItems().addAll("Boeing 737", "Boeing 747", "Boeing 757", "Boeing 767", "Boeing 777", "Boeing 787", "Airbus A220", "Airbus A300", "Airbus A310", "Airbus A320", "Airbus A330", "Airbus A340", "Airbus A350", "Airbus A380");
        // Terminal
        terminalCmbx.getItems().addAll("1");
        // Stato
        statoCmbx.getItems().addAll("In arrivo", "In partenza", "In manutenzione", "In attesa");

        // Imposto Spinner
        ritardoSpn.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
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
            }
        };
        // Avvio l'animazione
        timer.start();
    }
}
