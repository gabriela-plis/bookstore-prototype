package org.example;

import java.sql.*;
import java.util.*;

import static org.example.DAOConfig.*;

public class BooksDAO {

    private static final String BORROW_QUERY = "UPDATE books SET available_amount = available_amount -1 WHERE id = ? ;";
    private static final String RETURN_QUERY = "UPDATE books SET available_amount = available_amount +1 WHERE id = ? ;";
    private static final String SELECT_AVAILABLE_BOOK_TO_BORROW_QUERY = """
            SELECT DISTINCT books.id, title, author, publish_year, can_be_borrow, available_amount, name AS type FROM books
            LEFT JOIN customers_to_books ON books.id = customers_to_books.book_id JOIN book_types ON books.type_id = book_types.id
            WHERE books.id NOT IN (SELECT book_id FROM customers_to_books WHERE customer_id = ? ) AND can_be_borrow = 'true' AND available_amount > 0;
            """;
    private static final String SELECT_AVAILABLE_BOOK_TO_REMOVE_QUERY = """
            SELECT books.id, title, author, publish_year, can_be_borrow, available_amount, name AS type FROM books
            LEFT JOIN customers_to_books ON books.id = customers_to_books.book_id JOIN book_types ON books.type_id = book_types.id
            WHERE book_id IS NULL;
            """;
    private static final String SELECT_CURRENT_BORROWS_QUERY = """
            SELECT books.id, title, author, publish_year, can_be_borrow, available_amount, name AS type FROM customers_to_books
            JOIN books ON customers_to_books.book_id = books.id JOIN book_types ON books.type_id = book_types.id
            WHERE customer_id = ? ;
            """;
    private static final String REMOVE_BOOK_QUERY = "DELETE FROM books WHERE id = ? ;";
    private static final String ADD_BOOK_QUERY = "INSERT INTO books (title, author, publish_year, can_be_borrow, available_amount, type_id) VALUES ( ? , ? , ? , ? , ? , ? );";
    private static final String SELECT_BOOK_TYPES_QUERY = "SELECT * FROM book_types;";
    private static final String GET_BOOK_ID_QUERY = "SELECT id FROM books WHERE title = ? ";
    private static final String GET_TYPE_ID_QUERY = "SELECT id FROM book_types WHERE name = ? ";

    private final String DB_URL;
    private final String DB_USER;
    private final String DB_PASS;

    BooksDAO () {
        this.DB_URL = DAOConfig.DB_URL;
        this.DB_USER = DAOConfig.DB_USER;
        this.DB_PASS = DAOConfig.DB_PASS;
    }

    //constructor for unit tests
    BooksDAO (String DB_URL, String DB_USER, String DB_PASS) {
        this.DB_URL = DB_URL;
        this.DB_USER = DB_USER;
        this.DB_PASS = DB_PASS;
    }

    public List<Book> getAvailableBooksToBorrow(int customerID) {
        checkIDCorrectness(customerID);

        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(SELECT_AVAILABLE_BOOK_TO_BORROW_QUERY)) {

            st.setInt(1, customerID);

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
        checkIDCorrectness(customerID);

        try (Connection connection = connect();
        PreparedStatement st = connection.prepareStatement(SELECT_CURRENT_BORROWS_QUERY)){

            st.setInt(1, customerID);

            ResultSet rs = st.executeQuery();

            return createListOfBooks(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Book> createListOfBooks (ResultSet rs) {

        try {
            List<Book> books = new ArrayList<>();

            while (rs.next()) {

                Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("publish_year"), rs.getBoolean("can_be_borrow"), rs.getInt("available_amount"), rs.getString("type"));
                books.add(book);
            }

            return books;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
        checkIDCorrectness(bookID);

        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(REMOVE_BOOK_QUERY)) {

            st.setInt(1, bookID);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBook(String title, String author, int publish_year, boolean can_be_borrow, int available_amount, int typeID) {
        checkIDCorrectness(typeID);

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

    public void updateAfterBorrow (int bookID) {
        checkIDCorrectness(bookID);

        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(BORROW_QUERY)) {

            st.setInt(1, bookID);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateAfterReturn (int bookID) {
        checkIDCorrectness(bookID);

        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(RETURN_QUERY)) {

            st.setInt(1, bookID);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getBookID (String title) {
        return getID(title, GET_BOOK_ID_QUERY);
    }

    public int getBookTypeID (String type) {
        return getID(type, GET_TYPE_ID_QUERY);
    }

    private int getID(String strRepresentation, String query) {
        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(query)) {

            st.setString(1, strRepresentation);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                return rs.getInt("id");
            }

        } catch ( SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    private void checkIDCorrectness (int ID) {
        if (ID < 1 ) {
            throw new IllegalArgumentException();
        }
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
