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
                    activiteService.participerActivite(activite);
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

}

        
    
    
        
    

