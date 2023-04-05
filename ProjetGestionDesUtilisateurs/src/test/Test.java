/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.Utilisateur;
import java.sql.SQLException;
import java.util.ArrayList;
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
      Utilisateur util=new Utilisateur(39,17,"hajer","hh","hajerhhhh39","44444444444","hajer_hajer@gmail.com","F","élève");
              try {
                     
         util_service.modifier_utilisateur(util);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
             
             
        }
    
     
}
