package agence.storage;

import agence.models.*;
import agence.request.Location;
import agence.request.Paiement;
import agence.request.Reservation;
import agence.request.RetourVehicule;

import java.time.LocalDateTime;
import java.util.*;
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
    public static Set<TypeVehicule> typesVehicule;
    private static StockagePersistant instance;

    public static StockagePersistant getInstance() {
        // singleton
        if (instance == null) {
            instance = new StockagePersistant();
        }
        return instance;
    }

    public StockagePersistant() {
        catalogueReservationEnCours = new HashMap<>();
        cataloguePaiement = new HashMap<>();
        catalogueClient = initClient();
        catalogueLocation = new HashMap<>();
        catalogueReservation = new HashMap<>();
        catalogueClientRetardataire = new HashMap<>();
        catalogueVehiculeDisponible = new HashMap<>();
        catalogueRetour = new HashMap<>();
        catalogueVehiculeRetourne = new HashMap<>();
        catalogueVehiculeRepare = new HashMap<>();
        catalogueVehiculeRetire = new HashMap<>();
        typesVehicule = initTypesVehicule();
        catalogueVehicule = initVehicule();
    }

    private Map<String, Client> initClient() {
        Map<String, Client> catalogueClient = new HashMap<>();
        Address address = new Address("3351", "Boulevard des Forges", "Trois-Rivières", "G8Z 3C9");
        Client client1 = new Client("Lubaki", "Josue", "UQTR-1", address, "819 111 0000", "4345123456789");
        Client client2 = new Client("Kanyinda", "Jonathan", "UQTR-2", address, "819 111 0000", "4340123456789");
        Client client3 = new Client("Kuibia", "Jordan", "UQTR-3", address, "819 411 0000", "4340123336789");
        catalogueClient.put(client1.getNumPermisConduire(), client1);
        catalogueClient.put(client2.getNumPermisConduire(), client2);
        catalogueClient.put(client3.getNumPermisConduire(), client3);
        return catalogueClient;
    }

    private HashSet<TypeVehicule> initTypesVehicule() {
        HashSet<TypeVehicule> typesVehicule = new HashSet<>();
        typesVehicule.add(new TypeVehicule("Simple"));
        typesVehicule.add(new TypeVehicule("Prestige"));
        typesVehicule.add(new TypeVehicule(("Camion")));
        return typesVehicule;
    }

    @Override
    public void ajoutTypeVehicule(TypeVehicule type) {
        typesVehicule.add(type);
    }

    @Override
    public List<TypeVehicule> getAllTypeVehicule() {
        return new ArrayList<>(typesVehicule);
    }

    @Override
    public Map<String, Vehicule> getCatalogueVehiculeRetires() {
        return catalogueVehiculeRetire;
    }

    @Override
    public Map<String, Vehicule> getCatalogueVehiculeRepare() {
        return catalogueVehiculeRepare;
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
    public Map<String, Vehicule> getVehiculeRetourne() {
        return catalogueRetour
                .values()
                .stream()
                .map(RetourVehicule::getLocation)
                .map(Location::getVehicule)
                .collect(Collectors.toMap(Vehicule::getImmatriculation, v -> v));
    }

//    @Override
    public Map<String, RetourVehicule> getVehiculeRetournes() {
        return catalogueRetour;
    }

    @Override
    public Optional<Vehicule> getVehiculeByImmatriculation(String immatriculation) {
        return Optional.of(catalogueVehicule.get(immatriculation));
    }

    @Override
    public Optional<Client> getClientByNumeroPermis(String numeroPermis) {
        return Optional.of(catalogueClient.get(numeroPermis));
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
                        && (r.getDate().isBefore(date)));
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
    public void afficherReservation() {
        catalogueReservation.values().forEach(System.out::println);
    }

    @Override
    public void afficherLocation() {
        catalogueLocation.values().forEach(System.out::println);
    }

    @Override
    public void afficherClient() {
        if (catalogueClient.isEmpty())
            return;

        catalogueClient.values().forEach(System.out::println);
    }



    public void setCatalogueLocation(Map<String, Location> catalogueLocation) {
        StockagePersistant.catalogueLocation = catalogueLocation;
    }

    public void setCatalogueReservation(Map<String, Reservation> catalogueReservation) {
        StockagePersistant.catalogueReservation = catalogueReservation;
    }

    private Map<String, Vehicule> initVehicule() {
        Vehicule vehicule1 =new Vehicule("IMM1", "Noir", new ArrayList<>(typesVehicule).get(0), 100);
        vehicule1.setModele(new ModelVehicule("Audi", 4));
        Vehicule vehicule2 =new Vehicule("IMM2", "Bleu", new ArrayList<>(typesVehicule).get(0), 120);
        vehicule2.setModele(new ModelVehicule("Renault", 4));

        return new HashMap<>() {{
            put(vehicule1.getImmatriculation(), vehicule1);
            put(vehicule2.getImmatriculation(), vehicule2);
        }};
    }
}
