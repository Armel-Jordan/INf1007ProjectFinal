package agence.request;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-14
 */
public class RetourVehicule {
    private String id;
    private final List<Double> listeFrais;
    private LocalDateTime dateRetourVehicule;

    public RetourVehicule() {
        this.id = UUID.randomUUID().toString();
        this.listeFrais = new ArrayList<>();
        this.dateRetourVehicule = LocalDateTime.now();
    }

    /**
     * Methode qui calcul les frais liés au retard
     * @param location La Location dont on souhaite calculer les frais de retard
     * */
    public List<Double> calculerFraisRetard(Location location){
        LocalDateTime dateFinReelleLocation = location.getDateFinReelleLocation();
        LocalDateTime dateFinPrevueLocation = location.getDateFinPrevueLocation();
        LocalTime heureFinPrevueLocation = dateFinPrevueLocation.toLocalTime();
        LocalTime hourFinReelleLocation = dateFinReelleLocation.toLocalTime();

        // kilometrage
        double kilometrageRetour = location.getVehicule().getKilometrage();

        // si le kilometrage offert a ete depassé
        double kilometrageAttendue = location.getKilometrageOffert() + location.getKilometrageActuel();
        if(kilometrageAttendue < kilometrageRetour){
            double difference = kilometrageRetour - kilometrageAttendue;
            listeFrais.add(difference * 0.25);
        }

        // si tout est beau, date de remise avant la date de fin prévue
        // ou la journée prévue retour mais quelques heures avant l'heure de fin prévue
        if(
                (dateFinReelleLocation.isEqual(dateFinPrevueLocation)
                        || dateFinPrevueLocation.isAfter(dateFinReelleLocation)
                ) &&
                (   hourFinReelleLocation.isBefore(heureFinPrevueLocation)
                        || hourFinReelleLocation.equals(heureFinPrevueLocation)
                )
        ){
            return null;
        }

        // si la date aujourd'hui est après la date fin prévue
        if(dateFinPrevueLocation.toLocalDate().isBefore(dateFinReelleLocation.toLocalDate())){
            listeFrais.add(75.0 * (dateFinReelleLocation.toLocalDate().toEpochDay() - dateFinPrevueLocation.toLocalDate().toEpochDay()));
        }

        // on multiplie le montant du frais par 2 la difference entre l'heure actuelle et l'heure de retour
        if (dateFinReelleLocation.toLocalDate().isEqual(dateFinPrevueLocation.toLocalDate())
                && heureFinPrevueLocation.isBefore(hourFinReelleLocation)){
          listeFrais.add((double) 2 * (hourFinReelleLocation.getHour() - heureFinPrevueLocation.getHour()));
        }

        return listeFrais;
    }

    /**
     * Methode qui retourne le frais dont on doit rembourser au client
     * @param location La location dont on souhaite calculer les frais de retard
     * @return double
     * */
    public double getFraisRetour(Location location){
        double total = 0;
        for(Double frais : listeFrais){
            total += frais;
        }
        return location.getMontantGarantie() - total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDateRetourVehicule() {
        return dateRetourVehicule;
    }

    public void setDateRetourVehicule(LocalDateTime dateRetourVehicule) {
        this.dateRetourVehicule = dateRetourVehicule;
    }
}
