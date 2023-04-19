/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Reclamation;
import Service.MyListener;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import Service.ServiceReclamation;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import magicbook1.FXMain;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author winxspace
 */
public class FXMLgstreclamationuserController implements Initializable {

    @FXML
    private AnchorPane anchore;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tftitre;
    @FXML
    private TextArea tfdescription;
    @FXML
    private GridPane grid;
    @FXML
    private TextField tfrecherche;
    @FXML
    private Label idgetter;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfstatut;
    @FXML
    private TextField tfusername;
    @FXML
    private DatePicker tfdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfdate.setValue(LocalDate.now());
    }    

@FXML
private void modifier(ActionEvent event) {
    if(controleDeSaisie().length() > 0) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur modification réclamation");
        alert.setContentText(controleDeSaisie());
        alert.showAndWait();
    } else {     
        ServiceReclamation sr = new ServiceReclamation();
        Reclamation r = sr.getReclamationById(Integer.parseInt(tfid.getText()));
        if (r == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur modification réclamation");
            alert.setContentText("La réclamation avec l'id " + tfid.getText() + " n'existe pas");
            alert.showAndWait();
            return;
        }
        r.setTitre_rec(tftitre.getText());
        r.setType_rec(tftype.getText());
        r.setDate_rec(Date.valueOf(tfdate.getValue()));
        r.setContenu_rec(tfdescription.getText());
        r.setStatut_rec(tfstatut.getText());
        r.setUsername(tfusername.getText());
        sr.modifier(r, Integer.valueOf(tfid.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Réclamation");
        alert.setContentText("Modification de la réclamation effectuée avec succès");
        alert.showAndWait();
        sr.afficher();
        tfid.clear();
        tftitre.clear();
        tfdescription.clear();
        tftype.clear();
        tfdate.setValue(null);
        tfstatut.clear();
        tfusername.clear();
    }
}

            
  public String controleDeSaisie(){
        String erreur="";
        if(tftitre.getText().trim().isEmpty()){
            erreur+="Titre vide!\n";
        }
        if(tftype.getText().trim().isEmpty()){
            erreur+="Type vide!\n";
        }
        if(tfusername.getText().trim().isEmpty()){
            erreur+="Username vide!\n";
        }
        if(tfstatut.getText().trim().isEmpty()){
            erreur+="Status vide!\n";
        }
        if(tfdescription.getText().trim().isEmpty()){
            erreur+="Description vide!\n";
        }
        return erreur;
    }
    @FXML
    private void supprimer(ActionEvent event) throws Exception {
        ServiceReclamation sr = new ServiceReclamation();
        sr.supprimer(Integer.valueOf(tfid.getText()));
        sr.afficher();
    }
  @FXML
private void generatepdf(ActionEvent event) {
    // Get the data from the form fields
    String titre = tftitre.getText();
    String type = tftype.getText();
    String description = tfdescription.getText();
    String statut = tfstatut.getText();
    String username = tfusername.getText();
    LocalDate date = tfdate.getValue();

    // Create a PDF document
    try {
        // Specify the file name and path for the PDF file
        String fileName = "reclamation.pdf";
        File file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(file);

        // Create a Document object
        Document document = new Document(PageSize.A4, 50, 50, 50, 50); // Set page size and margins
        PdfWriter writer = PdfWriter.getInstance(document, fos);

        // Open the document
        document.open();

        // Add the form field data to the document with formatting
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.UNDERLINE);
        Font labelFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        Paragraph title = new Paragraph("Réclamation", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Chunk("\n"));
        document.add(new Paragraph("Titre: ", labelFont));
        document.add(new Paragraph(titre, valueFont));

        document.add(new Paragraph("Type: ", labelFont));
        document.add(new Paragraph(type, valueFont));

        document.add(new Paragraph("Description: ", labelFont));
        document.add(new Paragraph(description, valueFont));

        document.add(new Paragraph("Statut: ", labelFont));
        document.add(new Paragraph(statut, valueFont));

        document.add(new Paragraph("Username: ", labelFont));
        document.add(new Paragraph(username, valueFont));

        document.add(new Paragraph("Date: ", labelFont));
        document.add(new Paragraph(date.toString(), valueFont));

        // Close the document
        document.close();

        // Show success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Réclamation");
        alert.setContentText("Génération du PDF effectuée avec succès");
        alert.showAndWait();
    } catch (DocumentException | IOException ex) {
        Logger.getLogger(FXMLgstreclamationuserController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    


    
  @FXML
private void tri(ActionEvent event) {

    ServiceReclamation sr = new ServiceReclamation();
    List<Reclamation> reclamations = sr.afficher();
    reclamations.sort((r1, r2) -> r1.getTitre_rec().compareTo(r2.getTitre_rec()));
    grid.getChildren().clear();
    int row = 1;
    for (Reclamation r : reclamations) {
        Label idLabel = new Label(String.valueOf(r.getId()));
        idLabel.setPadding(new Insets(5));
        grid.add(idLabel, 0, row);

        Label titreLabel = new Label(r.getTitre_rec());
        titreLabel.setPadding(new Insets(5));
        grid.add(titreLabel, 1, row);

        row++;
    }
}

    @FXML
    private void gotoajouterreclamation(ActionEvent event) {
           Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLreclamationfront.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Reclamation!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        @FXML
private void afficher(ActionEvent event) {
    // récupérer les réclamations
    ServiceReclamation sr = new ServiceReclamation();
    List<Reclamation> reclamations = sr.afficher();

    // vider la grille
    grid.getChildren().clear();

    // afficher les réclamations dans la grille
    int row = 1;
    for (Reclamation r : reclamations) {
        Label idLabel = new Label(String.valueOf(r.getId()));
        idLabel.setPadding(new Insets(5));
        grid.add(idLabel, 0, row);

        Label titreLabel = new Label(r.getTitre_rec());
        titreLabel.setPadding(new Insets(5));
        grid.add(titreLabel, 1, row);

        Label typeLabel = new Label(r.getType_rec());
        typeLabel.setPadding(new Insets(5));
        grid.add(typeLabel, 2, row);

        Label dateLabel = new Label(r.getDate_rec().toString());
        dateLabel.setPadding(new Insets(5));
        grid.add(dateLabel, 3, row);

        Label statutLabel = new Label(r.getStatut_rec());
        statutLabel.setPadding(new Insets(5));
        grid.add(statutLabel, 4, row);

        row++;
    }
}
 @FXML
    private void repondre (ActionEvent event) {
            Stage stageclose=(Stage)((Node)event.getSource()).getScene().getWindow();
        stageclose.close();
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLreponseback.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Reponse!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     
    
}
