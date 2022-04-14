package models;


import java.util.List;

public class Client extends Conductor {
    private int idClient;
    private String adresse;
    private int numTelephone;
    private int numCarteCredit;
    private int numPermisConduire;

    //lorsqu'un client s'inscrit il doit indiquer les autres conducteurs potentiels
    List<Conductor> conducteursAccompagnants;

    public Client(String nom, String preNom, int numPermisConduire, int idClient, String adresse, int numTelephone, int numCarteCredit) {
        super(nom, preNom, numPermisConduire);
        this.idClient = idClient;
        this.adresse = adresse;
        this.numTelephone = numTelephone;
        this.numCarteCredit = numCarteCredit;
        this.numPermisConduire = numPermisConduire;

    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(int numTelephone) {
        this.numTelephone = numTelephone;
    }

    public int getNumCarteCredit() {
        return numCarteCredit;
    }

    public void setNumCarteCredit(int numCarteCredit) {
        this.numCarteCredit = numCarteCredit;
    }

    public List<Conductor> getConducteursAccompagnants() {
        return conducteursAccompagnants;
    }

    public void setConducteursAccompagnants(List<Conductor> conducteursAccompagnants) {
        this.conducteursAccompagnants = conducteursAccompagnants;
    }

    @Override
    public int getNumPermisConduire() {
        return numPermisConduire;
    }

    @Override
    public void setNumPermisConduire(int numPermisConduire) {
        this.numPermisConduire = numPermisConduire;
    }

    public boolean equals(Client obj) {
        if(obj == null)
            return false;

        if(this.idClient == obj.idClient && this.numCarteCredit == obj.numCarteCredit)
            return true;
        return false;
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
}
