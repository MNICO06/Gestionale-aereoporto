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
    @FXML private Label lblDestinazione;
    @FXML private Label lblCompagniaAerea;
    // Codice Registrazione
    @FXML private Label lblGiornoArrivo;
    @FXML private Label lblOrarioArrivo;
    @FXML private Label lblGiornoPartenza;
    @FXML private Label lblOrarioPartenza;
    @FXML private Label lblPostiTotali;
    @FXML private Label lblRitardo;
    @FXML private Label lblGate;

    @FXML private Label lblTerminal;
    @FXML private Label lblPostiOccupati;

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
        lblGiornoArrivo.setText(aereo.getGiornoArrivoString());
        lblOrarioPartenza.setText(aereo.getOraPartenzaString());
        lblPostiOccupati.setText(String.valueOf(aereo.getNumeroPostiOccupatiInt()));
        lblTerminal.setText(String.valueOf(aereo.getTerminalInt()));

        // Chiamo metodo che imposta l'immagine dell'aereo corretto
        setImage(aereo.getModello(), aereo.getCompagnia());
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

    private void setImage(String modello, String compagnia) {
        String temp = "-fx-background-size: contain; -fx-background-repeat: no-repeat; -fx-background-position: center";
        switch (modello+compagnia) {
            case "Airbus A380Quatar Airways":
                // Imposta l'immagine dell'aereo
                anchImage.setStyle("-fx-background-image: url('mainFolder/immagini/A380Quatar.png'); " + temp);
                break;
            case "Airbus A320American Airlines":
                anchImage.setStyle("-fx-background-image: url('mainFolder/immagini/A320AmericanAirlines.jpg');" + temp);
                break;
            case "Boeing 787 DreamlinerQantas":
                anchImage.setStyle("-fx-background-image: url('mainFolder/immagini/B787Qantas.jpeg'); "+ temp);
                break;
            case "Airbus A350Singapore Airlines":
                anchImage.setStyle("-fx-background-image: url('mainFolder/immagini/A350SingaporeAirlines.jpg');" + temp);
                break;
            case "Boeing 737Delta Air Lines":
                anchImage.setStyle("-fx-background-image: url('mainFolder/immagini/B737Delta.jpg');" + temp);
                break;
            case "Airbus A330Emirates":
                anchImage.setStyle("-fx-background-image: url('mainFolder/immagini/A330Emirates.jpg');" + temp);
                break;
            case "Boeing 777Korean Air":
                anchImage.setStyle("-fx-background-image: url('mainFolder/immagini/B777KoreanAir.jpg');" + temp);
                break;
            case "Airbus A380Virgin Atlantic":
                anchImage.setStyle("-fx-background-image: url('mainFolder/immagini/A380VirginAtlantic.jpg');" + temp);
                break;
            case "Boeing 757United Airlines":
                anchImage.setStyle("-fx-background-image: url('mainFolder/immagini/B757United.jpg');" + temp);
                break;
            case "Boeing 747KLM":
                anchImage.setStyle("-fx-background-image: url('mainFolder/immagini/B747KLM.jpg');" + temp);
                break;

            default:
                break;
        }
    }
}