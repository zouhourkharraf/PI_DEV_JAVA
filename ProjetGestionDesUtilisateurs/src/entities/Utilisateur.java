/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MMD
 */
public class Utilisateur {
    int id,age_util;
    String nom_util,prenom_util,pseudo_util,mot_de_passe_util,email_util,genre_util,role_util;
    
    public Utilisateur(){}

    //constructeur complet (avec id)
    public Utilisateur(int id, int age_util, String nom_util, String prenom_util, String pseudo_util, String mot_de_passe_util, String email_util, String genre_util, String role_util) {
        this.id = id;
        this.age_util = age_util;
        this.nom_util = nom_util;
        this.prenom_util = prenom_util;
        this.pseudo_util = pseudo_util;
        this.mot_de_passe_util = mot_de_passe_util;
        this.email_util = email_util;
        this.genre_util = genre_util;
        this.role_util = role_util;
    }
    
    //Constructeur sans id
      public Utilisateur(int age_util, String nom_util, String prenom_util, String pseudo_util, String mot_de_passe_util, String email_util, String genre_util, String role_util) {
        this.age_util = age_util;
        this.nom_util = nom_util;
        this.prenom_util = prenom_util;
        this.pseudo_util = pseudo_util;
        this.mot_de_passe_util = mot_de_passe_util;
        this.email_util = email_util;
        this.genre_util = genre_util;
        this.role_util = role_util;
    }

      // Getters
    public int getId() {
        return id;
    }

    public int getAge_util() {
        return age_util;
    }

    public String getNom_util() {
        return nom_util;
    }

    public String getPrenom_util() {
        return prenom_util;
    }

    public String getPseudo_util() {
        return pseudo_util;
    }

    public String getMot_de_passe_util() {
        return mot_de_passe_util;
    }

    public String getEmail_util() {
        return email_util;
    }

    public String getGenre_util() {
        return genre_util;
    }

    public String getRole_util() {
        return role_util;
    }



    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAge_util(int age_util) {
        this.age_util = age_util;
    }

    public void setNom_util(String nom_util) {
        this.nom_util = nom_util;
    }

    public void setPrenom_util(String prenom_util) {
        this.prenom_util = prenom_util;
    }

    public void setPseudo_util(String pseudo_util) {
        this.pseudo_util = pseudo_util;
    }

    public void setMot_de_passe_util(String mot_de_passe_util) {
        this.mot_de_passe_util = mot_de_passe_util;
    }

    public void setEmail_util(String email_util) {
        this.email_util = email_util;
    }

    public void setGenre_util(String genre_util) {
        this.genre_util = genre_util;
    }

    public void setRole_util(String role_util) {
        this.role_util = role_util;
    }
    
   //toString
        @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", age_util=" + age_util + ", nom_util=" + nom_util + ", prenom_util=" + prenom_util + ", pseudo_util=" + pseudo_util + ", mot_de_passe_util=" + mot_de_passe_util + ", email_util=" + email_util + ", genre_util=" + genre_util + ", role_util=" + role_util + '}';
    }
    
    
    
}
