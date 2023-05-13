package model.XOR;

import java.time.LocalDate;

public class Customer {

    private String login, password;

    private MonthlySubscription monthlySubscription;
    private YearlySubscription yearlySubscription;

    public Customer(String login, String password) {
        setLogin(login);
        setPassword(password);
    }

    public MonthlySubscription getMonthlySubscription() {
        return monthlySubscription;
    }

    public void setMonthlySubscription(MonthlySubscription monthlySubscription) {
        if(this.monthlySubscription == null && monthlySubscription != null) {
            checkForActiveSubscriptions();

            // creating new association
            this.monthlySubscription = monthlySubscription;
            if(!monthlySubscription.getCustomer().equals(this)) {
                monthlySubscription.setCustomer(this);
            }

            // removing old association of the other class if existed
            if(this.yearlySubscription != null) {
                YearlySubscription tempYearly = this.yearlySubscription;
                this.yearlySubscription = null;
                tempYearly.setCustomer(null);
            }
        } else if(this.monthlySubscription != null && monthlySubscription == null) {
            if(this.monthlySubscription.getCustomer().equals(this)) {
                this.monthlySubscription.setCustomer(null);
            }
            this.monthlySubscription = null;
        } else if(this.monthlySubscription != null && this.monthlySubscription != monthlySubscription) {
            checkForActiveSubscriptions();

            // removing old association
            if(this.monthlySubscription.getCustomer().equals(this)) {
                this.monthlySubscription.setCustomer(null);
            }
            if(!monthlySubscription.getCustomer().equals(this)) {
                monthlySubscription.setCustomer(this);
            }
            this.monthlySubscription = monthlySubscription;

            // removing the other class if was assigned
            if(this.yearlySubscription != null) {
                YearlySubscription tempYearly = this.yearlySubscription;
                this.yearlySubscription = null;
                tempYearly.setCustomer(null);
            }
        }
    }


    public YearlySubscription getYearlySubscription() {
        return yearlySubscription;
    }

    public void setYearlySubscription(YearlySubscription yearlySubscription) {
        if(this.yearlySubscription == null && yearlySubscription != null) {
            checkForActiveSubscriptions();

            // creating new association
            this.yearlySubscription = yearlySubscription;
            if(!yearlySubscription.getCustomer().equals(this)) {
                yearlySubscription.setCustomer(this);
            }

            // removing old association of the other class if existed
            if(this.monthlySubscription != null) {
                MonthlySubscription tempMonthly = this.monthlySubscription;
                this.monthlySubscription = null;
                tempMonthly.setCustomer(null);
            }
        } else if(this.yearlySubscription != null && yearlySubscription == null) {
            if(this.yearlySubscription.getCustomer().equals(this)) {
                this.yearlySubscription.setCustomer(null);
            }
            this.yearlySubscription = null;
        } else if(this.yearlySubscription != null && this.yearlySubscription != yearlySubscription) {
            checkForActiveSubscriptions();

            // removing old association
            if(this.yearlySubscription.getCustomer().equals(this)) {
                this.yearlySubscription.setCustomer(null);
            }
            if(!yearlySubscription.getCustomer().equals(this)) {
                yearlySubscription.setCustomer(this);
            }
            this.yearlySubscription = yearlySubscription;

            // removing the other class if was assigned
            if(this.monthlySubscription != null) {
                MonthlySubscription tempMonthly = this.monthlySubscription;
                this.monthlySubscription = null;
                tempMonthly.setCustomer(null);
            }
        }
    }








    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if(login == null || login.isBlank()) throw new IllegalArgumentException("Login is required");

        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password == null || password.isBlank()) throw new IllegalArgumentException("Password is required");

        this.password = password;
    }

    private void checkForActiveSubscriptions() {
        if (this.yearlySubscription != null && this.yearlySubscription.getEndDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("This customer already has an active yearly subscription");
        }

        if (this.monthlySubscription != null && this.monthlySubscription.getEndDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("This customer already has an active monthly subscription");
        }
    }
}
