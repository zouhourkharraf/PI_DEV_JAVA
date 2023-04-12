/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Home
 */
public class Reclamation {

     private int id;
    private String type_rec ; 
    private String titre_rec ; 
    private String contenu_rec;
    private Date date_rec;
    private int statut_rec; 
    private String username;

    public Reclamation() {
    }
    public int getId() {
        return id;
    }

    public String getType_rec() {
        return type_rec;
    }

    public String getTitre_rec() {
        return titre_rec;
    }

    public String getContenu_rec() {
        return contenu_rec;
    }

    public Date getDate_rec() {
        return date_rec;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType_rec(String type_rec) {
        this.type_rec = type_rec;
    }

    public void setTitre_rec(String titre_rec) {
        this.titre_rec = titre_rec;
    }

    public void setContenu_rec(String contenu_rec) {
        this.contenu_rec = contenu_rec;
    }

    public void setDate_rec(Date date_rec) {
        this.date_rec = date_rec;
    }

    public int getStatut_rec() {
        return statut_rec;
    }

    public void setStatut_rec(int statut_rec) {
        this.statut_rec = statut_rec;
    }

    

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", type_rec=" + type_rec + ", titre_rec=" + titre_rec + ", contenu_rec=" + contenu_rec + ", date_rec=" + date_rec + ", statut_rec=" + statut_rec + ", username=" + username + '}';
    }

    public Reclamation(int id, String type_rec, String titre_rec, String contenu_rec, Date date_rec, int statut_rec, String username) {
        this.id = id;
        this.type_rec = type_rec;
        this.titre_rec = titre_rec;
        this.contenu_rec = contenu_rec;
        this.date_rec = date_rec;
        this.statut_rec = statut_rec;
        this.username = username;
    }

    public Reclamation(String titre_rec,String type_rec,  String contenu_rec, Date date_rec, int statut_rec, String username) {
       this.titre_rec = titre_rec;
        this.type_rec = type_rec;
        this.contenu_rec = contenu_rec;
        this.date_rec = date_rec;
        this.statut_rec = statut_rec;
        this.username = username;
    }
    
    
}