package mainFolder.controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainFolder.model.GestioneUtenti;
import mainFolder.model.Utenti;

public class passChangeController {
    @FXML private TextField inserisciMail;
    @FXML private PasswordField inserisciPassword;
    @FXML private PasswordField confermaNuovaPassword;
    
    @FXML private Button cambiPassword;
    @FXML private Button btnHome;

    GestioneUtenti gestioneUtenti = GestioneUtenti.getInstance();
    private int indice;


    public void initialize() {
        gestioneUtenti.aggiornaLista();
    }

    @FXML
    private void changePass() {
        if (checkFull() && confrontaPassword())
            cambiaPassword();
    }

    private boolean checkFull() {
        
        boolean pieni = true;
        if (inserisciMail.getText().isEmpty()) {
            pieni = false;
        }else if (inserisciPassword.getText().isEmpty()) {
            pieni = false;
        }else if (confermaNuovaPassword.getText().isEmpty()) {
            pieni = false;
        }
        return pieni;
    }

    private boolean confrontaPassword() {
        boolean passwordCorrette = false;
        if (inserisciPassword.getText().equals(confermaNuovaPassword.getText())){
            passwordCorrette = true;
        }
        else{
            passwordCorrette = false;
        }
        return passwordCorrette;
    }

    private void cambiaPassword() {
        ArrayList<Utenti> listaUtenti = new ArrayList<Utenti>();
        listaUtenti = gestioneUtenti.getUtenti();
        boolean utenteTtrovato = false;
        for(int i = 0; i < listaUtenti.size(); i++) {
            if (listaUtenti.get(i).getMail().equals(inserisciMail.getText())) {
                indice = i;
                utenteTtrovato = true;
            }
        }
        if (utenteTtrovato == true) {
            gestioneUtenti.getUtenti().get(indice).setPassword(inserisciPassword.getText());
            gestioneUtenti.scriviUtenti();
            gestioneUtenti.setLogin(indice);
            handleBtnHome();
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


}
