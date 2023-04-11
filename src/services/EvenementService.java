/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyDB;

/**
 *
 * @author Skand
 */
public class EvenementService implements IService<Evenement> {

    Connection cnx;

    public EvenementService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Evenement t) throws SQLException {
        String req = "insert into evenement(nom_ev,lieu_ev,dated_ev,datef_ev,lieu_ev,desc_ev,image_ev) values('" + t.getNom_ev()+ "','" + t.getDated_ev()+ "','" + t.getDatef_ev()+ "','" + t.getLieu_ev()+ "'," + t.getDesc_ev()+ "','" + t.getImage_ev()+ ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);

    }

    @Override
    public void modifier(Evenement t) throws SQLException {
        String req = "update evenement set nom_ev = ?, dated_ev = ?, datef_ev = ?, lieu_ev = ?, desc_ev = ?, image_ev = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        LocalDate datedEv = LocalDate.parse(t.getDate("dated_ev").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate datefEv = LocalDate.parse(t.getDate("datef_ev").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        ps.setString(1, t.getNom_ev());
        ps.setDate(2, java.sql.Date.valueOf(t.getDated_ev()));
        ps.setDate(3, java.sql.Date.valueOf(t.getDatef_ev()));        
        ps.setString(4, t.getLieu_ev());
        ps.setString(5, t.getDesc_ev());
        ps.setString(6, t.getImage_ev());
        ps.setInt(7, t.getId());

        ps.executeUpdate();

    }

    public void updateevent (Evenement a){
         String req = "update evenement set nom=?, description=?, categorie=?, date=?, image=?, prix=?,";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, a.getNom_ev());
        pst.setDate(2, java.sql.Date.valueOf(a.getDated_ev()));
        pst.setDate(3, java.sql.Date.valueOf(a.getDatef_ev()));        
        pst.setString(4, a.getLieu_ev());
        pst.setString(5, a.getDesc_ev());
        pst.setString(6, a.getImage_ev());
        pst.setInt(7, a.getId()); 
   
           
            pst.execute();
        } catch (Exception e) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, e);
        }
        }
    @Override
    public void supprimer(Evenement t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evenement> recuperer()  {
        List<Evenement> evenements = new ArrayList<>();
        String req = "select * from evenement";
        try{
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Evenement p = new Evenement();
            LocalDate datedEv = LocalDate.parse(rs.getDate("dated_ev").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate datefEv = LocalDate.parse(rs.getDate("datef_ev").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            p.setId(rs.getInt("id"));
            p.setNom_ev(rs.getString("nom_ev"));
            p.setDated_ev(datedEv);
            p.setDatef_ev(datefEv);
            p.setLieu_ev(rs.getString("lieu_ev"));
            p.setDesc_ev(rs.getString("desc_ev"));
            p.setImage_ev(rs.getString("image_ev"));

            evenements.add(p);
        }
        return evenements;
        }catch(SQLException ex){
        ex.printStackTrace();
        }
        return null;
    }

    public Evenement recupererById(int id) throws SQLException {
        String req = "select count(*) as nbr from evenement where id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        Evenement p = new Evenement();
        LocalDate datedEv = LocalDate.parse(rs.getDate("dated_ev").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate datefEv = LocalDate.parse(rs.getDate("datef_ev").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        rs.next();
        p.setId(rs.getInt("id"));
        p.setNom_ev(rs.getString("nom_ev"));
        p.setDated_ev(datedEv);
        p.setDatef_ev(datefEv);
        p.setLieu_ev(rs.getString("lieu_ev"));
        p.setDesc_ev(rs.getString("desc_ev"));
        p.setImage_ev(rs.getString("image_ev"));
        
        
        rs.getInt("nbr");

        return p;
    }

}
