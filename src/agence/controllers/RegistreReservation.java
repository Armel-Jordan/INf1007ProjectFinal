package agence.controllers;

import agence.models.Client;
import agence.models.Vehicule;
import agence.request.Reservation;
import agence.storage.StockagePersistant;
import agence.views.IReservationView;

import java.time.LocalDateTime;
import java.util.Optional;

public class RegistreReservation {
    private StockagePersistant stockage;
    private IReservationView view;

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

}
