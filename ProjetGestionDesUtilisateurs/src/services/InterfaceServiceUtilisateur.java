/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author MMD
 * @param <T>
 */
public interface InterfaceServiceUtilisateur<T> {
      
    void ajouter_utilisateur(T u) throws SQLException ;

    void modifier_utilisateur(T u) throws SQLException ;

    void supprimer_utilisateur(T u) throws SQLException ;

    List<T> recuperer_liste_utilisateur() throws SQLException ;
}
