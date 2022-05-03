package agence.controllers;

import agence.models.Client;
import agence.models.Vehicule;
import agence.request.Reservation;
import agence.storage.StockagePersistant;
import agence.views.IReservationView;
import agence.views.impl.ReservationView;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

public class RegistreReservation {
    private StockagePersistant stockage= StockagePersistant.getInstance();
    private IReservationView view=new ReservationView();

    /**
     * Methode qui permet d'ajouter la réservation dans le stockage
     * */
    public void ajoutReservation(){
        Reservation reservation = new Reservation();

        if (!associerClientReservation(reservation))
            return;

       do {
           if(!associerVehiculeReservation(reservation))
               break;

           ajouterDateReservation(reservation);

           if(!peutReservationDate(reservation)){
               view.erreurReservation(reservation);

               // possibilité de sortir de la boucle
               if(view.sortir()) return;
           }
           else {
               stockage.ajouterReservation(reservation);
               return;
           }

       } while(!peutReservationDate(reservation));
    }


    /**
     * Methode qui permet de modifier une reservation
     * */
    public void modifierReservation(){
        Reservation reservation;
        String numeroPermis = recupererNumeroPermis();

        try{
            if(!stockage.hasReservationByNumeroPermis(numeroPermis))
                view.modificationReservation(null);

            else {
                reservation = chargerReservationClient(numeroPermis);
                view.modificationReservation(reservation);
                terminerModificationReservationClient(reservation);
            }
        }
        catch (NoSuchElementException e){
            System.out.println("Le client n'a pas de réservation en cours");
        }
    }

    private void terminerModificationReservationClient(Reservation reservationClient) {
        stockage.modifierReservation(reservationClient);
    }

    /**
     * Methode qui permet de supprimer une reservation
     * */
    public void supprimerReservation(){
        String numeroPermis = recupererNumeroPermis();

        // verifier si le client a une reservation en cours
        try {
            if(!stockage.hasReservationByNumeroPermis(numeroPermis))
                view.suppressionReservation(null);

            else {
                Reservation reservationClient = chargerReservationClient(numeroPermis);
                view.suppressionReservation(reservationClient);
            }
        }
        catch (NoSuchElementException e){
            System.out.println("Le client n'a pas de réservation en cours");
        }
    }

    /**
     * Méthode qui permet de vérifier si le client a le droit de réserver le véhicule choisi à la date choisie
     * @param reservation @Reservation la reservation à vérifier
     * @return @boolean
     * */
    private boolean peutReservationDate(Reservation reservation) {
        return stockage.isVehiculeDisponible(reservation.getVehicule().getImmatriculation(), reservation.getDate());
    }

    /**
     * Méthode qui permet d'ajouter la date de la réservation
     * @param reservation @Reservation la reservation à associer
     * */
    private void ajouterDateReservation(Reservation reservation) {
        LocalDateTime dateReservation = view.dateReservation();
        reservation.setDate(dateReservation);
    }

    /**
     * Méthode qui permet d'associer le véhicule à la reservation
     * @param reservation @Reservation la reservation à associer
     * */
    private boolean associerVehiculeReservation(Reservation reservation) {
        String immatriculation = recupererVehiculeChoisi();
        Vehicule vehicule = stockage.getVehiculeByImmatriculation(immatriculation)
                .stream()
                .findFirst()
                .orElse(null);

        if(vehicule == null) {
            view.erreurVehicule();
            return false;
        }

        reservation.setVehicule(vehicule);
        return true;
    }

    /**
     * Méthode qui permet d'associer le client à la reservation
     * @param reservation @Reservation la reservation à associer
     * */
    private boolean associerClientReservation(Reservation reservation) {
        String numeroPermis = recupererNumeroPermis();
        Client client = verifierNumeroPermis(numeroPermis)
                .stream()
                .findFirst()
                .orElse(null);

        if (client == null) {
            view.erreurClient();
            return false;
        }

        reservation.setClient(client);
        return true;
    }

    /**
     * Méthode qui permet de récupérer le numero du permis du client
     * @return @string
     * */
    private String recupererNumeroPermis(){
       return view.identificationClient();
    }

    /**
     * Méthode qui permet de récupérer le véhicule choisi par le client
     * @return @string
     * */
    private String recupererVehiculeChoisi() {
        return view.identificationVehicule();
    }

    /**
     * Méthode qui permet de vérifier si le numero de permis existe dans le système
     * @param numeroPermis @string le numero de permis
     * @return @Optional<Client>
     * */
    private Optional<Client> verifierNumeroPermis(String numeroPermis) {
        return stockage.getClientByNumeroPermis(numeroPermis);
    }


    /**
     * Méthode qui permet de charger la reservation du client
     * @param numeroPermis @string le numero de permis
     * @return Reservation
     * */
    private Reservation chargerReservationClient(String numeroPermis) {
        return stockage.getReservationByClient(numeroPermis).get();
    }

}
