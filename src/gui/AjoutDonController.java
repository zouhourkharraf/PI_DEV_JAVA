/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutDonController implements Initializable {

    @FXML
    private ComboBox<Integer> cbeventid;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfsomme;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: initialize the ComboBox with the IDs of the events
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/magicbook", "root", "");
            String query = "SELECT id FROM evenement";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cbeventid.getItems().add(resultSet.getInt("id"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @FXML
    private void handleAjouter(ActionEvent event) {
        if (!validateInput()) {
            return;
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/magicbook", "root", "");
            String query = "INSERT INTO don (evenement_id,type_don,somme_don) VALUES ( ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, cbeventid.getValue());
            statement.setString(2, tftype.getText());
            statement.setString(3, tfsomme.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("");
            alert.setContentText("Insertion avec succés");
            alert.showAndWait();
            statement.executeUpdate();
            conn.close();
            // Si l'insertion a réussi, vous pouvez afficher un message de confirmation à l'utilisateur :
            System.out.println("Données ajoutées avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(AjoutDonController.class.getName()).log(Level.SEVERE, null, ex);
            // Si une erreur s'est produite, vous pouvez afficher un message d'erreur à l'utilisateur :
            System.err.println("Erreur lors de l'ajout des données : " + ex.getMessage());
            
        }
        
    }
    
    private boolean validateInput() {
    // Vérifier si tous les champs sont remplis
    if (cbeventid.getValue() == null || tftype.getText().isEmpty() || tfsomme.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("");
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();
        return false;
    }
    
    // Vérifier si le type est valide
    String type = tftype.getText().toLowerCase();
    if (!type.equals("argent") && !type.equals("pc") && !type.equals("fourniture")) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("");
        alert.setContentText("Le type doit être 'argent', 'pc' ou 'fourniture'");
        alert.showAndWait();
        return false;
    }
    
    
    return true;
}
     @FXML
    private void AfficherDon(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageDon.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}
