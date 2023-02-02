package org.example;

import java.sql.*;

public class DAOConfig {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/bookstore";
    public static final String DB_USER = "postgres";
    public static final String DB_PASS = "percia123";

    public Connection connect() {

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connect!");

            ResultSet rs = connection.createStatement().executeQuery("SELECT current_date as today");
            while(rs.next()) {
                System.out.println(rs.getDate("today"));
            }

            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
