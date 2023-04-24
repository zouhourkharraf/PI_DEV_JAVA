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
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import entities.Activite;
import services.ActiviteService;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FXML Controller class
 *
 * @author farah
 */
public class ActiviteModelController implements Initializable {

    @FXML
    private Label nomLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label nbLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Button descButton;
    @FXML
    private Button participerButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public String getNomLabel(){
        return nomLabel.getText();
    }
    
    public void setNomLabel(String nom) {
        this.nomLabel.setText(nom);
    }

    public String getPositionLabel() {
        return positionLabel.getText();
    }

    public void setPositionLabel(String position) {
        this.positionLabel.setText(position);
    }

    public String getDateLabel() {
        return dateLabel.getText();
    }

    public void setDateLabel(String date) {
        this.dateLabel.setText(date);
    }

    public int getNbLabel() {
    return Integer.parseInt(nbLabel.getText());
}

public void setNbLabel(int nb) {
    this.nbLabel.setText(Integer.toString(nb));
}

    public int getTypeLabel() {
        return Integer.parseInt(typeLabel.getText());
    }

    public void setTypeLabel(int type) {
        this.typeLabel.setText(Integer.toString(type));
    }

    
    public void setData(Activite activite){
        nomLabel.setText(activite.getNomAct());
        positionLabel.setText(activite.getPositionAct());
        //dateLabel.setText(activite.getDateAct());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Define date format
        String dateString = dateFormat.format(activite.getDateAct()); // Format the date object into a string
        dateLabel.setText(dateString); // Set the text of the label to the formatted date string
        //nbLabel.setText(activite.getNbParticipants());
        int nbParticipants = activite.getNbParticipants(); // Get the number of participants from the Activite object
        String nbParticipantsString = Integer.toString(nbParticipants); // Convert the integer to a String
        nbLabel.setText(nbParticipantsString);
        //typeLabel.setText(activite.getType());
        int type = activite.getType();
        String typeString = Integer.toString(type);
        typeLabel.setText(typeString);
    }
    
}
