package org.example;

import java.util.List;
import java.util.Objects;

public class BooksFacade {

    private final BooksDAO booksDAO;

    public BooksFacade () {
        this.booksDAO = new BooksDAO();
    }

    public List<Book> getAvailableBooksToBorrow (CustomerFacade facade) {
        return booksDAO.getAvailableBooksToBorrow();
    }

    public List<Book> getAvailableBooksToRemove () {
        return booksDAO.getAvailableBooksToRemove();
    }

    public List<Book> getAvailableBooksToReturn (int customerID) {
        return booksDAO.getCustomersBorrows(customerID);
    }
}
