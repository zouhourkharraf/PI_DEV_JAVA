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
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


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
    private ImageView imageIV;
    @FXML
    private Button participerBtn;
    @FXML
    private VBox VboxId;
    @FXML
    private Button bloc;
    

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
      
      
      
      
      
      
      
      //////////////************map**************************
      
    
    public void start(Stage primaryStage) {
        // Créer une WebView et ajouter un bouton à la scène
        WebView webView = new WebView();
       // Button button = new Button("Afficher la carte");
        BorderPane root = new BorderPane();
       // root.setTop(button);
        root.setCenter(webView);
        
        bloc.setOnAction(event -> {
    // Récupérer la référence de la WebView
    WebEngine engine = webView.getEngine();

    // Récupérer les coordonnées du lieu_ev
    String lieuEv = "Paris, France"; // remplacer par lieu_ev de votre Evenement
    double latitude = 0.0;
    double longitude = 0.0;
    try {
        String url = "https://nominatim.openstreetmap.org/search?format=json&q=" + URLEncoder.encode(lieuEv, StandardCharsets.UTF_8.name());
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String output = br.readLine();
        if (output != null && !output.isEmpty()) {
            JSONObject obj = new JSONArray(output).getJSONObject(0);
            latitude = obj.getDouble("lat");
            longitude = obj.getDouble("lon");
        }
        conn.disconnect();
    } catch (IOException e) {
    }

    // Afficher la carte avec Leaflet
    String html = "<html><head>"
            + "<link rel=\"stylesheet\" href=\"https://unpkg.com/leaflet@1.7.1/dist/leaflet.css\" integrity=\"sha384-tLw0Lkrwu8Nu0JKtIvOefyowQjK9XGupTlJFAHsYu+uV7sG+BHIoGx7JTrf6IcZ0\" crossorigin=\"\" />"
            + "<script src=\"https://unpkg.com/leaflet@1.7.1/dist/leaflet.js\" integrity=\"sha384-5HYHaQ+LqlP5oRWT5t5db5n5G5DzB+UKpULGZJLcwj+nr7tL4Ae4opECPg+alzLC\" crossorigin=\"\"></script>"
            + "</head><body>"
            + "<div id=\"mapid\" style=\"height: 500px\"></div>"
            + "<script>"
            + "var map = L.map('mapid').setView([" + latitude + ", " + longitude + "], 13);"
            + "L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {"
            + "attribution: 'Map data © <a href=\"https://openstreetmap.org\">OpenStreetMap</a> contributors',"
            + "maxZoom: 18"
            + "}).addTo(map);"
            + "L.marker([" + latitude + ", " + longitude + "]).addTo(map);"
            + "</script></body></html>";
    engine.loadContent(html);
});

      

    } 

    private static class JSONObject {

        public JSONObject() {
        }

        private double getDouble(String lat) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private static class JSONArray {

        public JSONArray(String output) {
        }

        private JSONObject getJSONObject(int i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
