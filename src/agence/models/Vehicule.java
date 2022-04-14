package agence.models;

public class Vehicule {

    private String immatriculation;
    private double kilometrage;
    private String couleur;
    private String type;
    private ModeleV modeleV;
    private boolean estEndommage;
    private boolean estDisponible;
    private String numeroLocation;
    //private List<Type> typeVehicule;



    //constructeur

    public Vehicule(String immatriculation, double kilometrage, String couleur, String type,boolean estEndommage, boolean estDisponible) {
        this.immatriculation = immatriculation;
        this.kilometrage = kilometrage;
        this.couleur = couleur;
        this.type = type;
        this.estEndommage = estEndommage;
        this.estDisponible = estDisponible;
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

    public ModeleV getListModele() {
        return modeleV;
    }

    public void setListModele(ModeleV modeleV) {
        this.modeleV = modeleV;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    public ModeleV getModele() {
        return modeleV;
    }

    public void setModele(ModeleV modele) {
        this.modeleV = modele;
    }

    public boolean isEstEndommage() {
        return estEndommage;
    }

    public void setEstEndommage(boolean estEndommage) {
        this.estEndommage = estEndommage;
    }

    public String getNumeroLocation() {
        return numeroLocation;
    }

    public void setNumeroLocation(String numeroLocation) {
        this.numeroLocation = numeroLocation;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "immatriculation='" + immatriculation + '\'' +
                ", kilometrage=" + kilometrage +
                ", couleur='" + couleur + '\'' +
                ", type='" + type + '\'' +
                ", modele=" + modeleV +
                ", estEndommage=" + estEndommage +
                ", estDisponible=" + estDisponible +
                ", numeroLocation='" + numeroLocation + '\'' +
                '}';
    }
}


