/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.Utilisateur;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import org.mindrot.jbcrypt.BCrypt;
import services.Mail;
import services.ServicesUtilisateur;

import t2s.son.LecteurTexte;

/**
 *
 * @author MMD
 */
public class Test {
     
    
    public static void main(String[] args) {
        
     ServicesUtilisateur util_service= new ServicesUtilisateur(); //appel de la classe service
   
    //affichage de tous les utilisateurs
    /*
                try {
                      List<Utilisateur> liste_utilisateurs=util_service.recuperer_liste_utilisateur();
           for (int i = 0; i < liste_utilisateurs.size(); i++) {
               System.out.println(liste_utilisateurs.get(i).toString());
           }
        //    System.out.println(ps.recupererById(3));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    */
    
/*
    //affichage des utilisateurs selon role 
    
                try {
                      List<Utilisateur> liste_utilisateurs=util_service.recuperer_liste_utilisateur_selon_role("administrateur");
           for (int i = 0; i < liste_utilisateurs.size(); i++) {
               System.out.println(liste_utilisateurs.get(i).toString());
           }
        //    System.out.println(ps.recupererById(3));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    //testé -----------> ça marche
   */
    
 //affichage des utilisateurs selon age
    /*
                try {
                      List<Utilisateur> liste_utilisateurs=util_service.recuperer_liste_utilisateur_selon_age("14","18","","");
           for (int i = 0; i < liste_utilisateurs.size(); i++) {
               System.out.println(liste_utilisateurs.get(i).toString());
           }
        //    System.out.println(ps.recupererById(3));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    //testé -----------> ça marche pour tous les cas (age_min,age_max,age_min_seulement,age_max_seulement)

    */
    
    /*
    
    //affichage des utilisateurs selon age et role
    
                try {
                      List<Utilisateur> liste_utilisateurs=util_service.recuperer_liste_utilisateur_selon_age_et_role("enseignant","","","","");
           for (int i = 0; i < liste_utilisateurs.size(); i++) {
               System.out.println(liste_utilisateurs.get(i).toString());
           }
        //    System.out.println(ps.recupererById(3));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   

    //testé -----------> ça marche pour tous les cas (role,age_min,age_max,age_min_seulement,age_max_seulement)
    
    */

     //ajout d'un tutilisateur
   /*
     Utilisateur util;
        util = new Utilisateur(9,"mmmmmm","maeiem","zz44","777777","hajer_hajer@gmail.com","F","élève","non");
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
  /*
   System.out.println("récupérer un utilisateur selon son pseudo");
    try {
              Utilisateur utilisateur=util_service.recuperer_utilisateur_par_pseudo("benamerhkkkajer5");
              
               System.out.println(utilisateur.toString());
       
       
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      System.out.println("fin récupérer un utilisateur selon son pseudo");
   
  */
        
  
   //récupérer un utilisateur selon son id
  /*
   System.out.println("récupérer un utilisateur selon son id");
    try {
              Utilisateur utilisateur=util_service.recuperer_utilisateur_par_id(7);
              
               System.out.println(utilisateur.toString());
       
       
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      System.out.println("fin récupérer un utilisateur selon son id");
   */
  
        
  
    //suppression 
    /*
       try {
     Utilisateur utilisateur=util_service.recuperer_utilisateur_par_pseudo("hhhhhatem21");
              util_service.supprimer_utilisateur(utilisateur);
              
             System.out.println("succès");
       
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   */  
   
    /*
    //  ***********************   le hachage des mots de passe
          //faire le hachage du mot de passe evec l'algorithme bcrypt  
     
          String mp_hache1 = BCrypt.hashpw( "123456Aa" , BCrypt.gensalt(13));
            
         System.out.println(mp_hache1);

     
          
   // Vérification d'un mot de passe à partir du hash
	
   if(BCrypt.checkpw("123456Aa","$2y$13$9h7A/O6JswYcrtrEt0mGeOFhSPBJTF/l/0zeNX84N9NSePDtOfAyu") )
   {
   System.out.println("It matches");
   }
   else
   {
       System.out.println("It does not match");
   }
*/
     

    //  ***********************  Fin hachage des mots de passe

    
    
  // ******************** Test : Envoyer un email avec GMAIL **********************
 /*
 //  String texte = "<H1>bonjour</H1><a href=\"mailto:moi@moi.fr\">mail</a>";
  //String texte2 = "<h4>Bonjour Monsieur</h4><h4>Abbess Ali</h4><h4>Vore code est : 10000</h4><h4>L'équipe Magic Book</h4>";
Mail.send("magicbook835@gmail.com", "yijfdvaakbioplfg", "zouhour.kharraf1@esprit.tn", "Magic Book : Récupération du mot de passe", texte2); //send est une méthode statique 
*/
//testé ---->ça marche avec le format html


  // ******************** FIN Test : Envoyer un email avec GMAIL **********************  

  
  
/*  
  Random rand = new Random(); // créer un  objet de type Random
   int Code = rand.nextInt(99999 - 10000 + 1) + 10000; //générer un nombre aléatoire entre 10000 et 99999
        System.out.println(Code);

*/

// *************** Test API syntétisuer vocal *************************
/*
 LecteurTexte lecteur = new LecteurTexte("bonjour");
 lecteur.playAll();
 lecteur.setTexte("je suis un synthétiseur vocal, qui êtes-vous?");
 lecteur.playAll();
*/
 //testé ---->ça marche
// *************** FIN Test API syntétisuer vocal *************************




    } 
    

}
