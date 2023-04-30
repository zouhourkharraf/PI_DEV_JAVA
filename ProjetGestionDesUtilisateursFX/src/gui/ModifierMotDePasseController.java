/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.mindrot.jbcrypt.BCrypt;
import services.ServicesUtilisateur;

/**
 * FXML Controller class
 *
 * @author MMD
 */
public class ModifierMotDePasseController implements Initializable {

    @FXML
    private PasswordField champ_mp;
    @FXML
    private PasswordField champ_confirm_mp;
    @FXML
    private Label erreur_globale;
    @FXML
    private Label erreur_mp;
    @FXML
    private Label erreur_confirmation_mp;
    @FXML
    private Button bouton_confirmer_mp;
    @FXML
    private Button bouton_annuler;
    
    Utilisateur utilisateur_a_modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ConfirmerMotDePasse(ActionEvent event) {
        
        Boolean valid=true;
        //les contrôles de saisie
        //le mot de passe
        if( ( champ_mp.getText().isEmpty() )&&( champ_confirm_mp.getText().isEmpty() ) )
        {
        erreur_globale.setText("Les champs sont vides !!!"); 
        valid=false;
        }
        else
        {
        erreur_globale.setText("");   
           if( !champ_mp.getText().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}"))
          {  
              erreur_mp.setText("le mot de passe doit comporter au moins 8 caractères avec au moins une lettre majuscule lettre miniscule, et un chiffre"); 
             valid=false;
          }
           else
          {  erreur_mp.setText(""); } 
         //La confirmation du mot de passe 
                if(champ_mp.getText().compareTo(champ_confirm_mp.getText())!= 0 )
          {
                 erreur_confirmation_mp.setText("Les mots de passe ne sont pas identiques !!!");
                 valid=false;
          }
            else
          {  erreur_confirmation_mp.setText(""); }  
        
        }
        if(valid)
        {
          ServicesUtilisateur util_service= new ServicesUtilisateur(); //appel de la classe service
           //faire le hachage du mot de passe avec l'algorithme bcrypt  
           String mp_hache=BCrypt.hashpw( champ_mp.getText() , BCrypt.gensalt(13)); 
          utilisateur_a_modifier.setMot_de_passe_util(mp_hache);
          try{
              util_service.modifier_utilisateur(utilisateur_a_modifier);
              RetourVersPageLogin();
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Magic Book | Récupération du compte");
                        alert.setHeaderText("Notification !");
                        alert.setContentText("Votre mot de passe a été modifié avec succès ");
                        alert.show();
              
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
        
        
        
    }

    @FXML
    private void BoutonAnnuler(ActionEvent event) {
        champ_mp.setText("");
        champ_confirm_mp.setText("");
        
    }
    
    public void setUtilisateur(Utilisateur utilisateur_reccupere)
    {
    utilisateur_a_modifier=utilisateur_reccupere;
        System.out.println(utilisateur_a_modifier.toString());
    }
    
    private void RetourVersPageLogin()
    {
         try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load(); ///le container
            champ_mp.getScene().setRoot(root);
             
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
}
