package org.example;

import java.sql.*;

import static org.example.DAOConfig.*;

public class EmployeeDAO {

    private static final String USER_EXISTS_QUERY = "SELECT EXISTS (SELECT * FROM employees WHERE id = ? AND password = ?);";

    private final String DB_URL;
    private final String DB_USER;
    private final String DB_PASS;

    EmployeeDAO () {
        this.DB_URL = DAOConfig.DB_URL;
        this.DB_USER = DAOConfig.DB_USER;
        this.DB_PASS = DAOConfig.DB_PASS;
    }

    //constructor for unit tests
    EmployeeDAO (String DB_URL, String DB_USER, String DB_PASS) {
        this.DB_URL = DB_URL;
        this.DB_USER = DB_USER;
        this.DB_PASS = DB_PASS;
    }

    public boolean isLogIn(int ID, String password) {

        try (Connection connection = connect();
             PreparedStatement st = connection.prepareStatement(USER_EXISTS_QUERY)) {

            st.setInt(1, ID);
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

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
