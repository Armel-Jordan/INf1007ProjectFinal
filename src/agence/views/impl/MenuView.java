package agence.views.impl;

import agence.controllers.RegistreInscription;
import agence.controllers.RegistreLocation;
import agence.controllers.RegistreReservation;
import agence.controllers.RegistreVehicule;
import agence.models.Vehicule;
import agence.storage.StockagePersistant;
import agence.storage.StockageRepository;
import agence.tools.ConsoleColors;
import agence.views.IMenuView;

import java.util.Scanner;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-29
 */
public class MenuView implements IMenuView {

    private final StockageRepository stockagePersistant = StockagePersistant.getInstance();
    private final RegistreInscription registreInscription = new RegistreInscription();
    private final RegistreLocation registreLocation = new RegistreLocation();
    private final RegistreReservation registreReservation = new RegistreReservation();
    private final RegistreVehicule registreVehicule = new RegistreVehicule();
    private static final Scanner scanner = new Scanner(System.in);
    private static String choice;

    @Override
    public void show() {
        System.out.println("\n\n=========== Menu ===========");
        System.out.println("1. Menu Préposés");
        System.out.println("2. Menu Clients");
        System.out.println("3. Menu Gestionnaires");
        System.out.println("0. Quitter");
        System.out.println("==============================");
    }

    @Override
    public void getMenu() {
        do {
            show();
            System.out.print("Entrez votre choix: ");
            choice = scanner.nextLine();
            // switch choice
            switch (choice) {
                case "1":
                    getChoicePrepose();
                    break;
                case "2":
                    getMenuClient();
                    break;
                case "3":
                    getChoiceGestionnaire();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Veuillez entrer un choix valide");
                    break;
            }
        } while (true);
    }

    private void menuGestionnaire() {
        System.out.println("\n\n=========== Menu Gestionnaire ===========");
        System.out.println("1. Saisir Nouveau Véhicule");
        System.out.println("2. Retrait d'un Véhicule");
        System.out.println("3. Consulter la liste des véhicules Retournes");
        System.out.println("4. Consulter la liste des véhicules À Réparer");
        System.out.println("5. Consulter la liste des véhicules Retirées du système");
        System.out.println("0. Quitter");
        System.out.println("==============================");
    }

    private void getChoiceGestionnaire(){
        do {
            menuGestionnaire();
            System.out.print("Entrez votre choix: ");
            choice = scanner.nextLine();
            // switch choice
            switch (choice) {
                case "1":
                    saisirNouveauVehicule();
                    break;
                case "2":
                    retirerVehicule();
                    break;
                case "3":
                    afficherVehiculeRetournes();
                    break;
                case "4":
                    afficherVehiculeRepare();
                    break;
                case "5":
                    afficherVehiculeRetires();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Veuillez entrer un choix valide");
                    break;
            }
        } while (true);
    }

    private void afficherVehiculeRetires() {
        System.out.println("\n\n=========== Liste des véhicules Retirés du système ===========");
        if (stockagePersistant.getCatalogueVehiculeRetires().isEmpty()) {
            System.out.println(ConsoleColors.GREEN_BOLD + "\t\t\tAucun véhicule n'a été retiré du système" + ConsoleColors.RESET);
        }
        for (Vehicule v : stockagePersistant.getCatalogueVehiculeRetires().values()) {
            System.out.println(v);
        }
        System.out.println("==============================================================");
    }

    private void afficherVehiculeRepare() {
        System.out.println("\n\n=========== Liste des véhicules Reparés du système ===========");
        if (stockagePersistant.getCatalogueVehiculeRepare().isEmpty()) {
            System.out.println(ConsoleColors.GREEN_BOLD + "\t\t\t\tAucun véhicule à réparer" + ConsoleColors.RESET);
        }
        for (Vehicule v : stockagePersistant.getCatalogueVehiculeRepare().values()) {
            System.out.println(v);
        }
        System.out.println("==============================================================");
    }

    private void afficherVehiculeRetournes() {
        System.out.println("\n\n=========== Liste des véhicules Retournés du système ===========");
        if (stockagePersistant.getVehiculeRetourne().isEmpty()) {
            System.out.println(ConsoleColors.GREEN_BOLD + "\t\t\tAucun véhicule n'a été retourné du système" + ConsoleColors.RESET);
        }
        for (Vehicule v : stockagePersistant.getVehiculeRetourne().values()) {
            System.out.println(v);
        }
        System.out.println("==============================================================");
    }

    private void retirerVehicule() {
        registreVehicule.retirerVehiculeEndommage();
    }

    private void saisirNouveauVehicule() {
        registreVehicule.ajouterVehicule();
    }

    private void menuClient() {
        System.out.println("\n\n=========== Menu ===========");
        System.out.println("1. Consulter la  liste des véhicules");
        System.out.println("0. Quitter");
        System.out.println("==============================");
    }

    private void getMenuClient() {
        do {
            menuClient();
            System.out.print("Entrez votre choix: ");
            choice = scanner.nextLine();
            // switch choice
            switch (choice) {
                case "1":
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n==============================================");
                    System.out.println("============= LISTE DES VEHICULES ============");
                    System.out.println("==============================================" + ConsoleColors.WHITE_BOLD_BRIGHT );
                    stockagePersistant.getCatalogueVehicule().values().forEach(System.out::println);
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "=========================================" + ConsoleColors.RESET);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Veuillez entrer un choix valide");
                    break;
            }
        } while (true);
    }

    private void menuPrepose() {
        System.out.println("\n\n=========== Menu Préposé ===========");
        System.out.println("1. Enregistrer Nouveau Client");
        System.out.println("2. Ajouter une location");
        System.out.println("3. Ajouter une réservation");
        System.out.println("4. Modifier une réservation");
        System.out.println("5. Supprimer une réservation");
        System.out.println("6. Afficher la liste des réservations");
        System.out.println("7. Afficher la liste des locations");
        System.out.println("8. Afficher la listes des clients");
        System.out.println("9. Créer un nouveau Retour Vehicule");
        System.out.println("0. Quitter");
        System.out.println("================================");
    }

    private void getChoicePrepose(){
        do {
            menuPrepose();
            System.out.print("Entrez votre choix: ");
            choice = scanner.nextLine();
            // switch choice
            switch (choice) {
                case "1":
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n=======================================================");
                    System.out.println("========= ENREGISTREMENT D'UN NOUVEAU CLIENT ==========");
                    System.out.println("=======================================================" + ConsoleColors.WHITE_BOLD_BRIGHT);
                    registreInscription.inscriptionClient();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "========================================================" + ConsoleColors.RESET);
                    break;
                case "2":
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n=========================================");
                    System.out.println("========= AJOUTER UNE LOCATION ==========");
                    System.out.println("=========================================" + ConsoleColors.WHITE_BOLD_BRIGHT);
                    registreLocation.creationLocation();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "=========================================" + ConsoleColors.RESET);
                    break;
                case "3":
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n============================================");
                    System.out.println("========= AJOUTER UNE RESERVATION ==========");
                    System.out.println("============================================" + ConsoleColors.WHITE_BOLD_BRIGHT);
                    registreReservation.ajoutReservation();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "============================================" + ConsoleColors.RESET);
                    break;
                case "4":
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n==============================================");
                    System.out.println("========= MODIFIER UNE RESERVATION ===========");
                    System.out.println("==============================================" + ConsoleColors.WHITE_BOLD_BRIGHT);
                    registreReservation.modifierReservation();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "==============================================" + ConsoleColors.RESET);
                    break;
                case "5":
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n==================================================");
                    System.out.println("========= SUPPRESSION D'UNE RESERVATION ==========");
                    System.out.println("==================================================" + ConsoleColors.WHITE_BOLD_BRIGHT);
                    registreReservation.supprimerReservation();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "==================================================" + ConsoleColors.RESET);
                    break;
                case "6":
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n===========================================");
                    System.out.println("========= LISTE DES RESERVATIONS ==========");
                    System.out.println("===========================================" + ConsoleColors.WHITE_BOLD_BRIGHT);
                    stockagePersistant.afficherReservation();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "===========================================" + ConsoleColors.RESET);
                    break;
                case "7":
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n========================================");
                    System.out.println("========= LISTE DES LOCATIONS ==========");
                    System.out.println("========================================" + ConsoleColors.WHITE_BOLD_BRIGHT);
                    stockagePersistant.afficherLocation();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "========================================" + ConsoleColors.RESET);
                    break;
                case "8":
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n======================================");
                    System.out.println("========= LISTE DES CLIENTS ==========");
                    System.out.println("======================================" + ConsoleColors.WHITE_BOLD_BRIGHT);
                    stockagePersistant.afficherClient();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "======================================" + ConsoleColors.RESET);
                    break;
                case "9":
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n==========================================");
                    System.out.println("========= CRÉER RETOUR VEHICULE ==========");
                    System.out.println("==========================================" + ConsoleColors.WHITE_BOLD_BRIGHT);
                    registreVehicule.creerNouveauRetour();
                    System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "==========================================" + ConsoleColors.RESET);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Veuillez entrer un chiffre entre 0 et 8");
                    System.out.println("================================");
                    break;
            }
        } while(true);
    }
}
