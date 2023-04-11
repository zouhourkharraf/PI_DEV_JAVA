/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MMD
 */
public class LoginController implements Initializable {

    @FXML
    private Button bouton_login;
    @FXML
    private TextField champ_pseudo;
    @FXML
    private PasswordField champ_mp;
    @FXML
    private Button bouton_incsi_eleve;
    @FXML
    private Button bouton_inscri_enseignant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SeConnecter(ActionEvent event) {
           try {
          /*
               FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterPersonne.fxml"));
            Parent root = loader.load();
            AjouterPersonneController controller = loader.getController();
            controller.setUsername(usernameTF.getText());
            usernameTF.getScene().setRoot(root);*/
          if(champ_pseudo.getText().compareTo("admin")==0)
          {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PageAdministrateur.fxml"));  
                Parent root = loader.load(); //Un container
                champ_pseudo.getScene().setRoot(root);
          }
          else
          {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("PageUtilisateur.fxml"));
                Parent root = loader.load(); //Un container
                champ_pseudo.getScene().setRoot(root);
          }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void CreerCompteEleve(ActionEvent event) {
       
        try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEleve.fxml"));  
                Parent root = loader.load(); //Un container
                champ_pseudo.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        }

    @FXML
    private void CreerCompteEnseignant(ActionEvent event) {
          
        try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEnseignant.fxml"));  
                Parent root = loader.load(); //Un container
                champ_pseudo.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
