package sys.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import sys.classes.Customer;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;

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
    @FXML private TableColumn<Customer, String> customerIDCol;
    @FXML private TableColumn<Customer, String> surnameCol;
    @FXML private TableColumn<Customer, String> firstNameCol;
    @FXML private TableColumn<Customer, String> middleNameCol;
    @FXML private TableColumn<Customer, String> emailAddressCol;
    @FXML private TableColumn<Customer, String> contactNumberCol;
    @FXML private TableColumn<Customer, String> debtAmountCol;
    @FXML private TableColumn<Customer, String> startDateCol;
    @FXML private TableColumn<Customer, String> dueDateCol;
    @FXML private TableColumn<Customer, String> statusCol;

    ObservableList<Customer> initialData(){
        Customer c1 = new Customer("1", "Ortega", "Sam Vincent Joey", "Dioso", "iamsammy6@gmail.com", "09184819700", "100", "2026-3-23", "2026-4-5", "PENDING");
        Customer c2 = new Customer("2", "a", "a", "a", "fifamsammy8@gmail.com", "39184819700", "1060", "2026-3-21", "2026-4-9", "PENDING");
        Customer c3 = new Customer("3", "b", "b", "b", "niamsammy6@gmail.com", "19184819700", "1200", "2026-3-26", "2026-4-8", "OVERDUE");

        return FXCollections.observableArrayList(c1, c2, c3);
    }

    // INITIALIZING VARIABLES
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panels = new VBox[]{mainPanel, createPanel, readPanel};
        createDebtTextFields = new TextField[]{textField_surname, textField_firstName, textField_middleName, textField_emailAddress, textField_contactNumber, textField_debtAmount};

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

        readTableView.setItems(initialData());

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
