package org.example;
import java.sql.*;
import static org.example.DAOConfig.*;

public class CustomerDAO {

    private static final String USER_EXISTS_QUERY = "SELECT EXISTS (SELECT * FROM customers WHERE id = ? AND password = ?);";
    private static final String SELECT_USER_QUERY = "SELECT * FROM customers WHERE id = ?";
    private static final String BORROW_QUERY = "INSERT INTO customers_to_books (customer_id, book_id) VALUES ( ? , ? );";
    private static final String RETURN_QUERY = "DELETE FROM customers_to_books WHERE customer_id = ? AND book_id = ?;";
    private static final String REGISTER_QUERY = """
                INSERT INTO customers (first_name, last_name, phone, email, password) 
                VALUES ( ? , ? , ? , ? , ?)
                RETURNING id;
            """;

    private int ID;

    public Customer getCustomer (int id, String password) throws SQLException {

        if (existsInDB(id, password)) {

            try (Connection connection = connect();
                 PreparedStatement st = connection.prepareStatement(SELECT_USER_QUERY)) {

                st.setInt(1, id);

                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    return new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"), rs.getString("email"));
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    private boolean existsInDB (int id, String password) {

        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(USER_EXISTS_QUERY)) {

            st.setInt(1, id);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("exists");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;

    }

    public void borrowBook(int customerId, int bookId) {

        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(BORROW_QUERY)) {

            st.setInt(1, customerId);
            st.setInt(2, bookId);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void returnBook(int customerId, int bookId) throws SQLException {
        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(RETURN_QUERY)) {

            st.setInt(1, customerId);
            st.setInt(2, bookId);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void register(String firstName, String lastName, String phone, String email, String password) {

        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(REGISTER_QUERY)) {

            st.setString(1, firstName);
            st.setString(2, lastName);
            st.setString(3, phone);
            st.setString(4, email);
            st.setString(5, password);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                this.ID = rs.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int getID() {
        return this.ID;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
