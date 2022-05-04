package agence.views.impl;

import agence.models.ModelVehicule;
import agence.models.TypeVehicule;
import agence.models.Vehicule;
import agence.storage.StockagePersistant;
import agence.views.IVehiculeView;

import java.util.Scanner;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-05-03
 */
public class VehiculeView implements IVehiculeView {
    private static final Scanner scanner = new Scanner(System.in);
    private final StockagePersistant stockage = StockagePersistant.getInstance();

    @Override
    public void ajouterInfoVehicule(Vehicule vehicule) {
        String immatriculation;
        String couleur;
        TypeVehicule type;
        ModelVehicule modelVehicule;
        double prixVehicule;

       //----------- IMMATRICULLATION -----------------
        do {
            // demander le numero de immatriculation
            System.out.println("Veuillez entrer votre numero d'immatriculation du vehicule ");
            immatriculation = scanner.nextLine();
        } while (immatriculation.isEmpty());

        //--------- verification if  vehicule existe
        if (stockage.getVehiculeByImmatriculation (immatriculation).isPresent()) {
            System.out.println("Le numero de permis existe deja dans le stockage");
            return;
        }

        //------- COULEUR ------------
        do {
            // demander le Couleur
            System.out.println("Veuillez entrer votre la couleur du vehicule ");
            couleur = scanner.nextLine();
        } while (couleur.isEmpty());

        type = demanderTypeVehicule();

        // PRIX VEHICULE
        String price;
        do {
            // demander le prix du vehicule
            System.out.println("Veuillez entrer le prix du vehicule ");
            price = scanner.nextLine();
        } while (!price.matches("^[0-9]+"));
        prixVehicule = Double.parseDouble(price);

        // creation du model
        modelVehicule = creationModel();

//        vehicule = new Vehicule(immatriculation, couleur, type, prixVehicule);
        vehicule.setImmatriculation(immatriculation);
        vehicule.setCouleur(couleur);
        vehicule.setType(type);
        vehicule.setPrixVehicule(prixVehicule);
        vehicule.setModele(modelVehicule);
    }

    @Override
    public void erreurVehicule() {
        System.out.println("Le vehicule n'existe pas dans la liste des vehicules retournés");
    }

    private TypeVehicule demanderTypeVehicule() {
        String typeVehicule;
        do {
            afficherTypeVehicules();
            // demander le type de vehicule
            System.out.println("Veuillez entrer le numero correspondant au type de vehicule ");
            typeVehicule = scanner.nextLine();
        } while (!typeVehicule.matches("[1-" + (stockage.getAllTypeVehicule().size() + 1) + "]"));

        // regex range 1 to last index
        if (typeVehicule.matches("[1-" + stockage.getAllTypeVehicule().size() + "]")) {
            return stockage.getAllTypeVehicule().get(Integer.parseInt(typeVehicule) - 1);
        } else if (Integer.parseInt(typeVehicule) == (stockage.getAllTypeVehicule().size() + 1)) {
            // creer un nouveau type de vehicule
            System.out.print("Veuillez entrer le nouveau type de vehicule : ");
            typeVehicule = scanner.nextLine();
            return new TypeVehicule(typeVehicule);
        }

        return null;
    }

    /**
     * affiche les types de vehicules
     */
    private void afficherTypeVehicules() {
        // afficher les types de vehicule
        for (int i = 0; i <= stockage.getAllTypeVehicule().size(); i++) {
            // last index
            if (i == stockage.getAllTypeVehicule().size()) {
                System.out.println((i + 1) + ". Créer un nouveau type de vehicule");
                continue;
            }

            System.out.println((i + 1) + ". " + stockage.getAllTypeVehicule().get(i));
        }
    }

    private ModelVehicule creationModel() {
        // demander le nom du model
        System.out.println("Veuillez entrer le nom du model du vehicule ");
        String nomModel = scanner.nextLine();

        // demander le prix du model
        System.out.println("Veuillez entrer le nombre de place du vehicule ");
        int nbrePlace = scanner.nextInt();

        return new ModelVehicule(nomModel, nbrePlace);
    }
}
