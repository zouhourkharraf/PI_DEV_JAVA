package tn.magicbook.controleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.magicbook.dao.ServiceMatiere;
import tn.magicbook.entité.Matiere;

public class Gestion_matiereController implements Initializable {

    @FXML
    private TableView<Matiere> subjectTable;
    @FXML
    private TableColumn<Matiere, Integer> idColumn;
    @FXML
    private TableColumn<Matiere, String> nameColumn;
    @FXML
    private TextField nameField;
    @FXML
    private Button addButton;

    private ServiceMatiere db = new ServiceMatiere();
    @FXML
    private Button resetButton;
    @FXML
    private Button deleteBtn;
    @FXML
    private TextField searchField;

    List<Matiere> myList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.myList = db.getAllSubjects();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom_mat"));

        ObservableList<Matiere> subjectList = FXCollections.observableArrayList(this.myList);
        subjectTable.setItems(subjectList);
        resetButton.setVisible(false);
        deleteBtn.setVisible(false);

        subjectTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nameField.setText(newSelection.getNom_mat());
                resetButton.setVisible(true);
                deleteBtn.setVisible(true);
                addButton.setText("Mise à jour");
            } else {
                nameField.clear();
                resetButton.setVisible(false);
                deleteBtn.setVisible(false);
                addButton.setText("Ajouter");
            }
        });
        subjectTable.setRowFactory(tv -> {
            TableRow<Matiere> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Matiere rowData = row.getItem();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/magicbook/interfaces/gestion_cours.fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                        Gestion_coursController contorller = loader.getController();
                        contorller.setup(rowData.getId());
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setResizable(false);
                        stage.setTitle("Gestion des cours");
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
    private void handleAddOrUpdate(ActionEvent event) {
        String name = nameField.getText().trim();
        if (name.isEmpty() && name.length() <= 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champ vide");
            alert.setHeaderText("le nom du cours ne peut pas être vide et doit avoir une longueur d'au moins 3");
            alert.showAndWait();
            return;
        }
        if (addButton.getText().equals("Ajouter")) {
            Matiere newSubject = new Matiere(name);
            int id = db.addMatiere(newSubject);
            newSubject.setId(id);
            subjectTable.getItems().add(newSubject);
            nameField.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Matière Ajouter");
            alert.setHeaderText("Votre matériel a été ajouté avec succès");
            alert.showAndWait();
            return;

        } else {
            Matiere selectedSubject = subjectTable.getSelectionModel().getSelectedItem();
            if (selectedSubject != null) {
                selectedSubject.setNom_mat(nameField.getText());
                db.updateSubject(selectedSubject);
                subjectTable.refresh();
                nameField.clear();
                subjectTable.getSelectionModel().clearSelection();
                addButton.setText("Ajouter");
                resetButton.setVisible(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès de la mise à jour");
                alert.setHeaderText("Votre matériel a été mis à jour avec succès");
                alert.showAndWait();
                return;
            }
        }
    }

    @FXML
    private void handleReset(ActionEvent event) {
        nameField.clear();
        resetButton.setVisible(false);
        deleteBtn.setVisible(false);
        addButton.setText("Ajouter");
    }

    @FXML
    private void onDelete(ActionEvent event) {
        Matiere selectedSubject = subjectTable.getSelectionModel().getSelectedItem();
        if (selectedSubject != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Boîte de dialogue de confirmation");
            alert.setHeaderText("Supprimer le cours");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer le cours sélectionné ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                subjectTable.getSelectionModel().clearSelection();
                db.deleteSubject(selectedSubject.getId());
                nameField.clear();
                resetButton.setVisible(false);
                deleteBtn.setVisible(false);
                addButton.setText("Ajouter");
                subjectTable.setItems(FXCollections.observableArrayList(db.getAllSubjects()));
            }
        }
    }

    @FXML
    private void OnSearch(ActionEvent event) throws IOException {
        String coursesName = this.searchField.getText();
        for (Matiere matiere : myList) {
            if (matiere.getNom_mat().equals(coursesName)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/magicbook/interfaces/gestion_cours.fxml"));
                Parent root;
                root = loader.load();
                Gestion_coursController contorller = loader.getController();
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
