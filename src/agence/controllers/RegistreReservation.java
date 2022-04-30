package agence.controllers;

import agence.models.Client;
import agence.models.Vehicule;
import agence.request.Reservation;
import agence.storage.StockagePersistant;
import agence.views.IReservationView;
import agence.views.impl.ReservationView;

import java.time.LocalDateTime;
import java.util.Optional;

public class RegistreReservation {
    private StockagePersistant stockage= StockagePersistant.getInstance();
    private IReservationView view=new ReservationView();

    /**
     * Methode qui permet d'ajouter la réservation dans le stockage
     * */
    public void ajoutReservation(){
        Reservation reservation = new Reservation();

        associerClientReservation(reservation);

       do {
           associerVehiculeReservation(reservation);
           ajouterDateReservation(reservation);

           if(!peutReservationDate(reservation)){
               view.erreurReservation(reservation);

               // possibilité de sortir de la boucle
               if(view.sortir()) return;
           }

       } while(!peutReservationDate(reservation));

       stockage.ajouterReservation(reservation);
    }


    /**
     * Methode qui permet de modifier une reservation
     * */
    public void modifierReservation(){
        Reservation reservation = new Reservation();
        String numeroPermis = recupererNumeroPermis();

        // verifier si le client a une reservation en cours
        if(!stockage.hasReservationByNumeroPermis(numeroPermis))
            throw new RuntimeException(String.format("Le client %s n'a pas de réservation en cours", numeroPermis));

        // charger la reservation du client
        Reservation reservationClient = chargerReservationClient(numeroPermis);

        reservationClient = view.modificationReservation(reservationClient);

        // terminer la modification
        terminerModificationReservationClient(reservationClient);
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
        if(!stockage.hasReservationByNumeroPermis(numeroPermis))
            throw new RuntimeException(String.format("Le client %s n'a pas de réservation en cours", numeroPermis));

        // charger la reservation du client
        Reservation reservationClient = chargerReservationClient(numeroPermis);

        view.suppressionReservation(reservationClient);
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
    private void associerVehiculeReservation(Reservation reservation) {
        String immatriculation = recupererVehiculeChoisi();
        Vehicule vehicule = stockage.getVehiculeByImmatriculation(immatriculation)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Le vehicule avec l'immatriculation %s n'existe pas dans le système", immatriculation)));

        reservation.setVehicule(vehicule);
    }

    /**
     * Méthode qui permet d'associer le client à la reservation
     * @param reservation @Reservation la reservation à associer
     * */
    private void associerClientReservation(Reservation reservation) {
        String numeroPermis = recupererNumeroPermis();
        Client client = verifierNumeroPermis(numeroPermis)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Le client avec le Numero de Permis %s n'existe pas dans le système", numeroPermis)));

        reservation.setClient(client);
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
