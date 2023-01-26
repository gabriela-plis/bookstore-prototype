package org.example;

import java.util.List;

public class CustomerFacade {

    private static final String CUSTOMER_DB_URL = Paths.CUSTOMERS.getPath();
    Database<Customer> database;
    Customer person;

    public CustomerFacade() {
        this.database = new Database<>(CUSTOMER_DB_URL, new CustomerDBObjectConverter());
        this.database.load();
        this.person = null;
    }

    public boolean isLogIn(int ID, String password) {

        if (database.isContains(ID) && database.get(ID).getSensitiveData().getPassword().equals(password)) {
            person = database.get(ID);
            return true;
        }

        return false;
    }

    public void register (String firstName, String lastName, String phone, String password, String email) {

        Customer newCustomer = new Customer(database.getAssignID(), password, firstName, lastName, phone, email);
        database.add(newCustomer);
        database.update();

        person = newCustomer;

    }

    public boolean wasRegisterSuccessful () {
        return !person.equals(null);
    }


    public void borrowBook (String title) {
        person.getBorrowedBooks().add(title);
        database.update();


    }

    public List<String> getBorrowedBooksTitles () {
        return person.getBorrowedBooks().getAllTitles();
    }

    public void returnBook (String title) {
        person.getBorrowedBooks().remove(title);
        database.update();
    }

    public Customer getPerson () {
        return person;
    }
}
