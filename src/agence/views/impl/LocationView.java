package agence.views.impl;

import agence.models.Client;
import agence.models.Vehicule;
import agence.request.Location;
import agence.request.Paiement;
import agence.storage.StockagePersistant;
import agence.views.ILocationView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-14
 */
public class LocationView implements ILocationView {

    private StockagePersistant stockage= StockagePersistant.getInstance();
    private final Scanner scanner = new Scanner(System.in);


    @Override
    public Location saisirInfomationLocation(Location location) {
        // demander les informations du client
        System.out.println("Entrez le numero du permis de conduire du client");
        String numeroPermis = scanner.nextLine();

        // get client by numero permis
        Client client = stockage.getClientByNumeroPermis(numeroPermis)
                .stream()
                .findFirst()
                .orElse(null);

        if(client == null){
            System.err.println("Le client n'existe pas");
            return null;
        }

        // verifier si le client a une location en cours
        if(stockage.hasLocationByNumeroPermis(numeroPermis)){
            System.out.println("Le client a deja une location en cours");
            return null;
        }

        // demander les informations la voiture
        String immatriculation = obtenirMatriculationChoisies();

        // get vehicule by immatriculation if exist in stockage otherwise throw exception
        Vehicule vehicule = stockage.getVehiculeByImmatriculation(immatriculation)
                .stream()
                .findFirst()
                .orElse(null);

        if(vehicule == null){
            System.err.println("Le vehicule n'existe pas");
            return null;
        }

        // demander la date de Fin de location
        System.out.println("Entrez la date de fin de location (format: dd/mm/yyyy)");
        String dateFin = scanner.nextLine();
        // convertir la date de fin en un objet LocalDateTime
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dateFinLocalDateTime = LocalDate.parse(dateFin, formatter).atStartOfDay();

        // setter les informations de la location
        location.setVehicule(vehicule);
        location.setClient(client);
        location.setDateFinPrevueLocation(dateFinLocalDateTime);

        return location;
    }

    private String obtenirMatriculationChoisies() {
        afficherListVehicules();
        System.out.println("Entrez l'immatriculation du vehicule que vous souhaitez louer");

        return scanner.nextLine();
    }

    @Override
    public void afficherListVehicules() {
        System.out.println("=================== Liste des vehicules =================");
        listVehicules().forEach(v -> System.out.println(v.getImmatriculation() + " | "
                + v.getModele() + " | " + v.getCouleur() + " | " + v.getPrixVehicule() + " CAD"));
    }

    private List<Vehicule> listVehicules(){
        return new ArrayList<>(stockage.getCatalogueVehicule().values());
    }

    @Override
    public void procederPaiement(Location location) {

        System.out.println("Entrez le numero de le montant du paiement");
        double montant = scanner.nextDouble();
        Paiement paiement = new Paiement(montant);

        stockage.ajouterPaiement(paiement);

        System.out.println("Le paiement a ete ajoute avec succes");
    }
}
