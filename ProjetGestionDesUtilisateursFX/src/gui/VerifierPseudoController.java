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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.Mail;
import services.ServicesUtilisateur;

/**
 * FXML Controller class
 *
 * @author MMD
 */
public class VerifierPseudoController implements Initializable {

    @FXML
    private Button bouton_valier_pseudo;
    @FXML
    private TextField champ_pseudo;
    @FXML
    private Label erreur_globale;
    @FXML
    private Button bouton_retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ValiderPseudo(ActionEvent event) {
          ServicesUtilisateur util_service= new ServicesUtilisateur(); //appel de la classe service
                      try {
             Utilisateur utilisateur1=util_service.recuperer_utilisateur_par_pseudo(champ_pseudo.getText());
           if(utilisateur1==null) 
           {
                erreur_globale.setText("Ce pseudo n'existe pas !!!"); 
           }
           else
           {
               erreur_globale.setText("");
               
               //****************  Variables pour créer le texte du message
               int CodeSecret=GenererCodeSecret();
               String genre_utilisateur=""; //cette variable va contenir Monsieur ou Madame selon le genre de l'utilisateur réccupéré
            if(utilisateur1.getRole_util().compareTo("administrateur") != 0) //si l'utilisateur n'est administrateur donc il possède un genre
            {
             if(utilisateur1.getGenre_util().compareTo("F") ==0 ) 
             {
             genre_utilisateur="Madame";
             }
             else
             {
               genre_utilisateur="Monsieur";
             }
            }
             //sinon càd si l'utilisateur est administrateur (donc son attribut de genre contient null) la variable genre_utilisateur va rester vide  
            String nom_utilisateur="",prenom_utilisateur="";
             
             if(utilisateur1.getRole_util().compareTo("administrateur") != 0) //si l'utilisateur n'est administrateur donc il possède un nom et un prénom
             {
             nom_utilisateur=utilisateur1.getNom_util(); //---> le nom de l'utilisateur réccupéré 
             prenom_utilisateur=utilisateur1.getPrenom_util(); //---> le prénom de l'utilisateur réccupéré 
             }
              //sinon càd si l'utilisateur est administrateur (donc il n'a pas de nom ni un prénom) les deux variables nom_utilisateur et prenom_utilisateur vont rester vides
             
              //****************  FIN Variables pour créer le texte du message
              
             // **************** Variables pour la configuration de l'email
             String email_utilisateur=utilisateur1.getEmail_util();
             
             //construire le corps du message en html
             String message= "<h4>Bonjour "+genre_utilisateur+"</h4><h4>"+nom_utilisateur+" "+prenom_utilisateur+"</h4><h4>Vore code est : "+CodeSecret+"</h4><h4>L'équipe Magic Book</h4>";
             
             Mail.send("magicbook835@gmail.com", "yijfdvaakbioplfg",email_utilisateur, "Magic Book : Récupération du mot de passe", message); //send est une méthode statique 
             
            //testé -------> ça marche l'email paramétré est envoyé 
   
           
              try{
                
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("VerfierCodeSecret.fxml")); // envoyer à la page suivante : L'utilisateur validé avec  son pseudo + le code secret envoyé
                   Parent root = loader.load(); //Un container
                   VerfierCodeSecretController verifier_code_secret_controller=loader.getController();
                   verifier_code_secret_controller.reccuperer_information(utilisateur1, CodeSecret); 
                   champ_pseudo.getScene().setRoot(root);
                  
                    } catch (IOException ex) 
                    { System.out.println(ex.getMessage());}
            
            
            
            
           }
           
   
           
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
    
    //cette méthode permet de générer un nombre aléatoire compris entre 10000 et 99999

    private int GenererCodeSecret()
   {    
   Random rand = new Random(); // créer un  objet de type Random
   int Code = rand.nextInt(99999 - 10000 + 1) + 10000; //générer un nombre aléatoire entre 10000 et 99999
   return Code;
   }
 
}
