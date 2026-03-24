package sys.classes;

public class Customer {
    private int customerID;
    private String surname;
    private String firstName;
    private String middleName;
    private String emailAddress;
    private String contactNumber;
    private float debtAmount;
    private String startDate;
    private String dueDate;
    private String status;
    private int daysLeft;

    public int getCustomerID() {
        return customerID;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public float getDebtAmount() {
        return debtAmount;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public Customer(int customerID, String surname, String firstName, String middleName, String emailAddress, String contactNumber, float debtAmount, String startDate, String dueDate, String status, int daysLeft) {
        this.customerID = customerID;
        this.surname = surname;
        this.firstName = firstName;
        this.middleName = middleName;
        this.emailAddress = emailAddress;
        this.contactNumber = contactNumber;
        this.debtAmount = debtAmount;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
        this.daysLeft = daysLeft;
    }
}
