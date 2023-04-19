/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CardEventController implements Initializable {

    @FXML
    private ImageView imageIV;
    @FXML
    private Label descriptionText;
    @FXML
    private Label titreText;
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

        File file = new File("C:\\Users\\user\\OneDrive\\Documents\\NetBeansProjects\\PIDEV-MBV2\\src\\uploads\\" + event.getImage_ev());
        System.out.println(file);
        Image image = new Image(file.toURI().toString());
        System.out.println(image);
        imageIV.setImage(image);
        System.out.println("ccccccccccccccc");
        titreText.setText(event.getNom_ev());
        descriptionText.setText(event.getDesc_ev());

    }

}
