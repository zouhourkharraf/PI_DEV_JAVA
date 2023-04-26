package tn.magicbook.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tn.magicbook.connecteurs.Database;
import tn.magicbook.entité.Cours;

public class ServiceCours {

    private Connection connection;

    public ServiceCours() {
        try {
            this.connection = Database.getInstance().getConnection();
        } catch (SQLException ex) {
            System.out.println("Error connecting to database: " + ex.getMessage());
        }
    }

    public int addCours(Cours cours) {
        int generatedId = -1;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO cours(utilisateur_id, matiere_id, date_cour, temps_cour, titre_cour, fichier) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, cours.getUtilisateur_id());
            preparedStatement.setInt(2, cours.getMatiere_id());
            preparedStatement.setDate(3, new java.sql.Date(cours.getDate_cour().getTime()));
            preparedStatement.setInt(4, cours.getTemps_cour());
            preparedStatement.setString(5, cours.getTitre_cour());
            preparedStatement.setString(6, cours.getFichier().getPath());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    public void updateCours(Cours cours) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE cours SET utilisateur_id = ?, matiere_id = ?, date_cour = ?, temps_cour = ?, titre_cour = ?, fichier = ? WHERE id = ?");
            preparedStatement.setInt(1, cours.getUtilisateur_id());
            preparedStatement.setInt(2, cours.getMatiere_id());
            preparedStatement.setDate(3, new java.sql.Date(cours.getDate_cour().getTime()));
            preparedStatement.setInt(4, cours.getTemps_cour());
            preparedStatement.setString(5, cours.getTitre_cour());
            preparedStatement.setString(6, cours.getFichier().getPath());
            preparedStatement.setInt(7, cours.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCours(int id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM cours WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cours> getAllCours() {
        List<Cours> coursList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cours");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cours cours = new Cours();
                cours.setId(resultSet.getInt("id"));
                cours.setUtilisateur_id(resultSet.getInt("utilisateur_id"));
                cours.setMatiere_id(resultSet.getInt("matiere_id"));
                cours.setDate_cour(new Date(resultSet.getDate("date_cour").getTime()));
                Time time = resultSet.getTime("temps_cour");
                String seconds = time.getHours()+"" + time.getMinutes() + time.getSeconds();
                cours.setTemps_cour(Integer.valueOf(seconds));
                cours.setTitre_cour(resultSet.getString("titre_cour"));
                cours.setFichier(new File(resultSet.getString("fichier")));
                coursList.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coursList;
    }

    public List<Cours> getAllCoursByIdMatiere(int idMatiere) {
        List<Cours> coursList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM cours WHERE matiere_id=?");
            preparedStatement.setInt(1, idMatiere);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cours cours = new Cours();
                cours.setId(resultSet.getInt("id"));
                cours.setUtilisateur_id(resultSet.getInt("utilisateur_id"));
                cours.setMatiere_id(resultSet.getInt("matiere_id"));
                cours.setDate_cour(resultSet.getDate("date_cour"));
                cours.setTemps_cour(resultSet.getInt("temps_cour"));
                cours.setTitre_cour(resultSet.getString("titre_cour"));
                cours.setFichier(new File(resultSet.getString("fichier")));
                coursList.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coursList;
    }

}
