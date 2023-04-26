package tn.magicbook.entité;

public class Matiere {
    private int id;
    private String nom_mat;
    
    public Matiere(int id, String nom_mat) {
        this.id = id;
        this.nom_mat = nom_mat;
    }

    public Matiere() {
    }

    public Matiere(String nom_mat) {
        this.nom_mat = nom_mat;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_mat() {
        return nom_mat;
    }

    public void setNom_mat(String nom_mat) {
        this.nom_mat = nom_mat;
    }

    @Override
    public String toString() {
        return "matiere{" + "id=" + id + ", nom_mat=" + nom_mat + '}';
    }
}
