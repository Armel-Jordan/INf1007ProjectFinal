package models;

public class ModeleV {

    private String marque;
    private String modele;
    private int nbPlace;
    // private int moteur; ??
    //private Type type;

    public ModeleV(String marque, String modele, int nbPlace) {
        this.marque = marque;
        this.modele = modele;
        this.nbPlace = nbPlace;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    @Override
    public String toString() {
        return "Modele{" +
                "marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", nbPlace=" + nbPlace +
                '}';
    }
}

