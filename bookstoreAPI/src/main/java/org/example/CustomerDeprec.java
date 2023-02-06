package org.example;

import java.util.ArrayList;
import java.util.Objects;
@Deprecated

public class CustomerDeprec extends Person {
//    private String email;
//    private final BorrowedBooks borrowedBooks; // tylko tytuły
//
//    public CustomerDeprec(int ID, String password, String firstname, String lastname, String phone, String email) {
//        super(firstname, lastname, phone, password, ID);
//        this.email = email;
//        this.borrowedBooks = new BorrowedBooks();
//    }
//
//    public CustomerDeprec(int ID, String password, String firstname, String lastname, String phone, String email, ArrayList<String> borrowedBooks) {
//        super(firstname, lastname, phone, password, ID);
//        this.email = email;
//        this.borrowedBooks = new BorrowedBooks(borrowedBooks);
//    }
//
//    public String read() {
//        String connector = ",";
//
//        String borrowedBooksAsText = "";
//
//        if (!borrowedBooks.isEmpty()) {
//            StringBuilder sB = new StringBuilder(borrowedBooksAsText);
//
//            for (String book : borrowedBooks.getAllTitles()) {
//                String borrowedBooksConnector = "_";
//                if (sB.length() == 0) {
//                    sB.append(book);
//                } else {
//                    sB.append(borrowedBooksConnector).append(book);
//                }
//            }
//
//            borrowedBooksAsText = sB.toString();
//        }
//
//        return super.getSensitiveData().getID() + connector + super.getSensitiveData().getPassword() + connector + super.getFirstName() + connector + super.getLastName() + connector + super.getPhone() + connector + email + connector + "[" + borrowedBooksAsText + "]";
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public BorrowedBooks getBorrowedBooks() {
//        return borrowedBooks;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CustomerDeprec customer = (CustomerDeprec) o;
//        return Objects.equals(email, customer.email) && Objects.equals(borrowedBooks, customer.borrowedBooks);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(email, borrowedBooks);
//    }
//
//    @Override
//    public int getID() {
//        return getSensitiveData().getID();
//    }
}
