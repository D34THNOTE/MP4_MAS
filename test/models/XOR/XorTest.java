package models.XOR;

import model.XOR.Customer;
import model.XOR.MonthlySubscription;
import model.XOR.YearlySubscription;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class XorTest {

    @Test
    public void TestXorConstraint() {
        assertThrows(IllegalArgumentException.class, () -> new Customer("password", ""));
        assertThrows(IllegalArgumentException.class, () -> new Customer("", "32434"));

        Customer customer1 = new Customer("password", "incorrect");
        Customer customer2 = new Customer("pswrd", "corrected");

        assertThrows(IllegalArgumentException.class, () -> new MonthlySubscription(null, -1.00));
        assertThrows(IllegalArgumentException.class, () -> new MonthlySubscription(customer1, -1.00));

        assertNull(customer1.getMonthlySubscription());
        assertNull(customer1.getYearlySubscription());
        assertNull(customer2.getMonthlySubscription());
        assertNull(customer2.getYearlySubscription());

        MonthlySubscription monthlySubscription1 = new MonthlySubscription(customer1, 30.00);
        assertEquals(monthlySubscription1, customer1.getMonthlySubscription());
        assertEquals(customer1, monthlySubscription1.getCustomer());
        assertNull(customer1.getYearlySubscription());

        // setting null using subscription's setter
        monthlySubscription1.setCustomer(null);
        assertNull(customer1.getYearlySubscription());
        assertNull(monthlySubscription1.getCustomer());

        // setting monthly using customer's setter
        customer1.setMonthlySubscription(monthlySubscription1);
        assertEquals(monthlySubscription1, customer1.getMonthlySubscription());
        assertEquals(customer1, monthlySubscription1.getCustomer());

        // reassigning customer
        customer2.setMonthlySubscription(monthlySubscription1);
        assertEquals(monthlySubscription1, customer2.getMonthlySubscription());
        assertEquals(customer2, monthlySubscription1.getCustomer());
        assertNull(customer1.getYearlySubscription());
        assertNull(monthlySubscription1.getCustomer());

        customer1.setMonthlySubscription(monthlySubscription1);

        // throws error because there's an active subscription
        assertThrows(IllegalArgumentException.class, () -> customer1.setYearlySubscription(new YearlySubscription(customer1, 70.0)));

        YearlySubscription yearlySubscription = new YearlySubscription(customer2, 90.0);

        // throws error because there's an active subscription
        assertThrows(IllegalArgumentException.class, () -> customer1.setYearlySubscription(yearlySubscription));
    }

}
