package mainFolder.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import mainFolder.model.GestioneUtenti;
import mainFolder.model.Utenti;
import mainFolder.model.GestioneUtenti;

public class registratiController {
    
    @FXML private TextField inserimentoNome;
    @FXML private TextField inserimentoCognome;
    @FXML private PasswordField inserimentoPassword;
    @FXML private PasswordField ripetizionePassword;
    @FXML private TextField inserimentoMail;
    @FXML private TextField inserimentoTelefono;
    @FXML private TextField inserimentoNazione;
    @FXML private TextField inserimentoCitta;
    @FXML private TextField inserimentoIndirizzo;
    @FXML private TextField inserimentoCarta;
    @FXML private TextField inserimentoScadenza;

    @FXML private RadioButton acconsentoNormative;

    @FXML private Hyperlink vaiAccedi;

    @FXML private DatePicker selezionaData;

    private Boolean pieni;
    private Boolean passwordCorrette;

    private LocalDate dataSelezionata;

    


    GestioneUtenti gestioneUtenti = new GestioneUtenti();


    public void creaUtente(){

        //solo se l'utente acconsente alle normative
        if (acconsentoNormative.isSelected()){
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

  

    
}



