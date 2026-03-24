package sys.database;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DatabaseUtilities {

    private enum Status { PENDING, DUE, OVERDUE }

    public static String checkStatus(LocalDate dueDate) {
        String status;
        long difference = ChronoUnit.DAYS.between(LocalDate.now(), dueDate);

        if (difference > 0) {
            status = Status.PENDING.name();
        } else if (difference == 0) {
            status = Status.DUE.name();
        } else {
            status = Status.OVERDUE.name();
        }

        return status;
    }
}
