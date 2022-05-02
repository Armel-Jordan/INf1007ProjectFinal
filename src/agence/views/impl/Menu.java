package agence.views.impl;

import agence.controllers.RegistreInscription;
import agence.controllers.RegistreLocation;
import agence.controllers.RegistreReservation;
import agence.storage.StockagePersistant;
import agence.storage.StockageRepository;
import agence.tools.ConsoleColors;
import agence.views.IMenu;

import java.util.Scanner;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-29
 */
public class Menu implements IMenu {

    private final StockageRepository stockagePersistant = StockagePersistant.getInstance();
    private final RegistreInscription registreInscription = new RegistreInscription(stockagePersistant);
    private final RegistreLocation registreLocation = new RegistreLocation();
    private final RegistreReservation registreReservation = new RegistreReservation();

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
    public void getChoiceMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice;
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
                    System.out.println("=========== Menu Gestionnaires ===========");
                    menuGestionnaire();
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
        System.out.println("=========== Sous Menu Du Gestionnaire ===========");
        System.out.println("1. Saisir Nouveau Véhicule");
        System.out.println("2. Retrait d'un Véhicule");
        System.out.println("0. Quitter");
        System.out.println("==============================");
    }

    private void menuClient() {
        System.out.println("\n\n=========== Menu ===========");
        System.out.println("1. Consulter la  liste des véhicules");
        System.out.println("0. Quitter");
        System.out.println("==============================");
    }

    private void getMenuClient() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            menuClient();
            System.out.print("Entrez votre choix: ");
            choice = scanner.nextLine();
            // switch choice
            switch (choice) {
                case "1":
                    System.out.println("\n=========== Liste des véhicules ===========");
                    stockagePersistant.getCatalogueVehicule().values().forEach(System.out::println);
                    System.out.println("=========================================");
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
        System.out.println("0. Quitter");
        System.out.println("==============================");
    }

    private void getChoicePrepose(){
        // afficher le menu Propose
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez votre choix: ");
        String choice;
        do {
            menuPrepose();
            System.out.print("Entrez votre choix: ");
            choice = scanner.nextLine();
            // switch choice
            switch (choice) {
                case "1":
                    System.out.println("1. Enregistrer un nouveau client");
                    System.out.println("==============================");
                    registreInscription.inscriptionClient();
                    break;
                case "2":
                    System.out.println("2. Ajouter une location");
                    System.out.println("==============================");
                    registreLocation.creationLocation();
                    break;
                case "3":
                    System.out.println("3. Ajouter une réservation");
                    System.out.println("==============================");
                    registreReservation.ajoutReservation();
                    break;
                case "4":
                    System.out.println("4. Modifier une réservation");
                    System.out.println("==============================");
                    registreReservation.modifierReservation();
                    break;
                case "5":
                    System.out.println("5. Supprimer une réservation");
                    System.out.println("==============================");
                    registreReservation.supprimerReservation();
                    break;
                case "6":
                    System.out.println("6. Afficher la liste des réservations");
                    System.out.println("==============================");
                    stockagePersistant.afficherReservation();
                    break;
                case "7":
                    System.out.println("7. Afficher la liste des locations");
                    System.out.println("==============================");
                    stockagePersistant.afficherLocation();
                    break;
                case "8":
                    System.out.println("8. Afficher la listes des clients");
                    System.out.println("==============================");
                    stockagePersistant.afficherClient();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Veuillez entrer un chiffre entre 0 et 8");
                    System.out.println("==============================");
                    break;
            }
        } while(true);
    }
}
