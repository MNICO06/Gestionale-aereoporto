package mainFolder.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class userController {
    
    @FXML private Label orologio;
    @FXML private Button accediBtn;
    @FXML private Button cercaBtn;
    @FXML private Button prenotaBtn;
    @FXML private Button partenzeBtn;
    @FXML private Button arriviBtn;
    @FXML private Button infoBtn;
    @FXML private ToggleButton partenzeTglBtn;
    @FXML private ToggleButton arriviTglBtn;
    @FXML private DatePicker dataPartenze;
    @FXML private TextField cercaTxt;
    @FXML private BorderPane rootLayout;

    // Array di immagini per lo sfondo
    private String[] sfondi = {"../immagini/foto1.jpg", "../immagini/foto2.jpg", "../immagini/foto3.jpg", "../immagini/foto4.jpg", "../immagini/foto5.jpg"};
    private int count = 0; // Contatore per tenere traccia del cambio dello sfondo
    // Inizializzazione
    private final int SECONDS_TO_CHANGE_BACKGROUND = 5;
    private long lastBackgroundChangeTime = 0;

    public void initialize() {
        // Inizializzo l'immagine di sfondo
        rootLayout.setStyle("-fx-background-image: url('');");

        // Animation timer
        AnimationTimer timer = new AnimationTimer() {
            // Imposto la label orologio con l'orario attuale
            @Override
            public void handle(long now) {
                // Deve avere questo formato: ore:minuti:secondi
                orologio.setText(String.format("%02d:%02d:%02d",
                        java.time.LocalTime.now().getHour(),
                        java.time.LocalTime.now().getMinute(),
                        java.time.LocalTime.now().getSecond()));

                // Dentro il timer
                long currentTime = System.nanoTime();
                /*if (currentTime - lastBackgroundChangeTime >= SECONDS_TO_CHANGE_BACKGROUND * 1_000_000_000L) {
                    System.out.println("La foto che ho scelto è" + sfondi[count % sfondi.length] );
                    rootLayout.setStyle("-fx-background-image: url('" + sfondi[count % sfondi.length] + "');");
                    count++;
                    lastBackgroundChangeTime = currentTime;
                }*/
            }
        };

        timer.start();
    }

}
