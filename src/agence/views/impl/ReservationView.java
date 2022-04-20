package agence.views.impl;

import agence.models.Vehicule;
import agence.request.Reservation;
import agence.storage.StockagePersistant;
import agence.views.IReservationView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-20
 */
public class ReservationView implements IReservationView {

    private StockagePersistant stockage;

    // scanner
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String identificationClient() {
        System.out.println("Entrer votre Numero de Permis : ");
        return scanner.nextLine();
    }

    @Override
    public String identificationVehicule() {
        afficherListVehicules();
        System.out.println("Entrer l'immatricule du véhicule : ");
        return scanner.nextLine();
    }

    @Override
    public void afficherListVehicules() {
        System.out.println("=== Liste des vehicules ===");
        listVehicules().forEach(v -> System.out.println(v.getImmatriculation()
                + " | " + v.getModele() + " | " + v.getCouleur() + " | " + v.getPrixVehicule() + " CAD"));
    }

    @Override
    public LocalDateTime dateReservation() {
        System.out.println("Entrer la date de la reservation sous la forme : YYYY-MM-DD HH:MM");
        return LocalDateTime.parse(scanner.nextLine());
    }

    @Override
    public void erreurReservation(Reservation reservation) {
        System.out.printf("La réservation pour le véhicule %s ne peut être effectué à la date %s.\n",
                reservation.getVehicule().getImmatriculation(), reservation.getDate().toString());
    }

    @Override
    public boolean sortir() {
        System.out.println("Voulez-vous sortir ? (Oui/Non)");
        return scanner.nextLine().trim().equalsIgnoreCase("Oui");
    }

    private List<Vehicule> listVehicules() {
        return new ArrayList<>(stockage.getCatalogueVehicule().values());
    }
}
