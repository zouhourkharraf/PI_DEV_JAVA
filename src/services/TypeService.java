/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
public class TypeService implements IService<Type> {
    Connection cnx;
    
    public TypeService(){
        cnx = MyDB.getInstance().getCnx();
    }
    /*
    @Override
    public void ajouter(Type t) throws SQLException{
        //String req = "insert into type(nomtype,descriptiontype) values('"+ t.getNomType()+"','"+t.getDescriptionType()+")";
        String req = "insert into personne(nomtype,descriptiontype) values('" + t.getNomType() + "','" + t.getDescriptionType() + ")";
       // String req = "INSERT INTO type (nomtype, descriptiontype) VALUES (?, ?)";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }*/
    @Override
    public void ajouter(Type t) throws SQLException {
    String req = "INSERT INTO type (nomtype, descriptiontype) VALUES (?, ?)";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setString(1, t.getNomType());
    st.setString(2, t.getDescriptionType());
    st.executeUpdate();
}
    
    @Override
    public void modifier(Type t) throws SQLException{
        String req = "update type set nomtype = ?, descriptiontype = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setString(1, t.getNomType());
        ps.setString(2, t.getDescriptionType());
        ps.setInt(3, t.getId());
        
        ps.executeUpdate();
    }
    @Override
    public void supprimer(int id) throws SQLException {
    String req = "DELETE FROM type WHERE id = ?";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setInt(1, id);
    st.executeUpdate();
    }
    
    @Override
    public List<Type> recuperer() throws SQLException{
        List<Type> types = new ArrayList<>();
        String req = "select * from type";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Type t = new Type();
            t.setId(rs.getInt("id"));
            t.setNomType(rs.getString("nomtype"));
            t.setDescriptionType(rs.getString("descriptiontype"));
            
            types.add(t);
        }
        return types;
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
