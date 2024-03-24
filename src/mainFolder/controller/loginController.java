package mainFolder.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginController {

    @FXML private TextField inserisciMail;
    @FXML private PasswordField inserisciPassword;
    @FXML private TextField vediInserisciPassword;


    @FXML private CheckBox visualizzaPassword;

    @FXML private Hyperlink vaiRegistrati;

    public void initialize() {
        vediInserisciPassword.setEditable(false);
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

}
