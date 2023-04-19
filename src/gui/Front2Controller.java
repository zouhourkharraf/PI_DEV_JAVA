/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Front2Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private FlowPane flowpane;

    private EvenementService service;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new EvenementService();
        try {
            afficherEvenements();
        } catch (SQLException ex) {
            Logger.getLogger(Front2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void afficherEvenements() throws SQLException {
        List<Evenement> evenements = service.recuperer();
        for (Evenement evenement : evenements) {
            Node node = createNode(evenement);
            flowpane.getChildren().add(node);
        }
    }

    private Node createNode(Evenement evenement) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/FrontEvItem.fxml"));
            Node node = loader.load();
            Front2Controller controller = loader.getController();
            controller.setEvenement(evenement);

            ImageView imageView = (ImageView) node.lookup("#imageView");
            if (evenement.getImage_ev() != null && !evenement.getImage_ev().isEmpty()) {
                Image image = new Image(evenement.getImage_ev());
                imageView.setImage(image);
            }

            Label labelNom = (Label) node.lookup("#labelNom");
            labelNom.setText(evenement.getNom_ev());

            Label labelLieu = (Label) node.lookup("#labelLieu");
            labelLieu.setText(evenement.getLieu_ev());

            Label labelDate = (Label) node.lookup("#labelDate");
            labelDate.setText(evenement.getDated_ev().toString());

            return node;
        } catch (IOException e) {
            System.err.println("Erreur lors de la création de la vue pour l'événement " + evenement.getId() + " : " + e.getMessage());
            return null;
        }
    }   

    private void setEvenement(Evenement evenement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
