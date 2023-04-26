package tn.magicbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.magicbook.connecteurs.Database;
import tn.magicbook.entité.Matiere;

public class ServiceMatiere {

    private Connection connection;

    public ServiceMatiere() {
        try {
            this.connection = Database.getInstance().getConnection();
        } catch (SQLException ex) {
            System.out.println("Error connecting to database: " + ex.getMessage());
        }
    }

    public int addMatiere(Matiere subject) {
        int generatedId = -1;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into matiere(nom_mat) values (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, subject.getNom_mat());
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

    // Update an existing subject
    public void updateSubject(Matiere subject) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update matiere set nom_mat=? where id=?");
            // Parameters start with 1
            preparedStatement.setString(1, subject.getNom_mat());
            preparedStatement.setInt(2, subject.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a subject
    public void deleteSubject(int id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from matiere where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all subjects
    public List<Matiere> getAllSubjects() {
        List<Matiere> subjects = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from matiere");
            while (rs.next()) {
                Matiere subject = new Matiere();
                subject.setId(rs.getInt("id"));
                subject.setNom_mat(rs.getString("nom_mat"));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    // Get a subject by id
    public Matiere getSubjectById(int id) {
        Matiere subject = new Matiere();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from subjects where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                subject.setId(rs.getInt("id"));
                subject.setNom_mat(rs.getString("nom_mat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }
}
