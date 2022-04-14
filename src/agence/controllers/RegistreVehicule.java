package agence.controllers;

import agence.models.Vehicule;
import agence.request.Location;
import agence.request.RetourVehicule;
import agence.storage.GestionnairePersitance;
import agence.views.RetourVehiculeView;

import java.util.List;

public class RegistreVehicule {

    private GestionnairePersitance stockage;
    private RetourVehiculeView retourVehiculeViews;
    private RetourVehicule retourVehicule;
    private Location location;
    private RegistreLocation registreLocation;

    public void creerNouveauRetour(){
        retourVehicule = new RetourVehicule();
        location = new Location();

        // demande d'id de location
        String idLocation = retourVehiculeViews.demanderIdLocation();

        // charger la location
        location = registreLocation.chargerLocation(idLocation);

        // calculer les frais retard
        List<Double> fraisRetard = registreLocation.calculerFraisRetard(location);

        // verifier l'etat du vehicule retourné
        verifierEtatVehicule(location.getVehicule());

        // frais de retour
        double fraisRetour = retourVehicule.getFraisRetour(location);

        if(fraisRetour < 0){
            retourVehiculeViews.afficherAmende(Math.abs(fraisRetour));
        }
        else {
            retourVehiculeViews.afficherPrixRetour(fraisRetour);
        }

        // terminer la location
        registreLocation.terminerLocation(location);
        retourVehiculeViews.signalerFinRetourVehicule();
    }

    private void verifierEtatVehicule(Vehicule vehicule){

        if(vehicule.isEstEndommage()){
            stockage.getCatalogueVehicule()
                    .get(vehicule.getImmatriculation())
                    .setEstEndommage(true);

            stockage.addVehiculeEndommage(vehicule);
        }
        else {
            stockage.getCatalogueVehicule()
                    .get(vehicule.getImmatriculation())
                    .setEstDisponible(true);
            stockage.addVehiculeDisponible(vehicule);
        }
    }
}
