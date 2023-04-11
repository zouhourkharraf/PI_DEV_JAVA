/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author user
 */
public class Don {
    private int id,evenement_id,somme_don;
    String type_don;

    public Don() {
    }

    public Don(int id, int evenement_id, String type_don, int somme_don) {
        this.id = id;
        this.evenement_id = evenement_id;
        this.type_don = type_don;
        this.somme_don = somme_don;
        
    }

    public Don(int evenement_id, String type_don, int somme_don) {
        this.evenement_id = evenement_id;
        this.type_don = type_don;
        this.somme_don = somme_don;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    public int getSomme_don() {
        return somme_don;
    }

    public void setSomme_don(int somme_don) {
        this.somme_don = somme_don;
    }

    public String getType_don() {
        return type_don;
    }

    public void setType_don(String type_don) {
        this.type_don = type_don;
    }

    @Override
    public String toString() {
        return "Don{" + "id=" + id + ", evenement_id=" + evenement_id + ", somme_don=" + somme_don + ", type_don=" + type_don + '}';
    }
    
    
    
    
    
    
    
}
