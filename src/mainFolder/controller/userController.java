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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import mainFolder.model.GestioneAerei;
import mainFolder.model.GestioneUtenti;
import javafx.scene.control.Alert;

public class userController {

    @FXML private StackPane stackPane;

    @FXML private BorderPane rootLayout;
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

    @FXML private BorderPane infoPage;
    @FXML private ImageView infoImage;
    @FXML private Label orologio1;
    @FXML private Button accedBtn1;
    @FXML private Button homeBtn1;
    @FXML private TextArea infoTxt;

    // Imposto contatore delle immagini
    private int currentImageIndex = 0;
    // Lista delle immagini
    private String[] sfondi = { "foto1.jpg", "foto2.jpg", "foto3.jpg", "foto4.jpg", "foto5.jpg", "foto6.jpg" };
    // Durata della transizione
    private Duration transitionDuration = Duration.seconds(5);
    // Timeline per la transizione
    private Timeline timeline;

    //per tenere controllato se si è loggati
    GestioneUtenti gestioneUtenti = GestioneUtenti.getInstance();
    GestioneAerei gestioneAerei = GestioneAerei.getInstance();
    
    // Inizializzazione
    public void initialize() {
        // Rimuovo i figli dello stackPane
        stackPane.getChildren().clear();
        // Aggiungo il layout principale
        stackPane.getChildren().add(rootLayout);

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

        checkLogin(); 
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
                orologio1.setText(String.format("%02d:%02d:%02d",
                        java.time.LocalTime.now().getHour(),
                        java.time.LocalTime.now().getMinute(),
                        java.time.LocalTime.now().getSecond()));
            }
        };
        // Avvio l'animazione
        timer.start();
    }

    @FXML
    private void accediPage1() {
        try {
            gestioneUtenti.setSchermataPrecedente("Home");
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
    private void accediPage2() {
        try {
            gestioneUtenti.setSchermataPrecedente("Home");
            // Carica la seconda GUI (FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/loginGui.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Login GUI");
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la prima GUI (Finestra)
            Stage primaryStage = (Stage) accedBtn1.getScene().getWindow();
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
        String css = getClass().getResource("../cssFolder/alert.css").toExternalForm();
        // Controllo che la data sia stata inserita
        if (dataPartenze.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Data non inserita");
            alert.setContentText("Inserire una data per la ricerca");
            alert.getDialogPane().getScene().getStylesheets().add(css);
            alert.showAndWait();
            return false;
        }
        // Controllo che il campo di ricerca non sia vuoto
        if (cercaTxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Campo di ricerca vuoto");
            alert.setContentText("Inserire un valore da cercare");
            alert.getDialogPane().getScene().getStylesheets().add(css);
            alert.showAndWait();
            return false;
        }

        return true;
    }

    private void checkLogin() {
        if (gestioneUtenti.isLogged()) {
            accediBtn.setText(gestioneUtenti.getUtenti().get(gestioneUtenti.getIndice()).getNome());
            accediBtn.setDisable(true);
            accediBtn.setOpacity(1);
        }
    }

    @FXML
    private void infoPage() {
        // Rimuovo i figli dello stackPane
        stackPane.getChildren().clear();
        // Aggiungo il layout principale della infoPage
        stackPane.getChildren().add(infoPage);

        // Inizializzo il cambio dell'immagine automatico
        startImageTransition();
        // Inizializzo l'orologio
        startClockUpdateAnimation();
    }

    @FXML
    private void homePage() {
        // Rimuovo i figli dello stackPane
        stackPane.getChildren().clear();
        // Aggiungo il layout principale
        stackPane.getChildren().add(rootLayout);
    }

    private void startImageTransition() {
        // Cambio info image ogni 10 secondi
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            // Random da 0 a 4
            int i = (int) (Math.random() * 4 + 1);
            Image img = new Image("file:src/mainFolder/immagini/Areoporto" + i + ".jpeg");
            // Imposto la nuova immagine sull'ImageView
            infoImage.setImage(img);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
    