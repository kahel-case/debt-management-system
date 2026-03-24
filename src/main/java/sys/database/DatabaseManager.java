package sys.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String USER_DATA = DatabaseCollection.USER_DATA;

    // TABLE NAME:  customers
    // ATTRIBUTES:  CustomerID,     Surname,    FirstName,  MiddleName, EmailAddress,   ContactNumber,  DebtAmount, StartDate,  DueDate,     Status
    // TYPES:       INTEGER,        TEXT,       TEXT,       TEXT,       VARCHAR(255),   TEXT,           REAL,       DATE,       DATE,       TEXT
    public static void createTable() {
        try (Connection conn = DriverManager.getConnection(USER_DATA)) {
            if (conn != null) {
                System.out.println("Connected to SQLite database!");
            }
        } catch (SQLException e) {
            System.out.println("Cannot create database.");
        }

        try (Connection conn = DriverManager.getConnection(USER_DATA);
            Statement stmt = conn.createStatement()) {
            String sql = """
                        CREATE TABLE IF NOT EXISTS customers (
                            CustomerID INTEGER PRIMARY KEY,
                            Surname TEXT NOT NULL,
                            FirstName TEXT NOT NULL,
                            MiddleName TEXT NOT NULL,
                            EmailAddress VARCHAR(255) NOT NULL UNIQUE,
                            ContactNumber TEXT NOT NULL,
                            DebtAmount REAL DEFAULT 0.0,
                            StartDate DATE,
                            DueDate DATE,
                            Status TEXT NOT NULL
                        );
                    """;
            stmt.execute(sql);
            stmt.close();
            System.out.println("Table created or already exists.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
