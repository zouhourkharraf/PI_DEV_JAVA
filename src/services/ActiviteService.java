/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Activite;
import entities.Type;



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
 * @author farah
 */
public class ActiviteService implements IService<Activite> {
    
    Connection cnx;
    
    public ActiviteService(){
        cnx = MyDB.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Activite a) throws SQLException {
    String req = "INSERT INTO activite (nomact, positionact, dateact, nbparticipants, typeid) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setString(1, a.getNomAct());
    st.setString(2, a.getPositionAct());
    st.setDate(3, new java.sql.Date(a.getDateAct().getTime()));
    st.setInt(4, a.getNbParticipants());
    st.setInt(5, a.getType().getId());
    st.executeUpdate();
    // Fermer la connexion à la base de données
    st.close();
    cnx.close();
    }
    
    @Override
    public void modifier(Activite a) throws SQLException {
    String req = "UPDATE activite SET nomact = ?, positionact = ?, dateact = ?, nbparticipants = ?, typeid = ? WHERE id = ?";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setString(1, a.getNomAct());
    st.setString(2, a.getPositionAct());
    st.setDate(3, new java.sql.Date(a.getDateAct().getTime()));
    st.setInt(4, a.getNbParticipants());
    st.setInt(5, a.getType().getId());
    st.setInt(6, a.getId());
    st.executeUpdate();
}
    @Override
    public void supprimer(int id) throws SQLException {
    String req = "DELETE FROM activite WHERE id = ?";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setInt(1, id);
    st.executeUpdate();
    }
    
     @Override
    public List<Activite> recuperer() throws SQLException {
    List<Activite> activites = new ArrayList<>();
    String req = "select * from activite";
    Statement st = cnx.createStatement();
    ResultSet rs = st.executeQuery(req);
    while(rs.next())
    {
        Activite a = new Activite();
        a.setId(rs.getInt("id"));
        a.setNomAct(rs.getString("nomact"));
        a.setPositionAct(rs.getString("positionact"));
        a.setDateAct(rs.getDate("dateact"));
        a.setNbParticipants(rs.getInt("nbparticipants"));

        int typeId = rs.getInt("typeid");
        Type type = recupererTypeById(typeId);
        a.setType(type);

        activites.add(a);
    }
     return activites;
    }

     public Type recupererTypeById(int id) throws SQLException{
        String req = "select * from type where id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        Type type = new Type();
       if (rs.next()) {
        type = new Type();
        type.setId(rs.getInt("id"));
        type.setNomType(rs.getString("nomtype"));
        type.setDescriptionType(rs.getString("descriptiontype"));
    }
        
        
        return type;
    }
}
