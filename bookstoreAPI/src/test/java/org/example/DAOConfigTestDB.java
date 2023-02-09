package org.example;

public class DAOConfigTestDB {

    public static final String DB_URL;
    public static final String DB_USER = "postgres";
    public static final String DB_PASS = "percia123";

    static {
        DB_URL = "jdbc:postgresql://localhost:5432/testDB";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load postgres driver");
        }
    }
}
