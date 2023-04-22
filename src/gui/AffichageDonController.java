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
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AffichageDonController implements Initializable {

    @FXML
     private TableView<Don> tvdons;
    @FXML
    private TableColumn<Don, Integer> cidd;
    @FXML
    private TableColumn<Don, Integer> cidev;
    @FXML
    private TableColumn<Don, String> ctype;
    @FXML
    private TableColumn<Don, String> csomme;
    @FXML
    private Button drefresh;
    @FXML
    private TableColumn<Don, Void> cSupprimer2;
    @FXML
    private TableColumn<Don, Button> cModifier;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        cSupprimer2.setCellFactory(col -> new TableCell<Don, Void>() {
    private final Button deleteButton = new Button("Supprimer");

    {
        deleteButton.setOnAction(event -> {
            Don don = getTableView().getItems().get(getIndex());
            // Code pour supprimer l'événement de la base de données
            // et actualiser la table
            try {
                    deleteDon(don);
                    refresh();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ParseException ex) {
                Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(deleteButton);
        }
    }
});

        
        //**************************
        
       cModifier = new TableColumn<>("Modifier");
cModifier.setPrefWidth(100);
//cModifier.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
cModifier.setCellFactory(param -> new TableCell<Don, Button>() {
    private final Button button = new Button("Modifier");

    @Override
    protected void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(button);
            button.setOnAction(event -> {
                Don don = getTableView().getItems().get(getIndex());
                // Code pour ouvrir l'interface de modification avec le don sélectionné
            });
        }
    }
});


       
    } 
    
    
    
     @FXML
  public  ObservableList<Don> getDonsList() throws ParseException {
        Connection cnx = MyDB.getInstance().getCnx();
        
        ObservableList<Don> DonList = FXCollections.observableArrayList();
        try {
                String query2="SELECT * from don";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Don don;
                ResultSet rs= smt.executeQuery();
                
            while(rs.next()){
               don = new Don(rs.getInt("id"),rs.getInt("evenement_id"), rs.getString("type_don"),rs.getInt("somme_don"));
                DonList.add(don);
            }
                System.out.println(DonList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return DonList;
   
    }
  
//************************************
     
     @FXML
         public void showRec() throws ParseException{
             ObservableList<Don> list = getDonsList(); //tvevents.setItems(list);
             tvdons.setItems(list);
             cidd.setCellValueFactory(new PropertyValueFactory<>("id"));
             cidev.setCellValueFactory(new PropertyValueFactory<>("evenement_id"));
             ctype.setCellValueFactory(new PropertyValueFactory<>("type_don"));
             csomme.setCellValueFactory(new PropertyValueFactory<>("somme_don"));
             
             
             //   list.remove();
             //list.add();
          
                 Callback<TableColumn<Don, Void>, TableCell<Don, Void>> cellFactory = new Callback<TableColumn<Don, Void>, TableCell<Don, Void>>() {
                 @Override
                 public TableCell<Don, Void> call(final TableColumn<Don, Void> param) {
                 final TableCell<Don, Void> cell = new TableCell<Don, Void>() {
                 private final Button deleteButton = new Button("Supprimer");

                 {
                     deleteButton.setOnAction((ActionEvent event) -> {
                         Don don = getTableView().getItems().get(getIndex());
                         try {
                             deleteDon(don);
                         } catch (SQLException ex) {
                             Logger.getLogger(AffichageDonController.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     });
                 }

                 @Override
                 public void updateItem(Void item, boolean empty) {
                     super.updateItem(item, empty);
                     if (empty) {
                         setGraphic(null);
                     } else {
                         setGraphic(deleteButton);
                     }
                 }
             };
             return cell;
         }
     };
                 
                 
                 //**********************
                 
                 cModifier.setCellFactory(col -> new TableCell<Don, Button>() {
    private final Button editButton = new Button("Modifier");

    {
         editButton.setOnAction(event -> {
                Don don = getTableView().getItems().get(getIndex());
                // Ouvrir l'interface de modification avec le don sélectionné
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("modificationDon.fxml"));
                    Parent root = (Parent) loader.load();
                    ModificationDonController controller = loader.getController();
                    controller.setDon(don);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                }
            });
    }

    protected void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(editButton);
        }
    }
});
          tvdons.getColumns().addAll(cModifier);

   
     }
         
   private void deleteDon(Don don) throws SQLException {
             Connection cnx = MyDB.getInstance().getCnx();
    String query = "DELETE FROM don WHERE id=?";
    PreparedStatement smt = cnx.prepareStatement(query);
    smt.setInt(1, don.getId());
    smt.executeUpdate();
}
         
         
 //******************************
         
     private void refresh() throws ParseException{
       ObservableList<Don> list = getDonsList();
        cidd.setCellValueFactory(new PropertyValueFactory<>("id"));
        cidev.setCellValueFactory(new PropertyValueFactory<>("evenement_id"));
        ctype.setCellValueFactory(new PropertyValueFactory<>("type_don"));
        csomme.setCellValueFactory(new PropertyValueFactory<>("somme_don"));
        
        tvdons.setItems(list);
        
         
       
    }
     
     public TableView<Don> getSelectedDon(){
    return tvdons;
}


    private void initTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @FXML
    private void stat(ActionEvent event) {
        
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("statDon.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }
    
    
}
