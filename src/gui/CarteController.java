/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
