/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import com.mysql.jdbc.Constants;
import entities.Evenement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    @FXML
    private TextField tfRecherche;
    private ActionEvent event;
    @FXML
    private Pagination pagination;
    //private int itemsPerPage = 4; // Change this number to adjust the number of items per page
    //Evenement evenement;
    private int itemsPerPage = 5;
private List<Evenement> allEvents;
private int pageCount;

    
//    List<Participant> listParticipant;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        recherche(event);
        recherche2(event);
        
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
        
        
        //***********pagination********************
        
        
        
    }
    
    
   
    
    //***************recherche******************
    
      private Node createEventNode(Evenement event) throws FileNotFoundException {
    // Créer un VBox pour contenir le nom et le prix de l'article
     if(event == null) {
        return null;
    }
     else{
    VBox articleBox = new VBox();
          articleBox.setPrefSize(150, 150);
                articleBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");
              
               // File file = new File("C:\\Users\\user\\OneDrive\\Documents\\NetBeansProjects\\PIDEV-MBV2\\src\\uploads\\"+event.getImage_ev());
               // System.out.println(file);
               // Image image = new Image(file.toURI().toString());
                //System.out.println(image);

    // Créer des labels pour le nom et le prix de l'article
            Label namelabel=new Label(event.getNom_ev());    
            namelabel.setFont(Font.font("Verdana",FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            articleBox.getChildren().add(namelabel);
            
            Label lieulabel=new Label(event.getLieu_ev());    
            lieulabel.setFont(Font.font("Verdana",FontWeight.BOLD, 12));
            lieulabel.setAlignment(Pos.CENTER);
            articleBox.getChildren().add(lieulabel);
            
            Label datedlabel = new Label(event.getDated_ev().toString());
            datedlabel.setFont(Font.font("Verdana",FontWeight.BOLD, 12));
            datedlabel.setAlignment(Pos.CENTER);
            articleBox.getChildren().add(datedlabel);
            
            Label dateflabel = new Label(event.getDatef_ev().toString());
            dateflabel.setFont(Font.font("Verdana",FontWeight.BOLD, 12));
            dateflabel.setAlignment(Pos.CENTER);
            articleBox.getChildren().add(dateflabel);
            
            Label Desclabel=new Label(event.getDesc_ev());    
            Desclabel.setFont(Font.font("Verdana",FontWeight.BOLD, 10));
            Desclabel.setAlignment(Pos.CENTER);
            articleBox.getChildren().add(Desclabel);
            
             ImageView imageView;               
                imageView = new ImageView(new Image(new FileInputStream("C:\\Users\\user\\OneDrive\\Documents\\NetBeansProjects\\PIDEV-MBV2\\src\\uploads\\" + event.getImage_ev())));
                imageView.setFitWidth(150);
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);
                articleBox.getChildren().add(imageView);
                
           
           
    StackPane stackPane = new StackPane();
    stackPane.getChildren().addAll(articleBox);

    // Ajouter un style CSS au VBox pour qu'il soit bien présenté dans le FlowPane
    articleBox.setStyle("-fx-padding: 10px; -fx-background-color: #f2f2f2; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-border-color: #cccccc; -fx-border-width: 1px;");

    // Définir les contraintes de taille pour le VBox et l'ImageView
    articleBox.setPrefWidth(150);
    articleBox.setMaxWidth(150);
    mainVBox.getChildren().add(articleBox);
    mainVBox.setMargin(articleBox, new Insets(5, 5, 5, 5));

    // Retourner le StackPane contenant l'ImageView et le VBox
    return stackPane;
     }
}
      
private void recherche(ActionEvent event) {
    // Ajouter un listener sur le champ de recherche pour effectuer la recherche à chaque modification du texte
    tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
        EvenementService sp = new EvenementService();  
        // Filtrer les réclamations en utilisant le nouveau texte de recherche
        List<Evenement> eventrecherche = sp.recuperer().stream()
            .filter(evenement ->
                evenement.getNom_ev().toLowerCase().contains(newValue.toLowerCase())
            )
            .collect(Collectors.toList());

        // Vider le FlowPane actuel pour afficher les evenements filtrés
        mainVBox.getChildren().clear();
       for (Evenement evenement : eventrecherche) {
            Node articleNode = null;
            try {
                articleNode = createEventNode(evenement);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ShowAllController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (articleNode != null) {
                mainVBox.getChildren().add(articleNode); // ajouter le nouveau noeud dans le FlowPane
            }
        }
       
        mainVBox.layout();
    });
}

private void recherche2(ActionEvent event) {
    // Ajouter un listener sur le champ de recherche pour effectuer la recherche à chaque modification du texte
    tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
        EvenementService sp = new EvenementService();  
        // Filtrer les réclamations en utilisant le nouveau texte de recherche
        List<Evenement> eventrecherche = sp.recuperer().stream()
            .filter(evenement ->
                evenement.getLieu_ev().toLowerCase().contains(newValue.toLowerCase())
            )
            .collect(Collectors.toList());

        // Vider le FlowPane actuel pour afficher les evenements filtrés
        mainVBox.getChildren().clear();
       for (Evenement evenement : eventrecherche) {
            Node articleNode = null;
            try {
                articleNode = createEventNode(evenement);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ShowAllController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (articleNode != null) {
                mainVBox.getChildren().add(articleNode); // ajouter le nouveau noeud dans le FlowPane
            }
        }
       
        mainVBox.layout();
    });
}

    

    
}
