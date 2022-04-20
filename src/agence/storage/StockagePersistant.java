package agence.storage;

import agence.models.Client;
import agence.models.Vehicule;
import agence.request.Location;
import agence.request.Paiement;
import agence.request.Reservation;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StockagePersistant implements StockageRepository{

    private Map<String, Client> catalogueClient;
    private Map<String, Vehicule> catalogueVehicule;
    private Map<String, Location> catalogueLocation;
    private Map<String, Reservation> catalogueReservation;
    private Map<String, Reservation> catalogueReservationEnCours;
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
    public Optional<Location> getLocationById(String id) {
        return Optional.ofNullable(this.catalogueLocation.get(id));
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
    public Optional<Vehicule> getVehiculeByImmatriculation(String immatriculation) {
        return Optional.ofNullable(this.catalogueVehicule.get(immatriculation));
    }

    @Override
    public Optional<Client> getClientByNumeroPermis(String numeroPermis) {
        return Optional.ofNullable(this.catalogueClient.get(numeroPermis));
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

    @Override
    public boolean isVehiculeDisponible(String immatriculation, LocalDateTime date) {
        // vérifier si le véhicule est actuellement en location
        if (this.catalogueLocation
                .values()
                .stream()
                .anyMatch(l -> l.getVehicule().getImmatriculation().equals(immatriculation))) {
            return false;
        }

        // vérifier si le véhicule est actuellement en réservation
        return this.catalogueReservation
                .values()
                .stream()
                .noneMatch(r -> r.getVehicule().getImmatriculation().equals(immatriculation)
                        && r.getDate().isBefore(date));
    }

    public void setCatalogueLocation(Map<String, Location> catalogueLocation) {
        this.catalogueLocation = catalogueLocation;
    }

    public void setCatalogueReservation(Map<String, Reservation> catalogueReservation) {
        this.catalogueReservation = catalogueReservation;
    }
}
