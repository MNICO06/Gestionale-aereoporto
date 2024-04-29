package mainFolder.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import mainFolder.model.Aerei;

public class dettagliAereoController {
    // Modello, Provenienza, Posti Totali, Terminal, Giorno Arrivo, Giorno Partenza, 
    //Ritardo, Compagnia Aerea, Destinazione, Posti Disponibili, Gate, Orario Arrivo, Orario Partenza
    @FXML private Label nomeAereo;

    @FXML private Label lblModello;
    @FXML private Label lblProvenienza;
    @FXML private Label lblPostiTotali;
    @FXML private Label lblTerminal;
    @FXML private Label lblGiornoArrivo;
    @FXML private Label lblGiornoPartenza;
    @FXML private Label lblRitardo;
    @FXML private Label lblCompagniaAerea;
    @FXML private Label lblDestinazione;
    @FXML private Label lblPostiOccupati;
    @FXML private Label lblGate;
    @FXML private Label lblOrarioArrivo;
    @FXML private Label lblOrarioPartenza;

    @FXML private Label orologio;

    @FXML private AnchorPane anchImage;

    private Aerei aereo;

    public void setAereo(Aerei aereo) {
        this.aereo = aereo;

        lblModello.setText(aereo.getModello());
        lblProvenienza.setText(aereo.getProvenienzaString());
        lblPostiTotali.setText(String.valueOf(aereo.getPostiMassimi()));
        lblGiornoPartenza.setText(aereo.getGiornoPartenzaString());
        lblRitardo.setText(String.valueOf(aereo.getRitardoInt()));
        lblCompagniaAerea.setText(aereo.getCompagnia());
        lblDestinazione.setText(aereo.getDestinazioneString());
        lblGate.setText(String.valueOf(aereo.getGateInt()));
        lblOrarioArrivo.setText(aereo.getOraArrivoString());

        //// NON ANCORA IMPLEMENTATI
        /* 
        //lblPostiOccupati.setText(String.valueOf(aereo.getNumeroPostiOccupatiInt()));
        //lblTerminal.setText(String.valueOf(aereo.getTerminalInt()));
        //lblGiornoArrivo.setText(aereo.getGiornoArrivoString());
        //lblOrarioPartenza.setText(aereo.getOraPartenzaString());
        */
    }

    // Initialize method
    @FXML
    private void initialize() {
        startClockUpdateAnimation();
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