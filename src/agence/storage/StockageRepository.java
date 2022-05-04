package agence.storage;

import agence.models.Client;
import agence.models.TypeVehicule;
import agence.models.Vehicule;
import agence.request.Location;
import agence.request.Paiement;
import agence.request.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-14
 */
public interface StockageRepository {
    Optional<Location> getLocationById(String id);
    void addVehiculeEndommage(Vehicule vehicule);
    void addVehiculeDisponible(Vehicule vehicule);
    Optional<Vehicule> getVehiculeByImmatriculation(String immatriculation);
    Optional<Client> getClientByNumeroPermis(String numeroPermis);
    void sauvegarderLocation(Location location);
    void ajouterPaiement(Paiement paiement);
    boolean isVehiculeDisponible(String immatriculation, LocalDateTime date);
    void ajouterReservation(Reservation reservation);
    void ajouterClient(Client client);
    void ajouterVehicule(Vehicule vehicule);
    Optional<Reservation> getReservationByClient(String numeroPermis);
    boolean hasLocationByNumeroPermis(String numeroPermis);
    boolean hasReservationByNumeroPermis(String numeroPermis);
    void deleteReservationClient(Reservation reservation);
    void modifierReservation(Reservation reservationClient);
    void supprimerVehiculeByImmatriculation(String immatriculation);
    Map<String, Vehicule> getVehiculeRetourne();
    void afficherReservation();
    void afficherLocation();
    void afficherClient();
    Map<String, Vehicule> getCatalogueVehicule();
    void ajoutTypeVehicule(TypeVehicule typeVehicule);
    List<TypeVehicule> getAllTypeVehicule();
    Map<String, Vehicule> getCatalogueVehiculeRetires();
    Map<String, Vehicule> getCatalogueVehiculeRepare();
}
