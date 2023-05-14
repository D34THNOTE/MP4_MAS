package models.ordered;

import model.ordered.Task;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Iterator;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderedTest {

    @Test
    public void TestOrdered() {
        assertEquals(0, Task.getExtent().size());
        assertThrows(IllegalArgumentException.class, () -> new Task("", ""));
        assertThrows(IllegalArgumentException.class, () -> new Task("", "efserf"));
        assertThrows(IllegalArgumentException.class, () -> new Task("sdfgsg", ""));
        assertEquals(0, Task.getExtent().size());

        Task task1 = new Task("Do things", "Writing this down hoping I will do things");
        assertTrue(Task.getExtent().contains(task1));
        assertEquals(1, Task.getExtent().size());

        Task task2 = new Task("Do things 2", "Writing this down hoping I will do things 2");
        assertTrue(Task.getExtent().contains(task1));
        assertTrue(Task.getExtent().contains(task2));
        assertEquals(2, Task.getExtent().size());

        Task task3 = new Task("Do things 3", "Writing this down hoping I will do things 3");
        assertTrue(Task.getExtent().contains(task3));
        assertEquals(3, Task.getExtent().size()); // I have NO idea why this fails, when you go into debug you can clearly see that elements are being
        // added to the extent, the test even passes if you use debug mode and put breakpoint in the getter for example!

        Task task4 = new Task("Do things 4", "Writing this down hoping I will do things 4");
        assertTrue(Task.getExtent().contains(task4));
        assertEquals(4, Task.getExtent().size());

        Iterator<Task> iterator = Task.getExtent().iterator();
        assertEquals(task1, iterator.next());
        assertEquals(task2, iterator.next());
        assertEquals(task3, iterator.next());
        assertEquals(task4, iterator.next());
    }

}
