/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.matiere;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.MatiereService;

/**
 * FXML Controller class
 *
 * @author ALYSSA
 */
public class AfficherMatiereController implements Initializable {
    
    @FXML
    private TableView<matiere> tableview;
    
    @FXML
    private TableColumn<matiere, Integer> idcol;
    @FXML
    private TableColumn<matiere, String> matcol;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            MatiereService ms = new MatiereService();
            List<matiere> matieres = ms.recuperer();
            ObservableList<matiere> obs = FXCollections.observableArrayList(matieres);
            tableview.setItems(obs);
            
            matcol.setCellValueFactory(new PropertyValueFactory<>("nom_mat"));
            idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            // obs.remove()
            //obs.add()
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     @FXML
    private void Ajoutmat(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterMatiere.fxml"));
            Parent root = loader.load();

            tableview.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}
