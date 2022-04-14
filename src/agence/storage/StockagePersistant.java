package agence.storage;

import agence.models.Client;
import agence.models.Vehicule;
import agence.request.Location;
import agence.request.Paiement;
import agence.request.Reservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StockagePersistant implements StockageRepository{

    private Map<String, Client> catalogueClient;
    private Map<String, Vehicule> catalogueVehicule;
    private Map<String, Location> catalogueLocation;
    private Map<String, Reservation> catalogueReservation;
    private Map<String, Client> catalogueClientRetardataire;
    private Map<String, Vehicule> catalogueVehiculeDisponible;
    private Map<String, Paiement> cataloguePaiement;


    public StockagePersistant() {
        this.catalogueClient = new HashMap<>();
        this.catalogueVehicule = new HashMap<>();
        this.catalogueLocation =new HashMap<>();
        this.catalogueReservation= new HashMap<>();
        this.catalogueClientRetardataire= new HashMap<>();
        this.catalogueVehiculeDisponible = new HashMap<>();
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

    @Override
    public List<Vehicule> getVehiculesDisponibles() {
        return this.catalogueVehiculeDisponible.values().stream().collect(Collectors.toList());
    }

    public Map<String, Vehicule> getCatalogueVehicule() {
        return catalogueVehicule;
    }

    @Override
    public Vehicule getVehiculeByImmatriculation(String immatriculation) {
        return this.catalogueVehicule.get(immatriculation);
    }

    @Override
    public Client getClientByNumeroPermis(String numeroPermis) {
        return this.catalogueClient.get(numeroPermis);
    }

    @Override
    public void addClient(Client client) {
        this.catalogueClient.put(client.getNumPermisConduire(), client);
    }

    @Override
    public void sauvegarderLocation(Location location) {
        this.catalogueLocation.put(location.getIdLocation(), location);
    }

    @Override
    public void ajouterPaiement(Paiement paiement) {
        this.cataloguePaiement.put(paiement.getId(), paiement);
    }
}
