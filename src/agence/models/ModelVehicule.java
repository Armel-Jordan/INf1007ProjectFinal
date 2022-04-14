package agence.models;

public class ModelVehicule {

    private String marque;
    private int nbPlace;

    public ModelVehicule(String marque, String modele, int nbPlace) {
        this.marque = marque;
        this.nbPlace = nbPlace;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
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
                ", nbPlace=" + nbPlace +
                '}';
    }
}

