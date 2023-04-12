/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magicbook1;

import Entite.Reclamation;
import Entite.Reponse;
import Service.ServiceReclamation;
import Service.ServiceReponse;
import java.sql.Date;

/**
 *
 * @author Home
 */
public class MagicBook1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
       // sr.modifier(r, 1);
        //sr.supprimer(1);
      //  sr.afficher();
        Reclamation r= new Reclamation("test", "ttest", "bien", new Date(2023, 6, 6), 1, "yaya");
        ServiceReclamation sr=new ServiceReclamation();
        //sr.ajouter(r);
       
        Reponse re=new Reponse(new Date(2025, 5, 5), 2, "teet");
        ServiceReponse sre=new ServiceReponse();
       // sre.supprimer(19);
        /* sre.modifier(r, 1);
        sr.supprimer(1);*/
        sre.ajouter(re);
        //sre.supprimer(2);
    }
    
}
