package mainFolder.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import mainFolder.model.Aerei;

public class dettagliAereoAdminController {
    @FXML private Label nomeAereo;

    @FXML private Label txtfModello;
    @FXML private Label txtfProvenienza;
    @FXML private Label txtfDestinazione;
    @FXML private Label txtfCompagniaAerea;
    // Codice Registrazione
    @FXML private Label txtfGiornoArrivo;
    @FXML private Label txtfOrarioArrivo;
    @FXML private Label txtfGiornoPartenza;
    @FXML private Label txtfOrarioPartenza;
    @FXML private Label txtfPostiTotali;
    @FXML private Label txtfRitardo;
    @FXML private Label txtfGate;

    @FXML private Label txtfTerminal;
    @FXML private Label txtfPostiOccupati;

    @FXML private Label orologio;

    @FXML private AnchorPane anchImage;


    public void setAereo(Aerei aereo) {
        nomeAereo.setText(aereo.getModello());
        txtfModello.setText(aereo.getModello());
        txtfProvenienza.setText(aereo.getProvenienzaString());
        txtfPostiTotali.setText(String.valueOf(aereo.getPostiMassimi()));
        txtfGiornoPartenza.setText(aereo.getGiornoPartenzaString());
        txtfRitardo.setText(String.valueOf(aereo.getRitardoInt()));
        txtfCompagniaAerea.setText(aereo.getCompagnia());
        txtfDestinazione.setText(aereo.getDestinazioneString());
        txtfGate.setText(String.valueOf(aereo.getGateInt()));
        txtfOrarioArrivo.setText(aereo.getOraArrivoString());
        txtfGiornoArrivo.setText(aereo.getGiornoArrivoString());
        txtfOrarioPartenza.setText(aereo.getOraPartenzaString());
        txtfPostiOccupati.setText(String.valueOf(aereo.getNumeroPostiOccupatiInt()));
        txtfTerminal.setText(String.valueOf(aereo.getTerminalInt()));

        // Chiamo metodo che imposta l'immagine dell'aereo corretto
        setImage(aereo.getModello(), aereo.getCompagnia());
    }

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
