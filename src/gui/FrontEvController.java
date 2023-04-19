/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import services.EvenementService;
import util.Statics;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FrontEvController implements Initializable {

    @FXML
    private FlowPane listevents;
   // ObservableList<Evenement>listprod=FXCollections.observableArrayList;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            displayart();
            // TODo
        } catch (SQLException ex) {
            Logger.getLogger(FrontEvController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
      
    private void displayart() throws SQLException{
        EvenementService ps=new EvenementService();
        ObservableList<Evenement>lista=FXCollections.observableArrayList();
        lista=(ObservableList<Evenement>) ps.recuperer();
        System.out.println("tesst");
        //listp=ps.afficher();
        for(Evenement a:lista){
            
            VBox card=new VBox();
            card.setPrefSize(150, 150);
            card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
           
           
           /* ImageView imageView;
            try {
               imageView = new ImageView(new Image(new FileInputStream(Statics.uploadDirectory+a.getImage_ev())));
                imageView.setFitWidth(120);
                imageView.setFitHeight(80);
                imageView.setPreserveRatio(true);
                card.getChildren().add(imageView);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrontEvController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            Label namelabel=new Label(a.getNom_ev());    
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            card.getChildren().add(namelabel);
            Label lieulabel=new Label(a.getLieu_ev()); 
            lieulabel.setWrapText(true);
            lieulabel.setAlignment(Pos.CENTER);
            card.getChildren().add(lieulabel);
           
           /* if(a.getStock()==0){
                Label dispo=new Label("Out Of Stock");
                dispo.setAlignment(Pos.CENTER);
                dispo.setTextFill(Color.RED);
                card.getChildren().add(dispo);
            }else{
                Label dispo=new Label("In Stock");
                dispo.setAlignment(Pos.CENTER);
                dispo.setTextFill(Color.GREEN);
                card.getChildren().add(dispo);
            }*/
           // Label catLabel=new Label(a.getCategorie().getNom_c());
         //   catLabel.setAlignment(Pos.CENTER);
           // card.getChildren().add(catLabel);
       
            listevents.getChildren().add(card);
            FlowPane.setMargin(card, new Insets(5, 5, 5, 5));
    }
    


    
}}