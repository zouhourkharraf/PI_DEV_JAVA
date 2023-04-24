/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Evenement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.EvenementService;
import util.MyDB;


/**
 * FXML Controller class
 *
 * @author user
 */
public class AffichageController implements Initializable {

    @FXML
   
    TableView<Evenement> tvevents;

    @FXML
    private TableColumn<Evenement, Integer> cid;
    @FXML
    private TableColumn<Evenement, String> cnom;
    @FXML
    private TableColumn<Evenement, LocalDate> cdd;
    @FXML
    private TableColumn<Evenement, LocalDate> cdf;
    @FXML
    private TableColumn<Evenement, String> clieu;
    @FXML
    private TableColumn<Evenement, String> cdesc;
    @FXML
    private TableColumn<Evenement, String> cimg;
    @FXML
    private TableColumn<Evenement, Float> cnote;
    @FXML
    private Button refresh;
    
     private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private TableColumn<Evenement, Void> cSupprimer;
    private TableColumn<Evenement, Button> cModif;
    @FXML
    private Button refresh1;
  

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        cSupprimer.setCellFactory(col -> new TableCell<Evenement, Void>() {
    private final Button deleteButton = new Button("Supprimer");

    {
        deleteButton.setOnAction(event -> {
            Evenement evenement = getTableView().getItems().get(getIndex());
            // Code pour supprimer l'événement de la base de données
            // et actualiser la table
            try {
                    deleteEvenement(evenement);
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
        
        //*********modif***************
        
        cModif = new TableColumn<>("Modifier");
     cModif.setPrefWidth(100);
//cModifier.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    cModif.setCellFactory(param -> new TableCell<Evenement, Button>() {
    private final Button button = new Button("Modifier");

    @Override
    protected void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(button);
            button.setOnAction(event -> {
                Evenement evenement = getTableView().getItems().get(getIndex());
                 //Code pour ouvrir l'interface de modification avec le don sélectionné
            });
        }
    }
});

       
    } 
    
 private void deleteEvenement(Evenement evenement) throws SQLException {
             Connection cnx = MyDB.getInstance().getCnx();
    String query = "DELETE FROM evenement WHERE id=?";
    PreparedStatement smt = cnx.prepareStatement(query);
    smt.setInt(1, evenement.getId());
    smt.executeUpdate();
}
 
  
   @FXML
  public  ObservableList<Evenement> getEventsList() throws ParseException {
        Connection cnx = MyDB.getInstance().getCnx();
        
        ObservableList<Evenement> EventList = FXCollections.observableArrayList();
        try {
                String query2="SELECT * from evenement";
                PreparedStatement smt = cnx.prepareStatement(query2);
                Evenement event;
                ResultSet rs= smt.executeQuery();
                
            while(rs.next()){
              //  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             //Date datedEv = (Date) dateFormat.parse(rs.getDate("dated_ev").toString());
             //Date datefEv = (Date) dateFormat.parse(rs.getDate("datef_ev").toString());
             LocalDate datedEv = LocalDate.parse(rs.getDate("dated_ev").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
             LocalDate datefEv = LocalDate.parse(rs.getDate("datef_ev").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

         event = new Evenement(rs.getInt("id"), rs.getString("nom_ev"),datedEv,datefEv,rs.getString("lieu_ev"), rs.getString("desc_ev"), rs.getString("image_ev"),rs.getFloat("note_ev"));
                EventList.add(event);
            }
                System.out.println(EventList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return EventList;
   
    }
  
//************************************
     
     @FXML
         public void showRec() throws ParseException{
             ObservableList<Evenement> list = getEventsList(); //tvevents.setItems(list);
             tvevents.setItems(list);
             cid.setCellValueFactory(new PropertyValueFactory<>("id"));
             cnom.setCellValueFactory(new PropertyValueFactory<>("nom_ev"));
             cdd.setCellValueFactory(new PropertyValueFactory<>("dated_ev"));
             cdf.setCellValueFactory(new PropertyValueFactory<>("datef_ev"));
             clieu.setCellValueFactory(new PropertyValueFactory<>("lieu_ev"));
             cdesc.setCellValueFactory(new PropertyValueFactory<>("desc_ev"));
             cimg.setCellValueFactory(new PropertyValueFactory<>("image_ev"));            
             cnote.setCellValueFactory(new PropertyValueFactory<>("note_ev"));
             
             //   list.remove();
             //list.add();
          
                 Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory = new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
                 @Override
                 public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
                 final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {
                 private final Button deleteButton = new Button("Supprimer");

                 {
                     deleteButton.setOnAction((ActionEvent event) -> {
                         Evenement evenement = getTableView().getItems().get(getIndex());
                         try {
                             deleteEvenement(evenement);
                         } catch (SQLException ex) {
                             Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
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
                 
                 cModif.setCellFactory(col -> {
                 return new TableCell<Evenement, Button>() {
                     private final Button editButton = new Button("Modifier");
                     
                     {
                         editButton.setOnAction(event -> {
                             Evenement evenement = getTableView().getItems().get(getIndex());
                             // Ouvrir l'interface de modification avec le don sélectionné
                             try {
                                 FXMLLoader loader = new FXMLLoader(getClass().getResource("modification.fxml"));
                                 Parent root = (Parent) loader.load();
                                 ModificationController controller = loader.getController();
                                 controller.setEvenement(evenement);
                                 Stage stage = new Stage();
                                 stage.setScene(new Scene(root));
                                 stage.show();
                             } catch (IOException e) {
                             }
                         });
                     }
                     
                     @Override
                     protected void updateItem(Button item, boolean empty) {
                         super.updateItem(item, empty);
                         if (empty) {
                             setGraphic(null);
                         } else {
                             setGraphic(editButton);
                         }
                     }
                 };           });
          tvevents.getColumns().addAll(cModif);
     }
         
         
 //******************************
         
     private void refresh() throws ParseException{
       ObservableList<Evenement> list = getEventsList();
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnom.setCellValueFactory(new PropertyValueFactory<>("nom_ev"));
        cdd.setCellValueFactory(new PropertyValueFactory<>("dated_ev"));
        cdf.setCellValueFactory(new PropertyValueFactory<>("datef_ev"));
        clieu.setCellValueFactory(new PropertyValueFactory<>("lieu_ev"));
        cdesc.setCellValueFactory(new PropertyValueFactory<>("desc_ev"));
        cimg.setCellValueFactory(new PropertyValueFactory<>("image_ev"));
        cnote.setCellValueFactory(new PropertyValueFactory<>("note_ev"));
        tvevents.setItems(list);
         
       
    }
     
    

    private void initTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    /////////PDF****************  
    
    
@FXML
private void buttpdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException, BadElementException, IOException {
 
  
try{
        EvenementService eventService = new EvenementService();
        List<Evenement> events = eventService.getAll();

// Create a new PDF document
com.itextpdf.text.Document document = new com.itextpdf.text.Document();
PdfWriter.getInstance(document, new FileOutputStream("events.pdf"));

// Open the document
document.open();

// Add the logo to the header
Image logo = Image.getInstance("C:\\Users\\user\\OneDrive\\Documents\\NetBeansProjects\\PIDEV-MBV2\\src\\uploads\\logo2.jpg\\");
logo.scaleAbsolute(72, 72); // set logo size
logo.setAlignment(Element.ALIGN_CENTER);
document.add(logo);

// Add a paragraph before the table
//document.add(new Paragraph("Liste des événements Magic Book \n\n"));

// Create a centered paragraph with red text
Paragraph title = new Paragraph("Liste des événements Magic Book", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLUE));
title.setAlignment(Element.ALIGN_CENTER);

// Add a blank line
document.add(new Paragraph("\n"));

// Add the title to the document
document.add(title);

// Add a blank line
document.add(new Paragraph("\n"));


// Create a table with four columns
PdfPTable table = new PdfPTable(6);

/// Add table headers
PdfPCell cell = new PdfPCell(new Phrase("Nom"));
cell.setBackgroundColor(BaseColor.GRAY);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Date deb"));
cell.setBackgroundColor(BaseColor.GRAY);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Date fin"));
cell.setBackgroundColor(BaseColor.GRAY);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Lieu"));
cell.setBackgroundColor(BaseColor.GRAY);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Description"));
cell.setBackgroundColor(BaseColor.GRAY);
table.addCell(cell);

cell = new PdfPCell(new Phrase("Image"));
cell.setBackgroundColor(BaseColor.GRAY);
table.addCell(cell);

// Add the information of all sponsors to the table
for (Evenement s : events) {
    table.addCell(new PdfPCell(new Phrase(s.getNom_ev()))); //customize this cell
    table.addCell(s.getDated_ev().toString());
    table.addCell(s.getDatef_ev().toString());
    table.addCell(s.getLieu_ev());
    table.addCell(s.getDesc_ev());
    table.addCell(s.getImage_ev());
}


// Add the table to the document
document.add(table);

// Close the document
document.close(); 

// Show a success message
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Impression réussie");
        alert.setContentText("La liste des évènements a été imprimée avec succès.");
        alert.showAndWait();
    }
    catch (Exception e) {
        // Show an error message if an exception occurs
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur lors de l'impression");
        alert.setContentText("Une erreur s'est produite lors de l'impression de la liste des évènements.");
        alert.showAndWait();
  
 
    
    }
    }

@FXML
    private void statEv(ActionEvent event) {
        
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("statEv.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }
    
    
}

    