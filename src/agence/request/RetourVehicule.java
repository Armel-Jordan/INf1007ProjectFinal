package agence.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-14
 */
public class RetourVehicule {
    private String id;
    private List<Double> listeFrais;

    public RetourVehicule() {
        this.id = UUID.randomUUID().toString();
        this.listeFrais = new ArrayList<>();
    }

    private void calculerFraisRetard(Location location){
        LocalDateTime dateFinPrevueLocation = location.getDateFinPrevueLocation();
        LocalDateTime dateNow = LocalDateTime.now();
        LocalTime hourNow = LocalDateTime.now().toLocalTime();
        LocalTime hourEndLocation = dateFinPrevueLocation.toLocalTime();

        // kilometrage
        double kilometrageRetour = location.getVehicule().getKilometrage();

        // si tout est beau
        if(dateFinPrevueLocation.equals(dateNow) && hourEndLocation.isBefore(hourNow))
            return;

        // si la date aujourd'hui est après la date fin prévue
        if(dateNow.isAfter(dateFinPrevueLocation)){
            listeFrais.add(75.0 * (dateNow.toLocalDate().toEpochDay() - dateFinPrevueLocation.toLocalDate().toEpochDay()));
        }

        // on multiplie le montant du frais par 2 la difference entre l'heure actuelle et l'heure de retour
        if (dateFinPrevueLocation.equals(dateNow)
                && hourNow.isAfter(hourEndLocation)){
          listeFrais.add((double) 2 * (hourNow.getHour() - hourEndLocation.getHour()));
        }

        // si le kilometrage offert a ete depassé
        double kilometrageAttendue = location.getKilometrageOffert() + location.getKilometrageActuel();
        if(kilometrageAttendue < kilometrageRetour){
            double difference =kilometrageRetour - kilometrageAttendue;
            listeFrais.add(difference * 0.25);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
