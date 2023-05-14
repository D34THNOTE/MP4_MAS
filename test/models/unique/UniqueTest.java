package models.unique;

import model.unique.Car;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class UniqueTest {

    @Test
    public void TestUniqueConstraint() {
        Car car1 = new Car("Nissan", "Idk", "test");

        assertThrows(IllegalArgumentException.class, () -> new Car("", "Idk", "test"));
        assertThrows(IllegalArgumentException.class, () -> new Car("Nissan", "", "test"));
        assertThrows(IllegalArgumentException.class, () -> new Car("Nissan", "Idk", ""));
        assertThrows(IllegalArgumentException.class, () -> new Car("Nissan", "Idk", "test")); // license occupied

        Car car2 = new Car("Nissan", "Idk", "testing");
        Car car3 = new Car("Nissan", "Idk", "tester");

        car2.setLicensePlate("newLicense");
        assertEquals("newLicense", car2.getLicensePlate());
        assertThrows(IllegalArgumentException.class, () -> car3.setLicensePlate("tester"));
        car3.setLicensePlate("testing"); // previously car2's license but now free

        assertEquals("testing", car3.getLicensePlate());
        assertThrows(IllegalArgumentException.class, () -> car3.setLicensePlate("test"));
        Car.removeCarFromExtent(car1);
        car3.setLicensePlate("test");
        assertEquals("test", car3.getLicensePlate());
    }

}
