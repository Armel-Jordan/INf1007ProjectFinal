package agence.controllers;

import agence.request.Location;
import agence.request.RetourVehicule;
import agence.storage.StockagePersistant;
import agence.views.ILocationView;
import agence.views.impl.LocationView;

import java.util.List;

public class RegistreLocation {

    private final StockagePersistant stockage= StockagePersistant.getInstance();
    private final ILocationView locationView= new LocationView();

    public Location chargerLocation(String idLocation){
        return stockage.getLocationById(idLocation)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public List<Double> calculerFraisRetard(Location location){
        RetourVehicule retourVehicule = new RetourVehicule(location);
        return retourVehicule.calculerFraisRetard();
    }

    /**
     * Methode qui permet de création une nouvelle location
     *
     * */
    public void creationLocation(){
        // initialiser une location
        Location location = new Location();

        // demander les informations du véhicule, du client et de la date de fin de location
        location = locationView.saisirInfomationLocation(location);

        if(location == null){
            return;
        }

        // creation la location
        Location locationCree = new Location(location.getVehicule(), location.getClient(), location.getDateFinPrevueLocation());

        // procéder au paiement de la location
        locationView.procederPaiement(locationCree);

        // sauvegarder la location
        stockage.sauvegarderLocation(locationCree);

        // terminer l'opération creation de location
        demarrerLocation(locationCree);
    }

    /**
     * Methode qui permet de démarrer la location
     * */
    public void demarrerLocation(Location location){
        location.setEstEnCours(true);
    }
}
