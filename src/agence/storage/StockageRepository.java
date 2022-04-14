package agence.storage;

import agence.models.Vehicule;
import agence.request.Location;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-14
 */
public interface StockageRepository {
    Location getLocationById(String id);
    void addVehiculeEndommage(Vehicule vehicule);
    void addVehiculeDisponible(Vehicule vehicule);
}
