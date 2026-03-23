package sys.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class DatabaseHandler {
    private static final String USER_DATA = DatabaseCollection.USER_DATA;

    public static void insertDebt(String surname, String firstName, String middleName, String emailAddress, String contactNumber, String debtAmount, LocalDate startDate, LocalDate dueDate) {
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
        String status = "PENDING";

        String sql = "INSERT INTO customers (Surname, FirstName, MiddleName, EmailAddress, ContactNumber, DebtAmount, StartDate, DueDate, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(USER_DATA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < elements.length; i++) {
                if (elements[i] instanceof String) {
                    stmt.setString(i+1, elements[i].toString());
                } else if (elements[i] instanceof LocalDate) {
                    stmt.setObject(i+1, elements[i]);
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

}
