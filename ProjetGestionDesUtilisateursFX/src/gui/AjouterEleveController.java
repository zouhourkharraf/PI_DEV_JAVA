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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author MMD
 */
public class AjouterEleveController implements Initializable {

    @FXML
    private Button bouton_retour;
    @FXML
    private Button bouton_ajouter_eleve;
    @FXML
    private Button bouton_annuler_eleve;
    @FXML
    private Label erreur_nom;
    @FXML
    private Label erreur_prenom;
    @FXML
    private Label erreur_age;
    @FXML
    private Label erreur_globale;
    @FXML
    private Label erreur_email;
    @FXML
    private Label erreur_mp;
    @FXML
    private Label erreur_confirmation_mp;
    @FXML
    private TextField champ_nom;
    @FXML
    private TextField champ_prenom;
    @FXML
    private TextField champ_age;
    @FXML
    private TextField champ_email;
    @FXML
    private RadioButton radio_genre_homme;
    @FXML
    private RadioButton radio_genre_femme;
    @FXML
    private PasswordField champ_mp;
    @FXML
    private PasswordField champ_confirm_mp;
    @FXML
    private ToggleGroup genre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load(); ///le container
             bouton_retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void AjouterEleve(ActionEvent event) {
        boolean valid=true; 
        //les contrôles de saisie
   /*     if( (champ_nom.getText().isEmpty()) || ( champ_prenom.getText().isEmpty() ) || ( champ_age.getText().isEmpty() ) || ( champ_email.getText().isEmpty() )||( champ_mp.getText().isEmpty() )|| ( champ_confirm_mp.getText().isEmpty() ) )
        {
        erreur_globale.setText("Veuillez renseigner tous les champs !!!");
        valid=false;
        
        }
        else
        {  */
         erreur_globale.setText("");   
         
         //le nom
          if( champ_nom.getText().matches(".*(\\d)+.*") ) 
          {
             erreur_nom.setText("le nom ne doit pas contenir des chiffres");
             valid=false;
          }
          else
          {  erreur_nom.setText(""); }
          
          //le prénom
          if(champ_prenom.getText().matches(".*(\\d)+.*"))
          {  
              erreur_prenom.setText("le prénom ne doit pas contenir des chiffres"); 
             valid=false;
          }
           else
          {  erreur_prenom.setText(""); }
          
          // nom et  prénom : tester si le nom et le prénom sont identique
            if(champ_prenom.getText().compareTo(champ_nom.getText())==0)
          {
                 erreur_prenom.setText("le nom et le prénom sont identiques !!!");
                 valid=false;
          }
            else
          {  erreur_prenom.setText(""); }
          
           //l'age
          if(!champ_age.getText().matches("(\\d)+"))
          {
                 erreur_age.setText("Valeur invalide !!");
                 valid=false;
          }
          else
          { if(Integer.parseInt(champ_age.getText()) < 5)
                 {  erreur_age.setText("Vous devez avoir au moins 5 ans"); }
               else
                 {  erreur_age.setText(""); } 
          }
         
          
           
       //  }  
        
        
    }

    @FXML
    private void AnnulerEleve(ActionEvent event) {
        champ_nom.clear();
        champ_prenom.clear();
        champ_age.clear();
        champ_email.clear();
        champ_mp.clear();
        champ_confirm_mp.clear();
        
    }
    
}
