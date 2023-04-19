/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import services.EvenementService;
import services.MyListener;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FrontController implements Initializable {

   @FXML
    private GridPane grid;
    
     EvenementService eventService=new EvenementService();
    List<Evenement> eventList=eventService.recuperer();

    
    private MyListener myListener;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData(eventList);
    } 
    
    
    public void loadData(List<Evenement> eventList) {

    if (eventList.size() > 0) {
     // setChosenOffer(eventList.get(0));
      myListener =
          new MyListener() {
          
          @Override
          public void OnClickListener(Evenement event) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
            
          };
    }

    int column = 0;
    int row = 1;

    try {
      for (int i = 0; i < eventList.size(); i++) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/carte.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        CarteController cardController = fxmlLoader.getController();
        cardController.setData(eventList.get(i), myListener);
        if (column == 3) {
          column = 0;
          row++;
        }
        Evenement evenement;
        grid.add(anchorPane, column++, row);
        GridPane.setMargin(anchorPane, new Insets(10));
        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
        grid.setMaxWidth(Region.USE_PREF_SIZE);
        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
        grid.setMaxHeight(Region.USE_PREF_SIZE);
        

            
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    }
    
}
