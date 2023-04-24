/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    
}
