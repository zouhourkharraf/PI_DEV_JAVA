/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;

/**
 *
 * @author Skand
 */
public class Evenement {

    private int id;
    private String nom_ev,lieu_ev,desc_ev,image_ev;
    LocalDate dated_ev,datef_ev;
    private float note_ev;

    public Evenement() {
    }

    public Evenement(int id, String nom_ev, LocalDate dated_ev, LocalDate datef_ev, String lieu_ev, String desc_ev, String image_ev) {
        this.id = id;
        this.nom_ev = nom_ev;
        this.lieu_ev = lieu_ev;
        this.desc_ev = desc_ev;
        this.image_ev = image_ev;
        this.dated_ev = dated_ev;
        this.datef_ev = datef_ev;
    }

    public Evenement(String nom_ev, LocalDate dated_ev, LocalDate datef_ev, String lieu_ev, String desc_ev, String image_ev) {
        this.nom_ev = nom_ev;
        this.lieu_ev = lieu_ev;
        this.desc_ev = desc_ev;
        this.image_ev = image_ev;
        this.dated_ev = dated_ev;
        this.datef_ev = datef_ev;
    }

    public Evenement(int id, String nom_ev,LocalDate dated_ev, LocalDate datef_ev, String lieu_ev, String desc_ev, String image_ev, float note_ev) {
        this.id = id;
        this.nom_ev = nom_ev;
         this.dated_ev = dated_ev;
        this.datef_ev = datef_ev;
        this.lieu_ev = lieu_ev;
        this.desc_ev = desc_ev;
        this.image_ev = image_ev;       
        this.note_ev = note_ev;
    }

    public Evenement(String nom_ev, LocalDate dated_ev, LocalDate datef_ev,String lieu_ev, String desc_ev, String image_ev,  float note_ev) {
        this.nom_ev = nom_ev;
        this.dated_ev = dated_ev;
        this.datef_ev = datef_ev;
        this.lieu_ev = lieu_ev;
        this.desc_ev = desc_ev;
        this.image_ev = image_ev;        
        this.note_ev = note_ev;
    }

    public Evenement(int aInt, String string, String toString, String toString0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_ev() {
        return nom_ev;
    }

    public void setNom_ev(String nom_ev) {
        this.nom_ev = nom_ev;
    }

    public String getLieu_ev() {
        return lieu_ev;
    }

    public void setLieu_ev(String lieu_ev) {
        this.lieu_ev = lieu_ev;
    }

    public String getDesc_ev() {
        return desc_ev;
    }

    public void setDesc_ev(String desc_ev) {
        this.desc_ev = desc_ev;
    }

    public String getImage_ev() {
        return image_ev;
    }

    public void setImage_ev(String image_ev) {
        this.image_ev = image_ev;
    }

    public LocalDate getDated_ev() {
        return dated_ev;
    }

    public void setDated_ev(LocalDate dated_ev) {
        this.dated_ev = dated_ev;
    }

    public LocalDate getDatef_ev() {
        return datef_ev;
    }

    public void setDatef_ev(LocalDate datef_ev) {
        this.datef_ev = datef_ev;
    }

    public float getNote_ev() {
        return note_ev;
    }

    public void setNote_ev(float note_ev) {
        this.note_ev = note_ev;
    }

    public Evenement(int id, String nom_ev, String lieu_ev, String desc_ev, String image_ev, float note_ev) {
        this.id = id;
        this.nom_ev = nom_ev;
        this.lieu_ev = lieu_ev;
        this.desc_ev = desc_ev;
        this.image_ev = image_ev;
        this.note_ev = note_ev;
    }
    
    

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom_ev=" + nom_ev + ", lieu_ev=" + lieu_ev + ", desc_ev=" + desc_ev + ", image_ev=" + image_ev + ", dated_ev=" + dated_ev + ", datef_ev=" + datef_ev + '}';
    }

    public Object getDate(String dated_ev) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
  
    
}
