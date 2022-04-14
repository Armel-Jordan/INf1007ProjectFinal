package agence.models;

import java.util.UUID;

public class Facture {

    private String id;
    private int montantTotal;

    public Facture(int montantTotal) {
        this.id = UUID.randomUUID().toString();
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
}
