/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AllEventsController implements Initializable {

    @FXML
    private VBox mainVBox;
    
    private List<Evenement> listEvent;
    
    private EvenementService eventService = new EvenementService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listEvent = new ArrayList<>(eventService.recuperer());
            for(Evenement event:listEvent ){
                System.out.println(event);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("card.fxml"));
                   try {
                    VBox vBox = fxmlLoader.load();
                    CardController listoffreContoller = fxmlLoader.getController();
                    
                    listoffreContoller.setData(event);
                    mainVBox.getChildren().add(vBox);
                } catch (IOException ex) {
                    Logger.getLogger(ShowAllController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
        }
  
       
            }
    
}
