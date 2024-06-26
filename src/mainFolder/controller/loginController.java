package mainFolder.controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import mainFolder.model.GestioneUtenti;
import mainFolder.model.Utenti;
import mainFolder.salvataggioDati.LeggiDati;

public class loginController {

    @FXML private TextField inserisciMail;
    @FXML private PasswordField inserisciPassword;
    @FXML private TextField vediInserisciPassword;
    @FXML private Button btnHome;


    @FXML private CheckBox visualizzaPassword;

    @FXML private Hyperlink vaiRegistrati;
    @FXML private Hyperlink vaiResetPassword;

    @FXML private Label segnalaErrore;

    LeggiDati leggi;
    private int indice;

    GestioneUtenti gestioneUtenti = GestioneUtenti.getInstance();

    public void initialize() {
        vediInserisciPassword.setEditable(false);
        vediInserisciPassword.setVisible(false);

        inserisciPassword.setVisible(true);
        inserisciPassword.setEditable(true);

        gestioneUtenti.aggiornaLista();

        // Listener per il tasto ENTER su inserisciMail e inserisciPassword
        inserisciMail.setOnKeyPressed(this::handleKeyPress);
        inserisciPassword.setOnKeyPressed(this::handleKeyPress);
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            login();
        }
    }
    
    @FXML
    public void visualizza() {
        if (visualizzaPassword.isSelected()){
            inserisciPassword.setVisible(false);
            vediInserisciPassword.setVisible(true);
            vediInserisciPassword.setText(inserisciPassword.getText());
        }
        else if (visualizzaPassword.isSelected() == false){
            inserisciPassword.setVisible(true);
            vediInserisciPassword.setVisible(false);
        }
    }

    // Metodo per il tasto Home
    

    @FXML
    public void login() {
        ArrayList<Utenti> listaUtenti = new ArrayList<Utenti>();
        listaUtenti = gestioneUtenti.getUtenti();
        boolean utenteTtrovato = false;
        if (inserisciMail.getText().isEmpty() && inserisciPassword.getText().isEmpty()) {
        }else {
            //controllo per admin
            if (inserisciMail.getText().equals("admin") && inserisciPassword.getText().equals("admin")) {
                handleAdminPage();
            }

            //controllo per utenti
            for(int i = 0; i < listaUtenti.size(); i++) {
                if (listaUtenti.get(i).getMail().equals(inserisciMail.getText()) && 
                    listaUtenti.get(i).getPassword().equals(inserisciPassword.getText())) {
                    indice = i;
                    utenteTtrovato = true;
                }
            }
        }
        if (utenteTtrovato == false) {
            segnalaErrore.setText("mail o password errati");
        }
        if (utenteTtrovato == true) {
            gestioneUtenti.setLogin(indice);
            switch (gestioneUtenti.getSchermataPrecedente()) {
                case "Home":
                    handleBtnHome();
                    break;
                case "PrenotaPage":
                    prenotaPage();
                    break;
                case "UserMainPageA":
                    userMainPage(0);
                    break;
                case "UserMainPageP":
                    userMainPage(1);
                default:
                    break;
            }
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

    private void prenotaPage() {
        try {
            // Carica la seconda GUI (FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/prenotazioneGui.fxml"));
            Parent root = loader.load();
            prenotazioneController controller = loader.getController();
            controller.setLogged(true);
            Stage stage = new Stage();
            stage.setTitle("Prenotazione GUI");
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la prima GUI (Finestra)
            Stage primaryStage = (Stage) btnHome.getScene().getWindow();
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void userMainPage(int partenze) {
        try {
            // Carica la seconda GUI (FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/userMainGui.fxml"));
            Parent root = loader.load();
            // Mando un messaggio al controller della seconda GUI
            userMainController controller = loader.getController();
            // in base a quale button chiama il metodo userMainPage, passo il valore true o
            // false
            controller.setPartenzeSelected((partenze==1));

            Stage stage = new Stage();
            stage.setTitle("User Main GUI");
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
    private void handleRegistratiLink() {
        try {
            gestioneUtenti.setSchermataPrecedente(gestioneUtenti.getSchermataPrecedente());
            // Carica la seconda GUI (FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/registratiGui.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Registrati page");
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la prima GUI (Finestra)
            Stage primaryStage = (Stage) vaiRegistrati.getScene().getWindow();
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePasswChangeLink() {
        try {
            // Carica la seconda GUI (FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/passwChange.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Passw Change page");
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la prima GUI (Finestra)
            Stage primaryStage = (Stage) vaiRegistrati.getScene().getWindow();
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAdminPage() {
        try {
            // Carica la seconda GUI (FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/mainGui.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Admin page");
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la prima GUI (Finestra)
            Stage primaryStage = (Stage) vaiRegistrati.getScene().getWindow();
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
