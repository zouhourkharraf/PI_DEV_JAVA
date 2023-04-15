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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import services.ServicesUtilisateur;

/**
 * FXML Controller class
 *
 * @author MMD
 */
public class ModifierEleveController implements Initializable {

    @FXML
    private Button bouton_retour2;
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
    private ToggleGroup genre;
    @FXML
    private RadioButton radio_genre_femme;
    @FXML
    private Button bouton_modifier_eleve;
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
    private Label ErreurPrenom;
    @FXML
    private Label ErreurNomPrenom;
    @FXML
    private Label AdminConnecte3;
    
Utilisateur utilisateur_a_modifier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AdminConnecte3.setVisible(false);
    }    

    @FXML
    private void RetourVersDashboard(ActionEvent event) {
            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PageAdministrateur.fxml")); 
             Parent root = loader.load(); //Un container
             PageAdministrateurController page_admin_controller=loader.getController();
             page_admin_controller.setAdmin_connecte(AdminConnecte3.getText());
             //actualiser l'affichage de la table
          //   page_admin_controller.AfficherTable();
                   AdminConnecte3.getScene().setRoot(root);
                    }catch (IOException ex) 
                    { System.out.println(ex.getMessage());}
        
    }

    @FXML
    private void ModifierEleve(ActionEvent event) {
         ServicesUtilisateur util_service= new ServicesUtilisateur(); //appel de la classe service
         
         //test sur les bouton radio de genre
              String genre1="F";
              if(radio_genre_homme.isSelected())
              { genre1="H"; }
              
           int age1=Integer.parseInt(champ_age.getText()); //convertir L'age saisi en int
     Utilisateur util2=new Utilisateur(utilisateur_a_modifier.getId(),age1,champ_nom.getText(),champ_prenom.getText(),"",utilisateur_a_modifier.getMot_de_passe_util(),champ_email.getText(), genre1,utilisateur_a_modifier.getRole_util());
              try {
                     
         util_service.modifier_utilisateur(util2);
              Utilisateur util3=util_service.recuperer_utilisateur_par_email(util2.getEmail_util()); //réccupérer l'utilisateur déja saisi pour qu'on puise générer son pseudo (à partir de id+nom+prénom) (càd on va le modifier on va lui ajouter le pseudo)
                 util2.setPseudo_util(util2.GenererPseudo()); //générer le pseudo de utilisateur2 et modifier la valeur de son pseudo qui est égale à ""
                 util_service.modifier_utilisateur(util2);
         
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
        
        
    }

    @FXML
    private void AnnulerEleve(ActionEvent event) {
        champ_nom.clear();
        champ_prenom.clear();
        champ_age.clear();
        champ_email.clear();
      
    }
    
    public void setFormData(Utilisateur util,String admin_connecte)
    {
        
    champ_nom.setText(util.getNom_util());
    champ_prenom.setText(util.getPrenom_util());
    champ_age.setText(String.valueOf(util.getAge_util()));
    champ_email.setText(util.getEmail_util());
    AdminConnecte3.setText(admin_connecte);
    
      utilisateur_a_modifier=util; 
    
    
    }
}
