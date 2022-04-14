package agence.views;

import java.util.Scanner;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-14
 */
public class RetourVehiculeView {

    private Scanner scanner = new Scanner(System.in);

    public String demanderIdLocation(){
        System.out.println("Entrer le numero d'identification de la Location");
        return scanner.nextLine();
    }

    public void afficherAmende(double abs) {
        System.out.println("L'amende est de : " + abs);
    }

    public void afficherPrixRetour(double fraisRetour) {
        System.out.println("Le prix de retour est de : " + fraisRetour);
    }

    public void signalerFinRetourVehicule() {
        System.out.println("Fin de retour du vehicule");
    }
}
