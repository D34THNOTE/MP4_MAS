package model.attribute;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccount {

    private static List<BankAccount> extent = new ArrayList<>();

    private String firstName, lastName, phoneNumber;

    private static Integer minimumAge;

    private double balance;

    private LocalDate dateOfBirth;

    public BankAccount(String firstName, String lastName, String phoneNumber, double balance, LocalDate dateOfBirth) {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setBalance(balance);
        setDateOfBirth(dateOfBirth);

        extent.add(this);
    }

    public static int getMinimumAge() {
        return minimumAge;
    }

    public static void setMinimumAge(Integer minimumAge) {
        if(minimumAge == null) throw new IllegalArgumentException("Minimum age cannot be empty");
        if(minimumAge < 1) throw new IllegalArgumentException("Minimum age cannot be smaller than 1");
        if(BankAccount.minimumAge != null && BankAccount.minimumAge.equals(minimumAge)) return; // if the provided age is the same as the currently set age then we avoid performing unnecessary processing
        if(BankAccount.minimumAge != null && BankAccount.minimumAge < minimumAge) checkNewMinimumAge(minimumAge); // if the minimum age is to be increased we check if
        // all existing accounts can still exist under the new minimum

        BankAccount.minimumAge = minimumAge;
    }



    public static List<BankAccount> getExtent() {
        return Collections.unmodifiableList(extent);
    }

    public static void removeAccountFromExtent(BankAccount bankAccount) {
        if(bankAccount == null) throw new IllegalArgumentException("Please choose an account to remove");
        if(!extent.contains(bankAccount)) throw new IllegalArgumentException("Chosen account doesn't exist in the database");

        extent.remove(bankAccount);
    }



    public int getAge() {
        Period age = Period.between(dateOfBirth, LocalDate.now());
        return age.getYears();
    }





    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName == null || firstName.isBlank()) throw new IllegalArgumentException("First name is required");

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName == null || lastName.isBlank()) throw new IllegalArgumentException("Last name is required");

        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber == null || phoneNumber.isBlank()) throw new IllegalArgumentException("Phone number is required");
        if(!phoneNumber.matches("\\d+")) throw new IllegalArgumentException("A phone number must only consist of numbers");

        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if(balance < 0) throw new IllegalArgumentException("Balance of an account cannot be negative");

        this.balance = balance;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) { // dynamic
        if(dateOfBirth == null) throw new IllegalArgumentException("Date of birth is required");
        Period age = Period.between(dateOfBirth, LocalDate.now());
        if(age.getYears() < minimumAge) throw new IllegalArgumentException("This person's age is below the minimum allowed age");

        this.dateOfBirth = dateOfBirth;
    }





    private static void checkNewMinimumAge(Integer minimumAge) {
        for(BankAccount toCheckAge : extent) {
            if(toCheckAge.getAge() < minimumAge) throw new IllegalArgumentException("At least one account doesn't comply with the new minimum age");
        }
    }
}
