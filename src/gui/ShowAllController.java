/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import com.mysql.jdbc.Constants;
import entities.Evenement;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ShowAllController implements Initializable {

    @FXML
    private Text topText;
    @FXML
    private VBox mainVBox;

    public static Event currentEvent;

    
    private List<Evenement> listEvent;
    
    private EvenementService eventService = new EvenementService();
    
//    List<Participant> listParticipant;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listEvent = new ArrayList<>(eventService.recuperer());
            System.out.println(listEvent);
            for(Evenement event:listEvent ){
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
    /*
    

    void displayData() throws IOException {
        mainVBox.getChildren().clear();

        Collections.reverse(listEvent);

        if (!listEvent.isEmpty()) {
            for (Evenement event : listEvent) {

                mainVBox.getChildren().add(makeEventModel(event));

            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }
    
   // public static final String FXML_FRONT_MODEL_EVENT = "/PIDEV-MBV2/gui/card.fxml";

    public Parent makeEventModel(
            Evenement event
    ) throws IOException {
        
        Parent parent = null;
        //parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_FRONT_MODEL_EVENT)));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("card.fxml"));
         Parent root = loader.load();
         ShowAllController controller = loader.getController();

        HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
        ((Text) innerContainer.lookup("#titreText")).setText("Nom : " + event.getNom_ev());
        ((Text) innerContainer.lookup("#descriptionText")).setText("Description : " + event.getDesc_ev());
       // Path selectedImagePath = FileSystems.getDefault().getPath(event.getImage_ev());
        //if (selectedImagePath.toFile().exists()) {
          //  ((ImageView) innerContainer.lookup("#imageIV")).setImage(new Image(selectedImagePath.toUri().toString()));
        //}
        Text participerText = ((Text) innerContainer.lookup("#participerText"));
        Button participateBtn = ((Button) innerContainer.lookup("#participerBtn"));
        participerText.setText("participer");
      //  participateBtn.setOnAction((ignored) -> participer(event, participerText, participateBtn));
        return parent;
    }

    /*private void participer(Evenement event, Text text, Button participateBtn) {
       // Participant participant = new Participant(LocalDate.now(), MainApp.session, new RelationObject(event.getId(), event.getTitre()));
        //if (ParticipantService.getInstance().add(participant)) {
            participateBtn.setVisible(false);
            text.setText("Vous participer a cet evenement");
        }*/
      
    
}
