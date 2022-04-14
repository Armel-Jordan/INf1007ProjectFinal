package models;

public class Facture {

    private int montantTotal;

    public Facture(int montantTotal) {
        this.montantTotal = montantTotal;
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
