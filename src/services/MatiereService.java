/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.matiere;
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
 * @author Skand
 */
public class MatiereService implements IService<matiere> {

    Connection cnx;

    public MatiereService() {
        cnx = MyDB.getInstance().getCnx();
    }
  

   
      public void ajouter(matiere m) throws SQLException {
        String req = "insert into matiere(nom_mat) values('" + m.getNmatiere() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);

    }

    

    public void modifier(matiere m) throws SQLException {
        String req = "update matiere set nom_mat=? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);

        ps.setString(1, m.getNmatiere());
        

        ps.executeUpdate();

    }

  
    public void supprimer(matiere m) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   public List<matiere> recuperer() throws SQLException {
        List<matiere> matieres = new ArrayList<>();
        String req = "select * from matiere";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            matiere p = new matiere();
            p.setId(rs.getInt("id"));
            p.setNmatiere(rs.getString("nom_mat"));
          

            matieres.add(p);
        }
        return matieres;
    }

    public matiere recupererById(int id) throws SQLException {
        String req = "select count(*) as nbr from matiere where id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        matiere p = new matiere();
        rs.next();
        p.setId(rs.getInt("id"));
        p.setNmatiere(rs.getString("nom_mat"));
        rs.getInt("nbr");

        return p;
    }
}
