package agence.views;

import agence.request.Reservation;

import java.time.LocalDateTime;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-20
 */
public interface IReservationView {
    String identificationClient();
    String identificationVehicule();
    void afficherListVehicules();
    LocalDateTime dateReservation();
    void erreurReservation(Reservation reservation);
    boolean sortir();
}
