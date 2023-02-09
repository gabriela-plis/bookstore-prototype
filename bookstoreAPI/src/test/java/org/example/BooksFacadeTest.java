package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BooksFacadeTest {

    BooksFacade booksFacade;

    @BeforeEach
    void initEach () {
        booksFacade = new BooksFacade(DAOConfigTestDB.DB_URL, DAOConfigTestDB.DB_USER, DAOConfigTestDB.DB_PASS);
    }


    @Test
    void getAvailableBooksToBorrow_CustomerWithBorrowedBooks_ListOfBooks () {
        List<Book> expected = List.of(
                new Book (2,"book2", "author2", 2000, true, 5,"Classics"),
                new Book (6,"book6", "author6", 2020, true, 5,"Classics")
        );

        List<Book> result = booksFacade.getAvailableBooksToBorrow(3);

        assertEquals(expected, result);
    }

    @Test
     void getAvailableBooksToBorrow_CustomerWithAllAvailableBooksToBorrow_EmptyListOfBooks () {
        List<Book> expected = new ArrayList<>();

        List<Book> result = booksFacade.getAvailableBooksToBorrow(1);

        assertEquals(expected, result);

    }

    @Test
    void getAvailableBooksToRemove_TwoAvailableBooksToRemove_ListOfBooks () {
        List<Book> expected = List.of (
                new Book (4, "book4", "author4", 2010, false,0, "Fairy tales"),
                new Book (5, "book5", "author5", 2015, false,15, "Adventure stories")
        );

        List<Book> result = booksFacade.getAvailableBooksToRemove();

        assertEquals(expected, result);
    }

    @Test
    void getAvailableBooksToReturn_CustomerWithFourBorrows_ListOfBooks () {
        List<Book> expected = List.of(
                new Book (1,"book1", "author1", 1995, true, 5, "Adventure stories"),
                new Book (2,"book2", "author2", 2000, true, 5,"Classics"),
                new Book (3,"book3", "author3", 2005, true, 0,"Crime"),
                new Book (6,"book6", "author6", 2020, true, 5,"Classics")
        );

        List<Book> result = booksFacade.getAvailableBooksToReturn(1);

        assertEquals(expected, result);
    }

    @Test
    void getAvailableBooksToReturn_CustomerWithNoBorrows_EmptyListOfBooks () {
        List<Book> expected = new ArrayList<>();
        List<Book> result = booksFacade.getAvailableBooksToReturn(2);

        assertEquals(expected, result);
    }

    @Test
    void getBooksTypes_FourBooksTypes_ListOfBookTypes () {
        List<BookType> expected = List.of(
                new BookType("Adventure stories", 1),
                new BookType("Classics", 2),
                new BookType("Crime", 3),
                new BookType("Fairy tales", 4)
        );

        List<BookType> result = booksFacade.getBookTypes();

        assertEquals(expected, result);
    }
}