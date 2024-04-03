import java.sql.*;

// BankAccount class represents a simple bank account
class BankAccount {
    private String accountHolder;
    private double balance;

    // JDBC URL, username, and password of SQLite server
    private static final String JDBC_URL = "jdbc:sqlite:banking_app.db";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;
    private static Statement statement;

    // Constructor to initialize the account with an account holder and initial balance
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        initializeDatabase();
    }

    // Method to deposit money into the account
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
        updateBalanceInDatabase();
        displayBalance();
    }

    // Method to withdraw money from the account
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            updateBalanceInDatabase();
            displayBalance();
        }
    }

    // Method to display the current account balance
    public void displayBalance() {
        System.out.println("Current Balance: $" + balance);
    }

    // Method to initialize the database and create the accounts table if it doesn't exist
    private void initializeDatabase() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);
            statement = connection.createStatement();
            // SQL query to create the accounts table if it doesn't exist
            String createTableQuery = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "account_holder TEXT PRIMARY KEY," +
                    "balance REAL)";
            statement.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Method to update the balance in the database
    private void updateBalanceInDatabase() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);
            // SQL query to update the balance in the accounts table
            String updateQuery = "UPDATE accounts SET balance = " + balance + " WHERE account_holder = '" + accountHolder + "'";
            statement = connection.createStatement();
            statement.executeUpdate(updateQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    // Method to close the database connection and statement
    private void closeResources() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
