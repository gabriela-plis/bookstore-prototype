package org.example;

import java.sql.*;
import java.util.*;

import static org.example.DAOConfig.*;

public class BooksDAO {

    private static final String BORROW_QUERY = "UPDATE books SET available_amount = available_amount -1 WHERE id = ? ;";
    private static final String RETURN_QUERY = "UPDATE books SET available_amount = available_amount +1 WHERE id = ? ;";
    private static final String SELECT_AVAILABLE_BOOK_TO_BORROW_QUERY = """
            SELECT books.id, title, author, publish_year, can_be_borrow, available_amount, name AS type FROM books
            LEFT JOIN customers_to_books ON books.id = customers_to_books.book_id JOIN book_types ON books.type_id = book_types.id
            WHERE books.id NOT IN (SELECT book_id FROM customers_to_books WHERE customer_id = ? ) AND can_be_borrow = 'true' AND available_amount > 0;
            """;
    private static final String SELECT_AVAILABLE_BOOK_TO_REMOVE_QUERY = """
            SELECT books.id, title, author, publish_year, can_be_borrow, available_amount, name AS type FROM books
            LEFT JOIN customers_to_books ON books.id = customers_to_books.book_id JOIN book_types ON books.type_id = book_types.id
            WHERE can_be_borrow = 'true' AND available_amount > 0 AND book_id IS NULL;
            """;
    private static final String SELECT_CURRENT_BORROWS_QUERY = """
            SELECT books.id, title, author, publish_year, can_be_borrow, available_amount, name AS type FROM customers_to_books
            JOIN books ON customers_to_books.book_id = books.id JOIN book_types ON books.type_id = book_types.id
            WHERE customer_id = ? ;
            """;
    private static final String REMOVE_BOOK_QUERY = "DELETE FROM books WHERE id = ? ;";
    private static final String ADD_BOOK_QUERY = "INSERT INTO books (title, author, publish_year, can_be_borrow, available_amount, type_id) VALUES ( ? , ? , ? , ? , ? , ? );";
    private static final String SELECT_BOOK_TYPES_QUERY = "SELECT * FROM book_types;";


    public List<Book> getAvailableBooksToBorrow() {

        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(SELECT_AVAILABLE_BOOK_TO_BORROW_QUERY)) {

            ResultSet rs = st.executeQuery();

            return createListOfBooks(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Book> getAvailableBooksToRemove() {

        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(SELECT_AVAILABLE_BOOK_TO_REMOVE_QUERY)) {

            ResultSet rs = st.executeQuery();

            return createListOfBooks(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Book> getCustomersBorrows(int customerID) {
        try (Connection connection = connect();
        PreparedStatement st = connection.prepareStatement(SELECT_CURRENT_BORROWS_QUERY)){

            st.setInt(1, customerID);

            ResultSet rs = st.executeQuery();

            return createListOfBooks(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Book> createListOfBooks (ResultSet rs) throws SQLException {

        List<Book> books = new ArrayList<>();

        while (rs.next()) {
            Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("publish_year"), rs.getBoolean("can_be_borrow"), rs.getInt("available_amount"), rs.getString("type"));
            books.add(book);
        }

        return books;
    }

    public List<BookType> getBookTypes() {

        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(SELECT_BOOK_TYPES_QUERY)) {

            ResultSet rs = st.executeQuery();

            List<BookType> types = new ArrayList<>();
            while (rs.next()) {
                BookType bookType = new BookType(rs.getString("name"), rs.getInt("id"));
                types.add(bookType);
            }

            return types;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeBook(int bookID) {

        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(REMOVE_BOOK_QUERY)) {

            st.setInt(1, bookID);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBook(String title, String author, int publish_year, boolean can_be_borrow, int available_amount, int typeID) {

        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(ADD_BOOK_QUERY)) {

            st.setString(1, title);
            st.setString(2, author);
            st.setInt(3, publish_year);
            st.setBoolean(4, can_be_borrow);
            st.setInt(5, available_amount);
            st.setInt(6, typeID);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
