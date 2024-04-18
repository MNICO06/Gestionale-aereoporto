package mainFolder.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import mainFolder.model.Aerei;

public class dettagliAereoController {
    // Modello, Provenienza, Posti Totali, Terminal, Giorno Arrivo, Giorno Partenza, 
    //Ritardo, Compagnia Aerea, Destinazione, Posti Disponibili, Gate, Orario Arrivo, Orario Partenza
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

    @FXML private AnchorPane anchImage;

    private Aerei aereo;

    public void setAereo(Aerei aereo) {
        this.aereo = aereo;

        lblModello.setText(aereo.getModello());
        lblProvenienza.setText(aereo.getProvenienzaString());
        lblPostiTotali.setText(String.valueOf(aereo.getPostiMassimi()));
        lblTerminal.setText(String.valueOf(aereo.getTerminalInt()));
        lblGiornoArrivo.setText(aereo.getGiornoArrivoString());
        lblGiornoPartenza.setText(aereo.getGiornoPartenzaString());
        lblRitardo.setText(String.valueOf(aereo.getRitardoInt()));
        lblCompagniaAerea.setText(aereo.getCompagnia());
        lblDestinazione.setText(aereo.getDestinazioneString());
        lblPostiOccupati.setText(String.valueOf(aereo.getNumeroPostiOccupatiInt()));
        lblGate.setText(String.valueOf(aereo.getGateInt()));
        lblOrarioArrivo.setText(aereo.getOraArrivoString());
        lblOrarioPartenza.setText(aereo.getOraPartenzaString());
    }
}
