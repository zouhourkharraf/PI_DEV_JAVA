/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ALYSSA
 */

public class matiere {
    
    private int id;
    private String nmatiere;
    
    public matiere() {
        
    }

    public matiere(int id, String nmatiere) {
        this.id = id;
        this.nmatiere = nmatiere;
    }
   
     public matiere(String nmatiere) {
        this.nmatiere = nmatiere;
    }
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNmatiere() {
        return nmatiere;
    }

    public void setNmatiere(String nmatiere) {
        this.nmatiere = nmatiere;
    }

    @Override
    public String toString() {
        return "matiere{" + "id=" + id + ", nmatiere=" + nmatiere + '}';
    }
    
    
   
    
}
