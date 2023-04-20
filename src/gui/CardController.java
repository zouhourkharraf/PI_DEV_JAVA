/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.Evenement;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CardController implements Initializable {

    @FXML
    private Text titreText;
    @FXML
    private Text descriptionText;
    @FXML
    private Text participerText;
    @FXML
    private ImageView imageIV;
    @FXML
    private Button participerBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        public void setData(Evenement event) {

        File file = new File("C:\\Users\\user\\OneDrive\\Documents\\NetBeansProjects\\PIDEV-MBV2\\src\\uploads\\"+event.getImage_ev());
        System.out.println(file);
        Image image = new Image(file.toURI().toString());
        System.out.println(image);
        imageIV.setImage(image);
        System.out.println("ccccccccccccccc");
        titreText.setText(event.getNom_ev());
        descriptionText.setText(event.getDesc_ev());
        //participerBtn.setOnAction(this::handleParticiperBtnAction);


    }
        
        //*********************SMS***********************************

public static final String ACCOUNT_SID = "AC6c526778abadd654ee726d7cafb49951";

  public static final String AUTH_TOKEN = "6f59d6aeed838a1f3ef292f59f28041a";
  
  
      public static void sendSms(String recipient, String messageBody) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(
            new PhoneNumber("+21695173280"), // To number
            new PhoneNumber("+12764092348"), // From number
            messageBody) // SMS body
        .create();

    System.out.println("Message sent: " + message.getSid());
  }
      
    /*  @FXML
private void handleParticiperBtnAction(ActionEvent event) {
    // Replace the phone number and message body with appropriate values for your use case
    sendSms("+21695173280", "Hello from JavaFX!");
}*/

    
}
