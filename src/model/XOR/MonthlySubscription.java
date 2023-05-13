package model.XOR;

import java.time.LocalDate;

public class MonthlySubscription {
    private Customer customer;

    private static double monthlyFee_pln;
    private LocalDate startDate, endDate;

    public MonthlySubscription(Customer customer, double monthlyFee_pln) {
        setCustomer(customer);
        setMonthlyFee_pln(monthlyFee_pln);

        this.startDate = LocalDate.now();
        this.endDate = startDate.plusMonths(1);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if(this.customer == null && customer != null) {
            this.customer = customer;
            if(customer.getMonthlySubscription() == null) {
                customer.setMonthlySubscription(this);
            }
            if(!customer.getMonthlySubscription().equals(this)) {
                customer.setMonthlySubscription(this);
            }

        } else if(this.customer != null && customer == null) {
            if(this.customer.getMonthlySubscription() != null && this.customer.getMonthlySubscription().equals(this)) {
                this.customer.setMonthlySubscription(null);
            }
            this.customer = null;
        } else if(this.customer != null && this.customer != customer) {
            if(this.customer.getMonthlySubscription().equals(this)) {
                this.customer.setMonthlySubscription(null);
            }
            if(!customer.getMonthlySubscription().equals(this)) {
                customer.setMonthlySubscription(this);
            }
            this.customer = customer;
        }
    }




    public double getMonthlyFee_pln() {
        return monthlyFee_pln;
    }

    public void setMonthlyFee_pln(double monthlyFee_pln) {
        if(monthlyFee_pln < 0) throw new IllegalArgumentException("The fee cannot be a negative number");

        MonthlySubscription.monthlyFee_pln = monthlyFee_pln;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
