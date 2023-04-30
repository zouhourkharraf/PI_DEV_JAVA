/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicbook1;

import Entite.Reclamation;
import Entite.Repons;
import static GUI.FXMLreclamationfrontController.sendSms;
import Service.ServiceReclamation;
import Service.ServiceRepons;
import java.sql.Date;
//import java.util.Date;

/**
 *
 * @author Home
 */
public class MagicBook1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         //sendSms("+21696869820", "Hello from JavaFX!");
        // TODO code application logic here
       
       /* Reclamation r= new Reclamation("proposition", "test", "moyen", new Date(2023, 7,3), 1, "Eyaa");
        ServiceReclamation sr=new ServiceReclamation();
        // sr.modifier(r, 1);
        sr.supprimer(24);
        //sr.afficher();
        //sr.ajouter(r);
        
       
        Reponse re=new Reponse(new Date(2025, 5, 5), 2, "teet");
        ServiceReponse sre=new ServiceReponse();
       // sre.supprimer(19);
        /* sre.modifier(r, 1);
        sr.supprimer(1);*/
       // sre.ajouter(re);
        //sre.supprimer(2);
    }//
   
}
 