package agence.controllers;

import agence.models.Vehicule;
import agence.request.Location;
import agence.request.RetourVehicule;
import agence.storage.StockagePersistant;
import agence.views.IRetourVehiculeView;
import agence.views.IVehiculeView;
import agence.views.impl.RetourVehiculeView;
import agence.views.impl.VehiculeView;

import java.util.List;

public class RegistreVehicule {

    private final StockagePersistant stockage = StockagePersistant.getInstance();
    private final IRetourVehiculeView retourVehiculeView = new RetourVehiculeView();
    private final IVehiculeView vehiculeView = new VehiculeView();
    private final RegistreLocation registreLocation = new RegistreLocation();

    public void creerNouveauRetour() {
        Location location = new Location();
        RetourVehicule retourVehicule = new RetourVehicule(location);

        // demande d'id de location
        String idLocation = retourVehiculeView.demanderIdLocation();

        // charger la location
        location = registreLocation.chargerLocation(idLocation);

        // verifier si la location existe
        if(location == null) {
            retourVehiculeView.erreurVehicule();
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
            retourVehiculeView.afficherAmende(Math.abs(fraisRetour));
        }
        else {
            retourVehiculeView.afficherPrixRetour(fraisRetour);
        }

        // terminer la location
        terminerRetourVehicule(retourVehicule);
        retourVehiculeView.signalerFinRetourVehicule();
    }

    private void terminerRetourVehicule(RetourVehicule retourVehicule) {
        stockage.getVehiculeRetournes().put(retourVehicule.getLocation().getVehicule().getImmatriculation(), retourVehicule);
        retourVehicule.getLocation().setEstEnCours(false);
    }


    public void retirerVehiculeEndommage(){
       String immatricule = retourVehiculeView.demanderImmatriculeEndommage();

       // verifier si le vehicule existe dans la liste des vehicules retournés
       if(!stockage.getVehiculeRetourne().containsKey(immatricule)){
           vehiculeView.erreurVehicule();
           return;
       }

       Vehicule vehicule = stockage.getVehiculeRetourne().get(immatricule);

       retourVehiculeView.demanderDestinationFinale(vehicule);
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

    //Ajouter vehicule
    public void ajouterVehicule(){
     Vehicule vehicule = new Vehicule();
     vehiculeView.ajouterInfoVehicule(vehicule);
     stockage.ajouterVehicule(vehicule);
    }


 }