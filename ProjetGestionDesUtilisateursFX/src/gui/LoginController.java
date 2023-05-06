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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;

import javafx.scene.input.MouseEvent;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import org.mindrot.jbcrypt.BCrypt;

import services.ServicesUtilisateur;
import t2s.son.LecteurTexte;

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
    @FXML
    private Label erreur_globale;
    @FXML
    private Label erreur_globale2;
    @FXML
    private Hyperlink lien_mp_oublie;
    private Button bouton_test;
    
    public static Boolean SyntheseVocale=false; //cette variable teste si la synthèse vocale est activée par l'utilisateur ou pas
    
    @FXML
    private ImageView activer_lecture;
    @FXML
    private ImageView desactiver_lecture;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          erreur_globale.setText("");
    }    

    @FXML
    private void SeConnecter(ActionEvent event) {
        LecteurTexte lecteur = new LecteurTexte(""); //initialiser le texte à lire par une chaine vide
        
          ServicesUtilisateur util_service= new ServicesUtilisateur(); //appel de la classe service
     if((champ_mp.getText().isEmpty()) && (champ_pseudo.getText().isEmpty()))
     {
          if(SyntheseVocale)
        { 
         lecteur.setTexte("Les champs sont vides!"); //modifier le texte avec l'erreur affiché
          lecteur.playAll();
        }
        erreur_globale.setText("Les champs sont vides !!!");
        
     }
     else
     {   
         erreur_globale.setText("");
         if( (!(champ_mp.getText().isEmpty()))&& (champ_pseudo.getText().isEmpty()))
         {
           if(SyntheseVocale)
        {
             lecteur.setTexte("Veuillez saisir votre pseudo!"); //modifier le texte avec l'erreur affiché
              lecteur.playAll();
        }
         erreur_globale.setText("Veuillez saisir votre pseudo !");
         }
       if(!(champ_pseudo.getText().isEmpty()))
       {
            
           try{
               erreur_globale.setText("");
               Utilisateur util=util_service.recuperer_utilisateur_par_pseudo(champ_pseudo.getText());
           if(util==null) 
           {
             if(SyntheseVocale)
               {
               lecteur.setTexte("Ce pseudo n'existe pas"); //modifier le texte avec l'erreur affiché
                lecteur.playAll();
               }
                erreur_globale.setText("Ce pseudo n'existe pas");
              
           }
           else
           {
                erreur_globale2.setText("");
            Utilisateur utilisateur1=util_service.recuperer_utilisateur_par_pseudo(champ_pseudo.getText()); //réccupérer l'utilisateur avec le pseudo déja saisi
             String mot_de_passe_reccupere=utilisateur1.getMot_de_passe_util(); //le mot de l'utilisateur récupéré
             //on va la transformer au fomat d'origine de l'algorithme Bcrypt (le format d'origine commence avec $2a et non pas avec $2y(c'est le format bogué)---> la partie web utilise le format bogué donc pour assuré l'intégration des 2 application on doit transformer $2y par $2a lors de l'authentification s'il existe )
             if(mot_de_passe_reccupere.startsWith("$2y"))
             { 
                 mot_de_passe_reccupere=mot_de_passe_reccupere.replace("$2y", "$2a"); 
             }
             
              if(!BCrypt.checkpw(champ_mp.getText(),mot_de_passe_reccupere) )  //si le hachage du mot de passe saisi ne correspond pas au hachage du mot de passe de l'util réccupéré (càd les mot de passe ne correspondent pas) 
              { 
                 if(SyntheseVocale)
                  {
                  lecteur.setTexte("Mot de passe invalide!"); //modifier le texte avec l'erreur affiché
                   lecteur.playAll();
                   }
                    erreur_globale2.setText("Mot de passe invalide !!!"); 
               }
              else  //si le hachage du mot de passe saisi correspond au hachage du mot de passe de l'util réccupéré (càd les mot de passe correspondent) 
              {
                  
                  try{
                  if( (utilisateur1.getRole_util().compareTo("élève")==0) || (utilisateur1.getRole_util().compareTo("enseignant")==0) )
                  {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("PageUtilisateur.fxml"));
                    Parent root = loader.load(); //Un container
                   PageUtilisateurController page_utilisateur_controller=loader.getController();
                   page_utilisateur_controller.setUtilisateurConnecte(champ_pseudo.getText(),SyntheseVocale);
                   champ_pseudo.getScene().setRoot(root);
                  }
                  else
                  {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));  
                   Parent root = loader.load(); //Un container
                   DashboardController dashboard_controller=loader.getController();
                   dashboard_controller.setAdminConnecteeDashboard(champ_pseudo.getText());  
                     champ_pseudo.getScene().setRoot(root);
                  }
                    } catch (IOException ex) 
                    { System.out.println(ex.getMessage());}
                erreur_globale2.setText("");      
              }
           }
           }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       }
     
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

    @FXML
    private void CliquerSurLeLienMpOublie(ActionEvent event) {
          
           try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("VerifierPseudo.fxml"));  
                Parent root = loader.load(); //Un container
                champ_pseudo.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    // ********************** Méthodes pour l'API synthèse vocale ****************************************
    
    @FXML
    private void ActiverSyntheseVocale(MouseEvent event) {
        SyntheseVocale=true;
 
    }

    @FXML
    private void DesactiverSyntheseVocale(MouseEvent event) {
        SyntheseVocale=false;
  
    }

    @FXML
    private void LireActiverMicro(MouseEvent event) {
         LecteurTexte lecteur = new LecteurTexte("Activer le mode lecture");
          lecteur.playAll();
       
    }

    @FXML
    private void LireCouperMicro(MouseEvent event) {
        LecteurTexte lecteur = new LecteurTexte("Désactiver le mode lecture");
          lecteur.playAll();
        
    }

  

    @FXML
    private void LireSeConnecter(MouseEvent event) {
        if(SyntheseVocale)
        {
          LecteurTexte lecteur = new LecteurTexte("Se connecter");
          lecteur.playAll();
        }
    }
  

    @FXML
    private void LireMpOublie(MouseEvent event) {
        if(SyntheseVocale)
        {
        LecteurTexte lecteur = new LecteurTexte("Mot de passe oublié ?");
          lecteur.playAll();
        }
    }


    @FXML
    private void LireChampPseudo(MouseEvent event) {
        if(SyntheseVocale)
        {
         LecteurTexte lecteur = new LecteurTexte("Donnez votre pseudo");
          lecteur.playAll();
           lecteur.muet();
        }
    }

    @FXML
    private void LireChampMP(MouseEvent event) {
        if(SyntheseVocale)
        {
         LecteurTexte lecteur = new LecteurTexte("Donnez votre mot de passe");
          lecteur.playAll();
          lecteur.muet();
        }
    }

    @FXML
    private void LireCompteEL(MouseEvent event) {
        if(SyntheseVocale)
        {
         LecteurTexte lecteur = new LecteurTexte("Créer un compte élève");
          lecteur.playAll();
        }
    }

    @FXML
    private void LireCompteEn(MouseEvent event) {
        if(SyntheseVocale)
        {
         LecteurTexte lecteur = new LecteurTexte("Créer un compte enseignant");
          lecteur.playAll();
        }
    
            // ********************** FIN Méthodes pour l'API synthèse vocale ****************************************
        
        
        
    }

   
    

  

 


  


    
}
