package agence.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class RetourVehiculeTest {
    /**
     * Method under test: {@link RetourVehicule#getFraisRetour(Location)}
     */
    @Test
    void testGetFraisRetour() {
        RetourVehicule retourVehicule = new RetourVehicule();
        assertEquals(0.0d, retourVehicule.getFraisRetour(new Location()));
    }

}

