/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.MyListener;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CarteController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label labelTitre;
    private Evenement e;
    private MyListener myListener;
    @FXML
    private Label labelLieu;
    @FXML
    private Label labelDesc;
    @FXML
    private Label labelDated;
    @FXML
    private Label labeldatef;
    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void setData(Evenement e, MyListener myListener){
       this.e=e;
       this.myListener=myListener;
       labelTitre.setText(e.getNom_ev());
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Define the date format
       String formattedDate = e.getDated_ev().format(formatter); // Convert the LocalDate object to a String using the formatter
       labelDated.setText(formattedDate); // Set the text of the label to the formatted date string
       String formattedDatef = e.getDatef_ev().format(formatter); // Convert the LocalDate object to a String using the formatter
       labeldatef.setText(formattedDatef); // Set the text of the label to the formatted date string
       labelLieu.setText(e.getLieu_ev());
       labelDesc.setText(e.getDesc_ev());
     //  Image image = new Image(e.getImage_ev()); // Create an Image object using the image URL from the Evenement object
     //  imageView.setImage(image); // Set the image to the imageView
     

        

    anchorPane.setStyle("-fx-background-color: #e6e6e6;"); // Set the initial background color
    
}
  
   
    private void SelectedEventClicked(MouseEvent event) {
        myListener.OnClickListener(e);

    }

    private void cardExited(MouseEvent event) {
        anchorPane.setStyle("-fx-background-color: #e6e6e6;");
    }

    private void cardEentered(MouseEvent event) {
        anchorPane.setStyle("-fx-background-color: #b3b3b3;");
    }

    @FXML
    private void SelectedEventClicked(javafx.scene.input.MouseEvent event) {
    }
    
}
