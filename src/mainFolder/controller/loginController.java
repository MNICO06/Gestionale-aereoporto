package mainFolder.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginController {

    @FXML private TextField inserisciMail;
    @FXML private PasswordField inserisciPassword;


    @FXML private CheckBox visualizzaPassword;

    @FXML private Hyperlink vaiRegistrati;

    
    @FXML
    public void visualizza() {
        if (visualizzaPassword.isSelected()){
            
        }
        else if (visualizzaPassword.isSelected() == false){
            inserisciPassword.setVisible(false);
        }
    }

}
