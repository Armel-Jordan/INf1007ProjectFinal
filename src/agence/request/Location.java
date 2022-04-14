package agence.request;

import agence.models.Client;
import agence.models.Vehicule;

import java.time.LocalDateTime;
import java.util.UUID;

public class Location {
    private String idLocation;
    private LocalDateTime dateDebutLocation;
    private LocalDateTime dateFinPrevueLocation;
    private double kilometrageOffert;
    private double kilometrageActuel;
    private Vehicule vehicule;
    private Client client;
    private boolean estEnCours;
    private double montantGarantie;

    public Location(LocalDateTime dateFinPrevueLocation, Vehicule vehicule, Client client) {
        this.idLocation = UUID.randomUUID().toString();
        this.dateDebutLocation = LocalDateTime.now();
        this.dateFinPrevueLocation = dateFinPrevueLocation;
        this.kilometrageOffert = calculKilometrageOffert();
        this.kilometrageActuel = this.vehicule.getKilometrage();
        this.vehicule = vehicule;
        this.client = client;
        this.estEnCours = false;
        this.montantGarantie = 200;
    }

    public Location() {}

    /**
     * Methode qui permet de calculer le nombre de kilometrage permit pour la location
     * @return double
     * */
    public double calculKilometrageOffert() {
        LocalDateTime dateDebutLocation = this.dateDebutLocation;
        // 4 jours de location
        LocalDateTime dateFinPrevueLocation = this.dateFinPrevueLocation;
        // difference de jour entre la date de debut et la date de fin
        long nbJour = dateFinPrevueLocation.toLocalDate().toEpochDay() - dateDebutLocation.toLocalDate().toEpochDay();

        // nombre de kilometrage offert
        return (double) (nbJour * 100);
    }

    public LocalDateTime getDateDebutLocation() {
        return dateDebutLocation;
    }

    public void setDateDebutLocation(LocalDateTime dateDebutLocation) {
        this.dateDebutLocation = dateDebutLocation;
    }

    public LocalDateTime getDateFinPrevueLocation() {
        return dateFinPrevueLocation;
    }

    public void setDateFinPrevueLocation(LocalDateTime dateFinPrevueLocation) {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isEstEnCours() {
        return estEnCours;
    }

    public void setEstEnCours(boolean estEnCours) {
        this.estEnCours = estEnCours;
    }

    private void mettreFinLocation(){
        this.estEnCours = true;
    }

    @Override
    public String toString() {
        return "Location{" +
                "dateDebutLocation=" + dateDebutLocation +
                ", dateFinPrevueLocation=" + dateFinPrevueLocation +
                ", kilometrageOffert=" + kilometrageOffert +
                ", vehicule=" + vehicule +
                ", client=" + client +
                '}';
    }


    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public double getKilometrageActuel() {
        return kilometrageActuel;
    }

    public double getMontantGarantie() {
        return montantGarantie;
    }

    public void setMontantGarantie(double montantGarantie) {
        this.montantGarantie = montantGarantie;
    }
}
