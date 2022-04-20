package agence.views.impl;

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
    public String demanderIdLocation(){
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
}
