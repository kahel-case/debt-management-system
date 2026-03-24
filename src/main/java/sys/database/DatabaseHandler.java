package sys.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sys.classes.Customer;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DatabaseHandler {
    // TABLE NAME:  customers
    // ATTRIBUTES:  CustomerID, Surname, FirstName, MiddleName, EmailAddress, ContactNumber, DebtAmount, StartDate, DueDate, Status

    // TABLE --+--
    // ATTRIBUTES:  CustomerID,     Surname,    FirstName,  MiddleName, EmailAddress,   ContactNumber,  DebtAmount, StartDate,  DueDate,    Status
    // TYPES:       INTEGER,        TEXT,       TEXT,       TEXT,       VARCHAR(255),   TEXT,           REAL,       DATE,       DATE,       TEXT

    private static final String USER_DATA = DatabaseCollection.USER_DATA;

    public static void insertDebt(String surname, String firstName, String middleName, String emailAddress, String contactNumber, float debtAmount, LocalDate startDate, LocalDate dueDate) {
        Object[] elements = new Object[]{
                surname,
                firstName,
                middleName,
                emailAddress,
                contactNumber,
                debtAmount,
                startDate,
                dueDate
        };
        String status = DatabaseUtilities.checkStatus(dueDate);

        String sql = "INSERT INTO customers (Surname, FirstName, MiddleName, EmailAddress, ContactNumber, DebtAmount, StartDate, DueDate, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(USER_DATA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < elements.length; i++) {
                if (elements[i] instanceof String) {
                    stmt.setString(i+1, elements[i].toString());
                } else if (elements[i] instanceof LocalDate) {
                    stmt.setObject(i+1, elements[i]);
                } else {
                    stmt.setString(i+1, elements[i].toString());
                }
            }
            stmt.setString(elements.length+1, status);
            stmt.executeUpdate();
            stmt.close();

            System.out.println("Insertion successful!");
        } catch (Exception e) {
            System.out.println("Debt Insertion Error: " + e.getMessage());
        }
    }

    @SuppressWarnings({"ClassEscapesDefinedScope", "CallToPrintStackTrace"})
    public static ObservableList<Customer> fetchAll() {
        ObservableList<Customer> list = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(USER_DATA);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {

            while (rs.next()) {
                int customerID = rs.getInt("CustomerID");
                String surname = rs.getString("Surname");
                String firstName = rs.getString("FirstName");
                String middleName = rs.getString("MiddleName");
                String emailAddress = rs.getString("EmailAddress");
                String contactNumber = rs.getString("ContactNumber");
                float debtAmount = rs.getFloat("DebtAmount");
                String startDate = rs.getString("StartDate");
                String dueDate = rs.getString("dueDate");
                String status = rs.getString("Status");

                int daysLeft = (int) ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.parse(dueDate));

                Customer customer = new Customer(customerID, surname, firstName, middleName, emailAddress, contactNumber, debtAmount, startDate, dueDate, status, daysLeft);
                list.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
