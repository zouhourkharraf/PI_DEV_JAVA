/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author MMD
 */
public class DashboardController implements Initializable {

    @FXML
    private Button bouton_gestion_util;
    @FXML
    private Label AdminConnecteDashboard;
    @FXML
    private Button bouton_gestion_ev;
    @FXML
    private Button bouton_gestion_rec;
    @FXML
    private Button bouton_gestion_cours;
    @FXML
    private Button bouton_gestion_form;
    @FXML
    private Button bouton_gestion_act;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gerer_utilisateurs(ActionEvent event) {
        try{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("PageAdministrateur.fxml"));  
                   Parent root = loader.load(); //Un container
                   PageAdministrateurController admin_controller=loader.getController();
                   admin_controller.setAdmin_connecte(AdminConnecteDashboard.getText());
                  AdminConnecteDashboard.getScene().setRoot(root);
                  
                    } catch (IOException ex) 
                   { System.out.println(ex.getMessage());}
                    
        
        
    }

    void setAdminConnecteeDashboard(String pseudo_admin) {
      AdminConnecteDashboard.setText("Administrateur connecté : "+pseudo_admin);
    }
    

    
}
