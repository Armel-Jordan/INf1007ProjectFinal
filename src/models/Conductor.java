package models;

public class Conductor {

    private String nom;
    private String preNom;
    private int numPermisConduire;

    public Conductor(String nom, String preNom, int numPermisConduire) {
        this.nom = nom;
        this.preNom = preNom;
        this.numPermisConduire = numPermisConduire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPreNom() {
        return preNom;
    }

    public void setPreNom(String preNom) {
        this.preNom = preNom;
    }

    public int getNumPermisConduire() {
        return numPermisConduire;
    }

    public void setNumPermisConduire(int numPermisConduire) {
        this.numPermisConduire = numPermisConduire;
    }

    @Override
    public String toString() {
        return "Conducteur{" +
                "nom='" + nom + '\'' +
                ", preNom='" + preNom + '\'' +
                ", numPermisConduire=" + numPermisConduire +
                '}';
    }
}
