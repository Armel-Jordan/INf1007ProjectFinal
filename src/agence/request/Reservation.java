package agence.request;

import agence.models.Client;
import agence.models.Vehicule;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-14
 */
public class Reservation {
    private String id;
    private String date;
    private Client client;
    private Vehicule vehicule;

    public Reservation(String date, Client client, Vehicule vehicule) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.client = client;
        this.vehicule = vehicule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
