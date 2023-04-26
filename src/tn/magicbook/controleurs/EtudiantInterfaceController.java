package tn.magicbook.controleurs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import tn.magicbook.dao.ServiceCours;
import tn.magicbook.entité.Cours;

public class EtudiantInterfaceController implements Initializable {

    @FXML
    private TableView<Cours> courseTable;
    @FXML
    private TableColumn<Cours, Date> dateColumn;
    @FXML
    private TableColumn<Cours, Integer> tempsColumn;
    @FXML
    private TableColumn<Cours, String> titreColumn;

    private final ServiceCours coursDao = new ServiceCours();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date_cour"));
        tempsColumn.setCellValueFactory(new PropertyValueFactory<>("temps_cour"));
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titre_cour"));
        ObservableList<Cours> courses = FXCollections.observableArrayList(coursDao.getAllCours());
        courseTable.setItems(courses);
        courseTable.setRowFactory(tv -> {
            TableRow<Cours> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    Cours cours = row.getItem();
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Choose Destination Directory");
                    fileChooser.setInitialFileName(row.getItem().getFichier().getName()+".pdf");
                    File destDir = fileChooser.showSaveDialog(null);
                    if (destDir != null) {
                        try {
                            File file = new File(cours.getFichier().getPath());
                            InputStream inputStream = new FileInputStream(file);
                            OutputStream outputStream = new FileOutputStream(destDir);

                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = inputStream.read(buffer)) > 0) {
                                outputStream.write(buffer, 0, length);
                            }

                            inputStream.close();
                            outputStream.close();

                            System.out.println("File downloaded successfully!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            return row;
        });
    }
    
    public void setup(int id) {
        ObservableList<Cours> courses = FXCollections.observableArrayList(coursDao.getAllCoursByIdMatiere(id));
        courseTable.setItems(courses);
    }

}
