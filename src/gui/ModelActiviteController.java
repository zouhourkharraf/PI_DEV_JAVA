/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Activite;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import entities.Activite;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import services.ActiviteService;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author farah
 */
public class ModelActiviteController implements Initializable {

    
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

    public String getNbLabel() {
        return nbLabel.getText();
    }

    public void setNbLabel(String nb) {
        this.nbLabel.setText(nb);
    }

    public String getTypeLabel() {
        return typeLabel.getText();
    }

    public void setTypeLabel(String type) {
        this.typeLabel.setText(type);
    }
    
    
    
    

   public List<Activite> recupererModel() throws SQLException {
    Connection cnx;
    cnx = MyDB.getInstance().getCnx();
    List<Activite> activites = new ArrayList<>();
    String req = "select * from activite";
    
    //String req = "select id, nomact, positionact from activite";
    Statement st = cnx.createStatement();
    ResultSet rs = st.executeQuery(req);
    while(rs.next())
    {
        Activite a = new Activite();
        a.setId(rs.getInt("id"));
        a.setNomAct(rs.getString("nomact"));
        a.setPositionAct(rs.getString("positionact"));
        a.setDateAct(rs.getDate("dateact"));
        a.setNbParticipants(rs.getInt("nbparticipants"));
        a.setType(rs.getInt("typeid"));
        activites.add(a);
    }
     return activites;
    }
}
