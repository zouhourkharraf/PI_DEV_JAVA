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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.ServicesUtilisateur;


/**
 * FXML Controller class
 *
 * @author MMD
 */
public class PageAdministrateurController implements Initializable {

    
    @FXML
    private TableColumn<Utilisateur, String> colonne_role;
    @FXML
    private TableColumn<Utilisateur, Integer> colonne_id;
    @FXML
    private TableColumn<Utilisateur, String> colonne_nom;
    @FXML
    private TableColumn<Utilisateur, String> colonne_prenom;
    @FXML
    private TableColumn<Utilisateur, String> colonne_pseudo;
    @FXML
    private TableColumn<Utilisateur, String> colonne_mp;
    @FXML
    private TableColumn<Utilisateur, String> colonne_email;
    @FXML
    private TableColumn<Utilisateur, Integer> colonne_age;
    @FXML
    private TableColumn<Utilisateur, String> colonne_genre;
    @FXML
    private TableColumn<?, ?> colonne_modifier;
    @FXML
    private TableView<Utilisateur> table_util;
    @FXML
    private Label admin_connecte;
    @FXML
    private ImageView BoutonModifier;
    @FXML
    private ImageView BoutonSupprimer;
    @FXML
    private Button BoutonApprouver;
    @FXML
    private ImageView BoutonActualiser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   AfficherTable(); //appeler la méthode AfficherTable 
         
    }  

    void setAdmin_connecte(String pseudo_admin_connecte) {
        admin_connecte.setText(pseudo_admin_connecte);
    }
    
    //cette méthode permet d'afficher tout les utilisateurs
    public void AfficherTable()
    {
          try {
       ServicesUtilisateur util_service= new ServicesUtilisateur(); //appel de la classe service
           List<Utilisateur> liste_utilisateurs=util_service.recuperer_liste_utilisateur();
           ObservableList<Utilisateur> obs=FXCollections.observableArrayList(liste_utilisateurs);
           table_util.setItems(obs);
             
           colonne_role.setCellValueFactory(new PropertyValueFactory<>("role_util"));
           colonne_id.setCellValueFactory(new PropertyValueFactory<>("id"));
           colonne_nom.setCellValueFactory(new PropertyValueFactory<>("nom_util"));
           colonne_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_util"));
           colonne_pseudo.setCellValueFactory(new PropertyValueFactory<>("pseudo_util"));
           colonne_mp.setCellValueFactory(new PropertyValueFactory<>("mot_de_passe_util"));
           colonne_email.setCellValueFactory(new PropertyValueFactory<>("email_util"));
           colonne_age.setCellValueFactory(new PropertyValueFactory<>("age_util"));

           colonne_genre.setCellValueFactory(new PropertyValueFactory<>("genre_util"));
           
 
             //obs.remove()
            //obs.add()
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ModifierUtilisateur(MouseEvent event) {
        
        
       Utilisateur util_a_modifier=table_util.getSelectionModel().getSelectedItem();
      //  System.out.println(util_a_modifier.toString());
       if((util_a_modifier.getRole_util().compareTo("élève")==0) || (util_a_modifier.getRole_util().compareTo("enseignant")==0))
       {
           //aller à la page modifier élève et insérer les données de util_a_modifier dans le formulaire de la page
           try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEleve.fxml"));  
                   Parent root = loader.load(); //Un container
                   ModifierEleveController modifier_eleve_controller=loader.getController();
                   //passer l'utilisateur à la fonction setFormData()
                   modifier_eleve_controller.setFormData(util_a_modifier,admin_connecte.getText());
                   admin_connecte.getScene().setRoot(root);            
                    } catch (IOException ex) 
                    { System.out.println(ex.getMessage());}
           

       }
       else
       {
           Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Magic Book | Erreur");
                        alert.setHeaderText("Attention !");
                        alert.setContentText("Vous ne pouvez pas modifier les informations d'un administrateur !");
                        alert.show();
       }
    }

    @FXML
    private void SupprimerUtilisateur(MouseEvent event) {
          System.out.println("Supprimer");
    }

    @FXML
    private void ApprouverDemande(ActionEvent event) {
          System.out.println("approuver demande ");
    }

    @FXML
    private void ActualiserPage(MouseEvent event) {
          System.out.println("actualizer");
    }
    
    
    
}
