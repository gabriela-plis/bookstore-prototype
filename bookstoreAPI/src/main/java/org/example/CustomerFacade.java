package org.example;

import java.util.List;

public class CustomerFacade {

    private final CustomerDAO customerDAO;
    private final BooksDAO booksDAO;

    public CustomerFacade () {
        this.customerDAO = new CustomerDAO();
        this.booksDAO = new BooksDAO();
    }

    public CustomerFacade (String DB_URL, String DB_USER, String DB_PASS) {
        this.customerDAO = new CustomerDAO(DB_URL, DB_USER, DB_PASS);
        this.booksDAO = new BooksDAO(DB_URL, DB_USER, DB_PASS);
    }

    public Customer getCustomer (int id, String password) {
        return customerDAO.getCustomer(id, password);
    }


    public int register (String firstName, String lastName, String phone, String password, String email) {
        return customerDAO.register(firstName, lastName, phone, email, password);
    }

    public void borrowBook (int customerID, int bookID) {
        customerDAO.borrowBook(customerID, bookID);
        booksDAO.updateAfterBorrow(bookID);
    }

    public List<Book> getBorrowedBooks (int customerID) {
        return booksDAO.getCustomersBorrows(customerID);
    }

    public void returnBook (int customerID, int bookID) {
        customerDAO.returnBook(customerID, bookID);
        booksDAO.updateAfterReturn(bookID);
    }

}
