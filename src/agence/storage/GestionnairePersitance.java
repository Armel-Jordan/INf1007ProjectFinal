package agence.storage;

import agence.models.Client;
import agence.models.Vehicule;
import agence.request.Location;
import agence.request.Reservation;

import java.util.HashMap;
import java.util.Map;

public class GestionnairePersitance implements StockageRepository{

    private Map<String, Client> catalogueClient;
    private Map<String, Vehicule> catalogueVehicule;
    private Map<String, Location> catalogueLocation;
    private Map<String, Reservation> catalogueReservation;
    private Map<String, Client> catalogueClientRetardataire;
    private Map<String, Vehicule> catalogueVehiculeDisponible;


    public GestionnairePersitance() {
        this.catalogueClient = new HashMap<>();
        this.catalogueVehicule = new HashMap<>();
        this.catalogueLocation =new HashMap<>();
        this.catalogueReservation= new HashMap<>();
        this.catalogueClientRetardataire= new HashMap<>();
    }

    @Override
    public Location getLocationById(String id) {
        return this.catalogueLocation.get(id);
    }

    @Override
    public void addVehiculeEndommage(Vehicule vehicule) {
        this.catalogueVehicule.put(vehicule.getImmatriculation(), vehicule);
    }

    @Override
    public void addVehiculeDisponible(Vehicule vehicule) {
        this.catalogueVehiculeDisponible.put(vehicule.getImmatriculation(), vehicule);
    }

    public Map<String, Vehicule> getCatalogueVehicule() {
        return catalogueVehicule;
    }
}
