/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;

/**
 *
 * @author Home
 */
public class Reponse {
         private int id;
   
   private String contenu_rep;
   private Date date_rep ; 
   private int status_rep;

    public Reponse() {
    }

    public Reponse(int id, Date date_rep, int Status_rep, String contenu_rep) {
        this.id = id;
        this.date_rep = date_rep;
        this.status_rep = Status_rep;
        this.contenu_rep = contenu_rep;
    }

    public Reponse(Date date_rep, int status_rec, String contenu_rec) {
        this.date_rep = date_rep;
        this.status_rep = status_rec;
        this.contenu_rep = contenu_rec;
    }

    public int getId() {
        return id;
    }

    public Date getDate_rep() {
        return date_rep;
    }

    public int getStatus_rep() {
        return status_rep;
    }

    

    public String getContenu_rep() {
        return contenu_rep;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_rep(Date date_rep) {
        this.date_rep = date_rep;
    }

    public void setStatus_rep(int status_rec) {
        this.status_rep = status_rep;
    }

    public void setContenu_rep(String contenu_rec) {
        this.contenu_rep = contenu_rep;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", date_rep=" + date_rep + ", Status_rec=" + status_rep + ", contenu_rep=" + contenu_rep + '}';
    }
}
