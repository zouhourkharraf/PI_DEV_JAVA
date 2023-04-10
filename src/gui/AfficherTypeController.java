/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import entities.Type;
import services.TypeService;   

import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ListView;


/**
 * FXML Controller class
 *
 * @author farah
 */
public class AfficherTypeController implements Initializable {

    
    
    

    private ObservableList<Type> types = FXCollections.observableArrayList();
    @FXML
    private TableView<Type> tableview;
    @FXML
    private TableColumn<Type, Integer> idCol;
    @FXML
    private TableColumn<Type, String> nomtypeCol;
    @FXML
    private TableColumn<Type, String> descriptionCol;
    
    //TypeService ts = new TypeService();
    
    Type type = new Type();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        showTypes();
    }  
    
    public void showTypes(){
        try{
            TypeService ts = new TypeService();
            List<Type> listeTypes = ts.recuperer();
            ObservableList<Type> obs = FXCollections.observableArrayList(listeTypes);
            tableview.setItems(obs);
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomtypeCol.setCellValueFactory(new PropertyValueFactory<>("nomType") );
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("descriptionType"));
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//    
//    public void showType(){
//        List<Type> listeTypes = ts.recuperer();
//        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
//        nomtypeCol.setCellValueFactory(new PropertyValueFactory<>("nomtype") );
//        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("descriptiontype"));
//        tableview.setItems(typeservice.recuperer());
//    }
    
}
