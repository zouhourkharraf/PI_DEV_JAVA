package tn.magicbook.controleurs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.apache.commons.mail.EmailException;
import tn.magicbook.api.EmailSender;
import tn.magicbook.dao.ServiceCours;
import tn.magicbook.entité.Cours;

public class Gestion_coursController implements Initializable {

    String email = "ellyssa.khalfaoui@esprit.tn";

    @FXML
    private TableView<Cours> courseTable;
    @FXML
    private TableColumn<Cours, Integer> matiereIdColumn;
    @FXML
    private TableColumn<Cours, Date> dateColumn;
    @FXML
    private TableColumn<Cours, Integer> tempsColumn;
    @FXML
    private TableColumn<Cours, String> titreColumn;
    @FXML
    private TextField titreField;
    @FXML
    private Button addButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button uploadButton;

    private final ServiceCours coursDao = new ServiceCours();

    private String filePath = "";
    @FXML
    private DatePicker datePicker;

    int pageid;
    @FXML
    private Text fileName;
    @FXML
    private TextField timer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        matiereIdColumn.setCellValueFactory(new PropertyValueFactory<>("matiere_id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date_cour"));
        tempsColumn.setCellValueFactory(new PropertyValueFactory<>("temps_cour"));
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre_cour"));

        courseTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { 
                Cours selectedCourse = courseTable.getSelectionModel().getSelectedItem();
                if (selectedCourse != null) {
                    titreField.setText(selectedCourse.getTitre_cour());
                    timer.setText(selectedCourse.getTemps_cour() + "");
                    this.fileName.setText(selectedCourse.getFichier().getName());
                }
            }
        });
        courseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                resetButton.setVisible(true);
                deleteButton.setVisible(true);
                addButton.setText("Mise à jour");
            }
        });
        deleteButton.setOnAction(e -> {
            Cours selectedCourse = courseTable.getSelectionModel().getSelectedItem();
            if (selectedCourse != null) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Boîte de dialogue de confirmation");
                alert.setHeaderText("Supprimer le cours");
                alert.setContentText("Êtes-vous sûr de vouloir supprimer le cours sélectionné ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    courseTable.getItems().remove(selectedCourse);
                    coursDao.deleteCours(selectedCourse.getId());
                }
            }
        });
    }

    @FXML
    private void onUpload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a PDF file to upload");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("PDF Files", "*.pdf")
        );
        File selectedFile = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());
        if (selectedFile != null) {
            this.fileName.setText(selectedFile.getName());
            File destDir = new File("cours");
            if (!destDir.exists()) {
                destDir.mkdir();
            }
            File destFile = new File(destDir, selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                this.filePath = destFile.getPath();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("succès");
                alert.setHeaderText("fichier téléchargé avec succès");
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setup(int id) {
        ObservableList<Cours> courses = FXCollections.observableArrayList(coursDao.getAllCoursByIdMatiere(id));
        courseTable.setItems(courses);
        this.pageid = id;
    }

    @FXML
    private void onAdd(ActionEvent event) {
        String buttonText = this.addButton.getText();

        LocalDate date = datePicker.getValue();

        if (date == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Date non valide");
            alert.setHeaderText("la date est non valide");
            alert.showAndWait();
            return;
        }
        if (titreField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champ vide");
            alert.setHeaderText("le titre ne peut pas être vide");
            alert.showAndWait();
            return;
        }
        try {
            int time = Integer.parseInt(this.timer.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ce n'est pas un numéro valide");
            alert.setHeaderText("le nombre des heure n'est pas un numéro valide");
            alert.showAndWait();
            return;
        }
        if (buttonText.equals("Mise à jour")) {
            handleUpdateCourse();
        } else if (buttonText.equals("Ajouter")) {
            handleAddCourse();
        }
    }

    private void handleUpdateCourse() {
        Cours selectedCourse = courseTable.getSelectionModel().getSelectedItem();

        if (selectedCourse != null) {
            int selectedCourseIndex = courseTable.getSelectionModel().getSelectedIndex();

            String titre = titreField.getText();
            LocalDate date = datePicker.getValue();
            int temps = Integer.valueOf(this.timer.getText());
            File fichier = new File(this.filePath);

            selectedCourse.setTitre_cour(titre);
            selectedCourse.setDate_cour(Date.valueOf(date));
            selectedCourse.setTemps_cour(temps*10000);
            selectedCourse.setFichier(fichier);
            coursDao.updateCours(selectedCourse);
            courseTable.getItems().set(selectedCourseIndex, selectedCourse);
            resetFields();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succès");
            alert.setHeaderText("cours mis à jour avec succès");
            alert.showAndWait();
            try {
                EmailSender.sendEmail(this.email, "Mise à jour des cours", "le cours" + titre + "a été mis à jour");
            } catch (EmailException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleAddCourse() {
        String titre = titreField.getText();
        int utilisateurId = 1;
        int matiereId = this.pageid;
        LocalDate date = datePicker.getValue();
        int temps = Integer.valueOf(this.timer.getText());
        File fichier = new File(this.filePath);

        if (date != null && !titre.isEmpty()) {
            Cours newCourse = new Cours(utilisateurId, matiereId, Date.valueOf(date), temps, titre, fichier);
            int id = coursDao.addCours(newCourse);
            resetFields();
            newCourse.setId(id);
            courseTable.getItems().add(newCourse);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succès");
            alert.setHeaderText("cours ajouter avec succès");
            alert.showAndWait();
            try {
                EmailSender.sendEmail(this.email, "Nouveau cours", "un nouveau cours a été ajouté, intitulé " + titre);
            } catch (EmailException e) {
                e.printStackTrace();
            }
        }
    }

    private void resetFields() {
        titreField.clear();
        resetButton.setVisible(false);
        deleteButton.setVisible(false);
        addButton.setText("Ajouter");
    }

    @FXML
    private void OnReset(ActionEvent event) {
        resetFields();
    }

}
