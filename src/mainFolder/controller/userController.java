package mainFolder.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    // Imposto contatore delle immagini
    private int currentImageIndex = 0;
    // Lista delle immagini
    private String[] sfondi = { "foto1.jpg", "foto2.jpg", "foto3.jpg", "foto4.jpg", "foto5.jpg", "foto6.jpg" };
    // Durata della transizione
    private Duration transitionDuration = Duration.seconds(5);
    // Timeline per la transizione
    private Timeline timeline;

    // Inizializzazione
    public void initialize() {
        // Imposto un immagine iniziale
        Image backgroundImage = new Image("file:src/mainFolder/immagini/" + sfondi[0]);
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true));
        Background background = new Background(backgroundImg);
        // Assegno il background al layout
        rootLayout.setBackground(background);
        // Start background transition animation
        startBackgroundTransition();
        // Start clock update animation
        startClockUpdateAnimation();

        // Gestione toggle button
        partenzeTglBtn.setOnAction(event -> {
            if (partenzeTglBtn.isSelected()) {
                arriviTglBtn.setSelected(false);
            } else {
                arriviTglBtn.setSelected(true);
            }
        });

        arriviTglBtn.setOnAction(event -> {
            if (arriviTglBtn.isSelected()) {
                partenzeTglBtn.setSelected(false);
            }  else {
                partenzeTglBtn.setSelected(true);
            }
        });

        // Imposto come bottone selezionato di default quello delle partenze
        partenzeTglBtn.setSelected(true);
    }

    private void startBackgroundTransition() {
        // Creo un'immagine di sfondo in base all'indice corrente
        new Image("file:src/mainFolder/immagini/" + sfondi[currentImageIndex]);
        // Creo una nuova Timeline di KeyFrame
        timeline = new Timeline(new KeyFrame(transitionDuration, event -> {
            // Cambio l'immagine di sfondo
            currentImageIndex = (currentImageIndex + 1) % sfondi.length;
            // Imposto l'immagine di sfondo
            Image backgroundImage = new Image("file:src/mainFolder/immagini/" + sfondi[currentImageIndex]);
            BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true)); 
                    // Con F,F,F, true l'immagine si adatta alla finestra in larghezza e altezza
            Background background = new Background(backgroundImg);
            // Assegno il background al layout
            rootLayout.setBackground(background);
        }));
        // Imposto il ciclo della Timeline
        timeline.setCycleCount(Timeline.INDEFINITE);
        // Avvio la Timeline
        timeline.play();
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

    @FXML
    private void accediPage() {
        try {
            // Carica la seconda GUI (FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/loginGui.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Login GUI");
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la prima GUI (Finestra)
            Stage primaryStage = (Stage) accediBtn.getScene().getWindow();
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void userMainPage() {
        try {

            // Carica la seconda GUI (FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/userMainGui.fxml"));
            Parent root = loader.load();
            // Mando un messaggio al controller della seconda GUI
            userMainController controller = loader.getController();
            // in base a quale button chiama il metodo userMainPage, passo il valore true o false
            controller.setPartenzeSelected(partenzeBtn.isFocused() && !arriviBtn.isFocused());

            Stage stage = new Stage();
            stage.setTitle("User Main GUI");
            stage.setScene(new Scene(root));
            stage.show();


            // Chiudi la prima GUI (Finestra)
            Stage primaryStage = (Stage) accediBtn.getScene().getWindow();
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void prenotaPage(){
        System.out.println("Prenota");        
        try {
            // Carica la seconda GUI (FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/prenotazioneGui.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Prenotazione GUI");
            stage.setScene(new Scene(root));
            stage.show();
            
            // Chiudi la prima GUI (Finestra)
            Stage primaryStage = (Stage) prenotaBtn.getScene().getWindow();
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cercaPage() {
        System.out.println("Cerca");
        // Chiamo un metodo che convalidi i dati inseriti prima di aprire la schermata di ricerca
        if (controlloRicerca()) {
            try {
                // Carica la seconda GUI (FXML)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/userMainGui.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("User Main GUI");
                stage.setScene(new Scene(root));
                stage.show();

                // Mando un messaggio al controller della seconda GUI
                userMainController controller = loader.getController();
                // in base a quale toggle è selezionato, passo il valore true o false
                controller.setPartenzeSelected(partenzeTglBtn.isSelected());
                // Altro controllo
                if(controlloRicerca()){
                    // Passo anche i valori da cercare e la data
                    controller.setCercaTxt(cercaTxt.getText());
                    controller.setDataPartenze(dataPartenze.getValue());
                }
                // Chiudi la prima GUI (Finestra)
                Stage primaryStage = (Stage) cercaBtn.getScene().getWindow();
                primaryStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private boolean controlloRicerca(){
        // Controllo che la data sia stata inserita
        if (dataPartenze.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Data non inserita");
            alert.setContentText("Inserire una data per la ricerca");
            alert.showAndWait();
            return false;
        }
        // Controllo che il campo di ricerca non sia vuoto
        if (cercaTxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Campo di ricerca vuoto");
            alert.setContentText("Inserire un valore da cercare");
            alert.showAndWait();
            return false;
        }

        return true;
    }
}
