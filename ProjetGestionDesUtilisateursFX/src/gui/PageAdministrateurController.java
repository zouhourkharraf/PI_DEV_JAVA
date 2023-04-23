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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
    @FXML
    private Hyperlink lien_filter_par;
    @FXML
    private Hyperlink critere_role;
    @FXML
    private Hyperlink critere_age;
    @FXML
    private ChoiceBox<String> ChoiseBoxRole;
    @FXML
    private TextField champ_age_min;
    @FXML
    private TextField champ_age_max;
    @FXML
    private Button BoutonFiltrer;
    @FXML
    private Button BoutonAnnuler;
    @FXML
    private Text titre_resultat1;
    @FXML
    private Label titre_resultat2;
    @FXML
    private Label label_resultat_role;
    @FXML
    private Label label_resultat_age_min;
    @FXML
    private Label label_resultat_age_max;
    @FXML
    private Label erreur_filtre;
    
    boolean CliqueSurLeLienRole=false; //cette varible globale indique si on a cliqué sur le lien rôle ou pas
    boolean CliqueSurLeLienAge=false; //cette varible globale indique si on a cliqué sur le lien age ou pas
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   AfficherTable(); //appeler la méthode AfficherTable 
   BoutonApprouver.setVisible(false);
  
   //initialiser les choix du ChoiseBox du filtage par rôle
   ChoiseBoxRole.getItems().addAll("tous les utilisateurs","administrateur","enseignant","élève");
   ChoiseBoxRole.getSelectionModel().selectFirst(); //définir le chois par défuat ---> "tous les utilisateurs"
   
   // cacher les options et les opération du filtrage des données
    critere_role.setVisible(false);
    critere_age.setVisible(false);
    ChoiseBoxRole.setVisible(false);
    champ_age_min.setVisible(false);
    champ_age_max.setVisible(false);
    BoutonFiltrer.setVisible(false);
    BoutonAnnuler.setVisible(false);
    titre_resultat1.setVisible(false);
   titre_resultat2.setVisible(false);
    label_resultat_role.setVisible(false);
  label_resultat_age_min.setVisible(false);
   label_resultat_age_max.setVisible(false);
 erreur_filtre.setVisible(false);
   // FIN cacher les options et les opération du filtrage des données
   
   
   
   
   
   
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
          //appler la méthode AnnulerFiltre(ActionEvent event) de ce controlleur
          AnnulerFiltre(new ActionEvent());
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

    @FXML
    private void CliquerSurLeLienFiltrer(ActionEvent event) {
        //on va afficher les deux critères du filtre
        critere_role.setVisible(true);
        critere_age.setVisible(true);
        
        
    }

    @FXML
    private void CliquerSurLeLienRole(ActionEvent event) {
        //on va afficher la liste de choix des roles
        ChoiseBoxRole.setVisible(true);
        BoutonFiltrer.setVisible(true);
        CliqueSurLeLienRole=true;
       
    }

    @FXML
    private void CliquerSurLeLienAge(ActionEvent event) {
        //on va afficher les champs pour définir agemin et agemax
        champ_age_min.setVisible(true);
         champ_age_max.setVisible(true);
          BoutonFiltrer.setVisible(true);
          CliqueSurLeLienAge=true;
       
    }

    @FXML
    private void Filtrer(ActionEvent event) {
        //afficher le bouton Annuler pour qu'on puisse annuler le filtre
        BoutonAnnuler.setVisible(true);
        
        erreur_filtre.setVisible(true);
        
        //initialiser les variables qui vont contenir les résultat
         String role_choisi=""; //cette variable va contenir le role choisi 
         String age_max_choisi="",age_min_choisi=""; //ces 2 variables vont contenir l'age minimamle et l'age maximale choisis dans le cas ou l'utilisateur a rempli les 2 champs(agemin et agemax)
         String age_min_seuleument="";
         String age_max_seuleument="";
         
            
          titre_resultat2.setText("");
          label_resultat_age_min.setText("");
          label_resultat_age_min.setVisible(false);
          label_resultat_age_max.setText("");
          label_resultat_age_max.setVisible(false);
         erreur_filtre.setText(""); 
          
        //traiter le critère role ( affichage des résultat dans la zone des résultat + réccupération du choix dans la variable "role_choisi" )
      if(CliqueSurLeLienRole)
      {
      titre_resultat1.setVisible(true);
      label_resultat_role.setVisible(true);
      role_choisi=ChoiseBoxRole.getValue();
      label_resultat_role.setText(role_choisi);
      }
   
       //traiter le critère age ( affichage des résultat dans la zone des résultat  + réccupération des choix )
      if(CliqueSurLeLienAge)
      {
        
       // ***********************  Afficher l'age minimale et l'age maximale choisi selon les champs que l'utilisateur a rempli 
       if( (!champ_age_min.getText().isEmpty()) && (!champ_age_max.getText().isEmpty()) ) //si l'utilisateur a défini age_min et age_max
       {
          if(Integer.parseInt(champ_age_min.getText()) < 5 )
             { erreur_filtre.setText("l'age minimale est 5 ans"); }
            else
             {
               if(Integer.parseInt(champ_age_max.getText()) < 5 )
             { erreur_filtre.setText("l'age minimale est 5 ans"); }
              else
               {
                   if(Integer.parseInt(champ_age_max.getText()) < Integer.parseInt(champ_age_min.getText()) )
             { erreur_filtre.setText("Bornes invalides"); }
                   else
                   {
                       erreur_filtre.setText("");
         titre_resultat2.setVisible(true); //afficher titre_resultat2 
         titre_resultat2.setText("Entre           et            ans"); //afficher la valeur adéquate
         
            //afficher les label de agemin et agemax
         label_resultat_age_min.setVisible(true);
         label_resultat_age_min.setLayoutX(571);
         label_resultat_age_max.setVisible(true);
           //afficher l'age min et l'age max choisi   
         label_resultat_age_min.setText( champ_age_min.getText() );                        
         label_resultat_age_max.setText( champ_age_max.getText() ); 
         //enregistrer l'age minimale et l'age maximales choisies
         age_min_choisi=champ_age_min.getText();
         age_max_choisi=champ_age_max.getText();
                   }
               }
             }
       }
       else
       {
          
           //vider le titre du résultat et les labels de résultat  
         titre_resultat2.setText("");
         label_resultat_age_min.setText("");  
         titre_resultat2.setVisible(true);
         
         
         label_resultat_age_max.setText("");  
         label_resultat_age_max.setVisible(false); //parceque dans les deux opération suivantes on va utiliser qu'un seul label le label "label_resultat_age_min"
         
         if(  (!(champ_age_min.getText().isEmpty()) ) && (champ_age_max.getText().isEmpty()) ) // ****** si l'utilisateur a défini age_min seulement
         {
            if(Integer.parseInt(champ_age_min.getText()) < 5 )
             { erreur_filtre.setText("l'age minimale est 5 ans"); }
            else
             {
              erreur_filtre.setText(""); 
              label_resultat_age_min.setVisible(true);
             titre_resultat2.setText("Plus que             ans");
             label_resultat_age_min.setLayoutX(610); //décaler le label pour qu'il soit adapté au nouveau titre
             label_resultat_age_min.setText(champ_age_min.getText());
             age_min_seuleument=champ_age_min.getText();
             }    
         }
         else
         {   
         if( (!champ_age_max.getText().isEmpty()) && (champ_age_min.getText().isEmpty()) ) // ******* si l'utilisateur a défini age_max seulement
         {
                if(Integer.parseInt(champ_age_max.getText()) < 5 )
                { erreur_filtre.setText("l'age minimale est 5 ans"); }
                else
                {
                 erreur_filtre.setText("");
                 label_resultat_age_min.setVisible(true);
                 titre_resultat2.setText("Moins de           ans");
                 label_resultat_age_min.setLayoutX(610); //décaler le label pour qu'il soit adapté au nouveau titre
                 label_resultat_age_min.setText(champ_age_max.getText());
                 age_max_seuleument=champ_age_max.getText();
                }
         }
         
          //si l'utilisateur a cliqué sur le lien age mais n'a rempli aucun champ
         else
          {
             //réinitialiser les valuers et les posistion des labels et du titre(pour le résultat age) et les cacher
           titre_resultat2.setText("");
           titre_resultat2.setVisible(false);
           
           label_resultat_age_min.setText("");
           label_resultat_age_max.setText("");
           
           label_resultat_age_min.setVisible(false);
           label_resultat_age_max.setVisible(false);
           label_resultat_age_min.setLayoutX(571);
          
            //réinitialiser les valeurs des variables qui enregisrent les choix
           age_min_choisi="";
           age_max_choisi="";
           age_min_seuleument="";
           age_max_seuleument="";
          }    
         }
          // ***********************  FIN Afficher l'age minimale et l'age maximale choisi selon les champs que l'utilisateur a rempli  
          
       }
    //afficher le résultat dans la table
       
       
       
    
       
      }
       
 
      
      
       
       
       
       
      
    }

    @FXML //cette méthode permet d'annuler tous les filtres appliqués et d'afficher la table sans filtre
    private void AnnulerFiltre(ActionEvent event) {
       // cacher les options et les opération du filtrage des données
    critere_role.setVisible(false);
    critere_age.setVisible(false);
    ChoiseBoxRole.setVisible(false);
    champ_age_min.setVisible(false);
    champ_age_max.setVisible(false);
    BoutonFiltrer.setVisible(false);
    BoutonAnnuler.setVisible(false);
    titre_resultat1.setVisible(false);
   titre_resultat2.setVisible(false);
    label_resultat_role.setVisible(false);
  label_resultat_age_min.setVisible(false);
   label_resultat_age_max.setVisible(false);
  erreur_filtre.setVisible(false);
  // FIN cacher les options et les opération du filtrage des données    
   
   // afficher tous les utilisateurs 
      AfficherTable(); //appeler la méthode AfficherTable     
    }

 

   
    
    
}
