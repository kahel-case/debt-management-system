package sys.database;

import java.time.LocalDate;

public class DatabaseHandler {
    public static void insertDebt(String surname, String firstName, String middleName, String emailAddress, String contactNumber, String debtAmount, LocalDate startDate, LocalDate dueDate) {
        System.out.println(surname);
        System.out.println(firstName);
        System.out.println(middleName);
        System.out.println(emailAddress);
        System.out.println(contactNumber);
        System.out.println(debtAmount);
        System.out.println(startDate);
        System.out.println(dueDate);
    }
}
