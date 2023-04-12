/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.Utilisateur;
import java.sql.SQLException;
import java.util.List;
import services.ServicesUtilisateur;


/**
 *
 * @author MMD
 */
public class Test {
     
    
    public static void main(String[] args) {
        
     ServicesUtilisateur util_service= new ServicesUtilisateur(); //appel de la classe service
   
    //affichage de tous les utilisateurs
    
                try {
                      List<Utilisateur> liste_utilisateurs=util_service.recuperer_liste_utilisateur();
           for (int i = 0; i < liste_utilisateurs.size(); i++) {
               System.out.println(liste_utilisateurs.get(i).toString());
           }
        //    System.out.println(ps.recupererById(3));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    
     //ajout d'un tutilisateur
   /*
     Utilisateur util=new Utilisateur(14,"hajer","hhhhh","hajerhhhh12","777777","hajer_hajer@gmail.com","F","élève");
              try {
                     
           util_service.ajouter_utilisateur(util);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        */
   
   //modification d'un utilisateur
   /*
   Utilisateur util2=new Utilisateur(7,17,"hajer","malki","hajerhhhh7","123456789","hajer_hajer@gmail.com","F","enseignant");
              try {
                     
         util_service.modifier_utilisateur(util2);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
             
      */

   //récupérer un utilisateur selon son email
  /*
   System.out.println("récupérer un utilisateur selon son email");
    try {
              Utilisateur utilisateur=util_service.recuperer_utilisateur_par_email("zouhour.kharraf1@esprit.tn");
              
               System.out.println(utilisateur.toString());
       
       
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      System.out.println("fin récupérer un utilisateur selon son email");
   */
 
  //récupérer un utilisateur selon son pseudo
  
   System.out.println("récupérer un utilisateur selon son pseudo");
    try {
              Utilisateur utilisateur=util_service.recuperer_utilisateur_par_pseudo("benamerhkkkajer5");
              
               System.out.println(utilisateur.toString());
       
       
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      System.out.println("fin récupérer un utilisateur selon son pseudo");
   
  
        }
    
     
}
