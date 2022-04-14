package agence.request;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-14
 */
public class Paiement {
    private String id;
    private LocalDateTime date;
    private double montant;

    public Paiement() {}

    public Paiement(double montant) {
        this.id = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.montant = montant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }


}
