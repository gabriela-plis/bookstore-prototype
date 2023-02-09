package org.example;

import java.util.List;
import java.util.Objects;

public class BooksFacade {

    private final BooksDAO booksDAO;

    public BooksFacade () {
        this.booksDAO = new BooksDAO();
    }

    public BooksFacade (String DB_URL, String DB_USER, String DB_PASS) {
        this.booksDAO = new BooksDAO(DB_URL, DB_USER, DB_PASS);
    }

    public List<Book> getAvailableBooksToBorrow (int customerID) {
        return booksDAO.getAvailableBooksToBorrow(customerID);
    }

    public List<Book> getAvailableBooksToRemove () {
        return booksDAO.getAvailableBooksToRemove();
    }

    public List<Book> getAvailableBooksToReturn (int customerID) {
        return booksDAO.getCustomersBorrows(customerID);
    }

    public int getBookID (String title) {
        return booksDAO.getBookID(title);
    }

    public int getBookTypeID (String type) {
        return booksDAO.getBookTypeID(type);
    }

    public List<BookType> getBookTypes () {
        return booksDAO.getBookTypes();
    }
}
