package agence.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import agence.models.Client;
import agence.models.TypeVehicule;
import agence.models.Vehicule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RetourVehiculeTest {
    /**
     * Method under test: {@link RetourVehicule#calculerFraisRetard()}
     */
    @Test
    @DisplayName("calcul de frais retour - happy path avec retour complete de la garantie")
    void testCalculerFraisRetard() {
        Vehicule vehicule = new Vehicule("IMM", "Blue", new TypeVehicule("Audi"), 100);
        Location location = new Location(vehicule, new Client() ,LocalDateTime.now().plusDays(1));
        RetourVehicule retourVehicule = new RetourVehicule(location);
        retourVehicule.calculerFraisRetard();

        // assert
        assertEquals(200.0d, retourVehicule.getFraisRetour());
    }

    /**
     * Method under test: {@link RetourVehicule#calculerFraisRetard()}
     */
    @Test
    @DisplayName("calcul de frais retour - dépassement X2 des kilometrages offerts")
    void testCalculerFraisRetard2() {
        Vehicule vehicule = new Vehicule("IMM", "Blue", new TypeVehicule("Audi"), 100);
        Location location = new Location(vehicule, new Client() ,LocalDateTime.now().plusDays(1));
        location.getVehicule().setKilometrage(200);
        RetourVehicule retourVehicule = new RetourVehicule(location);
        retourVehicule.calculerFraisRetard();

        // assert
        assertEquals(175.0d, retourVehicule.getFraisRetour());
    }

    /**
     * Method under test: {@link RetourVehicule#calculerFraisRetard()}
     */
    @Test
    @DisplayName("calcul de frais retour - vehicule avec 150km initial - dépassement des kilometrages offerts")
    void testCalculerFraisRetard3() {
        Vehicule vehicule = new Vehicule("IMM", "Blue", new TypeVehicule("Audi"), 100);
        vehicule.setKilometrage(150);
        Location location = new Location(vehicule, new Client() ,LocalDateTime.now().plusDays(1));
        location.getVehicule().setKilometrage(350);
        RetourVehicule retourVehicule = new RetourVehicule(location);
        retourVehicule.calculerFraisRetard();

        // assert
        assertEquals(175.0d, retourVehicule.getFraisRetour());
    }

    /**
     * Method under test: {@link RetourVehicule#calculerFraisRetard()}
     */
    @Test
    @DisplayName("calcul de frais retour avec retard d'une journée")
    void testCalculerFraisRetard4() {
        Vehicule vehicule = new Vehicule("IMM", "Blue", new TypeVehicule("Audi"), 100);
        Location location = new Location(vehicule, new Client(), LocalDateTime.now().plusDays(1));
        location.getVehicule().setKilometrage(75);
        location.setDateFinReelleLocation(location.getDateFinPrevueLocation().plusDays(1));
        RetourVehicule retourVehicule = new RetourVehicule(location);
        retourVehicule.calculerFraisRetard();

        // assert
        assertEquals(125.0d, retourVehicule.getFraisRetour());
    }

    /**
     * Method under test: {@link RetourVehicule#calculerFraisRetard()}
     */
    @Test
    @DisplayName("calcul de frais retour avec retard d'une journée et un dépassement de kilometrage")
    void testCalculerFraisRetard5() {
        Vehicule vehicule = new Vehicule("IMM", "Blue", new TypeVehicule("Audi"), 100);
        Location location = new Location(vehicule, new Client(), LocalDateTime.now().plusDays(1));
        location.getVehicule().setKilometrage(200);
        location.setDateFinReelleLocation(location.getDateFinPrevueLocation().plusDays(1));
        RetourVehicule retourVehicule = new RetourVehicule(location);
        retourVehicule.calculerFraisRetard();

        // assert
        assertEquals(100.0d, retourVehicule.getFraisRetour());
    }

    /**
     * Method under test: {@link RetourVehicule#calculerFraisRetard()}
     */
    @Test
    @DisplayName("calcul de frais retour pour un retour faite dans la journée prévue mais avec un retard de 5 hours")
    void testCalculerFraisRetard6() {
        Vehicule vehicule = new Vehicule("IMM", "Blue", new TypeVehicule("Audi"), 100);
        Location location = new Location(vehicule, new Client(), LocalDateTime.now().plusDays(1));
        location.getVehicule().setKilometrage(75);
        location.setDateFinReelleLocation(location.getDateFinPrevueLocation().plusHours(5));
        RetourVehicule retourVehicule = new RetourVehicule(location);
        retourVehicule.calculerFraisRetard();

        // assert
        assertEquals(125.0d, retourVehicule.getFraisRetour());
    }

    /**
     * Method under test: {@link RetourVehicule#calculerFraisRetard()}
     */
    @Test
    @DisplayName("Happy path - calcul de frais retour pour un retour faite dans la journée prévue et un peu plus tôt que prévue")
    void testCalculerFraisRetard7() {
        Vehicule vehicule = new Vehicule("IMM", "Blue", new TypeVehicule("Audi"), 100);
        Location location = new Location(vehicule, new Client(), LocalDateTime.now().plusDays(1));
        location.getVehicule().setKilometrage(75);
        location.setDateFinReelleLocation(location.getDateFinPrevueLocation().minusHours(5));
        RetourVehicule retourVehicule = new RetourVehicule(location);
        retourVehicule.calculerFraisRetard();

        // assert
        assertEquals(200.0d, retourVehicule.getFraisRetour());
    }

}

