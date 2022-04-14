package agence.controllers;

import agence.request.Location;
import agence.request.RetourVehicule;
import agence.storage.GestionnairePersitance;

import java.util.List;

public class RegistreLocation {

    private GestionnairePersitance stockage;

    public Location chargerLocation(String idLocation){
        return stockage.getLocationById(idLocation);
    }

    public List<Double> calculerFraisRetard(Location location){
        RetourVehicule retourVehicule = new RetourVehicule();
        return retourVehicule.calculerFraisRetard(location);
    }

    /**
     * Methode qui permet de mettre fin à une location
     * */
    public void terminerLocation(Location location) {
       location.setEstEnCours(false);
    }
}
