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
import javafx.scene.control.Alert;
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
    private Label titre1;
    @FXML
    private Label AdminConnecte3;
    @FXML
    private Label label_espace_precedent;
    
   Utilisateur utilisateur_a_modifier;
 
   Boolean modification=false;  
   Utilisateur utilisateur_modifie; //cet attibut va contenir l'utlisateur qu'on modifié ses informations(une modification validé)
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      AdminConnecte3.setVisible(false);
      label_espace_precedent.setVisible(false);
        erreur_globale.setText("");
     
        
    }    

     @FXML
    private void RetourVersPagePrecedente(ActionEvent event) {
        
      if(label_espace_precedent.getText().compareTo("DashboardAdmin") == 0 ) //si l'espace précédent est le dashboard admin
      {
          //---> on va retourner vers le dashboard admin
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PageAdministrateur.fxml")); 
             Parent root = loader.load(); //Un container
             PageAdministrateurController page_admin_controller=loader.getController();
             page_admin_controller.setAdmin_connecte(AdminConnecte3.getText());
                   AdminConnecte3.getScene().setRoot(root);
                    }catch (IOException ex) 
                    { System.out.println(ex.getMessage());}
      } 
      else //si l'espace précédent est l'espace utilisateur
      {
          //---> on va retourner vers l'espace utilisateur
       try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PageUtilisateur.fxml")); 
             Parent root = loader.load(); //Un container
             PageUtilisateurController page_utilisateur_controller=loader.getController();
             if(modification == false) //càd si on va retourner à l'espace utilisateur sans avoir validé la modification de cet utilisateur
             { //----> on va retourner le même pseudo(le pseudo de l'utilisateur qui a appeler cette page)         
             page_utilisateur_controller.setUtilisateurConnecte(AdminConnecte3.getText());
             }  
             else  //--> sinon càd si modifcation==true (on a effectué une modification sur l'utilisateur)
             {
                 page_utilisateur_controller.setUtilisateurConnecte(utilisateur_modifie.getPseudo_util());
             }  
                 
                   AdminConnecte3.getScene().setRoot(root);
                    }catch (IOException ex) 
                    { System.out.println(ex.getMessage());}
      }   
        
    }


    @FXML
    private void ModifierEleve(ActionEvent event) {
        
        
          boolean valid=true; 
            ServicesUtilisateur util_service= new ServicesUtilisateur(); //appel de la classe service
        
        //les contrôles de saisie
        if( (champ_nom.getText().isEmpty()) && ( champ_prenom.getText().isEmpty() )&&( champ_age.getText().isEmpty() )&&( champ_email.getText().isEmpty() ) )
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
           //faire le controle de saisie de l'âge selon le role de l'utilisateur(enseignant ou élève)
           //si l'utilisateur à modifier est un élève
           if(utilisateur_a_modifier.getRole_util().compareTo("élève")==0)
           {
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
           }
           //si l'utilisateur à modifier est un enseignant
          if(utilisateur_a_modifier.getRole_util().compareTo("enseignant")==0)
           {
          if(!champ_age.getText().matches("(\\d)+"))
          {
                 erreur_age.setText("Valeur invalide !!");
                 valid=false;
          }
          else
          { 
              if(Integer.parseInt(champ_age.getText()) < 22)
               {  erreur_age.setText("Vous devez avoir au moins 22 ans pour créer un compte enseignant"); 
                   valid=false;
               }
              else
               {  erreur_age.setText(""); } 
          }
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
               if((util != null) && (util.equals(utilisateur_a_modifier)==false))//càd si l'utilisateur avec cet email existe et ce n'est pas l'utilisateur qu'on est en train de le modifier  
                 {  
                   erreur_email.setText("L'email existe déja !!!"); //afficher un message d'erreur l'email existe déja
                   valid=false;
                 } 
               else
                { erreur_email.setText("");  }
              
              } catch (SQLException ex) {
              System.out.println(ex.getMessage());
                }     
          }    
        }
          if( (champ_nom.getText().isEmpty()) || ( champ_prenom.getText().isEmpty() ) || ( champ_age.getText().isEmpty() ) || ( champ_email.getText().isEmpty() ) )
        {
        erreur_globale.setText("Veuillez renseigner tous les champs !!!");
        valid=false;
        } 
          // *******   Fin des contrôles de saisies
         
           // si le formulaire est validé on va modifier l'élève
          if(valid)
          {
         //test sur les bouton radio de genre
              String genre1="F";
              if(radio_genre_homme.isSelected())
              { genre1="H"; }
              
           int age1=Integer.parseInt(champ_age.getText()); //convertir L'age saisi en int
     Utilisateur util2=new Utilisateur(utilisateur_a_modifier.getId(),age1,champ_nom.getText(),champ_prenom.getText(),"",utilisateur_a_modifier.getMot_de_passe_util(),champ_email.getText(), genre1,utilisateur_a_modifier.getRole_util(),utilisateur_a_modifier.getDemande_suppression());
              try {
                     
         util_service.modifier_utilisateur(util2);
              Utilisateur util3=util_service.recuperer_utilisateur_par_email(util2.getEmail_util()); //réccupérer l'utilisateur déja saisi pour qu'on puise générer son pseudo (à partir de id+nom+prénom) (càd on va le modifier on va lui ajouter le pseudo)
                 util2.setPseudo_util(util2.GenererPseudo()); //générer le pseudo de utilisateur2 et modifier la valeur de son pseudo qui est égale à ""
                 util_service.modifier_utilisateur(util2);
                 modification=true; //indiquer qu'on effectué une modification sur cet utilisateur (cette atrribut on va utilisateur que si la page précédente est l'espace utilisateur )
               utilisateur_modifie=util_service.recuperer_utilisateur_par_pseudo(util2.getPseudo_util()); //enregister l'utillisateur qu'on a modifié
               
              this.RetourVersPagePrecedente(new ActionEvent()); //retourner à la page précédente en appelent la méhthode du controleur RetourVersPagePrecedente()
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Magic Book | Modifier Élève");
                        alert.setHeaderText("Notification !");
                        alert.setContentText("Votre compte est maintenant à jour | Votre nouveau pseudo : "+ util2.getPseudo_util());
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
      
    }
    
    public void setFormData(Utilisateur util,String admin_connecte,String nom_espace)
    {
        
    champ_nom.setText(util.getNom_util());
    champ_prenom.setText(util.getPrenom_util());
    champ_age.setText(String.valueOf(util.getAge_util()));
    champ_email.setText(util.getEmail_util());
    AdminConnecte3.setText(admin_connecte); //on utilise ce label pour qu'on puisse retourner vers l'espace de l'utilisateur connecté (qu'il soit util ou admin) dés qu'on termine l'opération de modification
    label_espace_precedent.setText(nom_espace); //enregistrer l'espace précédent à cette page dans le label "label_espace_precedent"
    
      utilisateur_a_modifier=util; 
       if(utilisateur_a_modifier.getRole_util().compareTo("élève")==0)
        {
        titre1.setText("Modifier mes informations : Élève");
        }
        else
        {
         titre1.setText("Modifier mes informations : Enseignant");
        }
    
    }

   
}
