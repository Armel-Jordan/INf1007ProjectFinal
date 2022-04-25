package agence.views;

import agence.models.Client;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-25
 */
public interface IInscription {
    void ajouterInfoClient(Client client);

    void ajouterConducteur(Client client);

    void terminerInscription();
}
