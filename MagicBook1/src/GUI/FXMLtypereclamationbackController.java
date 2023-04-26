/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Reclamation;
import Entite.Repons;
import Entite.Statut;
import Service.ServiceReclamation;
import Service.ServiceRepons;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import magicbook1.FXMain;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author winxspace
 */
public class FXMLtypereclamationbackController implements Initializable {

    @FXML
    private TextField tfstatus;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tfid;
    @FXML
    private TableView<Repons> tvtype;
    @FXML
    private TableColumn<Repons, Integer> cid;
    @FXML
    private TableColumn<Repons, String> cnom;
    @FXML
    private TableColumn<Repons, Date> cdate;
    @FXML
    private TableColumn<Repons, String> cstatus;
    @FXML
    private TableColumn<Repons, String> cdesc;
    @FXML
    private ComboBox<String> reclamant;
    
    ServiceReclamation str=new ServiceReclamation();
    ServiceRepons sre=new ServiceRepons();
    ObservableList<String> data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //tfdate.setValue(LocalDate.now());
        data.addAll(str.getAllTitre());
        reclamant.setItems(data);
        displayData();
        tfdate.setValue(LocalDate.now());
         
    }
    
@FXML
private void ajouter(ActionEvent event) {
    
    ServiceReclamation service_rec=new ServiceReclamation();
    // Get the values from the UI controls
    if (controleDeSaisie().length() > 0) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur ajout reclamation");
        alert.setContentText(controleDeSaisie());
        alert.showAndWait();
        sendSms("+21696869820", "Hello , votre reclam a ete traité");
    } else {
    int reclamationId = str.getReclamationByTitre(reclamant.getSelectionModel().getSelectedItem().toString());
    
    String contenuRep = tfdesc.getText();
    LocalDate localDate = tfdate.getValue();
    java.sql.Date dateRep = java.sql.Date.valueOf(localDate);
Reclamation rec= service_rec.getReclamationById(reclamationId);
rec.setStatut_rec(Statut.TRAITE);
    service_rec.modifier(rec, reclamationId);
    // Create a new Repons object with the entered values
    Repons newRepons = new Repons(reclamationId, dateRep, contenuRep,Statut.TRAITE);           
    // Call the add method and refresh the table view
    sre.ajouter(newRepons);
    displayData();
    
    
    // Clear the UI controls
    reclamant.getSelectionModel().clearSelection();
 
    tfdesc.clear();
    tfdate.setValue(null);
}
        

}


    
    
    
    @FXML
private void modifier(ActionEvent event) {
    // Check if any row is selected
    Repons selectedRepons = tvtype.getSelectionModel().getSelectedItem();
    if (selectedRepons == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row to update!");
        alert.showAndWait();
        return;
    }
    
    // Get the updated values from the UI controls
    System.out.println("test");
    int reclamationId = str.getReclamationByTitre(reclamant.getSelectionModel().getSelectedItem().toString());
     
    String contenuRep = tfdesc.getText();
    LocalDate localDate = tfdate.getValue();
    Date dateRep = Date.valueOf(localDate);
    
    // Update the selected Repons object
    selectedRepons.setId_reclamation_id(reclamationId);
    selectedRepons.setStatus_rep(Statut.TRAITE);
    selectedRepons.setContenu_rep(contenuRep);
    selectedRepons.setDate_rep(dateRep);
    
    // Call the update method and refresh the table view
    sre.modifier(selectedRepons, 1);
    
    displayData();
}
@FXML
private void afficher(ActionEvent event)
{
 displayData();
}
    
    @FXML
    private void supprimer(ActionEvent event) throws Exception {
        if(tvtype.getSelectionModel().getSelectedItem()!=null){
            int id=tvtype.getSelectionModel().getSelectedItem().getId();
            sre.supprimer(id);
            displayData();
        }
    }
    
      @FXML
private void displayData() {
    ObservableList<Repons> dataList = FXCollections.observableArrayList(sre.afficher());
    cid.setCellValueFactory(new PropertyValueFactory<>("id"));
    cnom.setCellValueFactory(new PropertyValueFactory<>("id_reclamation_id"));
    cdate.setCellValueFactory(new PropertyValueFactory<>("date_rep"));
    cstatus.setCellValueFactory(new PropertyValueFactory<>("status_rep"));
    cdesc.setCellValueFactory(new PropertyValueFactory<>("contenu_rep"));
    tvtype.setItems(dataList);
}
    
public String controleDeSaisie(){
        String erreur="";
        if(tfdesc.getText().trim().isEmpty()){
            erreur+="Description vide!\n";
        }
        return erreur;
    }
    @FXML
    private void gotogstrec(ActionEvent event) {
        Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLgstreclamationfront.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Reclamation!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //*********************SMS***********************************
    public static final String ACCOUNT_SID = "ACf59b42860c72bbb9f62190343c7c3";

    public static final String AUTH_TOKEN = "4a05e0d45442ff0dad819f130e49a79b";

    public void sendSms(String recipient, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber("+21696869820"), // To number
                new PhoneNumber("+12762862741"), // From number
                messageBody) // SMS body
                .create();

        System.out.println("Message sent: " + message.getSid());
    }

   



    
}
