package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFacadeTest {
    CustomerFacade customerFacade;

    @BeforeEach
    void initEach () {
        customerFacade = new CustomerFacade(DAOConfigTestDB.DB_URL, DAOConfigTestDB.DB_USER, DAOConfigTestDB.DB_PASS);
    }

    @Test
    void getBorrowedBooks_CustomerWithFourBorrows_ListOfBooks () {
        List<Book> expected = List.of(
                new Book (1,"book1", "author1", 1995, true, 5, "Adventure stories"),
                new Book (2,"book2", "author2", 2000, true, 5,"Classics"),
                new Book (3,"book3", "author3", 2005, true, 0,"Crime"),
                new Book (6,"book6", "author6", 2020, true, 5,"Classics")
        );

        List<Book> result = customerFacade.getBorrowedBooks(1);

        assertEquals(expected, result);
    }

}