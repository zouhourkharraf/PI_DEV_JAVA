/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
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
    @FXML
    private Rectangle rectangle_vert;
    @FXML
    private Rectangle rectangle_orange;
    @FXML
    private ImageView image_util;
    @FXML
    private Label label_bonjour;
    @FXML
    private Label label_citation;
    @FXML
    private Label label_auteur_citation;
    @FXML
    private Label label_info;
    @FXML
    private Label label11;
    @FXML
    private Label label22;
    @FXML
    private Label label33;
    @FXML
    private Label label44;
    @FXML
    private Label label55;
    @FXML
    private Label label66;
    @FXML
    private Label label_nom;
    @FXML
    private Label label_prenom;
    @FXML
    private Label label_age;
    @FXML
    private Label label_genre;
    @FXML
    private Label label_pseudo;
    @FXML
    private Label label_email;
 
    @FXML
    private Label label_demande;
    @FXML
    private Button bouton_modifier;
    @FXML
    private Button bouton_demande_supp;
    @FXML
    private Rectangle rectangle_blanc;
    @FXML
    private Line ligne1;
    
     ServicesUtilisateur util_service= new ServicesUtilisateur(); //appel de la classe service
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //cacher tous les éléments du profil 
    rectangle_vert.setVisible(false);
   rectangle_orange.setVisible(false);
   rectangle_blanc.setVisible(false);
    image_util.setVisible(false);
    ligne1.setVisible(false);
    label_bonjour.setVisible(false);
    label_citation.setVisible(false);
     label_auteur_citation.setVisible(false);
    label_info.setVisible(false);
    label11.setVisible(false);
     label22.setVisible(false);
    
     label33.setVisible(false);
     label44.setVisible(false);
     label55.setVisible(false);
    label66.setVisible(false);
     label_nom.setVisible(false);
   label_prenom.setVisible(false);
    label_age.setVisible(false);
   label_genre.setVisible(false);
    label_pseudo.setVisible(false);
     label_email.setVisible(false);
   bouton_modifier.setVisible(false);
       bouton_demande_supp.setVisible(false);
    label_demande.setVisible(false);
        
        
        
        
    }    

    @FXML
    private void AfficherProfil(ActionEvent event) {
         //Montrer tous les éléments du profil 
    rectangle_vert.setVisible(true);
   rectangle_orange.setVisible(true);
   rectangle_blanc.setVisible(true);
    image_util.setVisible(true);
     ligne1.setVisible(true);
    label_bonjour.setVisible(true);
    label_citation.setVisible(true);
     label_auteur_citation.setVisible(true);
    label_info.setVisible(true);
    label11.setVisible(true);
     label22.setVisible(true);
    
     label33.setVisible(true);
     label44.setVisible(true);
     label55.setVisible(true);
    label66.setVisible(true);
     label_nom.setVisible(true);
   label_prenom.setVisible(true);
    label_age.setVisible(true);
   label_genre.setVisible(true);
    label_pseudo.setVisible(true);
     label_email.setVisible(true);
   bouton_modifier.setVisible(true);
       bouton_demande_supp.setVisible(true);
    label_demande.setVisible(true);  
    
                try {
             Utilisateur utilisateur_connecte=util_service.recuperer_utilisateur_par_pseudo(MenuButton1.getText());
                   label_nom.setText(utilisateur_connecte.getNom_util());
                   label_prenom.setText(utilisateur_connecte.getPrenom_util());
                    label_age.setText(String.valueOf(utilisateur_connecte.getAge_util()));
                    
                    //afficher le genre et l'avatar selon le genre
                    if(utilisateur_connecte.getGenre_util().compareTo("F")==0)
                    {   
                        //insérer l'avatar femme
                        Image avatar_femme = new Image(getClass().getResourceAsStream("/images/avatar_femme.jpg"),179,179,false,true); // créer un nouveau objet image avatar_femme et l'initilaiser avec le fichier "avatar_femme.jpg"        
                        image_util.setImage(avatar_femme); //ajouter l'image crée à l'image view de notre fichier
                        
                        label_genre.setText("Femme");  
                    }
                    else
                     {  
                        //insérer l'avatar homme
                        Image avatar_homme = new Image(getClass().getResourceAsStream("/images/avatar_homme.png"),179,179,false,true); // créer un nouveau objet image avatar_femme et l'initilaiser avec le fichier "avatar_femme.jpg"        
                        image_util.setImage(avatar_homme); //ajouter l'image crée à l'image view de notre fichier
                       
                        label_genre.setText("Homme");  
                     }   
                    
                    label_pseudo.setText(utilisateur_connecte.getPseudo_util());
                    label_email.setText(utilisateur_connecte.getEmail_util());
                //remplir le proverbe selon le rôle (enseignant ou élève)         
                  if(utilisateur_connecte.getRole_util().compareTo("enseignant")==0)   
                  {
                  label_citation.setText("« Enseigner, c’est apprendre deux fois. »");
                  label_auteur_citation.setText(" – Joseph Joubert");
                  }
                  else
                  {
                  label_citation.setText("« La persévérance, c’est ce qui rend l’impossible possible, le possible probable et le probable réalisé. »");
                  label_citation.setWrapText(true); //répartir le texte sur 2 lignes
                  label_auteur_citation.setText(" – Léon Trotsky");
                  }
                    
                    
                    
                    
                    
                    
               
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    
    
    
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

   
    
      public void setUtilisateurConnecte(String pseudo_admin) {
      MenuButton1.setText(pseudo_admin);
      
          
            try {
             Utilisateur utilisateur_connecte=util_service.recuperer_utilisateur_par_pseudo(MenuButton1.getText());
                
   //changer le nom de l'espace selon l'utilisateur connecté 
        if( utilisateur_connecte.getRole_util().compareTo("enseignant")==0 )
        {
        Label_espace.setText("     Espace enseignnat");
        }
        else
        {
        Label_espace.setText("            Espace élève");
        }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
      
    }

    @FXML
    private void ModifierUtilisateur(ActionEvent event) {
       
         
                 try{
                        try{
                        Utilisateur utilisateur_a_modifier = util_service.recuperer_utilisateur_par_pseudo(MenuButton1.getText());
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEleve.fxml"));  
                   Parent root = loader.load(); //Un container
                   ModifierEleveController modifier_eleve_controller=loader.getController();
                   //passer l'utilisateur à modifier + le pseudo de l'utilisateur connecté + le nom de l'espace à la fonction setFormData()
                   modifier_eleve_controller.setFormData(utilisateur_a_modifier,MenuButton1.getText(),"EspaceUtilisateur");
                   MenuButton1.getScene().setRoot(root);      
                           } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                           }       
                    } catch (IOException ex) { 
                     System.out.println(ex.getMessage());
                     } 
        
    }

    @FXML
    private void EnvoyerLaDemande(ActionEvent event) {
          
             
              try {
               Utilisateur utilisateur_connecte = util_service.recuperer_utilisateur_par_pseudo(MenuButton1.getText());  
               if(utilisateur_connecte.getDemande_suppression().compareTo("oui")==0 )
               {
                             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Magic Book | Opération en attente");
                        alert.setHeaderText("Vous avez déja envoyé une demande");
                        alert.setContentText("Votre demande est en cour de traitement par l'administrateur");
                        alert.show();  
               }
               else
               {
               utilisateur_connecte.setDemande_suppression("oui");
                util_service.modifier_utilisateur(utilisateur_connecte);
               }
               
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
   
        
         
        
        
    }

   
    
}
