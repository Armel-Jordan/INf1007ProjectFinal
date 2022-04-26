package agence.controllers;

import agence.models.Vehicule;
import agence.request.Location;
import agence.request.RetourVehicule;
import agence.storage.StockagePersistant;
import agence.views.IRetourVehiculeView;

import java.util.List;

public class RegistreVehicule {

    private StockagePersistant stockage;
    private IRetourVehiculeView retourVehiculeViews;
    private RetourVehicule retourVehicule;
    private Location location;
    private RegistreLocation registreLocation;

    public void creerNouveauRetour() {
        location = new Location();
        retourVehicule = new RetourVehicule(location);

        // demande d'id de location
        String idLocation = retourVehiculeViews.demanderIdLocation();

        // charger la location
        location = registreLocation.chargerLocation(idLocation);
        retourVehicule.setLocation(location);

        // calculer les frais retard
        List<Double> fraisRetard = registreLocation.calculerFraisRetard(location);

        // verifier l'etat du vehicule retourné
        verifierEtatVehicule(location.getVehicule());

        // frais de retour
        double fraisRetour = retourVehicule.getFraisRetour();

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


    public void retirerVehiculeEndommage(){
       String immatricule = retourVehiculeViews.demanderImmatriculeEndommage();

       // verifier si le vehicule existe dans la liste des vehicules retournés
       if(!stockage.getVehiculeRetourne().containsKey(immatricule))
           throw new IllegalStateException("Le vehicule n'existe pas dans la liste des vehicules retournés");

       Vehicule vehicule = stockage.getVehiculeRetourne().get(immatricule);

       retourVehiculeViews.demanderDestinationFinale(vehicule);
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