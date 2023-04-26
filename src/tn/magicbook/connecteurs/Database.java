package tn.magicbook.connecteurs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/magicbook";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    private static Database instance;
    private Connection connection;
    
    private Database() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    public Connection getConnection() {
        return connection;
    }
}
