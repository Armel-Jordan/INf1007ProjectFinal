package agence.views.impl;

import agence.models.Address;
import agence.models.Client;
import agence.models.Conductor;
import agence.storage.StockagePersistant;
import agence.views.IInscription;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-25
 */
public class InscriptionView implements IInscription {
    private static Scanner scanner = new Scanner(System.in);
    private StockagePersistant stockage = StockagePersistant.getInstance();

    @Override
    public void ajouterInfoClient(Client client) {

        // demander le numero de permis
        System.out.println("Veuillez entrer votre numero de permis");
        String numeroPermis = scanner.nextLine();

        // verifier si le numero de permis existe deja dans le stockage
        boolean existe = stockage.getClientByNumeroPermis(numeroPermis).isPresent();

        // si le numero de permis existe deja dans le stockage
        if (existe) {
            System.out.println("Le numero de permis existe deja dans le stockage");
            return;
        }

        // demander le nom
        System.out.println("Veuillez entrer votre nom");
        String nom = scanner.nextLine();

        // demander le prenom
        System.out.println("Veuillez entrer votre prenom");
        String prenom = scanner.nextLine();

        // demander le numero de telephone
        System.out.println("Veuillez entrer votre numero de telephone");
        String numeroTelephone = scanner.nextLine();

        // demander le numero de carte de credit
        System.out.println("Veuillez entrer votre numero de carte de credit");
        String numeroCarteCredit = scanner.nextLine();

        System.out.println("========  Veuillez entrer votre adresse  ========\n");
        Address adresse = new Address();

        // demander le numero municipale
        System.out.println("Veuillez entrer votre numero municipale");
        String numeroMunicipale = scanner.nextLine();

        // demander le nom de rue
        System.out.println("Veuillez entrer votre nom de rue");
        String nomRue = scanner.nextLine();

        // demander la ville
        System.out.println("Veuillez entrer votre ville");
        String ville = scanner.nextLine();

        // demander le code postal
        System.out.println("Veuillez entrer votre code postal");
        String zip = scanner.nextLine();

        // setter les valeurs
        adresse.setNumeroMunicipal(numeroMunicipale);
        adresse.setStreet(nomRue);
        adresse.setCity(ville);
        adresse.setZip(zip);

        // setter les valeurs du client
        client.setNumPermisConduire(numeroPermis);
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setNumTelephone(numeroTelephone);
        client.setNumCarteCredit(numeroCarteCredit);
        client.setAdresse(adresse);
    }

    @Override
    public void ajouterConducteur(Client client) {
        HashSet<Conductor> conducteursAccompagnants = new HashSet<>();
        Conductor conducteur = new Conductor();

        while(true) {
            // demander si le client souhait rajouter un conducteur
            System.out.println("Voulez-vous rajouter un conducteur? (Oui/Non)");
            String reponse = scanner.nextLine();

            // si le client souhaite rajouter un conducteur
            if (!reponse.equalsIgnoreCase("Oui")) {
                client.setConducteursAccompagnants(conducteursAccompagnants);
                return;
            }

            // demander le nom
            System.out.println("Veuillez entrer le nom du conducteur");
            String nom = scanner.nextLine();

            // demander le prenom
            System.out.println("Veuillez entrer le prenom du conducteur");
            String prenom = scanner.nextLine();

            // demander le numero de telephone
            System.out.println("Veuillez entrer le numero de permis du conducteur");
            String numeroPermis = scanner.nextLine();

            // setter les valeurs du conducteur
            conducteur.setNumPermisConduire(numeroPermis);
            conducteur.setNom(nom);
            conducteur.setPrenom(prenom);
            conducteursAccompagnants.add(conducteur);
        }

        // ajouter le conducteur dans le set
    }

    @Override
    public void terminerInscription() {
        System.out.println("========  Inscription terminée  ========\n");
    }
}
