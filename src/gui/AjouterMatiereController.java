/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.matiere;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.MatiereService;

/**
 * FXML Controller class
 *
 * @author Skand
 */

public class AjouterMatiereController implements Initializable {

    @FXML
    private TextField nomTF;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterMatiere(ActionEvent event) throws IOException {
        String nom = nomTF.getText();
        matiere m = new matiere(nom);
        MatiereService ms = new MatiereService();
        try {
            ms.ajouter(m);
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherMatiere.fxml"));
            Parent root = loader.load();
            
            nomTF.getScene().setRoot(root);
       
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  

}
