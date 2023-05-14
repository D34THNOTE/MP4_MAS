package models.subset;

import model.subset.Component;
import model.subset.ComponentType;
import model.subset.Motherboard;
import model.unique.Car;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class SubsetTest {

    @Test
    public void TestSubset() {
        assertThrows(IllegalArgumentException.class, () -> new Component("smg", null));
        assertThrows(IllegalArgumentException.class, () -> new Component("", ComponentType.GPU));

        Component cpu1 = new Component("Intel i5 10600", ComponentType.CPU);
        Component cpu2 = new Component("Ryzen 9 3950X", ComponentType.CPU);
        Component gpu1 = new Component("RTX 3060Ti", ComponentType.GPU);
        Component RAM1 = new Component("Corsair Vengeance 16GB", ComponentType.RAM);
        Component drive1 = new Component("Samsung 1TB NVMe 980", ComponentType.DRIVE);

        assertThrows(IllegalArgumentException.class, () -> new Motherboard(""));

        Motherboard motherboard1 = new Motherboard("Asus Z490-P");

        assertNull(cpu1.getMotherboardProcesses());
        motherboard1.addComponent(cpu1);
        assertEquals(motherboard1, cpu1.getMotherboard());
        assertEquals(motherboard1, cpu1.getMotherboardProcesses());

        assertTrue(motherboard1.getComponentList().contains(cpu1));
        assertTrue(motherboard1.getProcessorList().contains(cpu1));

        motherboard1.removeComponent(cpu1);
        assertFalse(motherboard1.getComponentList().contains(cpu1));
        assertFalse(motherboard1.getProcessorList().contains(cpu1));
        assertNull(cpu1.getMotherboard());
        assertNull(cpu1.getMotherboardProcesses());

        cpu1.setMotherboard(motherboard1);
        assertEquals(motherboard1, cpu1.getMotherboard());
        assertEquals(motherboard1, cpu1.getMotherboardProcesses());
        assertTrue(motherboard1.getComponentList().contains(cpu1));
        assertTrue(motherboard1.getProcessorList().contains(cpu1));

        cpu1.setMotherboard(null);
        assertFalse(motherboard1.getComponentList().contains(cpu1));
        assertFalse(motherboard1.getProcessorList().contains(cpu1));
        assertNull(cpu1.getMotherboard());
        assertNull(cpu1.getMotherboardProcesses());

        motherboard1.addComponent(cpu1);
        motherboard1.addComponent(cpu2);
        motherboard1.addComponent(gpu1);
        motherboard1.addComponent(RAM1);
        motherboard1.addComponent(drive1);

        // adding the same object
        assertThrows(IllegalArgumentException.class, () -> motherboard1.addComponent(gpu1));

        assertEquals(motherboard1, cpu2.getMotherboard());
        assertEquals(motherboard1, cpu2.getMotherboardProcesses());
        assertEquals(motherboard1, gpu1.getMotherboard());
        assertEquals(motherboard1, RAM1.getMotherboard());
        assertEquals(motherboard1, drive1.getMotherboard());
    }
}
