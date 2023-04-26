/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.MyDB;

/**
 *
 * @author MMD
 */
public class ServicesUtilisateur implements InterfaceServiceUtilisateur<Utilisateur> {

     Connection cnx;

    public ServicesUtilisateur() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    
    @Override
    public void ajouter_utilisateur(Utilisateur u) throws SQLException {
             
     
        String req;
         req = "INSERT INTO utilisateur(nom_util,prenom_util,pseudo_util,mot_de_passe_util,email_util,age_util,genre_util,role_util,demande_suppression) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, u.getNom_util());
        ps.setString(2, u.getPrenom_util());
        ps.setString(3, u.getPseudo_util());
        ps.setString(4, u.getMot_de_passe_util());
        ps.setString(5, u.getEmail_util());
        ps.setInt(6, u.getAge_util());
        ps.setString(7, u.getGenre_util());
        ps.setString(8, u.getRole_util());
        ps.setString(9, u.getDemande_suppression());
        
        ps.executeUpdate();
        
    }
    
    @Override
    public void modifier_utilisateur(Utilisateur u) throws SQLException {
         String req = "update utilisateur set nom_util= ?,prenom_util= ?,pseudo_util= ?,mot_de_passe_util= ?,email_util= ?,age_util= ?,genre_util= ?,role_util= ? ,demande_suppression= ? where id= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, u.getNom_util());
        ps.setString(2, u.getPrenom_util());
        ps.setString(3, u.getPseudo_util());
        ps.setString(4, u.getMot_de_passe_util());
        ps.setString(5, u.getEmail_util());
        ps.setInt(6, u.getAge_util());
        ps.setString(7, u.getGenre_util());
        ps.setString(8, u.getRole_util());
        ps.setString(9, u.getDemande_suppression());
         ps.setInt(10, u.getId());
        ps.executeUpdate();
    }

    @Override
    public void supprimer_utilisateur(Utilisateur u) throws SQLException {
   
     //   String id_string=String.valueOf(u.getId());
        // String req = "delete from utilisateur where id="+id_string;
       String req = "DELETE FROM utilisateur WHERE id=?";
 
        PreparedStatement ps = cnx.prepareStatement(req);
       ps.setInt(1, u.getId());
 
         int rowsDeleted = ps.executeUpdate();
   if (rowsDeleted > 0) {
    System.out.println("A user was deleted successfully!");
    
}
       
    }

    @Override
    public List<Utilisateur> recuperer_liste_utilisateur() throws SQLException {
          List<Utilisateur> liste_utilisateurs = new ArrayList<>();
        String req = "select * from utilisateur";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Utilisateur utilisateur1 = new Utilisateur();
         utilisateur1.setId(rs.getInt("id"));
         utilisateur1.setNom_util(rs.getString("nom_util"));
         utilisateur1.setPrenom_util(rs.getString("prenom_util"));
         utilisateur1.setPseudo_util(rs.getString("pseudo_util"));
         utilisateur1.setMot_de_passe_util(rs.getString("mot_de_passe_util"));
         utilisateur1.setEmail_util(rs.getString("email_util"));
         utilisateur1.setAge_util(rs.getInt("age_util"));
         utilisateur1.setGenre_util(rs.getString("genre_util"));
         utilisateur1.setRole_util(rs.getString("role_util"));
         utilisateur1.setDemande_suppression(rs.getString("demande_suppression"));
         
         liste_utilisateurs.add(utilisateur1);
        
        }
        return liste_utilisateurs;
    }
        
        //cette méthode permer de chercher un utilisateur par son email
     public Utilisateur recuperer_utilisateur_par_email(String email2) throws SQLException
     {
         Utilisateur util=new Utilisateur();
         String req="select * from utilisateur where email_util=?";
         PreparedStatement st = cnx.prepareStatement(req);
         st.setString(1, email2);
         ResultSet rs = st.executeQuery();
         rs.next();
         util.setId(rs.getInt("id"));
         util.setNom_util(rs.getString("nom_util"));
         util.setPrenom_util(rs.getString("prenom_util"));
         util.setPseudo_util(rs.getString("pseudo_util"));
         util.setMot_de_passe_util(rs.getString("mot_de_passe_util"));
         util.setEmail_util(rs.getString("email_util"));
         util.setAge_util(rs.getInt("age_util"));
         util.setGenre_util(rs.getString("genre_util"));
         util.setRole_util(rs.getString("role_util"));
         util.setDemande_suppression(rs.getString("demande_suppression"));
         return util;
         
     }   
        
    //cette méthode permer de chercher un utilisateur par son pseudo
     public Utilisateur recuperer_utilisateur_par_pseudo(String pseudo2) throws SQLException
     {
         Utilisateur util=new Utilisateur();
         String req="select * from utilisateur where pseudo_util=?";
         PreparedStatement st = cnx.prepareStatement(req);
         st.setString(1, pseudo2);
         ResultSet rs = st.executeQuery();
         
         if(rs.next())
         {
         util.setId(rs.getInt("id"));
         util.setNom_util(rs.getString("nom_util"));
         util.setPrenom_util(rs.getString("prenom_util"));
         util.setPseudo_util(rs.getString("pseudo_util"));
         util.setMot_de_passe_util(rs.getString("mot_de_passe_util"));
         util.setEmail_util(rs.getString("email_util"));
         util.setAge_util(rs.getInt("age_util"));
         util.setGenre_util(rs.getString("genre_util"));
         util.setRole_util(rs.getString("role_util"));
         util.setDemande_suppression(rs.getString("demande_suppression"));
         }
         else
         {
           util=null;
         }
         return util;
     }    
                 
    //cette méthode permer de chercher un utilisateur par son id
     public Utilisateur recuperer_utilisateur_par_id(int id2) throws SQLException
     {
         Utilisateur util=new Utilisateur();
         String req="select * from utilisateur where id=?";
         PreparedStatement st = cnx.prepareStatement(req);
         st.setInt(1, id2);
         ResultSet rs = st.executeQuery();
         rs.next();
         util.setId(rs.getInt("id"));
         util.setNom_util(rs.getString("nom_util"));
         util.setPrenom_util(rs.getString("prenom_util"));
         util.setPseudo_util(rs.getString("pseudo_util"));
         util.setMot_de_passe_util(rs.getString("mot_de_passe_util"));
         util.setEmail_util(rs.getString("email_util"));
         util.setAge_util(rs.getInt("age_util"));
         util.setGenre_util(rs.getString("genre_util"));
         util.setRole_util(rs.getString("role_util"));
         util.setDemande_suppression(rs.getString("demande_suppression"));
         
         return util;
         
     } 
     
    // ***************************** Méthodes du métier filtage des données ****************************************** 
     
   //  cette méthode permet de réccuoérer la liste des utilisateurs qui on le role "role" passé en paramètre
       public List<Utilisateur> recuperer_liste_utilisateur_selon_role(String role) throws SQLException {
          List<Utilisateur> liste_utilisateurs = new ArrayList<>();
        String req = "select * from utilisateur where role_util='"+role+"'";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Utilisateur utilisateur1 = new Utilisateur();
         utilisateur1.setId(rs.getInt("id"));
         utilisateur1.setNom_util(rs.getString("nom_util"));
         utilisateur1.setPrenom_util(rs.getString("prenom_util"));
         utilisateur1.setPseudo_util(rs.getString("pseudo_util"));
         utilisateur1.setMot_de_passe_util(rs.getString("mot_de_passe_util"));
         utilisateur1.setEmail_util(rs.getString("email_util"));
         utilisateur1.setAge_util(rs.getInt("age_util"));
         utilisateur1.setGenre_util(rs.getString("genre_util"));
         utilisateur1.setRole_util(rs.getString("role_util"));
         utilisateur1.setDemande_suppression(rs.getString("demande_suppression"));
         
         liste_utilisateurs.add(utilisateur1);
        
        }
        return liste_utilisateurs;
    }   
       
     
    //  cette méthode permet de réccupérer les utilisateurs selon les valeurs d'age passés en paramètres
       public List<Utilisateur> recuperer_liste_utilisateur_selon_age(String age_min,String age_max,String age_min_seulement,String age_max_seulement) throws SQLException {
          List<Utilisateur> liste_utilisateurs = new ArrayList<>();
          String req="";
          if(age_min_seulement.compareTo("") !=0 ) //---> si age_min_seulement n'est pas une chaine vide  donc on doit retouner les utilisateurs ayant comme age minnimale "age_min_seulement"
          {
           req = "select * from utilisateur where age_util>="+age_min_seulement;  //définir la requête
          }
          if(age_max_seulement.compareTo("") !=0)  //---> si age_max_seulement n'est pas une chaine vide donc on doit retouner les utilisateurs ayant comme age maximale "age_max_seulement"
          {
           req = "select * from utilisateur where age_util<="+age_max_seulement;  //définir la requête
          } 
          if( (age_min.compareTo("") !=0) && (age_max.compareTo("") !=0) ) //---> si age_min et age_max s ne sont pas vides(sont défini)  retouner les utilisateurs ayant un age entre age_min et age_max
          {
          req = "select * from utilisateur where age_util>="+age_min+" AND age_util<="+age_max;  //définir la requête
          }
    
        Statement st = cnx.createStatement();  //créer la requete
        ResultSet rs = st.executeQuery(req); //exécuter la requete
        while (rs.next()) {
            Utilisateur utilisateur1 = new Utilisateur();
         utilisateur1.setId(rs.getInt("id"));
         utilisateur1.setNom_util(rs.getString("nom_util"));
         utilisateur1.setPrenom_util(rs.getString("prenom_util"));
         utilisateur1.setPseudo_util(rs.getString("pseudo_util"));
         utilisateur1.setMot_de_passe_util(rs.getString("mot_de_passe_util"));
         utilisateur1.setEmail_util(rs.getString("email_util"));
         utilisateur1.setAge_util(rs.getInt("age_util"));
         utilisateur1.setGenre_util(rs.getString("genre_util"));
         utilisateur1.setRole_util(rs.getString("role_util"));
         utilisateur1.setDemande_suppression(rs.getString("demande_suppression"));
         
         liste_utilisateurs.add(utilisateur1);
        
        }
        return liste_utilisateurs;
    }       
     
        //  cette méthode permet de réccupérer les utilisateurs selon les valeurs d'age passés en paramètres et dont leurs role est le role "role1" passé en paramètre
       public List<Utilisateur> recuperer_liste_utilisateur_selon_age_et_role(String role1,String age_min,String age_max,String age_min_seulement,String age_max_seulement) throws SQLException {
          List<Utilisateur> liste_utilisateurs = new ArrayList<>();
          String req="";
          if(age_min_seulement.compareTo("") !=0 ) //---> si age_min_seulement n'est pas une chaine vide  donc on doit retouner les utilisateurs ayant comme age minnimale "age_min_seulement" et dont leurs role "role1"
          {
           req = "select * from utilisateur where age_util>="+age_min_seulement+" AND role_util='"+role1+"'";  //définir la requête
          }
          if(age_max_seulement.compareTo("") !=0)  //---> si age_max_seulement n'est pas une chaine vide donc on doit retouner les utilisateurs ayant comme age maximale "age_max_seulement" et dont leurs role "role1"
          {
           req = "select * from utilisateur where age_util<="+age_max_seulement+" AND role_util='"+role1+"'";  //définir la requête
          } 
          if( (age_min.compareTo("") !=0) && (age_max.compareTo("") !=0) ) //---> si age_min et age_max s ne sont pas vides(sont défini)  retouner les utilisateurs ayant un age entre age_min et age_max et dont leurs role "role1"
          {
          req = "select * from utilisateur where age_util>="+age_min+" AND age_util<="+age_max+" AND role_util='"+role1+"'";  //définir la requête
          }
    
        Statement st = cnx.createStatement();  //créer la requete
        ResultSet rs = st.executeQuery(req); //exécuter la requete
        while (rs.next()) {
            Utilisateur utilisateur1 = new Utilisateur();
         utilisateur1.setId(rs.getInt("id"));
         utilisateur1.setNom_util(rs.getString("nom_util"));
         utilisateur1.setPrenom_util(rs.getString("prenom_util"));
         utilisateur1.setPseudo_util(rs.getString("pseudo_util"));
         utilisateur1.setMot_de_passe_util(rs.getString("mot_de_passe_util"));
         utilisateur1.setEmail_util(rs.getString("email_util"));
         utilisateur1.setAge_util(rs.getInt("age_util"));
         utilisateur1.setGenre_util(rs.getString("genre_util"));
         utilisateur1.setRole_util(rs.getString("role_util"));
         utilisateur1.setDemande_suppression(rs.getString("demande_suppression"));
         
         liste_utilisateurs.add(utilisateur1);
        
        }
        return liste_utilisateurs;
    }  
       
       
       
       
     // ***************************** FIN Méthodes du métier filtage des données ****************************************** 
   
    
    
}
