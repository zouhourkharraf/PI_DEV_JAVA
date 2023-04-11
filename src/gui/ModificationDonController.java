/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Don;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModificationDonController implements Initializable {
    
       @FXML
    private TextField txttype;
    @FXML
    private TextField txtsomme;
    @FXML
    private ComboBox<Integer> cbxid;
    @FXML
    private Button btnmodifier;
    private Don don;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxid.setItems(getEvenementsIds());
       //modifierDon(ActionEvent,don);
    }
   
public void setDon(Don don) {
    // Pré-remplir les champs avec les données du don à modifier
    cbxid.setValue(don.getEvenement_id());
    txttype.setText(don.getType_don());
    txtsomme.setText(String.valueOf(don.getSomme_don()));
    this.don = don;
}

@FXML
private void handleModifier(ActionEvent event) throws IOException {
    
    
    
   // Don don = parentController.getSelectedDon().getSelectionModel().getSelectedItem();
    try {
        modifierDon(event,don);
    } catch (SQLException ex) {
    }
}
    
    
    private void modifierDon(ActionEvent event,Don don) throws SQLException, IOException {
        // Récupérer les nouvelles valeurs
        int evenement_id = cbxid.getValue();
        String type_don = txttype.getText();
        int somme_don = Integer.parseInt(txtsomme.getText());
         

        // Mettre à jour le don dans la base de données
        Connection cnx = MyDB.getInstance().getCnx();
        String query = "UPDATE don SET evenement_id=?, type_don=?, somme_don=? WHERE id=?";
        PreparedStatement smt = cnx.prepareStatement(query);
        smt.setInt(1, evenement_id);
        smt.setString(2, type_don);
        smt.setInt(3, somme_don);
        smt.setInt(4, don.getId()); // Assumes getId() is a method that returns the ID of the current donation
        smt.executeUpdate();

        // Fermer la fenêtre de modification
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("affichageDon.fxml"));
        Parent root;
        try {
            root = (Parent) fxmlLoader.load();
            Stage stage = (Stage) btnmodifier.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

  /*  public void setDon(Don don) {
        // Pré-remplir les champs avec les données du don à modifier
        cbxid.setValue(don.getEvenement_id());
        txttype.setText(don.getType_don());
        txtsomme.setText(String.valueOf(don.getSomme_don()));
    }
*/
    private ObservableList<Integer> getEvenementsIds() {
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        try {
            Connection cnx = MyDB.getInstance().getCnx();
            String query = "SELECT id FROM evenement";
            PreparedStatement smt = cnx.prepareStatement(query);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }
        } catch (SQLException ex) {
        }
        return ids;
    }
    
}
