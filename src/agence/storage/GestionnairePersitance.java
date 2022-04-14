package agence.storage;

import agence.models.Client;
import agence.request.Reservation;
import agence.models.Vehicule;
import agence.request.Location;

import java.util.Map;

public class GestionnairePersitance {

    private Map<String, Client> catalogueClient;
    private Map<String, Vehicule> catalogueVehicule;
    private Map<String, Location> catalogueLocation;
    private Map<String, Reservation> catalogueReservation;

}
