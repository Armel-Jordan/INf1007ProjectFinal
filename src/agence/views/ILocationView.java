package agence.views;

import agence.request.Location;

/**
 * @author Jordan Kuibia
 * @version 1.0
 * @since 2022-04-19
 */
public interface ILocationView {

    Location saisirInfomationLocation(Location location);
    void procederPaiement(Location location);
}
