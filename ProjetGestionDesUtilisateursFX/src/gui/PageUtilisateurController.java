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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import services.ServicesUtilisateur;

/**
 * FXML Controller class
 *
 * @author MMD
 */
public class PageUtilisateurController implements Initializable {

    @FXML
    private MenuItem choix_profil;
    @FXML
    private MenuItem choix_deconnexion;
    @FXML
    private MenuButton MenuButton1;
    @FXML
    private Button bouton_incsi_eleve;
    @FXML
    private Button bouton_incsi_eleve1;
    @FXML
    private Button bouton_incsi_eleve2;
    @FXML
    private Button bouton_incsi_eleve3;
    @FXML
    private Label Label_espace;

    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }    

    @FXML
    private void AfficherProfil(ActionEvent event) {
        
    }

    @FXML
    private void SeDeconnecter(ActionEvent event) {
            try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load(); ///le container
             MenuButton1.getScene().setRoot(root);
         
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void CreerCompteEleve(ActionEvent event) {
    }
    
      public void setUtilisateurConnecte(String pseudo_admin) {
      MenuButton1.setText(pseudo_admin);
      
           ServicesUtilisateur util_service= new ServicesUtilisateur(); //appel de la classe service
            try {
             Utilisateur utilisateur_connecte=util_service.recuperer_utilisateur_par_pseudo(MenuButton1.getText());
                
   //changer le nom de l'espace selon l'utilisateur connecté 
        if( utilisateur_connecte.getRole_util().compareTo("enseignant")==0 )
        {
        Label_espace.setText("  Espace enseignnat");
        }
        else
        {
        Label_espace.setText("  Espace élève");
        }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
      
    }
    
}
