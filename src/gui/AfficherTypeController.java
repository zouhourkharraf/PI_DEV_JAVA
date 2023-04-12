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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TitledPane;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert;


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
    
    @FXML
    private AnchorPane titlePane;
    
    @FXML
    private TextField nomTypeField;
    @FXML
    private TextField descriptionField;
    @FXML
    private Button boutonAjoutType;
    @FXML
    private TextField nomTypeMod;
    @FXML
    private TextField descriptionTypeMod;
    @FXML
    private Button boutonModType;
    @FXML
    private TitledPane titlePaneMod;
    @FXML
    private TitledPane titlePaneAjout;
    @FXML
    private TitledPane titlePaneDelete;
    @FXML
    private TextField deleteTypeField;
    @FXML
    private Button boutonDelType;
    @FXML
    private Button boutonRedirect;
    @FXML
    private TextField nomErreur;
    @FXML
    private TextField erreurDes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        showTypes();
        
         boutonAjoutType.setOnAction(e -> {
             addType();
             boutonAjoutType.setDisable(false);
             
         });
         
         boutonModType.setOnAction(e -> {
             updateType();
             boutonModType.setDisable(false);
             
         });
         
         boutonDelType.setOnAction(e -> {
             deleteType();
             boutonDelType.setDisable(false);
             
         });
         
         
        
         
         boutonRedirect.setOnAction((event) -> {
        try {
            handleButtonAction(event);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    });
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
    
    public void addType(){

            
             
            String nomType = nomTypeField.getText();
            String descriptionType = descriptionField.getText();
            Type type = new Type(nomType, descriptionType);
            TypeService ts = new TypeService();
            try{
                ts.ajouter(type);
                refresh();
            }
            catch(SQLException ex){
               System.out.println(ex.getMessage()); 
            }
        
    }
    
    public void updateType(){
        String nomType = nomTypeMod.getText();
        String descriptionType = descriptionTypeMod.getText();
        Type selectedItem = tableview.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
        selectedItem.setNomType(nomType);
        selectedItem.setDescriptionType(descriptionType);
        TypeService ts = new TypeService();
        try {
            ts.modifier(selectedItem);
            refresh();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
    
    public void deleteType(){
        String idType = deleteTypeField.getText();
        if(idType.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("pas d' ID !");
        alert.showAndWait();
        return;
        }
        TypeService ts = new TypeService();
        try{
            ts.supprimer(Integer.parseInt(idType));
            refresh();
        }catch(SQLException ex){
           System.out.println(ex.getMessage()); 
        }
    }
    
    public void refresh(){
        showTypes();
        nomTypeField.clear();
        descriptionField.clear();
        //boutonAjoutType.setDisable(true);
        nomTypeMod.clear();
        descriptionTypeMod.clear();
        //boutonModType.setDisable(true);
        
    }
    
    @FXML
    private void handleRowSelection() {
    Type selectedItem = tableview.getSelectionModel().getSelectedItem();
    if (selectedItem != null) {
        nomTypeMod.setText(selectedItem.getNomType());
        descriptionTypeMod.setText(selectedItem.getDescriptionType());
        titlePaneMod.setVisible(true);
       
    }
    }
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("ShowActivite.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) boutonRedirect.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
    
//    private void handleButtonAction(ActionEvent event) throws IOException {
//    Parent root = FXMLLoader.load(getClass().getResource("ShowActivite.fxml"));
//    Scene scene = new Scene(root);
//    Stage stage = new Stage();
//    stage.setScene(scene);
//    stage.show();
//}
    
//    private void chargerPage(ActionEvent event) throws IOException{
//        Scene currentScene = ((Node) event.getSource()).getScene();
//        AfficherTypeController premierePageController = (AfficherTypeController) currentScene.getUserData();
//        String donnees = premierPageController.
//    }
    
    
}
