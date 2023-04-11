/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutController implements Initializable {

    @FXML
    private Button b1;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tflieu;
    @FXML
    private TextField tfdesc;
    private TextField tfimg;
    @FXML
    private TextField tfnote;
    @FXML
    private DatePicker dpd;
    @FXML
    private DatePicker dpf;
    @FXML
    private Button imageUpload;
    private File file;
    private String lien = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleAjouter(ActionEvent event) {
        if (!validateInput()) {
            return;
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/magicbook", "root", "");
            String query = "INSERT INTO evenement (nom_ev, dated_ev, datef_ev, lieu_ev, desc_ev, image_ev) VALUES ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, tfnom.getText());
            LocalDate localDate = dpd.getValue();
            java.sql.Date dated_ev = java.sql.Date.valueOf(localDate);
            statement.setDate(2, dated_ev);
            LocalDate localDate2 = dpf.getValue();
            java.sql.Date datef_ev = java.sql.Date.valueOf(localDate2);
            statement.setDate(3, datef_ev);
            statement.setString(4, tflieu.getText());
            statement.setString(5, tfdesc.getText());
            statement.setString(6, lien + "png");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("");
            alert.setContentText("Insertion avec succés");
            alert.showAndWait();
            statement.executeUpdate();
            conn.close();
            // Si l'insertion a réussi, vous pouvez afficher un message de confirmation à l'utilisateur :
            System.out.println("Données ajoutées avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(AjoutController.class.getName()).log(Level.SEVERE, null, ex);
            // Si une erreur s'est produite, vous pouvez afficher un message d'erreur à l'utilisateur :
            System.err.println("Erreur lors de l'ajout des données : " + ex.getMessage());

        }
    }

    private boolean validateInput() {
        // Vérifier si tous les champs sont remplis
        if (tfnom.getText().isEmpty() || tflieu.getText().isEmpty() || tfdesc.getText().isEmpty() || lien.isEmpty() || dpd.getValue() == null || dpf.getValue() == null || tfnote.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            return false;
        }

        // Vérifier si la date de début est supérieure à la date de fin
        LocalDate dated = dpd.getValue();
        LocalDate datef = dpf.getValue();
        if (datef.isBefore(dated)) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("");
            alert.setContentText("La date de début doit être avant la date de fin");
            alert.showAndWait();
            return false;
        }

        // Vérifier si la date de début est supérieure à la date actuelle
        LocalDate today = LocalDate.now();
        if (dated.isBefore(today)) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("");
            alert.setContentText("La date de début doit être après la date actuelle");
            alert.showAndWait();
            return false;
        }

        return true;
    }

    @FXML
    private void AfficherEvent(ActionEvent event) {
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
                imageUpload.setText(file.getName());

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
//      
    }
}
