/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import javax.activation.DataSource;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import util.MyDB;




/**
 * FXML Controller class
 *
 * @author user
 */
public class StatDonController implements Initializable {

    @FXML
    private PieChart piechart;

    ObservableList< PieChart.Data> piechartdata;
    ArrayList< String> p = new ArrayList< String>();
    ArrayList< Integer> c = new ArrayList< Integer>();


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        loadData();
       
        piechart.setData(piechartdata);
        piechart.setTitle("Statistiques des types des dons MAGIC BOOK");
    }    
    
     public void loadData() {

        String query = "select COUNT(*) as count ,type_don from don GROUP BY type_don "; //ORDER BY P asc

        piechartdata = FXCollections.observableArrayList();

       
        Connection cnx = MyDB.getInstance().getCnx();


        try {

            ResultSet rs = cnx.createStatement().executeQuery(query);
            while (rs.next()) {
                
                piechartdata.add(new PieChart.Data(rs.getString("type_don"), rs.getInt("count")));
                p.add(rs.getString("type_don"));
                c.add(rs.getInt("count"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }  
      
    
}
