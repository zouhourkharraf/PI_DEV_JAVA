package tn.magicbook.entité;

public class utilisateur {
    private int id;
    private String nom;
    
    public utilisateur(int id, String name) {
        this.id = id;
        this.nom = name;
    }

    public utilisateur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "utilisateur{" + "id=" + id + ", nom=" + nom + '}';
    }
}
