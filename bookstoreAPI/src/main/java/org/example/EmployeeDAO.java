package org.example;

import java.sql.*;

import static org.example.DAOConfig.*;

public class EmployeeDAO {

    private static final String USER_EXISTS_QUERY = "SELECT EXISTS (SELECT * FROM employees WHERE id = ? AND password = ?);";

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
