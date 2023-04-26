package tn.magicbook.entité;

import java.io.File;
import java.util.Date;

public class Cours {
    private int id;
    private int utilisateur_id;
    private int matiere_id;
    private Date date_cour;
    private int temps_cour;
    private String titre_cour;
    private File fichier;

    public Cours(int id, int userId, int subjectId, Date date, int duration, String title, File file) {
        this.id = id;
        this.utilisateur_id = userId;
        this.matiere_id = subjectId;
        this.date_cour = date;
        this.temps_cour = duration;
        this.titre_cour = title;
        this.fichier = file;
    }

    public Cours(int utilisateur_id, int matiere_id, Date date_cour, int temps_cour, String titre_cour, File fichier) {
        this.utilisateur_id = utilisateur_id;
        this.matiere_id = matiere_id;
        this.date_cour = date_cour;
        this.temps_cour = temps_cour;
        this.titre_cour = titre_cour;
        this.fichier = fichier;
    }
    
    

    public Cours() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public int getMatiere_id() {
        return matiere_id;
    }

    public void setMatiere_id(int matiere_id) {
        this.matiere_id = matiere_id;
    }

    public Date getDate_cour() {
        return date_cour;
    }

    public void setDate_cour(Date date_cour) {
        this.date_cour = date_cour;
    }

    public int getTemps_cour() {
        return temps_cour;
    }

    public void setTemps_cour(int temps_cour) {
        this.temps_cour = temps_cour;
    }

    public String getTitre_cour() {
        return titre_cour;
    }

    public void setTitre_cour(String titre_cour) {
        this.titre_cour = titre_cour;
    }

    public File getFichier() {
        return fichier;
    }

    public void setFichier(File fichier) {
        this.fichier = fichier;
    }

    @Override
    public String toString() {
        return "cours{" + "id=" + id + ", utilisateur_id=" + utilisateur_id + ", matiere_id=" + matiere_id + ", date_cour=" + date_cour + ", temps_cour=" + temps_cour + ", titre_cour=" + titre_cour + ", fichier=" + fichier + '}';
    }
    
}
