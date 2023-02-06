package org.example;

public class UserSingletons {

    private static Customer customer;
    private static Employee employee;

    public static void setCustomer (Customer customer) {
        if (UserSingletons.customer == null) {
            UserSingletons.customer = customer;
            employee = null;
        }
    }

    public static void setEmployee (Employee employee) {
        if (UserSingletons.employee == null) {
            UserSingletons.employee = employee;
            customer = null;
        }
    }

    public static Customer getCustomer() {
        return customer;
    }

    public static Employee getEmployee() {
        return employee;
    }
}
