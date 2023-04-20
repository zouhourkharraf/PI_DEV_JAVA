/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModificationController implements Initializable {

    @FXML
    private TextField mdnom;
    @FXML
    private TextField mdlieu;
    @FXML
    private TextField mddesc;
    @FXML
    private DatePicker mdd;
    @FXML
    private DatePicker mdf;
    @FXML
    private Button modif;
    @FXML
    private TextField mdnote;
    @FXML
    private Button annuler;
    private Evenement evenement;
    @FXML
    private Button imgUpload;
    private File file;
    private String lien = "";


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
    public void setEvenement(Evenement evenement) {
    // Pré-remplir les champs avec les données du don à modifier
    mdnom.setText(evenement.getNom_ev());
    mdd.setValue(evenement.getDated_ev());
    mdf.setValue(evenement.getDated_ev());
    mdlieu.setText(evenement.getLieu_ev());
    mddesc.setText(evenement.getDesc_ev());
    mdnote.setText(String.valueOf(evenement.getNote_ev()));
    this.evenement = evenement;
}
    
    @FXML
private void handleModifierE(ActionEvent event) throws IOException {
    
   // Don don = parentController.getSelectedDon().getSelectionModel().getSelectedItem();
    try {
        modifierEvent(event,evenement);
    } catch (SQLException ex) {
    }
}
    
    
    private void modifierEvent(ActionEvent event,Evenement evenement) throws SQLException, IOException {
        // Récupérer les nouvelles valeurs
        //int evenement_id = cbxid.getValue();
        String nom_ev = mdnom.getText();
        LocalDate dated_ev = mdd.getValue();
        LocalDate datef_ev = mdf.getValue();
        String lieu_ev = mdlieu.getText();
        String desc_ev = mddesc.getText();
        float note_ev = Float.parseFloat(mdnote.getText());
         

        // Mettre à jour le don dans la base de données
        Connection cnx = MyDB.getInstance().getCnx();
        String query = "UPDATE evenement SET nom_ev=?, dated_ev=?, datef_ev=?, lieu_ev=?, desc_ev=?,image_ev=?, note_ev=? WHERE id=?";
        PreparedStatement smt = cnx.prepareStatement(query);
        smt.setString(1, nom_ev);
        //LocalDate dated= LocalDate.now(); // or any other LocalDate object
        String dated_ev_str = dated_ev.toString(); // convert LocalDate to String
        smt.setString(2, dated_ev_str); // set the second parameter with the String value
        //LocalDate datef= LocalDate.now(); // or any other LocalDate object
        String datef_ev_str = datef_ev.toString(); // convert LocalDate to String
        smt.setString(3, datef_ev_str);
        smt.setString(4, lieu_ev);
        smt.setString(5, desc_ev);
        smt.setString(6, lien + ".png");

       // float note; // or any other float value
        String note_ev_str = Float.toString(note_ev); // convert float to String
        smt.setString(7, note_ev_str); // set the 6th parameter with the String value

        smt.setInt(8, evenement.getId()); // Assumes getId() is a method that returns the ID of the current donation
        smt.executeUpdate();

        // Fermer la fenêtre de modification
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("affichage.fxml"));
        Parent root;
        try {
            root = (Parent) fxmlLoader.load();
            Stage stage = (Stage) modif.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageDonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

  
    @FXML
    private void Annuler(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("affichage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void PhotoImportation(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage image = ImageIO.read(file);
            WritableImage imagee = SwingFXUtils.toFXImage(image, null);

            try {
                // save image to PNG file
                this.lien = UUID.randomUUID().toString();
                File f = new File("src\\uploads\\" + this.lien + ".png");
                System.out.println(f.toURI().toString());
                ImageIO.write(image, "PNG", f);
                imgUpload.setText(file.getName());

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
//      
    }




    
}
