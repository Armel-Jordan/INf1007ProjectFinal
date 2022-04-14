package agence.models;

import java.util.UUID;

public class Facture {

    private String id;
    private int montantTotal;
    private String idLocation;
    private String idClient;

    public Facture(String idLocation, String idClient ,int montantTotal) {
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

    public int getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(int montantTotal) {
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
}
