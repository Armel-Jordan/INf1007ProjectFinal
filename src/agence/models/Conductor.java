package agence.models;

import java.util.UUID;

public class Conductor {

    private String idConductor;
    private String nom;
    private String prenom;
    private String numPermisConduire;

    public Conductor(String nom, String prenom, String numPermisConduire) {
        this.nom = nom;
        this.prenom = prenom;
        this.numPermisConduire = numPermisConduire;
    }

    public Conductor(){
        this.idConductor = UUID.randomUUID().toString();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumPermisConduire() {
        return numPermisConduire;
    }

    public void setNumPermisConduire(String numPermisConduire) {
        this.numPermisConduire = numPermisConduire;
    }


    public String getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(String idConductor) {
        this.idConductor = idConductor;
    }

    @Override
    public String toString() {
        return "Conducteur : \n" +
                "\tnom = '" + nom + "'\n" +
                "\tprenom = '" + prenom + "'\n" +
                "\tnumPermisConduire=" + numPermisConduire;
    }
}
