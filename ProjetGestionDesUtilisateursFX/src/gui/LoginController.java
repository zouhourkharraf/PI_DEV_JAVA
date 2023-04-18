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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.ServicesUtilisateur;

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
        
          ServicesUtilisateur util_service= new ServicesUtilisateur(); //appel de la classe service
     if((champ_mp.getText().isEmpty()) && (champ_pseudo.getText().isEmpty()))
     {
        erreur_globale.setText("Les champs sont vides !!!");
     }
     else
     {   
         erreur_globale.setText("");
         if( (!(champ_mp.getText().isEmpty()))&& (champ_pseudo.getText().isEmpty()))
         {
         erreur_globale.setText("Veuillez saisir votre pseudo !");
         }
       if(!(champ_pseudo.getText().isEmpty()))
       {
            
           try{
               erreur_globale.setText("");
               Utilisateur util=util_service.recuperer_utilisateur_par_pseudo(champ_pseudo.getText());
           if(util==null) 
           {
                erreur_globale.setText("Ce pseudo n'existe pas");
              
           }
           else
           {
                erreur_globale2.setText("");
            Utilisateur utilisateur1=util_service.recuperer_utilisateur_par_pseudo(champ_pseudo.getText());
              if(champ_mp.getText().compareTo(utilisateur1.getMot_de_passe_util()) != 0)
               {
                    erreur_globale2.setText("Mot de passe invalide !!!"); 
               }
              else
              {
                  try{
                  if( (utilisateur1.getRole_util().compareTo("élève")==0) || (utilisateur1.getRole_util().compareTo("enseignant")==0) )
                  {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("PageUtilisateur.fxml"));
                    Parent root = loader.load(); //Un container
                   PageUtilisateurController page_utilisateur_controller=loader.getController();
                   page_utilisateur_controller.setUtilisateurConnecte(champ_pseudo.getText());
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
    
}
