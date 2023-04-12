/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author Home
 */
import Entite.Reponse;
import Util.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Home
 */
public class ServiceReponse implements Iservice<Reponse>{
Connection conn;
    public ServiceReponse(){
        conn=DataSource.getInstance().getConn();
    }
    @Override
    public void ajouter(Reponse t) {
        try{
        String query = "INSERT INTO `reponse` (`date_rep`, `status_rep`, `contenu_rep`) VALUES "
                + "('" + t.getDate_rep() + "', '" + t.getStatus_rep() + "', '" + t.getContenu_rep() + "')";
        
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } 
        catch (SQLException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public void modifier(Reponse t, int id) {
          try {
            String query="UPDATE `reponse` SET `status_rep`='"+t.getStatus_rep()+"',"
                    + "`date_rep`='"+t.getDate_rep()+"',"
                    + "`contenu_rep`='"+t.getContenu_rep()+
                   "' WHERE id="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        }}

    @Override
    public void supprimer(int id) throws Exception {
       try {
            String query="DELETE FROM `reponse` WHERE id="+id;
            Statement st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        } }

    @Override
    public List<Reponse> afficher() {
     List<Reponse> lre=new ArrayList<>();
        try {
            String query="SELECT * FROM `reponse`";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Reponse re=new Reponse();
                re.setDate_rep(rs.getDate("date_rep"));
                re.setStatus_rep(rs.getInt("status_rep"));
                re.setContenu_rep(rs.getString("contenu_rep"));
                re.setId(rs.getInt("id"));
                lre.add(re);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lre; }
    
}

