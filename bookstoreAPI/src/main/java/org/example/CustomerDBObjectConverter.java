package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class CustomerDBObjectConverter implements DBObjectConverter<Customer> {

    @Override
    public String parseToFileLine(Customer customer) {
        return customer.read();
    }

    @Override
    public Customer parseFromFileLine(String line) {
        ArrayList<String> separatedData = new ArrayList<>();
        Collections.addAll(separatedData, line.split(","));

        String borrowedBooks = separatedData.get(separatedData.size()-1);
        borrowedBooks = borrowedBooks.substring(1, borrowedBooks.length()-1); // usunięcie zapisu []

        ArrayList<String> separatedBorrowedBooks = new ArrayList<>();
        Collections.addAll(separatedBorrowedBooks, borrowedBooks.split("_") );

        if (separatedBorrowedBooks.size() == 1 && separatedBorrowedBooks.get(0).length() == 0) {
            separatedBorrowedBooks.clear();
        }

        return new Customer(Integer.parseInt(separatedData.get(0)),separatedData.get(1),separatedData.get(2),separatedData.get(3), separatedData.get(4), separatedData.get(5), separatedBorrowedBooks);
    }
}
