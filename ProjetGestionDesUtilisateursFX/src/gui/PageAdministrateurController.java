/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author MMD
 */
public class PageAdministrateurController implements Initializable {

    @FXML
    private Label admin_connecte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    void setAdmin_connecte(String pseudo_admin) {
        admin_connecte.setText("Administrateur connecté : "+pseudo_admin);
    }
    
    
    
    
}
