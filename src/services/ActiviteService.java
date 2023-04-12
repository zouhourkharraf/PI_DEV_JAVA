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
    String req_update = "UPDATE activite SET nomact = ?, positionact = ?, dateact = ?, nbparticipants = ? WHERE id = ?";
    PreparedStatement st_update = cnx.prepareStatement(req_update);
    st_update.setString(1, a.getNomAct());
    st_update.setString(2, a.getPositionAct());
    st_update.setDate(3, new java.sql.Date(a.getDateAct().getTime()));
    st_update.setInt(4, a.getNbParticipants());
    st_update.setInt(5, a.getId());
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
//    //st.setInt(5, a.getType());
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
        String req = "SELECT * FROM activite WHERE id = ?";
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
    }
        
        
        return activite;
    }
     
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
     
     /*
     public boolean validateInput(String nom, String position, int nb, Date date, int idtype) {
         // Vérifier que le nom n'est pas vide
    if (nom == null || nom.isEmpty()) {
        showAlert("Le champ nom est obligatoire.",Alert.AlertType.ERROR);
        return false;
    }
    
    // Vérifier que la position n'est pas vide
    if (position == null || position.isEmpty()) {
        showAlert("Le champ position est obligatoire.", Alert.AlertType.ERROR);
        return false;
    }
    
    // Vérifier que le nombre est positif
    if (nb <= 0) {
        showAlert("Le nombre doit être positif.",Alert.AlertType.ERROR);
        return false;
    }
    
    // Vérifier que la date est renseignée
    if (date == null) {
        showAlert("Le champ date est obligatoire.", Alert.AlertType.ERROR);
        return false;
    }
    
    // Vérifier que l'id de type est positif
    if (idtype <= 0) {
        showAlert("L'identifiant de type doit être positif.", Alert.AlertType.ERROR);
        return false;
    }
    
    // Si toutes les validations sont passées, retourner true
    return true;
    
}
     private void showAlert(String message, Alert.AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}*/
}
