package org.example;

import java.util.List;
import java.util.Objects;

public class BooksFacade {

    private static final String BOOK_DB_URL = Paths.BOOKS.getPath();
    private final Database<Book> database;



    public BooksFacade () {
        this.database = new Database<>(BOOK_DB_URL, new BookDBObjectConverter());
        this.database.load();
    }

    public List<String> getAvailableBooksToBorrow (CustomerFacade facade) {

        return database.getAll().stream()
                .filter(book -> book.getAvailableAmount() > 0 && book.isCanBeBorrow())
                .map(Book::getTitle)
                .filter( title -> !facade.getPerson().getBorrowedBooks().isContains(title))
                .sorted()
                .toList();

    }

    public List<String> getAvailableBooksToRemove () {
        return database.getAll().stream()
                .filter(book -> book.getBorrowedAmount() == 0)
                .map(Book::getTitle)
                .toList();
    }

    public void updateBookAfterBorrow (String title) {
        int key = getKeyToBooksDatabase(database.getAll(), title);

        if (key == -1) {
            throw new IllegalArgumentException();
        }

        Book book = database.get(key);
        book.decrementAvailableAmount();
        book.incrementBorrowedAmount();

        database.update();
    }

    public void updateBookAfterReturn (String title) {
        int key = getKeyToBooksDatabase(database.getAll(), title);

        if (key == -1) {
            throw new IllegalArgumentException();
        }

        Book book = database.get(key);
        book.incrementAvailableAmount();
        book.decrementBorrowedAmount();

        database.update();
    }

    public void remove (String title) {
        int key = getKeyToBooksDatabase(database.getAll(), title);

        if (key == -1) {
            throw new IllegalArgumentException();
        }

        database.delete(key);
        database.update();
    }

    public void add (String title, String author, String series, String publishYear, boolean canBeBorrow, int amount) {
        database.add(new Book(title, author, series, publishYear, amount, canBeBorrow, database.getAssignID() ));
        database.update();
    }

    private int getKeyToBooksDatabase (List<Book> books, String title) {
        return books.stream()
                .filter(book -> Objects.equals(book.getTitle(), title))
                .map(Book::getID)
                .findAny()
                .orElse(-1);
    }
}
