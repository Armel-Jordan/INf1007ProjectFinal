package agence.controllers;

import agence.models.Client;
import agence.storage.StockagePersistant;
import agence.views.IInscription;

public class RegistreInscription {
    private StockagePersistant stockage;
    private IInscription inscriptionView;

    public RegistreInscription(StockagePersistant stockage) {
        this.stockage = stockage;
    }

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
