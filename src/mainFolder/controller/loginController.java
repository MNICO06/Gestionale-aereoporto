package mainFolder.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import mainFolder.salvataggioDati.LeggiDati;

public class loginController {

    @FXML private TextField inserisciMail;
    @FXML private PasswordField inserisciPassword;
    @FXML private TextField vediInserisciPassword;
    @FXML private Button btnHome;


    @FXML private CheckBox visualizzaPassword;

    @FXML private Hyperlink vaiRegistrati;

    LeggiDati leggi;

    public void initialize() {
        vediInserisciPassword.setEditable(false);
        vediInserisciPassword.setVisible(false);

        inserisciPassword.setVisible(true);
        inserisciPassword.setEditable(true);

        leggi = new LeggiDati();
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

    @FXML
    private void handleRegistratiLink() {
        try {
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
    public void login() {
        
    }
}
