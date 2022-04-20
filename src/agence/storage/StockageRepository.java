package agence.storage;

import agence.models.Client;
import agence.models.Vehicule;
import agence.request.Location;
import agence.request.Paiement;

import java.time.LocalDateTime;
import java.util.List;
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
    List<Vehicule> getVehiculesDisponibles();
    Optional<Vehicule> getVehiculeByImmatriculation(String immatriculation);
    Optional<Client> getClientByNumeroPermis(String numeroPermis);
    void addClient(Client client);
    void sauvegarderLocation(Location location);
    void ajouterPaiement(Paiement paiement);
    boolean isVehiculeDisponible(String immatriculation, LocalDateTime date);
}
