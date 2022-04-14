package agence.request;

import agence.models.Client;
import agence.models.Vehicule;

import java.util.Date;

public class Location {
    private Date dateDebutLocation;
    private Date dateFinPrevueLocation;
    private double kilometrageOffert;
    private Vehicule vehicule;
    private String duree;
    private Client client;
    private boolean estTermine;

    public Location(Date dateDebutLocation, Date dateFinPrevueLocation, double kilometrageOffert, Vehicule vehicule, String duree, Client client) {
        this.dateDebutLocation = dateDebutLocation;
        this.dateFinPrevueLocation = dateFinPrevueLocation;
        this.kilometrageOffert = kilometrageOffert;
        this.vehicule = vehicule;
        this.duree = duree;
        this.client = client;
        this.estTermine = false;
    }

    public Date getDateDebutLocation() {
        return dateDebutLocation;
    }

    public void setDateDebutLocation(Date dateDebutLocation) {
        this.dateDebutLocation = dateDebutLocation;
    }

    public Date getDateFinPrevueLocation() {
        return dateFinPrevueLocation;
    }

    public void setDateFinPrevueLocation(Date dateFinPrevueLocation) {
        this.dateFinPrevueLocation = dateFinPrevueLocation;
    }

    public double getKilometrageOffert() {
        return kilometrageOffert;
    }

    public void setKilometrageOffert(double kilometrageOffert) {
        this.kilometrageOffert = kilometrageOffert;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isEstTermine() {
        return estTermine;
    }

    public void setEstTermine(boolean estTermine) {
        this.estTermine = estTermine;
    }

    private void mettreFinLocation(){
        this.estTermine = true;
    }

    @Override
    public String toString() {
        return "Location{" +
                "dateDebutLocation=" + dateDebutLocation +
                ", dateFinPrevueLocation=" + dateFinPrevueLocation +
                ", kilometrageOffert=" + kilometrageOffert +
                ", vehicule=" + vehicule +
                ", duree='" + duree + '\'' +
                ", client=" + client +
                '}';
    }


}
