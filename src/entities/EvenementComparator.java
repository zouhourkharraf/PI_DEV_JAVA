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

import java.util.Comparator;

public class EvenementComparator implements Comparator<Evenement> {
    @Override
    /*public int compare(Evenement e1, Evenement e2) {
        return e1.getNom_ev().compareTo(e2.getNom_ev());
    }*/
    public int compare(Evenement e1, Evenement e2) {
        return e1.getDated_ev().compareTo(e2.getDated_ev());
    }
}

    

