/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author MMD
 */    
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
     try {
            primaryStage.setTitle("Magic Book"); //--->dinner un titre à la fenêtre
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Login.fxml")); //leconainer va appeler le fichier .fxml
            Parent root = loader.load(); //--->un "layout" : un conteneur qui permet d'organiser les éléments d'une scène 
            Scene scene = new Scene(root,900,700); //--->créer une nouvelle scène "scene"
            
            primaryStage.setScene(scene); //--> affecter la scene à l'objet stage
            primaryStage.show();  //---> afficher la fenêtre(le stage)
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
    } //---> cette méthode permet de gérer la fenêtre

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args); //cette méthode permet d'appeler la méthode start()
    }
    
}

