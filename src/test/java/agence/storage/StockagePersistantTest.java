package agence.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import agence.models.Client;
import agence.models.TypeVehicule;
import agence.models.Vehicule;
import agence.request.Location;
import agence.request.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockagePersistantTest {

    StockagePersistant stockagePersistant;
    Map<String, Location> catalogueLocation;
    Map<String, Reservation> catalogueReservation;
    Vehicule vehicule;
    Vehicule vehicule2;
    Location location;
    Location location2;
    Client client = new Client();
    Client client2 = new Client();
    Reservation reservation = new Reservation();
    Reservation reservation2 = new Reservation();

    @BeforeEach
    void setUp() {
        stockagePersistant = new StockagePersistant();
        catalogueLocation = new HashMap<>();
        catalogueReservation = new HashMap<>();
        vehicule = new Vehicule("IMM", "Blue", TypeVehicule.SIMPLE, 120);
        vehicule2 = new Vehicule("IMM2", "Blue", TypeVehicule.SIMPLE, 120);
        location = new Location();
        location2 = new Location();
        client = new Client();
        client2 = new Client();
        reservation.setClient(client);
        reservation.setVehicule(vehicule);
        reservation.setDate(LocalDateTime.of(2022,4,27,12,0));
        reservation2.setClient(client2);
        reservation2.setVehicule(vehicule2);
        reservation2.setDate(LocalDateTime.of(2022,5,1,12,0));
    }

    @Test
    @DisplayName("Test isVehicleDisponible if vehicle is not available")
    void isVehiculeDisponible() {
        //-------------------  Arrange  -------------------//
        // créer et ajouter deux locations
        location.setVehicule(vehicule);
        location.setClient(client);
        location2.setVehicule(vehicule2);
        location2.setClient(client2);

        catalogueLocation.put(location.getIdLocation(), location);
        catalogueLocation.put(location2.getIdLocation(), location2);

        stockagePersistant.setCatalogueLocation(catalogueLocation);

        // ajouter deux réservations
        catalogueReservation.put(reservation.getId(), reservation);
        catalogueReservation.put(reservation2.getId(), reservation2);

        stockagePersistant.setCatalogueReservation(catalogueReservation);

        //---------------------  Act  ----------------------//
        boolean result = stockagePersistant.isVehiculeDisponible(
                vehicule.getImmatriculation(),
                LocalDateTime.of(2022,4,25,12,0)
        );

        //--------------------- Assert ----------------------//
        assertFalse(result);
    }

    @Test
    @DisplayName("Test isVehicleDisponible if vehicle is available")
    void isVehiculeDisponible2() {
        //-------------------  Arrange  -------------------//
        // ajouter une location
        location2.setVehicule(vehicule2);
        location2.setClient(client2);
        catalogueLocation.put(location2.getIdLocation(), location2);

        stockagePersistant.setCatalogueLocation(catalogueLocation);

        // ajouter deux réservations
        catalogueReservation.put(reservation.getId(), reservation);
        catalogueReservation.put(reservation2.getId(), reservation2);

        stockagePersistant.setCatalogueReservation(catalogueReservation);

        //---------------------  Act  ----------------------//
        boolean result = stockagePersistant.isVehiculeDisponible(
                vehicule.getImmatriculation(),
                LocalDateTime.of(2022,4,27,12,0)
        );

        //--------------------- Assert ----------------------//
        assertTrue(result);
    }

    @Test
    @DisplayName("Test isVehicleDisponible if vehicle is not available")
    void isVehiculeDisponible3() {
        //-------------------  Arrange  -------------------//
        // ajouter une location
        location2.setVehicule(vehicule2);
        location2.setClient(client2);
        catalogueLocation.put(location2.getIdLocation(), location2);

        stockagePersistant.setCatalogueLocation(catalogueLocation);

        // ajouter une réservation
        catalogueReservation.put(reservation.getId(), reservation);

        // ajouter une deuxième réservation
        catalogueReservation.put(reservation2.getId(), reservation2);
        stockagePersistant.setCatalogueReservation(catalogueReservation);

        //---------------------  Act  ----------------------//
        boolean result = stockagePersistant.isVehiculeDisponible(
                vehicule.getImmatriculation(),
                LocalDateTime.of(2022,5,1,12,0)
        );

        //--------------------- Assert ----------------------//
        assertFalse(result);
    }
}

