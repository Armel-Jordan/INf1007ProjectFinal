package agence.views;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-19
 */
public interface IRetourVehiculeView {
    String demanderIdLocation();
    void afficherAmende(double abs);
    void afficherPrixRetour(double fraisRetour);
    void signalerFinRetourVehicule();
}
