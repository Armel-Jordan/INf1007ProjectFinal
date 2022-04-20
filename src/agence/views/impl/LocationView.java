package agence.views.impl;

import agence.models.Client;
import agence.models.Vehicule;
import agence.request.Location;
import agence.request.Paiement;
import agence.storage.StockagePersistant;
import agence.views.ILocationView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-14
 */
public class LocationView {

    private StockagePersistant stockage;
    private Scanner scanner = new Scanner(System.in);

    public Location saisirInfomationLocation(Location location) {
        // demander les informations la voiture
        String immatriculation = obtenirMatriculationChoisies();

        // get vehicule by immatriculation if exist in stockage otherwise throw exception
        Vehicule vehicule = stockage.getVehiculeByImmatriculation(immatriculation)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Le vehicule avec l'ID %s n'existe pas", immatriculation)));

        // demander les informations du client
        System.out.println("Entrez le numero du permis de conduire du client");
        String numeroPermis = scanner.nextLine();

        // get client by numero permis
        Client client = stockage.getClientByNumeroPermis(numeroPermis)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Le client avec le numero de permis %s n'existe pas", numeroPermis)));

        // demander la date de Fin de location
        System.out.println("Entrez la date de fin de location (format: dd/mm/yyyy)");
        String dateFin = scanner.nextLine();
        // convertir la date de fin en un objet LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dateFinLocalDateTime = LocalDateTime.parse(dateFin, formatter);

        // setter les informations de la location
        location.setVehicule(vehicule);
        location.setClient(client);
        location.setDateFinPrevueLocation(dateFinLocalDateTime);

        return location;
    }

    private String obtenirMatriculationChoisies() {
        System.out.println("Voici la liste des vehicules disponibles");
        System.out.println("-----------------------------------------------");
        for(int i = 0; i < stockage.getVehiculesDisponibles().size(); i++) {
            System.out.println((stockage.getVehiculesDisponibles().get(i).getImmatriculation() + " | "
                    + stockage.getVehiculesDisponibles().get(i).getModele()
                    + " | " + stockage.getVehiculesDisponibles().get(i).getPrixVehicule() + " $"));
        }
        System.out.println("-----------------------------------------------");
        System.out.println("Entrez l'immatriculation du vehicule que vous souhaitez louer");

        return scanner.nextLine();
    }

    public void procederPaiement(Location location) {
        Paiement paiement = location.getPaiement();
        System.out.println("Entrez le numero de le montant du paiement");
        double montant = scanner.nextDouble();
        paiement.setMontant(montant);

        stockage.ajouterPaiement(paiement);

        System.out.println("Le paiement a ete ajoute avec succes");
    }
}
