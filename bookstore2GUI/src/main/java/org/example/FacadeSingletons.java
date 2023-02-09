package org.example;

public class FacadeSingletons {

    private static final CustomerFacade customerFacade = new CustomerFacade();
    private static final EmployeeFacade employeeFacade = new EmployeeFacade();
    private static final BooksFacade booksFacade = new BooksFacade();

    public static CustomerFacade getCustomerFacade() {
        return customerFacade;
    }

    public static EmployeeFacade getEmployeeFacade() {
        return employeeFacade;
    }

    public static BooksFacade getBooksFacade() {
        return booksFacade;
    }

}
