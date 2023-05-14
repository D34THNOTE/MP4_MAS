package models.bagHistory;

import model.bagHistory.Course;
import model.bagHistory.Enrollment;
import model.bagHistory.Student;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class BagHistoryTest {

    @Test
    public void TestBagHistory() {
        Student student1 = new Student("Mark", "Twain");
        Student student2 = new Student("Zerk", "Twerk");
        Student student3 = new Student("Zuni", "Tuni");

        Course course1 = new Course("Math");
        Course course2 = new Course("English");
        Course course3 = new Course("Polish");

        // testing setting end date before start date
        assertThrows(IllegalArgumentException.class, () -> new Enrollment(student1, course1, LocalDate.of(2022, 6, 1),
                LocalDate.of(2022, 5, 1)));

        assertEquals(0, Enrollment.getExtent().size());
        Enrollment enrollment1 = new Enrollment(student1, course1, LocalDate.of(2022, 10, 1), LocalDate.of(2023, 2, 1));

        // testing creating an enrollment which collides with an already existing one
        assertThrows(IllegalArgumentException.class, () -> new Enrollment(student1, course1, LocalDate.of(2023, 1, 15),
                LocalDate.of(2023, 5, 1)));

        Enrollment enrollment2 = new Enrollment(student1, course1, LocalDate.of(2023, 3, 1),
                LocalDate.of(2023, 7, 1));

        Enrollment enrollment3 = new Enrollment(student1, course1, LocalDate.of(2022, 3, 1),
                LocalDate.of(2022, 7, 1));

        assertEquals(3, Enrollment.getExtent().size());

        enrollment3.remove();

        Enrollment enrollment4 = new Enrollment(student1, course1, LocalDate.of(2022, 3, 1),
                LocalDate.of(2022, 7, 1));

        assertEquals(3, Enrollment.getExtent().size());
        enrollment4.remove();

        assertEquals(2, Enrollment.getExtent().size());

        Enrollment.addEnrollment(enrollment3);
        assertEquals(3, Enrollment.getExtent().size());
    }

}
