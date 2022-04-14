package agence.controllers;

import agence.request.Location;
import agence.request.RetourVehicule;
import agence.storage.StockagePersistant;
import agence.views.LocationView;

import java.util.List;

public class RegistreLocation {

    private StockagePersistant stockage;
    private LocationView locationView;

    public Location chargerLocation(String idLocation){
        return stockage.getLocationById(idLocation);
    }

    public List<Double> calculerFraisRetard(Location location){
        RetourVehicule retourVehicule = new RetourVehicule();
        return retourVehicule.calculerFraisRetard(location);
    }

    /**
     * Methode qui permet de création une nouvelle location
     *
     * */
    public void creationLoation(){
        // initialiser une location
        Location location = new Location();

        // demander les informations du vehicule, du client et de la date de fin de location
        location = locationView.saisirInfomationLocation(location);

        // proceder au paiement de la location
        locationView.procederPaiement(location);

        // sauvegarder la location
        stockage.sauvegarderLocation(location);

        // terminer l'opération creation de location
        demarrerLocation(location);
    }

    /**
     * Methode qui permet de mettre fin à une location
     * */
    public void terminerLocation(Location location) {
       location.setEstEnCours(false);
    }

    /**
     * Methode qui permet de démarrer la location
     * */
    public void demarrerLocation(Location location){
        location.setEstEnCours(true);
    }
}