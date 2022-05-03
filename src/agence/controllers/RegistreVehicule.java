package agence.controllers;

import agence.models.Vehicule;
import agence.request.Location;
import agence.request.RetourVehicule;
import agence.storage.StockagePersistant;
import agence.views.IRetourVehiculeView;
import agence.views.impl.RetourVehiculeView;

import java.util.List;

public class RegistreVehicule {

    private final StockagePersistant stockage = StockagePersistant.getInstance();
    private final IRetourVehiculeView views = new RetourVehiculeView();
    private final RegistreLocation registreLocation = new RegistreLocation();

    public void creerNouveauRetour() {
        Location location = new Location();
        RetourVehicule retourVehicule = new RetourVehicule(location);

        // demande d'id de location
        String idLocation = views.demanderIdLocation();

        // charger la location
        location = registreLocation.chargerLocation(idLocation);

        // verifier si la location existe
        if(location == null) {
            views.erreurVehicule();
            return;
        }

        retourVehicule.setLocation(location);

        // calculer les frais retard
        List<Double> fraisRetard = registreLocation.calculerFraisRetard(location);

        // verifier l'etat du vehicule retourné
        verifierEtatVehicule(location.getVehicule());

        // frais de retour
        double fraisRetour = retourVehicule.getFraisRetour();

        if(fraisRetour < 0){
            views.afficherAmende(Math.abs(fraisRetour));
        }
        else {
            views.afficherPrixRetour(fraisRetour);
        }

        // terminer la location
        registreLocation.terminerLocation(location);
        views.signalerFinRetourVehicule();
    }


    public void retirerVehiculeEndommage(){
       String immatricule = views.demanderImmatriculeEndommage();

       // verifier si le vehicule existe dans la liste des vehicules retournés
       if(!stockage.getVehiculeRetourne().containsKey(immatricule))
           throw new IllegalStateException("Le vehicule n'existe pas dans la liste des vehicules retournés");

       Vehicule vehicule = stockage.getVehiculeRetourne().get(immatricule);

       views.demanderDestinationFinale(vehicule);
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