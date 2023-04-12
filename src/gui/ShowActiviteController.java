/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import java.text.ParseException;

import entities.Activite;
import entities.Activity;
import services.ActivityService;
import entities.Type;
import services.ActiviteService;
import java.util.Date;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TitledPane;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author farah
 */
public class ShowActiviteController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private TableColumn<Activite, Integer> idCol;
    @FXML
    private TableColumn<Activite, String> nomCol;
    @FXML
    private TableColumn<Activite, Integer> nbCol;
    @FXML
    private TableView<Activite> tableview;
    @FXML
    private TableColumn<Activite, String> positionCol;
    @FXML
    private TableColumn<Activite, Date> dateCol;
    @FXML
    private TableColumn<Activite, Integer> typeCol;
    @FXML
    private TextField nomActField;
    @FXML
    private TextField nbField;
    @FXML
    private TextField positionField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField typeField;
    @FXML
    private Button boutonAjout;
    @FXML
    private TextField nomModField;
    @FXML
    private TextField nbModField;
    @FXML
    private TextField positionModField;
    @FXML
    private TextField dateModField;
    @FXML
    private Button boutonMod;
    @FXML
    private TextField idSuppField;
    @FXML
    private Button boutonSupprimer;
    @FXML
    private TextField idMod;
    @FXML
    private TitledPane boutonRetour;
    @FXML
    private Button boutonRet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherActivite();
        
        boutonAjout.setOnAction(e -> {
            try {
                addActivite();
                boutonAjout.setDisable(false);
            } catch (ParseException ex) {
                Logger.getLogger(ShowActiviteController.class.getName()).log(Level.SEVERE, null, ex);
            }
             
             
         });
         
//         boutonMod.setOnAction(e -> {
//            try {
//                updateActivite();
//            } catch (ParseException ex) {
//                Logger.getLogger(ShowActiviteController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//             boutonMod.setDisable(false);
//             
//         });
boutonMod.setOnAction(e -> {
    try {
        updateActivite();
        boutonMod.setDisable(false);
        
    } catch (ParseException ex) {
        Logger.getLogger(ShowActiviteController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(ShowActiviteController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
});
         
         boutonSupprimer.setOnAction(e -> {
             deleteActivite();
             boutonSupprimer.setDisable(false);
             
         });
         
         boutonRet.setOnAction((event) -> {
        try {
            handleButtonAction(event);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    });


    }  
    
    public void afficherActivite(){
        try{
            ActiviteService as = new ActiviteService();
            List<Activite> activites = as.recuperer();
            ObservableList<Activite> obs = FXCollections.observableArrayList(activites);
            //tableview.setItems(obs);
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomCol.setCellValueFactory(new PropertyValueFactory<>("nomact"));
            nbCol.setCellValueFactory(new PropertyValueFactory<>("nbparticipants"));
            positionCol.setCellValueFactory(new PropertyValueFactory<>("positionact"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("dateact"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("typeid"));
            tableview.setItems(obs);
        }catch(SQLException ex){
             System.out.println(ex.getMessage());
        }
    }
    
    public void addActivite() throws ParseException{
        String nomact = nomActField.getText();
        int nbparticipants = nbField.getText().isEmpty() ? 0 : Integer.parseInt(nbField.getText());
        String position = positionField.getText();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");// ou tout autre format que vous utilisez
        String dateStr = dateField.getText();
        
        Date date = null;
        try {
    date = format.parse(dateStr);
} catch (ParseException e) {
    // handle the exception
    date = null;
}
        int typeid = typeField.getText().isEmpty() ? 0 : Integer.parseInt(typeField.getText());
        Activite activite = new Activite(nomact, position, date, nbparticipants,typeid );
        ActiviteService as = new ActiviteService();
        try{
            as.ajouter(activite);
            refresh();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void updateActivite() throws ParseException, SQLException {
    int id = Integer.parseInt(idMod.getText());
    String nomActivite = nomModField.getText();
    int nombreParticipants = Integer.parseInt(nbModField.getText());
    String position = positionModField.getText();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    String dateStr = dateModField.getText();
    Date date = format.parse(dateStr);
    //int typeId = Integer.parseInt(typeModField.getText());
    
    ActiviteService activiteService = new ActiviteService();
    Activite activite = activiteService.recupererActiviteById(id);
    
    if (activite == null) {
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("l'activite n'existe pas");
        alert.showAndWait();
        
    } else {
        activite.setNomAct(nomActivite);
        activite.setPositionAct(position);
        activite.setDateAct(date);
        //activite.setType(typeId);
        activite.setNbParticipants(nombreParticipants);

        try {
            activiteService.modifier(activite);
            refresh();
        } catch(SQLException ex) {
            System.out.println("Erreur lors de la mise à jour de l'activité : " + ex.getMessage());
        }
    }
}

    
    
    
//    public void updateActivite() throws ParseException, SQLException {
//    int id = Integer.parseInt(idMod.getText());
//    String nomActivite = nomModField.getText();
//    int nombreParticipants = Integer.parseInt(nbModField.getText());
//    String position = positionModField.getText();
//    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//    String dateStr = dateModField.getText();
//    Date date = format.parse(dateStr);
//    //int typeId = Integer.parseInt(typeModField.getText());
//    
//    ActiviteService activiteService = new ActiviteService();
//    Activite activite = activiteService.recupererActiviteById(id);
//    
//    if (activite != null) {
//        activite.setNomAct(nomActivite);
//        activite.setPositionAct(position);
//        activite.setDateAct(date);
//        //activite.setType(typeId);
//        activite.setNbParticipants(nombreParticipants);
//
//        try {
//            activiteService.modifier(activite);
//            refresh();
//        } catch(SQLException ex) {
//            System.out.println("Erreur lors de la mise à jour de l'activité : " + ex.getMessage());
//        }
//    }
//}

    
//    public void updateActivite() throws ParseException, SQLException{
//        int id = Integer.parseInt(idMod.getText());
//        String nomact = nomModField.getText();
//        int nb = Integer.parseInt(nbModField.getText());
//        String position = positionModField.getText();
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");// ou tout autre format que vous utilisez
//        String dateStr = dateModField.getText();
//        Date date = format.parse(dateStr);
//        int typeid = Integer.parseInt(typeModField.getText());
//        //Activite selectedItem = tableview.getSelectionModel().getSelectedItem();
//        ActiviteService as = new ActiviteService();
//        Activite selectedItem = as.recupererActiviteById(id);
//        if(selectedItem != null){
//            selectedItem.setNomAct(nomact);
//            selectedItem.setPositionAct(position);
//            selectedItem.setDateAct(date);
//            selectedItem.setType(typeid);
//            selectedItem.setNbParticipants(nb);
//            //ActiviteService as = new ActiviteService();
//            try{
//                as.modifier(selectedItem);
//                refresh();
//            }catch(SQLException ex){
//                System.out.println(ex.getMessage());
//            }
//        }
//    }
        
        public void deleteActivite(){
            String idAct = idSuppField.getText();
            if (idAct.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("pas d' ID !");
        alert.showAndWait();
        return;
    }
            ActiviteService as = new ActiviteService();
            try{
                as.supprimer(Integer.parseInt(idAct));
                refresh();
            }catch(SQLException ex){
           System.out.println(ex.getMessage()); 
        }
            
        }
        
    
    
    public void refresh(){
        afficherActivite();
        nomActField.clear();
        nbField.clear();
        positionField.clear();
        dateField.clear();
        typeField.clear();
        //boutonAjout.setDisable(true);
        nomModField.clear();
        positionModField.clear();
        nbModField.clear();
        dateModField.clear();
        //typeModField.clear();
        idSuppField.clear();
        
    }
    
//    @FXML
//private void retournerVersParent(ActionEvent event) throws IOException {
//    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherType.fxml"));
//    Parent parent = loader.load();
//    Scene scene = new Scene(parent);
//    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//    stage.setScene(scene);
//    stage.show();
//}
   @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("AfficherType.fxml"));
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
}

    
    
    
}
