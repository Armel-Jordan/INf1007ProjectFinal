package agence.storage;

import agence.models.Client;
import agence.models.TypeVehicule;
import agence.models.Vehicule;
import agence.request.Location;
import agence.request.Paiement;
import agence.request.Reservation;
import agence.request.RetourVehicule;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StockagePersistant implements StockageRepository {

    public static Map<String, Client> catalogueClient;
    public static Map<String, Vehicule> catalogueVehicule;
    public static Map<String, Location> catalogueLocation;
    public static Map<String, Reservation> catalogueReservation;
    public static Map<String, RetourVehicule> catalogueRetour;
    public static Map<String, Reservation> catalogueReservationEnCours;
    public static Map<String, Client> catalogueClientRetardataire;
    public static Map<String, Vehicule> catalogueVehiculeDisponible;
    public static Map<String, Paiement> cataloguePaiement;
    public static Map<String, Reservation> catalogueVehiculeRetourne;
    public static Map<String, Vehicule> catalogueVehiculeRepare;
    public static Map<String, Vehicule> catalogueVehiculeRetire;
    private static StockagePersistant instance;

    public static StockagePersistant getInstance() {
        // singleton
        if (instance == null) {
            instance = new StockagePersistant();
        }
        return instance;
    }

    public StockagePersistant() {

        this.catalogueReservationEnCours = new HashMap<>();
        this.cataloguePaiement = new HashMap<>();
        this.catalogueClient = new HashMap<>();
        this.catalogueVehicule = new HashMap<>();
        this.catalogueLocation = new HashMap<>();
        this.catalogueReservation = new HashMap<>();
        this.catalogueClientRetardataire = new HashMap<>();
        this.catalogueVehiculeDisponible = new HashMap<>();
        this.catalogueRetour = new HashMap<>();
        initVehicule();
    }

    @Override
    public Optional<Location> getLocationById(String id) {
        return Optional.ofNullable(catalogueLocation.get(id));
    }

    @Override
    public void addVehiculeEndommage(Vehicule vehicule) {
        catalogueVehicule.put(vehicule.getImmatriculation(), vehicule);
    }

    @Override
    public void addVehiculeDisponible(Vehicule vehicule) {
        catalogueVehiculeDisponible.put(vehicule.getImmatriculation(), vehicule);
    }

    @Override
    public Map<String, Vehicule> getCatalogueVehicule() {
        return catalogueVehicule;
    }

    @Override
    public Optional<Vehicule> getVehiculeByImmatriculation(String immatriculation) {
        return Optional.ofNullable(catalogueVehicule.get(immatriculation));
    }

    @Override
    public Optional<Client> getClientByNumeroPermis(String numeroPermis) {
        return Optional.ofNullable(catalogueClient.get(numeroPermis));
    }

    @Override
    public void sauvegarderLocation(Location location) {
        catalogueLocation.put(location.getIdLocation(), location);
    }

    @Override
    public void ajouterPaiement(Paiement paiement) {
        cataloguePaiement.put(paiement.getId(), paiement);
    }

    @Override
    public boolean isVehiculeDisponible(String immatriculation, LocalDateTime date) {
        // vérifier si le véhicule est actuellement en location
        if (catalogueLocation
                .values()
                .stream()
                .anyMatch(l -> l.getVehicule().getImmatriculation().equals(immatriculation))) {
            return false;
        }

        // vérifier si le véhicule est actuellement en réservation
        return catalogueReservation
                .values()
                .stream()
                .noneMatch(r -> r.getVehicule().getImmatriculation().equals(immatriculation)
                        && r.getDate().isBefore(date));
    }

    @Override
    public void ajouterReservation(Reservation reservation) {
        reservation.setEstCours(true);
        catalogueReservation.put(reservation.getId(), reservation);
    }

    @Override
    public void ajouterClient(Client client) {
        catalogueClient.put(client.getNumPermisConduire(), client);
    }

    @Override
    public void ajouterVehicule(Vehicule vehicule) {
        catalogueVehicule.put(vehicule.getImmatriculation(), vehicule);
    }

    @Override
    public Optional<Reservation> getReservationByClient(String numeroPermis) {
        return catalogueReservation
                .values()
                .stream()
                .filter(r -> r.getClient().getNumPermisConduire().equals(numeroPermis))
                .findFirst();
    }

    @Override
    public boolean hasLocationByNumeroPermis(String numeroPermis) {
        return catalogueLocation
                .values()
                .stream()
                .anyMatch(l -> l.getClient().getNumPermisConduire().equals(numeroPermis));
    }

    @Override
    public boolean hasReservationByNumeroPermis(String numeroPermis) {
        return catalogueReservation
                .values()
                .stream()
                .anyMatch(r -> r.getClient().getNumPermisConduire().equals(numeroPermis));
    }

    @Override
    public void deleteReservationClient(Reservation reservation) {
        catalogueReservation.remove(reservation.getId());
    }

    @Override
    public void modifierReservation(Reservation reservationClient) {
        catalogueReservation.put(reservationClient.getId(), reservationClient);
    }

    @Override
    public void supprimerVehiculeByImmatriculation(String immatriculation) {
        catalogueVehicule.remove(immatriculation);
    }

    @Override
    public Map<String, Vehicule> getVehiculeRetourne() {
        return catalogueRetour
                .values()
                .stream()
                .map(RetourVehicule::getLocation)
                .map(Location::getVehicule)
                .collect(Collectors.toMap(Vehicule::getImmatriculation, v -> v));
    }

    @Override
    public void afficherReservation() {
        catalogueReservation.values().forEach(System.out::println);
    }

    @Override
    public void afficherLocation() {
        catalogueLocation.values().forEach(System.out::println);
    }

    @Override
    public void afficherClient() {
        catalogueClient.values().forEach(System.out::println);
    }



    public void setCatalogueLocation(Map<String, Location> catalogueLocation) {
        StockagePersistant.catalogueLocation = catalogueLocation;
    }

    public void setCatalogueReservation(Map<String, Reservation> catalogueReservation) {
        StockagePersistant.catalogueReservation = catalogueReservation;
    }
    private void initVehicule() {
        Vehicule vehicule1 =new Vehicule("ABC", "Peugeot", TypeVehicule.SIMPLE, 100);
        Vehicule vehicule2 =new Vehicule("AB", "Peugeot", TypeVehicule.SIMPLE, 10);
        catalogueVehicule.put(vehicule1.getImmatriculation(), vehicule1);
        catalogueVehicule.put(vehicule2.getImmatriculation(), vehicule2);
    }
}
