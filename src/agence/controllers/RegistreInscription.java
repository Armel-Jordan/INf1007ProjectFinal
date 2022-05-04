package agence.controllers;

import agence.models.Client;
import agence.storage.StockagePersistant;
import agence.storage.StockageRepository;
import agence.views.IInscription;
import agence.views.impl.InscriptionView;

public class RegistreInscription {
    private final StockageRepository stockage = StockagePersistant.getInstance();
    private final IInscription inscriptionView = new InscriptionView();

    public void inscriptionClient() {
        Client client = new Client();

        ajouterInfoClient(client);

        ajouterConducteur(client);

        terminerInscription(client);
    }

    private void ajouterInfoClient(Client client) {
        inscriptionView.ajouterInfoClient(client);
    }

    private void ajouterConducteur(Client client) {
        inscriptionView.ajouterConducteur(client);
    }

    private void terminerInscription(Client client) {
        stockage.ajouterClient(client);
        inscriptionView.terminerInscription();
    }

}
