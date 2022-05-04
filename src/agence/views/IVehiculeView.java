package agence.views;

import agence.models.Vehicule;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-05-03
 */
public interface IVehiculeView{

    void ajouterInfoVehicule(Vehicule vehicule);

    void erreurVehicule();
}
