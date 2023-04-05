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
             
     
        String req = "INSERT INTO utilisateur(nom_util,prenom_util,pseudo_util,mot_de_passe_util,email_util,age_util,genre_util,role_util) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, u.getNom_util());
        ps.setString(2, u.getPrenom_util());
        ps.setString(3, u.getPseudo_util());
        ps.setString(4, u.getMot_de_passe_util());
        ps.setString(5, u.getEmail_util());
        ps.setInt(6, u.getAge_util());
        ps.setString(7, u.getGenre_util());
        ps.setString(8, u.getRole_util());
        
        ps.executeUpdate();
        
    }

    @Override
    public void modifier_utilisateur(Utilisateur u) throws SQLException {
          String req = "UPDATE utilisateur SET nom_util=?,prenom_util=?,pseudo_util=?,mot_de_passe_util=?,email_util=?,age_util=?,genre_util=?,role_util=? WHERE id=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, u.getNom_util());
        ps.setString(2, u.getPrenom_util());
        ps.setString(3, u.getPseudo_util());
        ps.setString(4, u.getMot_de_passe_util());
        ps.setString(5, u.getEmail_util());
        ps.setInt(6, u.getAge_util());
        ps.setString(7, u.getGenre_util());
        ps.setString(8, u.getRole_util());
         ps.setInt(9, u.getId());
        ps.executeUpdate();
    }

    @Override
    public void supprimer_utilisateur(Utilisateur u) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
         
         
         liste_utilisateurs.add(utilisateur1);
         
            // appartient à l'ancien projet
           /*
            p.setId(rs.getInt("id"));
            p.setAge(rs.getInt("age"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
*/
 // appartient à l'ancien projet
 
      //      personnes.add(p);
        }
        return liste_utilisateurs;
        
        
    }
    
}
