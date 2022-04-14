package agence.models;

public class Vehicule {
    private String immatriculation;
    private double kilometrage;
    private String couleur;
    private TypeVehicule type;
    private ModelVehicule modelVehicule;
    private boolean estEndommage;
    private boolean estDisponible;

    //constructeur
    public Vehicule(String immatriculation, String couleur, TypeVehicule type) {
        this.immatriculation = immatriculation;
        this.kilometrage = 0;
        this.couleur = couleur;
        this.type = type;
        this.estEndommage = false;
        this.estDisponible = true;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public ModelVehicule getListModele() {
        return modelVehicule;
    }

    public void setListModele(ModelVehicule modelVehicule) {
        this.modelVehicule = modelVehicule;
    }

    public TypeVehicule getType() {
        return type;
    }

    public void setType(TypeVehicule type) {
        this.type = type;
    }

    public boolean isEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    public ModelVehicule getModele() {
        return modelVehicule;
    }

    public void setModele(ModelVehicule modele) {
        this.modelVehicule = modele;
    }

    public boolean isEstEndommage() {
        return estEndommage;
    }

    public void setEstEndommage(boolean estEndommage) {
        this.estEndommage = estEndommage;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "immatriculation='" + immatriculation + '\'' +
                ", kilometrage=" + kilometrage +
                ", couleur='" + couleur + '\'' +
                ", type='" + type + '\'' +
                ", modele=" + modelVehicule +
                ", estEndommage=" + estEndommage +
                ", estDisponible=" + estDisponible +
                '}';
    }
}


