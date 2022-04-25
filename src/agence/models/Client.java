package agence.models;


import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

public class Client {
    private String idClient;
    private Address adresse;
    private String nom;
    private String prenom;
    private String numTelephone;
    private String numCarteCredit;
    private String numPermisConduire;

    //lorsqu'un client s'inscrit il doit indiquer les autres conducteurs potentiels
    HashSet<Conductor> conducteursAccompagnants;

    public Client(String nom, String prenom, String numPermisConduire, Address adresse, String numTelephone, String numCarteCredit) {
        this.idClient = UUID.randomUUID().toString();
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numTelephone = numTelephone;
        this.numCarteCredit = numCarteCredit;
        this.numPermisConduire = numPermisConduire;
    }

    // constructeur vide
    public Client() {
        this.idClient = UUID.randomUUID().toString();
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public Address getAdresse() {
        return adresse;
    }

    public void setAdresse(Address adresse) {
        this.adresse = adresse;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public String getNumCarteCredit() {
        return numCarteCredit;
    }

    public void setNumCarteCredit(String numCarteCredit) {
        this.numCarteCredit = numCarteCredit;
    }

    public HashSet<Conductor> getConducteursAccompagnants() {
        return conducteursAccompagnants;
    }

    public void setConducteursAccompagnants(HashSet<Conductor> conducteursAccompagnants) {
        this.conducteursAccompagnants = conducteursAccompagnants;
    }

    public String getNumPermisConduire() {
        return numPermisConduire;
    }

    public void setNumPermisConduire(String numPermisConduire) {
        this.numPermisConduire = numPermisConduire;
    }

    public boolean equals(Client obj) {
        if(obj == null)
            return false;

        return Objects.equals(this.idClient, obj.idClient) && Objects.equals(this.numCarteCredit, obj.numCarteCredit);
    }

    @Override
    public String toString() {
        return "Client{" +
                "numeroPermis=" + numPermisConduire +
                ", idClient=" + idClient +
                ", adresse='" + adresse + '\'' +
                ", numTelephone=" + numTelephone +
                ", numCarteCredit=" + numCarteCredit +
                ", conducteursAccompagnants=" + conducteursAccompagnants +
                '}';
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
}
