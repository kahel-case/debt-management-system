package sys.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static sys.database.DatabaseHandler.*;

public class MainController implements Initializable {

    // PANELS
    @FXML private VBox mainPanel;
    @FXML private VBox createPanel;
    @FXML private VBox readPanel;
    private VBox[] panels;

    // CREATE PANEL
    @FXML private DatePicker debtStartDate;
    @FXML private DatePicker debtDueDate;

    @FXML private TextField textField_surname;
    @FXML private TextField textField_firstName;
    @FXML private TextField textField_middleName;
    @FXML private TextField textField_emailAddress;
    @FXML private TextField textField_contactNumber;
    @FXML private TextField textField_debtAmount;
    private TextField[] createDebtTextFields;

    // INITIALIZING VARIABLES
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panels = new VBox[]{mainPanel, createPanel, readPanel};
        createDebtTextFields = new TextField[]{textField_surname, textField_firstName, textField_middleName, textField_emailAddress, textField_contactNumber, textField_debtAmount};

        PageManager.switchPage(panels, mainPanel);
    }

    // CREATE NEW DEBT ENTRY
    @FXML
    protected void onCreateDebt() {
        String surname = textField_surname.getText().trim();
        String firstName = textField_firstName.getText().trim();
        String middleName = textField_middleName.getText().trim();
        String emailAddress = textField_emailAddress.getText().trim();
        String contactNumber = textField_contactNumber.getText().trim();
        String debtAmount = textField_debtAmount.getText().trim();
        LocalDate startDate = debtStartDate.getValue();
        LocalDate dueDate = debtDueDate.getValue();


        insertDebt(surname, firstName, middleName, emailAddress, contactNumber, debtAmount, startDate, dueDate);
        for (TextField textField : createDebtTextFields) {
            textField.setText("");
        }

        JOptionPane.showMessageDialog(null, "New debt entry created successfully~");
    }

    /*
    *   PAGE MANAGER
    * */

    // CREATE PANEL
    @FXML
    protected void onCreatePanel() {
        PageManager.switchPage(panels, createPanel);

        debtStartDate.setValue(LocalDate.now());
        debtDueDate.setValue(LocalDate.now().plusWeeks(2));
    }

    // READ PANEL
    @FXML
    protected void onReadEntries() {
        PageManager.switchPage(panels, readPanel);
    }

    // BACK TO MAIN PANEL
    @FXML
    protected void onBack() {
        PageManager.switchPage(panels, mainPanel);
    }
}
