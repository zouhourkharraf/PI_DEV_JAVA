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
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.time.LocalDate;
import javax.swing.JOptionPane;
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
        if (!validateInput(a.getNomAct(),a.getPositionAct(),a.getNbParticipants(),a.getDateAct(),a.getType())) {
        return;
    }
    String req = "INSERT INTO activite (nomact, positionact, dateact, nbparticipants, typeid) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setString(1, a.getNomAct());
    st.setString(2, a.getPositionAct());
    st.setDate(3, new java.sql.Date(a.getDateAct().getTime()));
    st.setInt(4, a.getNbParticipants());
    st.setInt(5, a.getType());
    
    st.executeUpdate();
    // Fermer la connexion à la base de données
    //st.close();
    //cnx.close();
    }
    
    
    
    
    @Override
    public void modifier(Activite a) throws SQLException {
    // Vérifier si l'ID existe
    String req_select = "SELECT id FROM activite WHERE id = ?";
    PreparedStatement st_select = cnx.prepareStatement(req_select);
    st_select.setInt(1, a.getId());
    ResultSet rs = st_select.executeQuery();
    if (!rs.next()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Activité n'existe pas");
        alert.showAndWait();
        return;
    }
    
    // Mettre à jour l'activité
    String req_update = "UPDATE activite SET nomact = ?, positionact = ?, dateact = ?, nbparticipants = ?, typeid = ? WHERE id = ?";
    PreparedStatement st_update = cnx.prepareStatement(req_update);
    st_update.setString(1, a.getNomAct());
    st_update.setString(2, a.getPositionAct());
    st_update.setDate(3, new java.sql.Date(a.getDateAct().getTime()));
    st_update.setInt(4, a.getNbParticipants());
    st_update.setInt(5, a.getType());
    st_update.setInt(6, a.getId());
    if (!validateInput(a.getNomAct(),a.getPositionAct(),a.getNbParticipants(),a.getDateAct(),a.getType())) {
        return;
    }
    st_update.executeUpdate();
}
    
//    @Override
//    public void modifier(Activite a) throws SQLException {
//    String req = "UPDATE activite SET nomact = ?, positionact = ?, dateact = ?, nbparticipants = ? WHERE id = ?";
//    PreparedStatement st = cnx.prepareStatement(req);
//
//    st.setString(1, a.getNomAct());
//    st.setString(2, a.getPositionAct());
//    st.setDate(3, new java.sql.Date(a.getDateAct().getTime()));
//    st.setInt(4, a.getNbParticipants());
//    st.setInt(5, a.getType());
//    st.setInt(5, a.getId());
//
//    st.executeUpdate();
//}

    @Override
    public void supprimer(int id) throws SQLException {
    String verifReq = "select * from activite where id = ?";
    PreparedStatement verifPs = cnx.prepareStatement(verifReq);
    verifPs.setInt(1, id);
    ResultSet rs = verifPs.executeQuery();

    if (!rs.next()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("activite n'existe pas !");
        alert.showAndWait();
        return;
    }

    String req = "delete from type where id = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setInt(1, id);
    ps.executeUpdate();
    System.out.println("Suppression effectuée avec succès.");
}
    
    
     @Override
    public List<Activite> recuperer() throws SQLException {
    List<Activite> activites = new ArrayList<>();
    String req = "select * from activite";
    
    //String req = "select id, nomact, positionact from activite";
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
        a.setType(rs.getInt("typeid"));
        activites.add(a);
    }
     return activites;
    }
    
    
    
    
    public Activite recupererActiviteById(int id) throws SQLException{
    String req = "SELECT a.*, t.nomtype, t.descriptiontype FROM activite a JOIN type t ON a.typeid = t.id WHERE a.id = ?";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setInt(1, id);
    ResultSet rs = st.executeQuery();
    Activite activite = new Activite();
    if (rs.next()) {
        activite = new Activite();
        activite.setId(rs.getInt("id"));
        activite.setDateAct(rs.getDate("dateact"));
        activite.setNbParticipants(rs.getInt("nbparticipants"));
        activite.setPositionAct(rs.getString("positionact"));
        activite.setType(rs.getInt("typeid"));
        activite.setNomAct(rs.getString("nomact"));
        activite.setNomType(rs.getString("nomtype"));
        activite.setDescriptionType(rs.getString("descriptiontype"));
    }
    return activite;
}


//     public Activite recupererActiviteById(int id) throws SQLException{
//        String req = "SELECT * FROM activite WHERE id = ?";
//        PreparedStatement st = cnx.prepareStatement(req);
//        st.setInt(1, id);
//        ResultSet rs = st.executeQuery();
//        Activite activite = new Activite();
//       if (rs.next()) {
//        activite = new Activite();
//        activite.setId(rs.getInt("id"));
//        activite.setDateAct(rs.getDate("dateact"));
//        activite.setNbParticipants(rs.getInt("nbparticipants"));
//        activite.setPositionAct(rs.getString("positionact"));
//        activite.setType(rs.getInt("typeid"));
//        activite.setNomAct(rs.getString("nomact"));
//    }
//        
//        
//        return activite;
//    }
     
      public boolean validateInput(String nom, String position, int nb, Date date, int idtype){
          if(nom.trim().isEmpty() || position.trim().isEmpty() || date == null){
            Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return false;  
          }
          if(nb<=0){
              Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Nombre de participants doit être positif !");
        alert.showAndWait();
        return false;
          }
          Date currentDate = new Date();
          if(date.before(currentDate)){
              Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("La date doit être supérieure à la date actuelle !");
        alert.showAndWait();
        return false;
          }
          return true;
      }
      
     public List<Activite> recupererActType() throws SQLException {
    List<Activite> activites = new ArrayList<>();
    //String req = "SELECT a.*, t.nomtype, t.descriptiontype FROM activite a JOIN type t ON a.typeid = t.id";
    String req = "SELECT a.*, t.nomtype, t.descriptiontype \n" +
"FROM activite a \n" +
"LEFT JOIN type t ON a.typeid = t.id";
    Statement st = cnx.createStatement();
    ResultSet rs = st.executeQuery(req);
    while(rs.next()) {
        Activite a = new Activite();
        a.setId(rs.getInt("id"));
        a.setNomAct(rs.getString("nomact"));
        a.setPositionAct(rs.getString("positionact"));
        a.setDateAct(rs.getDate("dateact"));
        a.setNbParticipants(rs.getInt("nbparticipants"));
        a.setType(rs.getInt("typeid"));
        a.setNomType(rs.getString("nomtype"));
        a.setDescriptionType(rs.getString("descriptiontype"));
        activites.add(a);
    }
    return activites;
    }  
     
//    public void participerActivite(Activite activite) {
//    try {
//        //Connection cnx = MyDB.getInstance().getConnection();
//        // Vérifier si l'utilisateur a déjà participé à l'activité
//        int idUtilisateur = 2;
//        String query = "SELECT COUNT(*) FROM utilisateur_activite WHERE id_utilisateur=? AND id_activite=?";
//        PreparedStatement pst = cnx.prepareStatement(query);
//        pst.setInt(1, idUtilisateur); // Remplacer idUtilisateur par l'ID de l'utilisateur connecté
//        //pst.setInt(1, 2); // Remplacer idUtilisateur par l'ID de l'utilisateur connecté
//        pst.setInt(2, activite.getId());
//        ResultSet rs = pst.executeQuery();
//        if (rs.next() && rs.getInt(1) > 0) {
//            // L'utilisateur a déjà participé à l'activité, afficher un message d'erreur ou faire une autre action
//            System.out.println("Vous avez déjà participé à cette activité !");
//        } else {
//            // L'utilisateur n'a pas encore participé à l'activité, ajouter une ligne dans la table utilisateur_activite
//            query = "INSERT INTO utilisateur_activite (id_utilisateur, id_activite) VALUES (?, ?)";
//            pst = cnx.prepareStatement(query);
//            pst.setInt(1, idUtilisateur); // Remplacer idUtilisateur par l'ID de l'utilisateur connecté
//           // pst.setInt(1, 2); // Remplacer idUtilisateur par l'ID de l'utilisateur connecté
//            pst.setInt(2, activite.getId());
//            pst.executeUpdate();
//            // Afficher un message de confirmation ou faire une autre action
//            System.out.println("Vous avez participé à l'activité " + activite.getNomAct() + " !");
//        }
//    } catch (SQLException ex) {
//        System.out.println(ex);
//    }
//}

     
     public void participerActivite(Activite activite) {
    try {
        int utilisateur_id = 2;
        String query = "SELECT COUNT(*) FROM utilisateur_activite WHERE utilisateur_id=? AND activite_id=?";
        PreparedStatement pst = cnx.prepareStatement(query);
        pst.setInt(1, utilisateur_id);
        pst.setInt(2, activite.getId());
        ResultSet rs = pst.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            //System.out.println("Vous avez déjà participé à cette activité !");
            JOptionPane.showMessageDialog(null, "Erreur!!  Vous avez déjà participé à cette activité !", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            query = "INSERT INTO utilisateur_activite (utilisateur_id, activite_id) VALUES (?, ?)";
            pst = cnx.prepareStatement(query);
            pst.setInt(1, utilisateur_id);
            pst.setInt(2, activite.getId());
            pst.executeUpdate();
            //System.out.println("Vous avez participé à l'activité " + activite.getNomAct() + " !");
            JOptionPane.showMessageDialog(null, "Succés! Vous avez  participé à l'activité " + activite.getNomAct() + " !", "Message de confirmation", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}


}
