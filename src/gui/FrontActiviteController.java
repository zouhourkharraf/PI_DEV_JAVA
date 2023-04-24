/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Activite;
import entities.Type;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import services.ActiviteService;

import gui.ModelActiviteController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.scene.Parent;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import util.MyDB;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javax.swing.JOptionPane;

import util.MyDB;



/**
 * FXML Controller class
 *
 * @author farah
 */
public class FrontActiviteController implements Initializable {
    private List<ModelActiviteController> controllers = new ArrayList<>();
    
    private ScrollPane scroll;
    @FXML
    private TableView<Activite> tableviewFront;
    @FXML
    private TableColumn<Activite, String> nomFront;
    @FXML
    private TableColumn<Activite, Date> dateFront;
    @FXML
    private TableColumn<Activite, String> positionFront;
    @FXML
    private TableColumn<Activite, Integer> nbFront;
    @FXML
    private TableColumn<Activite, String> typeFront;
    @FXML
    private TableColumn<Activite, String> descriptionFront;
    @FXML
    private TableColumn<Activite, Button> participerButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        participerButton.setCellFactory(column -> new TableCell<Activite, Button>() {
        final Button button = new Button("Participer");

        @Override
        protected void updateItem(Button item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(button);
               button.setOnAction(event -> {
                try {
                    Activite activite = getTableView().getItems().get(getIndex());
                    participerActivite(activite);
                } catch (SQLException ex) {
                    Logger.getLogger(FrontActiviteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            }
        }
    });
        
        afficherListeActivites();
*/
         ActiviteService activiteService = new ActiviteService();
        
        participerButton.setCellFactory(column -> new TableCell<Activite, Button>() {
        final Button button = new Button("Participer");

        @Override
        protected void updateItem(Button item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(button);
                button.setOnAction(event -> {
                    Activite activite = getTableView().getItems().get(getIndex());
                    //activiteService.participerActivite(activite);
                    participerActivite(activite);
                    //tableviewFront.refresh();
                });
            }
        }
    });

    afficherListeActivites();
    }
    
    public void afficherListeActivites() {
    try {
        ActiviteService as = new ActiviteService();
        List<Activite> activites = as.recupererActType();
        
        ObservableList<Activite> obs = FXCollections.observableArrayList(activites);
        
        tableviewFront.setItems(obs);
        
        nomFront.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomAct()));
        nbFront.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNbParticipants()).asObject());
        positionFront.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPositionAct()));
        dateFront.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDateAct()));
        typeFront.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomType()));
        descriptionFront.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescriptionType()));
        

        
    } catch(SQLException ex) {
        System.out.println(ex);
    }
    }
    
    
    
    public void participerActivite(Activite activite) {
    try {
        Connection cnx = MyDB.getInstance().getCnx();
        int utilisateur_id = 11;
        String query = "SELECT COUNT(*) FROM utilisateur_activite WHERE utilisateur_id=? AND activite_id=?";
        PreparedStatement pst = cnx.prepareStatement(query);
        pst.setInt(1, utilisateur_id);
        pst.setInt(2, activite.getId());
        ResultSet rs = pst.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            JOptionPane.showMessageDialog(null, "Erreur!!  Vous avez déjà participé à cette activité !", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            query = "SELECT nbparticipants FROM activite WHERE id=?";
            pst = cnx.prepareStatement(query);
            pst.setInt(1, activite.getId());
            rs = pst.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                query = "INSERT INTO utilisateur_activite (utilisateur_id, activite_id) VALUES (?, ?)";
                pst = cnx.prepareStatement(query);
                pst.setInt(1, utilisateur_id);
                pst.setInt(2, activite.getId());
                pst.executeUpdate();
                query = "UPDATE activite SET nbparticipants = nbparticipants - 1 WHERE id = ?";
                pst = cnx.prepareStatement(query);
                pst.setInt(1, activite.getId());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Succés! Vous avez participé à l'activité " + activite.getNomAct() + " !", "Message de confirmation", JOptionPane.INFORMATION_MESSAGE);
                chargerDonnees();
                tableviewFront.refresh();
            } else {
                JOptionPane.showMessageDialog(null, "Erreur!! Le nombre de participants maximum a été atteint pour cette activité !", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}

    
    
//     public void participerActivite(Activite activite) {
//    try {
//        Connection cnx;
//        cnx = MyDB.getInstance().getCnx();
//        int utilisateur_id = 10;
//        String query = "SELECT COUNT(*) FROM utilisateur_activite WHERE utilisateur_id=? AND activite_id=?";
//        PreparedStatement pst = cnx.prepareStatement(query);
//        pst.setInt(1, utilisateur_id);
//        pst.setInt(2, activite.getId());
//        ResultSet rs = pst.executeQuery();
//        if (rs.next() && rs.getInt(1) > 0) {
//            //System.out.println("Vous avez déjà participé à cette activité !");
//            JOptionPane.showMessageDialog(null, "Erreur!!  Vous avez déjà participé à cette activité !", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
//        } else {
//            query = "INSERT INTO utilisateur_activite (utilisateur_id, activite_id) VALUES (?, ?)";
//            pst = cnx.prepareStatement(query);
//            pst.setInt(1, utilisateur_id);
//            pst.setInt(2, activite.getId());
//            pst.executeUpdate();
//            
//
//               query = "UPDATE activite SET nbparticipants = nbparticipants - 1 WHERE id = ?";
//            pst = cnx.prepareStatement(query);
//            pst.setInt(1, activite.getId());
//            pst.executeUpdate();
//            
//            
//            JOptionPane.showMessageDialog(null, "Succés! Vous avez  participé à l'activité " + activite.getNomAct() + " !", "Message de confirmation", JOptionPane.INFORMATION_MESSAGE);
//            chargerDonnees();
//            tableviewFront.refresh();
//        }
//    } catch (SQLException ex) {
//        System.out.println(ex);
//    }
//   }
     
     @FXML
     public void chargerDonnees(){
         try{
             Connection cnx = MyDB.getInstance().getCnx();
              String query = "SELECT a.*, t.nomtype, t.descriptiontype \n" +
              "FROM activite a \n" +
              "LEFT JOIN type t ON a.typeid = t.id";
              PreparedStatement pst = cnx.prepareStatement(query);
              ResultSet rs = pst.executeQuery();

             ObservableList<Activite> activites = FXCollections.observableArrayList();
             while(rs.next()) {
//                Activite a = new Activite();
//                a.setId(rs.getInt("id"));
//                a.setNomAct(rs.getString("nomact"));
//                a.setPositionAct(rs.getString("positionact"));
//                a.setDateAct(rs.getDate("dateact"));
//                a.setNbParticipants(rs.getInt("nbparticipants"));
//                a.setType(rs.getInt("typeid"));
//                a.setNomType(rs.getString("nomtype"));
//                a.setDescriptionType(rs.getString("descriptiontype"));
//                activites.add(a);
                  int id = rs.getInt("id");
                  String nomact = rs.getString("nomact");
                  String positionact = rs.getString("positionact");
                  Date dateact = rs.getDate("dateact");
                  int nbparticipants = rs.getInt("nbparticipants");
                  int typeid = rs.getInt("typeid");
                  String nomtype = rs.getString("nomtype");
                  String descriptiontype = rs.getString("descriptiontype");
                  activites.add(new Activite(id,nomact,positionact,dateact,nbparticipants,typeid,nomtype,descriptiontype));
            }
             
             tableviewFront.setItems(activites);
        
        nomFront.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomAct()));
        nbFront.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNbParticipants()).asObject());
        positionFront.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPositionAct()));
        dateFront.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDateAct()));
        typeFront.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomType()));
        descriptionFront.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescriptionType()));
             

         }catch(SQLException ex){
             System.out.println(ex);
         }
     }

}

 
        
    

