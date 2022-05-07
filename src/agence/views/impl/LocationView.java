package agence.views.impl;

import agence.models.Client;
import agence.models.Facture;
import agence.models.Vehicule;
import agence.request.Location;
import agence.request.Paiement;
import agence.storage.StockagePersistant;
import agence.storage.StockageRepository;
import agence.tools.ConsoleColors;
import agence.views.ILocationView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-14
 */
public class LocationView implements ILocationView {

    private final StockageRepository stockage = StockagePersistant.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Location saisirInfomationLocation(Location location) {
        // demander les informations du client
        System.out.println("Entrez le numero du permis de conduire du client (exemple : UQTR-1 ou UQTR-2)");
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

        String dateFin;
        do {
            // demander la date de Fin de location
            System.out.println("Entrez la date de fin de location (format: dd/mm/yyyy)");
            dateFin = scanner.nextLine();
        } while (!isDateValid(dateFin));
        // convertir la date de fin en un objet LocalDateTime
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dateFinLocalDateTime = LocalDate.parse(dateFin, formatter).atStartOfDay();

        // setter les informations de la location
        location.setVehicule(vehicule);
        location.setClient(client);
        location.setDateFinPrevueLocation(dateFinLocalDateTime);

        return location;
    }

    private boolean isDateValid(String dateFin) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dateFinLocalDateTime = LocalDate.parse(dateFin, formatter).atStartOfDay();
        // avant la date d'hier
        if (dateFinLocalDateTime.isBefore(LocalDateTime.now().minusDays(1))) {
            System.err.println("La date de fin de location ne doit pas être inférieure à la date d'aujourd'hui");
            return false;
        }
        return true;
    }

    private String obtenirMatriculationChoisies() {
        afficherListVehicules();
        System.out.println("Entrez l'immatriculation du vehicule que vous souhaitez louer (exemple : IMM1 ou IMM2)");

        return scanner.nextLine();
    }

    @Override
    public void afficherListVehicules() {
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n==============================================");
        System.out.println("============= LISTE DES VEHICULES ============");
        System.out.println("==============================================" + ConsoleColors.WHITE_BOLD_BRIGHT );
        stockage.getCatalogueVehicule().values().forEach(System.out::println);
    }

    @Override
    public void procederPaiement(Location location) {

        String montant;
        do{
            System.out.println("Entrez le montant du paiement (Garantie : 200$)");
            montant = scanner.nextLine();

        } while(!montant.matches("^[0-9]+"));

        Paiement paiement = new Paiement(Double.parseDouble(montant));

        stockage.ajouterPaiement(paiement);

        // create Facture
        Facture facture = new Facture(location.getIdLocation(), location.getClient().getIdClient(), paiement.getMontant());

        System.out.println("Le paiement a ete ajoute avec succes");

        // impression de la facture
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n==============================================");
        System.out.println("================== FACTURE ===================");
        System.out.println(facture.impression());
        System.out.println("==============================================" + ConsoleColors.WHITE_BOLD_BRIGHT );

        System.out.println("\nVotre location a été enregistrée avec succes, " +
                "\nvoici l'ID de votre location: " + ConsoleColors.GREEN_BOLD_BRIGHT + location.getIdLocation() + ConsoleColors.RESET);
    }
}
