package sys.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import sys.classes.Customer;

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

    // READ PANEL
    @FXML private TableView<Customer> readTableView;
    @FXML private TableColumn<Customer, Integer> customerIDCol;
    @FXML private TableColumn<Customer, String> surnameCol;
    @FXML private TableColumn<Customer, String> firstNameCol;
    @FXML private TableColumn<Customer, String> middleNameCol;
    @FXML private TableColumn<Customer, String> emailAddressCol;
    @FXML private TableColumn<Customer, String> contactNumberCol;
    @FXML private TableColumn<Customer, String> debtAmountCol;
    @FXML private TableColumn<Customer, String> startDateCol;
    @FXML private TableColumn<Customer, String> dueDateCol;
    @FXML private TableColumn<Customer, String> statusCol;
    @FXML private TableColumn<Customer, Integer> daysLeftCol;

    // INITIALIZING VARIABLES
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // region This hidden section initializes all the getters for the TableView using PropertyValueFactory
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        middleNameCol.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        emailAddressCol.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        contactNumberCol.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        debtAmountCol.setCellValueFactory(new PropertyValueFactory<>("debtAmount"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        daysLeftCol.setCellValueFactory(new PropertyValueFactory<>("daysLeft"));
        // endregion

        // region This hidden section alters the row's background color depending on its current status
        readTableView.setRowFactory(tv -> new TableRow<Customer>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle(""); // Reset style for empty rows to avoid "ghost" colors
                } else {
                    // Check the specific property (column) you care about
                    switch (item.getStatus()) {
                        case "PENDING" -> setStyle("-fx-background-color: #cbffa8;"); // Light red for "Urgent" status
                        case "DUE" -> setStyle("-fx-background-color: #ffd2a8;"); // Light green for "Completed"
                        case "OVERDUE" -> setStyle("-fx-background-color: #ffa8a8;"); // Light green for "Completed"
                        case null, default -> setStyle(""); // Use default style for other values
                    }
                }
            }
        });
        // endregion

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

        // Automatically sorts the DUE DATE column in ascending order
        daysLeftCol.setSortType(TableColumn.SortType.ASCENDING);
        readTableView.setItems(fetchAll());
        readTableView.getSortOrder().clear();
        readTableView.getSortOrder().add(daysLeftCol);
        readTableView.sort();
    }

    // BACK TO MAIN PANEL
    @FXML
    protected void onBack() {
        PageManager.switchPage(panels, mainPanel);
    }
}
