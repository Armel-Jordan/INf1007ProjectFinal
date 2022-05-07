package agence.models;

import java.time.LocalDate;
import java.util.UUID;

public class Facture {

    private String id;
    private double montantTotal;
    private String idLocation;
    private String idClient;

    public Facture(String idLocation, String idClient, double montantTotal) {
        this.id = UUID.randomUUID().toString();
        this.idLocation = idLocation;
        this.idClient = idClient;
        this.montantTotal = montantTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "montantTotal=" + montantTotal +
                '}';
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String impression() {
        return "Facture : " + id + "\n" +
                "Montant total : " + montantTotal + "\n" +
                "Location : " + idLocation + "\n" +
                "Client : " + idClient + "\n"+
                "date : " + LocalDate.now();
    }
}
