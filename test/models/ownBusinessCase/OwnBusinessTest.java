package models.ownBusinessCase;

import model.ownBusinessCase.GasCan;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OwnBusinessTest {

    @Test
    public void TestOwnBusinessConstraint() {
        assertThrows(IllegalArgumentException.class, () -> new GasCan(-1, 30));
        assertThrows(IllegalArgumentException.class, () -> new GasCan(1, -30));
        assertThrows(IllegalArgumentException.class, () -> new GasCan(31, 30));

        GasCan gasCan1 = new GasCan(30, 30);

        assertThrows(IllegalArgumentException.class, () -> gasCan1.setCurrentGas_liters(31));

        gasCan1.setMaxGas_liters(25);
        assertEquals(25, gasCan1.getCurrentGas_liters());
        assertEquals(25, gasCan1.getMaxGas_liters());

        assertThrows(IllegalArgumentException.class, () -> gasCan1.setCurrentGas_liters(-1));
    }
}
