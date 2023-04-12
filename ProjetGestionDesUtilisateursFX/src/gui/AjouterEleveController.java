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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import services.ServicesUtilisateur;

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
    @FXML
    private Label ErreurPrenom;
    @FXML
    private Label ErreurNomPrenom;

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
            ServicesUtilisateur util_service= new ServicesUtilisateur(); //appel de la classe service
        
        //les contrôles de saisie
        if( (champ_nom.getText().isEmpty()) && ( champ_prenom.getText().isEmpty() )&&( champ_age.getText().isEmpty() )&&( champ_email.getText().isEmpty() )&&( champ_mp.getText().isEmpty() )&&( champ_confirm_mp.getText().isEmpty() ) )
        {
        erreur_globale.setText("Les champs sont vides !!!");
        valid=false;
        
        }
        else
        {  
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
              ErreurPrenom.setText("le prénom ne doit pas contenir des chiffres"); 
             valid=false;
          }
           else
          {  ErreurPrenom.setText(""); }
            if(champ_prenom.getText().compareTo(champ_nom.getText())==0)
          {
                 ErreurNomPrenom.setText("le nom et le prénom sont identiques !!!");
                 valid=false;
          }
            else
          {  ErreurNomPrenom.setText(""); }  
          
           //l'age
          if(!champ_age.getText().matches("(\\d)+"))
          {
                 erreur_age.setText("Valeur invalide !!");
                 valid=false;
          }
          else
          { 
              if(Integer.parseInt(champ_age.getText()) < 5)
               {  erreur_age.setText("Vous devez avoir au moins 5 ans pour créer un compte élève"); 
                   valid=false;
               }
              else
               {  erreur_age.setText(""); } 
          }
          //L'email
          if( !champ_email.getText().matches("^[A-Za-z0-9._-]+@[A-Za-z0-9._-]+\\.[a-z][a-z]+$"))  // format souhaité : aaazjksjd@fdfdfdd.fdfdfd
          {   
             erreur_email.setText("Adresse invalide !!!");
             valid=false;
          }
          else
          {  
              erreur_email.setText("");
              try{
              Utilisateur util=util_service.recuperer_utilisateur_par_email(champ_email.getText());
               if(util != null)
                 {  
                   erreur_email.setText("L'email existe déja !!!"); 
                   valid=false;
                 } 
               else
                { erreur_email.setText("");  }
              
              } catch (SQLException ex) {
              System.out.println(ex.getMessage());
                }     
          }    
         //le mot de passe
             if( !champ_mp.getText().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}"))
          {  
              erreur_mp.setText("le mot de passe doit comporter au moins 8 caractères avec au moins une lettre majuscule lettre miniscule, et un chiffre"); 
             valid=false;
          }
           else
          {  erreur_mp.setText(""); } // (?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])
         //La confirmation du mot de passe 
                if(champ_mp.getText().compareTo(champ_confirm_mp.getText())!= 0 )
          {
                 erreur_confirmation_mp.setText("Les mots de passe ne sont pas identiques !!!");
                 valid=false;
          }
            else
          {  erreur_confirmation_mp.setText(""); }  
            
         }  
          if( (champ_nom.getText().isEmpty()) || ( champ_prenom.getText().isEmpty() ) || ( champ_age.getText().isEmpty() ) || ( champ_email.getText().isEmpty() )||( champ_mp.getText().isEmpty() )|| ( champ_confirm_mp.getText().isEmpty() ) )
        {
        erreur_globale.setText("Veuillez renseigner tous les champs !!!");
        valid=false;
        } 
          // si le formulaire est validé on va ajouter l'élève
          if(valid)
          {
              //test sur les bouton radio de genre
              String genre1="F";
              if(radio_genre_homme.isSelected())
              { genre1="H"; }
              
              int age1=Integer.parseInt(champ_age.getText()); //convertir L'age saisi en int
             Utilisateur utilisateur1=new Utilisateur(age1,champ_nom.getText(),champ_prenom.getText(),"",champ_mp.getText(),champ_email.getText(),genre1, "élève"); //création de l'objet utilisateur sans pseudo
             
              try 
              {     
                 util_service.ajouter_utilisateur(utilisateur1); //--> ajout de l'utilisateur utilisateur1
                 Utilisateur utilisateur2=util_service.recuperer_utilisateur_par_email(utilisateur1.getEmail_util()); //réccupérer l'utilisateur déja saisi pour qu'on puise générer son pseudo (à partir de id+nom+prénom) (càd on va le modifier on va lui ajouter le pseudo)
                 utilisateur2.setPseudo_util(utilisateur2.GenererPseudo()); //générer le pseudo de utilisateur2 et modifier la valeur de son pseudo qui est égale à ""
                 util_service.modifier_utilisateur(utilisateur2);
              
                        this.retour(new ActionEvent()); //retourner à la page d'authentification
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Magic Book | opération réussite");
                        alert.setHeaderText("Félicitation");
                        alert.setContentText("Votre inscription a été effectué avec succès !  Votre pseudo : "+ utilisateur2.getPseudo_util());
                        alert.show();
              } catch (SQLException ex) {
              System.out.println(ex.getMessage());
              }
              
           
             
          }
        
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
