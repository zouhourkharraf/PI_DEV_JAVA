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
import java.util.Iterator;
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
import javafx.scene.control.SortEvent;
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
    @FXML
    private TableColumn<Utilisateur, String> colonne_demande_suppression;
    @FXML
    private Button BoutonDeconnexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   AfficherTable(); //appeler la méthode AfficherTable 
   BoutonApprouver.setVisible(false);
   
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
           
           /*
             // modifier le valeur de l'attribut genre dans la liste qui contient les données déja réccupéré depuis la BD 'F'-->'Femme' et 'H'--->'Homme'
             obs.forEach((utilisateur) -> {
                 if(utilisateur.getGenre_util().compareTo("F")==0)
                 {
                     utilisateur.setGenre_util("Femme");
                 }
                 else
                 {
                     utilisateur.setGenre_util("Homme");
                 }});
           */
    
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
        colonne_demande_suppression.setCellValueFactory(new PropertyValueFactory<>("demande_suppression"));
        
             //obs.remove()
            //obs.add()
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    @FXML
    private void ModifierUtilisateur(MouseEvent event) {
        
        
       Utilisateur util_a_modifier=table_util.getSelectionModel().getSelectedItem();
      
       if((util_a_modifier.getRole_util().compareTo("élève")==0) || (util_a_modifier.getRole_util().compareTo("enseignant")==0))
       {
           //aller à la page modifier élève et insérer les données de util_a_modifier dans le formulaire de la page
           try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEleve.fxml"));  
                   Parent root = loader.load(); //Un container
                   ModifierEleveController modifier_eleve_controller=loader.getController();
                   //passer l'utilisateur à la fonction setFormData()
                   modifier_eleve_controller.setFormData(util_a_modifier,admin_connecte.getText(),"DashboardAdmin");
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
          if(table_util.getSelectionModel().isEmpty())
          {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Magic Book | Erreur");
                        alert.setHeaderText("Impossible !");
                        alert.setContentText("Veuillez sélectionner un utilisateur !");
                        alert.show();  
          }
          else
          {
             Utilisateur util_a_supprimer=table_util.getSelectionModel().getSelectedItem();
        
             try{
                  ServicesUtilisateur util_service= new ServicesUtilisateur(); //appel de la classe service
                  util_service.supprimer_utilisateur(util_a_supprimer);           
                    } catch (SQLException ex) {
                   System.out.println(ex.getMessage());
                     }
             this.AfficherTable(); //actualiser l'affichage de la table
          }
    }

    @FXML
    private void ApprouverDemande(ActionEvent event) {
         
              Utilisateur utillisateur1=table_util.getSelectionModel().getSelectedItem();//réccupérer l'utilisateur selectionné
          
             try{
                  ServicesUtilisateur util_service= new ServicesUtilisateur(); //appel de la classe service
                  util_service.supprimer_utilisateur(utillisateur1);           
                    }catch (SQLException ex) {
                   System.out.println(ex.getMessage());
                     }
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Magic Book | Opération administrateur");
                        alert.setHeaderText("Approuver une demande de suppresssion : ");
                        alert.setContentText("La suppression a été effectué avec succès !");
                        alert.show();  
             
             
             this.AfficherTable(); //actualiser l'affichage de la table
       
          
    }

    
    
    @FXML
    private void ActualiserPage(MouseEvent event) {
          this.AfficherTable(); //actualiser l'affichage de la table
    }

    @FXML
    private void SeDeconnecter(ActionEvent event) {
        
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load(); ///le container
             BoutonDeconnexion.getScene().setRoot(root);
         
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void afficher_bouton_approuver(MouseEvent event) {
       if( table_util.getSelectionModel().getSelectedItem().getDemande_suppression().compareTo("oui")==0 ) //si l'utilisateur sélectionné sa demande de suppression égal à oui 
       {
       BoutonApprouver.setVisible(true); //on va afficher le bouton pour qu'on puisse approuver la demande ou pas
       }
       else
       {
       BoutonApprouver.setVisible(false);  //sinon on va cacher le bouton une autre fois
       }
           
    }

 

   
    
    
}
