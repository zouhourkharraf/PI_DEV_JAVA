/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Type;
import entities.Activite;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import services.ActiviteService;
import services.TypeService;
import util.MyDB;
import java.util.List;


/**
 *
 * @author farah
 */
public class Test {
    /*
    public static void main(String[] args){
        
        //Type t = new Type("tla", "3a32");
       // TypeService ts = new TypeService();
        
        try{
            
            //ts.modifier(t);
           // System.out.println(ts.recuperer());
           // System.out.println(ts.recupererTypeById(3));
            //System.out.println("here");
            //TypeService ts = new TypeService();
            
            
    Type t = ts.recupererTypeById(3);
    if (t != null) {
        t.setNomType("Nouveau nom de type");
        t.setDescriptionType("Nouvelle description de type");
        ts.modifier(t);
        System.out.println("Type modifié avec succès !");
    } else {
        System.out.println("Type avec l'ID 3 introuvable !");
    
            //TypeService ts = new TypeService();
    Type nouveauType = new Type();
    nouveauType.setNomType("Neeee");
    nouveauType.setDescriptionType("Ceci est un nouveau type");
    ts.ajouter(t);
    System.out.println("Nouveau type ajouté avec succès !");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("hello");
        
        
        TypeService ts = new TypeService();
int idASupprimer = 15; // l'identifiant à supprimer
try {
    ts.supprimer(idASupprimer);
    System.out.println("Le type avec l'identifiant " + idASupprimer + " a été supprimé avec succès !");
} catch (SQLException ex) {
    System.out.println("Erreur lors de la suppression du type avec l'identifiant " + idASupprimer + " : " + ex.getMessage());
}
    }
       

    // Ajouter l'objet Activite à la base de données
    try {
         Activite activite = new Activite();
    activite.setNomAct("Activité test");
    activite.setPositionAct("Test position");
    activite.setDateAct(new Date());
    activite.setNbParticipants(10);

    // Récupérer un objet Type existant depuis la base de données
   
    TypeService type = new TypeService();

    int idType = 2;
   Type t = type.recupererTypeById(idType);

    activite.setType(t);
    ActiviteService as = new ActiviteService();
     
    as.ajouter(activite);
    System.out.println("Activité ajoutée avec succès !");
       
        
       
    } catch (SQLException e) {
        System.err.println("Erreur lors de l'ajout de l'activité : " + e.getMessage());
    }

        //affichage

       TypeService typeService = new TypeService();
        try {
            List<Type> types = typeService.recuperer();
            for (Type t : types) {
                System.out.println(t.getId() + " " + t.getNomType() + " " + t.getDescriptionType());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    
    }
}
