/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author farah
 */

import java.util.Date;
public class Activity {
    private int id, nbparticipants;
    private String nomact, positionact;
    private Date dateact;
    
    
    public Activity(){
        //type = new Type(); 
    }
    
    
    public Activity(String nomact, String positionact, Date dateact, int nbparticipants){
        this.nomact=nomact;
        this.positionact=positionact;
        this.dateact=dateact;
        this.nbparticipants=nbparticipants;
        
    }
    
    public Activity(String nomact, String positionact, Date dateact){
        this.nomact=nomact;
        this.positionact=positionact;
        this.dateact=dateact;
    }
    
    public Activity(String nomact, String positionact,int nbparticipants){
        this.nomact=nomact;
        this.positionact=positionact;
        this.nbparticipants=nbparticipants;
    }
    public Activity(String nomact, Date dateact, int nbparticipants){
        this.nomact=nomact;
        this.dateact=dateact;
        this.nbparticipants=nbparticipants;
    }
    
    public Activity(String nomact, String positionact){
        this.nomact=nomact;
        this.positionact=positionact;
    }
    
    public Activity(String nomact, int nbparticipants){
        this.nomact=nomact;
        this.nbparticipants=nbparticipants;
    }
    public Activity(String nomact, Date dateact){
        this.nomact=nomact;
        this.dateact=dateact;
    }
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbParticipants() {
        return nbparticipants;
    }
    
    public void setNbParticipants(int nb){
        this.nbparticipants=nb;
    }
    
    public String getNomAct(){
        return nomact;
    }
    public void setNomAct(String nomact){
        this.nomact=nomact;
    }
    
    public String getPositionAct(){
        return positionact;
    }
    public void setPositionAct(String position){
        this.positionact=position;
    }
    
    public Date getDateAct(){
        return dateact;
    }
    
    public void setDateAct(Date dateact){
        this.dateact=dateact;
    }
    
    
    
    @Override
    public String toString() {
        return "Activite{" + "id=" + id + ", nomAct=" + nomact + ", position=" + positionact + ", date=" + dateact + ",nb participants=" + nbparticipants  + '}';
    }
    
}
