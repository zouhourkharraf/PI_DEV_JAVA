/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MMD
 */
public class VerfierCodeSecretController implements Initializable {

    @FXML
    private Button bouton_confirmer_code;
    @FXML
    private TextField champ_code_secret;
    @FXML
    private Label erreur_globale;
    @FXML
    private Button bouton_retour;
    @FXML
    private Label titre_information;
    @FXML
    private Button bouton_annuler_code;

    int code_secret_envoye;
    Utilisateur utilisareur_a_modifier;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ConfirmerCode(ActionEvent event) {
        
        if(champ_code_secret.getText().compareTo( String.valueOf(code_secret_envoye) ) !=0) //si le code saisi ne correspond pas au code envoyé à l'utilisateur
        {
          erreur_globale.setText("Code invalide !");
        }
        else
        {
                  try{
                
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierMotDePasse.fxml")); 
                   Parent root = loader.load(); //Un container
                   ModifierMotDePasseController modifier_mot_de_passe_controller=loader.getController();
                   modifier_mot_de_passe_controller.setUtilisateur(utilisareur_a_modifier);
                   champ_code_secret.getScene().setRoot(root);
                    } catch (IOException ex) 
                    { System.out.println(ex.getMessage());}  
            
            
        }
        
    }

    @FXML
    private void RetourVersLogin(ActionEvent event) {
             try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load(); ///le container
             bouton_retour.getScene().setRoot(root);
      
             
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AnnulerCode(ActionEvent event) {
        champ_code_secret.setText("");
        erreur_globale.setText("");
    }
    
    public void reccuperer_information(Utilisateur utilisateur1,int code_secret)
    {
    utilisareur_a_modifier=utilisateur1;
    code_secret_envoye=code_secret;
    
    //afficher un message 
    titre_information.setText("Un code est envoyé à l'adresse suivante : "+utilisateur1.getEmail_util());
    
    }
    
}
