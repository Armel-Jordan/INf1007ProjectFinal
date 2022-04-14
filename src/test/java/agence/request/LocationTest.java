package agence.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import agence.models.Client;
import agence.models.TypeVehicule;
import agence.models.Vehicule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class LocationTest {
    @Test
    void calculKilometrageOffert() {
        // given
        Date dateDebut = new Date();
        Date dateRetour = new Date(dateDebut.getTime() + (2 * 24 * 60 * 60 * 1000));

        // diiference between two dates in days
        int diff = (int) ((dateRetour.getTime() - dateDebut.getTime()) / (1000 * 60 * 60 * 24));

        assertEquals(200, diff * 100);
    }
}

