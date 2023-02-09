package org.example;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {

        CustomerDAO customerDAO = new CustomerDAO();
        System.out.println(customerDAO.getCustomer(2, "susan123"));

        BooksDAO booksDAO = new BooksDAO();
        booksDAO.getCustomersBorrows(2).stream().map(Book::title)
                .forEach(System.out::println);
    }
}
