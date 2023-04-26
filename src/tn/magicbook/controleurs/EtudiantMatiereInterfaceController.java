package tn.magicbook.controleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.magicbook.dao.ServiceMatiere;
import tn.magicbook.entité.Matiere;

public class EtudiantMatiereInterfaceController implements Initializable {

    @FXML
    private TableView<Matiere> subjectTable;
    @FXML
    private TableColumn<Matiere, String> nameColumn;

    private ServiceMatiere db = new ServiceMatiere();
    @FXML
    private TextField searchField;
    List<Matiere> myList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.myList = db.getAllSubjects();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom_mat"));
        ObservableList<Matiere> subjectList = FXCollections.observableArrayList(db.getAllSubjects());
        subjectTable.setItems(subjectList);

        subjectTable.setRowFactory(tv -> {
            TableRow<Matiere> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Matiere rowData = row.getItem();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/magicbook/interfaces/EtudiantInterface.fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                        EtudiantInterfaceController contorller = loader.getController();
                        contorller.setup(rowData.getId());
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setResizable(false);
                        stage.setTitle("Mes matieres");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(Gestion_matiereController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            return row;
        });
    }

    @FXML
    private void OnSearch(ActionEvent event) throws IOException {
        String coursesName = this.searchField.getText();
        for (Matiere matiere : myList) {
            if (matiere.getNom_mat().equals(coursesName)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/magicbook/interfaces/EtudiantInterface.fxml"));
                Parent root;
                root = loader.load();
                EtudiantInterfaceController contorller = loader.getController();
                contorller.setup(matiere.getId());
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setTitle("Gestion des cours");
                stage.setScene(scene);
                stage.show();
                return;
            }
        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("La matière n'existe pas");
        alert.setHeaderText("Cette matière n'existe pas");
        alert.showAndWait();
        return;
    }

}
