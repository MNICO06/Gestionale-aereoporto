package mainFolder.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainFolder.model.GestioneUtenti;

public class registratiController {
    
    @FXML private TextField inserimentoNome;
    @FXML private TextField inserimentoCognome;
    @FXML private TextField inserimentoMail;
    @FXML private PasswordField inserimentoPassword;
    @FXML private PasswordField ripetizionePassword;
    @FXML private TextField inserimentoTelefono;
    @FXML private TextField inserimentoNazione;
    @FXML private TextField inserimentoCitta;
    @FXML private TextField inserimentoIndirizzo;
    @FXML private TextField inserimentoCarta;
    @FXML private TextField inserimentoScadenza;

    @FXML private RadioButton acconsenteNormative;

    @FXML private Hyperlink vaiAccedi;

    @FXML private DatePicker selezionaData;

    private Boolean pieni;
    private Boolean passwordCorrette;

    private LocalDate dataSelezionata;

    GestioneUtenti gestioneUtenti = GestioneUtenti.getInstance();


    public void creaUtente(){
        //solo se l'utente acconsente alle normative
        if (acconsenteNormative.isSelected()){
            if (sonoPieni() && confrontaPassword()){

                gestioneUtenti.addUtenti(inserimentoNome.getText(), inserimentoCognome.getText(), inserimentoMail.getText(),
                convertiData(), inserimentoPassword.getText(), inserimentoTelefono.getText(), inserimentoNazione.getText(),
                inserimentoCitta.getText(), inserimentoIndirizzo.getText(), inserimentoCarta.getText(), inserimentoScadenza.getText());
            }
        }
    }



    public boolean sonoPieni(){
        if (inserimentoNome.getText().isEmpty()){
            pieni = false;
        }
        else if (inserimentoCognome.getText().isEmpty()){
            pieni = false;
        }
        else if (inserimentoMail.getText().isEmpty()){
            pieni = false;
        }
        else if (selezionaData.getValue() == null){
            pieni = false;
        }
        else if (inserimentoPassword.getText().isEmpty()){
            pieni = false;
        }
        else if (ripetizionePassword.getText().isEmpty()){
            pieni = false;
        }
        else if (inserimentoTelefono.getText().isEmpty()){
            pieni = false;
        }
        else if (inserimentoNazione.getText().isEmpty()){
            pieni = false;
        }
        else if (inserimentoCitta.getText().isEmpty()){
            pieni = false;
        }
        else if (inserimentoIndirizzo.getText().isEmpty()){
            pieni = false;
        }
        else if (inserimentoCarta.getText().isEmpty()){
            pieni = false;
        }
        else if (inserimentoScadenza.getText().isEmpty()){
            pieni = false;
        }
        else {
            pieni = true;
        }

        return pieni;
    }

    public boolean confrontaPassword() {
        if (inserimentoPassword.getText().equals(ripetizionePassword.getText())){
            passwordCorrette = true;
        }
        else{
            passwordCorrette = false;
        }
        return passwordCorrette;
    }

    private String convertiData(){
        dataSelezionata = selezionaData.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormattata = dataSelezionata.format(formatter);
        return dataFormattata;
    }

    
    @FXML
    private void handleAccedi() {
        try {
            // Carica la seconda GUI (FXML)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../guiFolder/loginGui.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Accedi page");
            stage.setScene(new Scene(root));
            stage.show();

            // Chiudi la prima GUI (Finestra)
            Stage primaryStage = (Stage) vaiAccedi.getScene().getWindow();
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  

    
}



