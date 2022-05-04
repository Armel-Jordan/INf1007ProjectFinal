package agence.views.impl;

import agence.models.Vehicule;
import agence.storage.StockagePersistant;
import agence.tools.ConsoleColors;
import agence.views.IRetourVehiculeView;

import java.util.Scanner;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-14
 */
public class RetourVehiculeView implements IRetourVehiculeView {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String demanderIdLocation() {
        System.out.println("Entrer le numero d'identification de la Location");
        return scanner.nextLine();
    }

    @Override
    public void afficherAmende(double abs) {
        System.out.println("L'amende est de : " + abs);
    }

    @Override
    public void afficherPrixRetour(double fraisRetour) {
        System.out.println("Le prix de retour est de : " + fraisRetour);
    }

    @Override
    public void signalerFinRetourVehicule() {
        System.out.println("Fin de retour du vehicule");
    }

    @Override
    public String demanderImmatriculeEndommage() {
        System.out.println("Entrer l'immatricule du vehicule endommage");
        return scanner.nextLine();
    }

    @Override
    public void demanderDestinationFinale(Vehicule vehicule) {
        // créer un switch
        do {
            System.out.println("Choisir la destination finale du vehicule : ");
            System.out.println("\t1- pour ajouter le vehicule à la liste des vehicules à Réparer");
            System.out.println("\t2- pour ajouter le vehicule à la liste des vehicules à supprimer");
            System.out.println("\t0- pour Retourner");
            System.out.print("Entrer : ");
            String choix = scanner.nextLine();
            switch (choix) {
                case "1":
                    StockagePersistant.catalogueVehiculeRepare.put(vehicule.getImmatriculation(), vehicule);
                    StockagePersistant.catalogueVehiculeRetourne.remove(vehicule.getImmatriculation());
                    StockagePersistant.catalogueVehicule.remove(vehicule.getImmatriculation());
                    System.out.println("===============================================================");
                    System.out.println(ConsoleColors.GREEN_BOLD + "Le vehicule a été ajouté dans la liste des vehicules à réparer" + ConsoleColors.RESET);
                    System.out.println("===============================================================");
                    return;
                case "2":
                    StockagePersistant.catalogueVehiculeRetire.put(vehicule.getImmatriculation(), vehicule);
                    StockagePersistant.catalogueVehiculeRetourne.remove(vehicule.getImmatriculation());
                    StockagePersistant.catalogueVehicule.remove(vehicule.getImmatriculation());
                    System.out.println("===============================================================");
                    System.out.println(ConsoleColors.GREEN_BOLD + "Le vehicule a été ajouté dans la liste des vehicules à retirer" + ConsoleColors.RESET);
                    System.out.println("===============================================================");
                    return;
                case "0":
                    return;
                default:
                    System.out.println("Choix invalide !\n");
                    break;
            }
        } while (true);
    }

    @Override
    public void erreurVehicule() {
        System.err.println("La location avec cet id n'existe pas");
    }
}
