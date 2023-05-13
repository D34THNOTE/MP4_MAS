package model.XOR;

import java.time.LocalDate;

public class YearlySubscription {
    private Customer customer;

    private static double yearlyFee_pln;
    private LocalDate startDate, endDate;

    public YearlySubscription(Customer customer, double yearlyFee_pln) {
        setCustomer(customer);
        setYearlyFee_pln(yearlyFee_pln);

        this.startDate = LocalDate.now();
        this.endDate = startDate.plusYears(1);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if(this.customer == null && customer != null) {
            this.customer = customer;
            if(customer.getYearlySubscription() == null) {
                customer.setYearlySubscription(this);
            }
            if(!customer.getYearlySubscription().equals(this)) {
                customer.setYearlySubscription(this);
            }

        } else if(this.customer != null && customer == null) {
            if(this.customer.getYearlySubscription() != null && this.customer.getYearlySubscription().equals(this)) {
                this.customer.setYearlySubscription(null);
            }
            this.customer = null;
        } else if(this.customer != null && this.customer != customer) {
            if(this.customer.getYearlySubscription().equals(this)) {
                this.customer.setYearlySubscription(null);
            }
            if(!customer.getYearlySubscription().equals(this)) {
                customer.setYearlySubscription(this);
            }
            this.customer = customer;
        }
    }






    public double getYearlyFee_pln() {
        return yearlyFee_pln;
    }

    public void setYearlyFee_pln(double yearlyFee_pln) {
        if(yearlyFee_pln < 0) throw new IllegalArgumentException("The fee cannot be a negative number");

        YearlySubscription.yearlyFee_pln = yearlyFee_pln;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
