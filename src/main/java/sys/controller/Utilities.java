package sys.controller;

import javafx.scene.control.Alert;

import java.time.LocalDate;

public class Utilities {

    public static boolean validateTextFields(String surname, String firstName, String emailAddress, String contact, LocalDate startDate, LocalDate dueDate) {
        StringBuilder errors = new StringBuilder();
        boolean isValid = true;

        // region Hidden Section: Surname, First name, and Email address validator
        if (surname.isEmpty()) errors.append("- Surname is required.\n");
        if (firstName.isEmpty()) errors.append("- First Name is required.\n");
        if (!isValidEmail(emailAddress)) errors.append("- Invalid Email address.\n");
        // endregion

        // region Hidden Section: Dates validator
        if (startDate == null) errors.append("- Start Date is required.\n");
        if (dueDate == null) errors.append("- Due Date is required.\n");
        if (startDate != null && dueDate != null && dueDate.isBefore(startDate)) {
            errors.append("- Due Date cannot be before Start Date.\n");
        }
        // endregion

        // region Hidden Section: Contact Number validator
        String contactRegex = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
        if (contact.isEmpty()) {
            errors.append("- Contact number is required.\n");
        } else if (!contact.matches(contactRegex)) {
            errors.append("- Invalid Contact Number (use digits, spaces, or +).\n");
        }
        // endregion

        if (!errors.isEmpty()) {
            showErrorAlert(errors.toString());
            isValid = false;
        }

        return isValid;
    }

    public static void showErrorAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("Please fix the following:");
        alert.setContentText(content);
        alert.showAndWait();
    }

    private static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
