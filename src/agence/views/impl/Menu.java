package agence.views.impl;

import agence.controllers.RegistreInscription;
import agence.controllers.RegistreLocation;
import agence.controllers.RegistreReservation;
import agence.request.RetourVehicule;
import agence.storage.StockagePersistant;
import agence.views.IMenu;

import java.util.Scanner;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-29
 */
public class Menu implements IMenu {

    private StockagePersistant stockagePersistant = StockagePersistant.getInstance();
    private RegistreInscription registreInscription = new RegistreInscription(stockagePersistant);
//    private RetourVehicule retourVehicule= new RetourVehicule();
    private RegistreLocation registreLocation= new RegistreLocation();
    private RegistreReservation registreReservation= new RegistreReservation();
    @Override
    public void show() {
        System.out.println("=========== Menu ===========");
        System.out.println("1. Menu Préposés");
        System.out.println("2. Menu Clients");
        System.out.println("3. Menu Gestionnaires");
        System.out.println("0. Quitter");
        System.out.println("==============================");
        getChoiceMenu();
    }

    @Override
    public void getChoiceMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez votre choix: ");
        String choice;
        do {
            choice = scanner.nextLine();
            // switch choice
            switch (choice) {
                case "1":
                    System.out.println("=========== Menu Préposés ===========");
                    menuPrepose();
                    break;
                case "2":
                    System.out.println("=========== Menu Clients ===========");
                    menuClient();
                    break;
                case "3":
                    System.out.println("=========== Menu Gestionnaires ===========");
                    menuGestionnaire();
                    break;
                case "0":
                    System.out.println("Quitter");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Veuillez entrer un choix valide");
                    break;
            }
        } while (!choice.matches("^[0-3]$"));
    }

    private void menuGestionnaire() {
        System.out.println("=========== Sous Menu Du Gestionnaire ===========");
        System.out.println("1. Saisir Nouveau Véhicule");
        System.out.println("2. Retrait d'un Véhicule");
        System.out.println("0. Quitter");
        System.out.println("==============================");

    }

    private void menuClient() {
        System.out.println("=========== Menu ===========");
        System.out.println("1. Consulter la  liste des véhicules");
        System.out.println("0. Quitter");
        System.out.println("==============================");
        getMenuClient();
    }

    private void getMenuClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez votre choix: ");
        String choice;
        do {
            choice = scanner.nextLine();
            // switch choice
            switch (choice) {
                case "1":
                    System.out.println("=========== Liste des véhicules ===========");
                    stockagePersistant.getCatalogueVehicule().values().forEach(System.out::println);
                    break;
                case "0":
                    System.out.println("Quitter");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Veuillez entrer un choix valide");
                    break;
            }
        } while (!choice.matches("^[0-1]$"));
    }

    private void menuPrepose() {

        System.out.println("=========== Sous Menu Du Gestionnaire ===========");
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
        getChoicePrepose();

    }

    private void getChoicePrepose(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez votre choix: ");
        String choice;
        do {
            choice = scanner.nextLine();
            // switch choice
            switch (choice) {
                case "1":
                    System.out.println("1. Enregistrer un nouveau client");
                    System.out.println("==============================");
                    registreInscription.inscriptionClient();
                    menuPrepose();
                    break;
                case "2":
                    System.out.println("2. Ajouter une location");
                    System.out.println("==============================");
                    registreLocation.creationLocation();
                    menuPrepose();
                    break;
                case "3":
                    System.out.println("3. Ajouter une réservation");
                    System.out.println("==============================");
                    registreReservation.ajoutReservation();
                    menuPrepose();
                    break;
                case "4":
                    System.out.println("4. Modifier une réservation");
                    System.out.println("==============================");
                    registreReservation.modifierReservation();
                    menuPrepose();
                    break;
                case "5":
                    System.out.println("5. Supprimer une réservation");
                    System.out.println("==============================");
                    registreReservation.supprimerReservation();
                    menuPrepose();
                    break;
                case "6":
                    System.out.println("6. Afficher la liste des réservations");
                    System.out.println("==============================");
                    stockagePersistant.afficherReservation();
                    menuPrepose();
                    break;
                case "7":
                    System.out.println("7. Afficher la liste des locations");
                    System.out.println("==============================");
                    stockagePersistant.afficherLocation();
                    menuPrepose();
                    break;
                case "8":
                    System.out.println("8. Afficher la listes des clients");
                    System.out.println("==============================");
                    stockagePersistant.afficherClient();
                    menuPrepose();
                    break;
                case "0":
                    System.out.println("0. Quitter");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Veuillez entrer un chiffre entre 0 et 8");
                    System.out.println("==============================");
                    getChoicePrepose();
                    break;
            }
        } while(!choice.matches("[0-8]"));
    }
}
