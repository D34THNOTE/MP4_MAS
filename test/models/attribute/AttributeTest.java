package models.attribute;

import model.attribute.BankAccount;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class AttributeTest {


    @Test
    public void TestAttributeConstraints() {
        // this throws exception because minimumAge isn't set
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("Henry", "Cavil", "374969385", 0.00,
                LocalDate.now().minusYears(23)));

        BankAccount.setMinimumAge(18);

        assertThrows(IllegalArgumentException.class, () -> new BankAccount("", "Cavil", "374969385", 0.00,
                LocalDate.now().minusYears(23)));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("Henry", "", "374969385", 0.00,
                LocalDate.now().minusYears(23)));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("Henry", "Cavil", "", 0.00,
                LocalDate.now().minusYears(23)));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("Henry", "Cavil", "374969385", -1.00,
                LocalDate.now().minusYears(23)));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("Henry", "Cavil", "374969385", 0.00,
                LocalDate.now().minusYears(17)));

        BankAccount bankAccount1 = new BankAccount("Henry", "Cavil", "374969385", 0.00,
                LocalDate.now().minusYears(18));

        BankAccount bankAccount2 = new BankAccount("Henry", "Cavil", "374969385", 0.00,
                LocalDate.now().minusYears(20));

        BankAccount bankAccount3 = new BankAccount("Henry", "Cavil", "374969385", 0.00,
                LocalDate.now().minusYears(19));

        assertThrows(IllegalArgumentException.class, () -> BankAccount.setMinimumAge(19));

        BankAccount.removeAccountFromExtent(bankAccount1);
        BankAccount.setMinimumAge(19);
        assertEquals(19, BankAccount.getMinimumAge());
    }
}
