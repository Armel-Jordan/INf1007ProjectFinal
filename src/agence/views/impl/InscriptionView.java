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
        String numeroPermis,
                nom,
                prenom,
                numeroTelephone,
                numeroCarteCredit,
                numeroMunicipale,
                nomRue,
                ville,
                zip;

        do {
            // demander le numero de permis
            System.out.println("Veuillez entrer votre numero de permis");
            numeroPermis = scanner.nextLine();
        } while (numeroPermis.isEmpty());

        // verifier si le numero de permis existe deja dans le stockage
        boolean existe = stockage.getClientByNumeroPermis(numeroPermis).isPresent();

        // si le numero de permis existe deja dans le stockage
        if (existe) {
            System.out.println("Le numero de permis existe deja dans le stockage");
            return;
        }

        do {
            // demander le nom
            System.out.println("Veuillez entrer votre nom");
            nom = scanner.nextLine();
        } while (nom.isEmpty());


        do {
            // demander le prenom
            System.out.println("Veuillez entrer votre prenom");
            prenom = scanner.nextLine();
        } while (prenom.isEmpty());


        do {
            // demander le numero de telephone
            System.out.println("Veuillez entrer votre numero de telephone");
            numeroTelephone = scanner.nextLine();
        } while (numeroTelephone.isEmpty());


        do {
            // demander le numero de carte de credit
            System.out.println("Veuillez entrer votre numero de carte de credit");
            numeroCarteCredit = scanner.nextLine();
        } while (numeroCarteCredit.isEmpty());

        System.out.println("===================================================");
        System.out.println("========  Veuillez entrer votre adresse  ==========");
        System.out.println("===================================================\n");
        Address adresse = new Address();


        do {
            // demander le numero municipale
            System.out.println("Veuillez entrer votre numero municipale");
            numeroMunicipale = scanner.nextLine();
        } while (numeroMunicipale.isEmpty());


        do {
            // demander le nom de rue
            System.out.println("Veuillez entrer votre nom de rue");
            nomRue = scanner.nextLine();
        } while (nomRue.isEmpty());


        do {
            // demander la ville
            System.out.println("Veuillez entrer votre ville");
            ville = scanner.nextLine();
        } while (ville.isEmpty());


        do {
            // demander le code postal
            System.out.println("Veuillez entrer votre code postal");
            zip = scanner.nextLine();
        } while (zip.isEmpty());

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
        Conductor conducteur;

        while(true) {
            // demander si le client souhait rajouter un conducteur
            System.out.println("Voulez-vous rajouter un conducteur? (Oui/Non)");
            String reponse = scanner.nextLine();

            if(!reponse.equalsIgnoreCase("Oui") && !reponse.equalsIgnoreCase("Non"))
                continue;

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
            conducteur = new Conductor();
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
